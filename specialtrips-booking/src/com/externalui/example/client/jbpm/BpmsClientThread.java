package com.externalui.example.client.jbpm;


import static javax.xml.xpath.XPathConstants.STRING;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpException;
import org.specialtripsagency.Applicant;
import org.specialtripsagency.ApplicantData;
import org.specialtripsagency.BookingObject;
import org.specialtripsagency.Flight;
import org.specialtripsagency.Hotel;
import org.specialtripsagency.PaymentDetails;
import org.specialtripsagency.TravelDetails;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.externalui.booking.BookingService;
import com.externalui.example.client.jbpm.interfaces.Jbpm6Client.TasksBy;


public class BpmsClientThread {

	// BPMS connection values
	public String server = "http://localhost:8084/business-central/";
	public String username = "erics";
	public String password = "bpmsuite1!";

	// Default sample input values
	private String applicantName = "Niraj";
	private String emailAddress = "npatel@redhat.com";
	private String numberOfTravelers = "2i";
	private String otherDetails = "NA";
	private String fromDestination = "London";
	private String toDestination = "Edinburgh";
	private String preferredDateOfArrival = "2014-06-06";
	private String preferredDateOfDeparture = "2014-06-12";

	private Jbpm6ClientImpl client;
	private String processId = null;
	private Jbpm6ClientObjects clientObjects;
	private BookingService bookingService;

	public BpmsClientThread() {

	}

	private void init() {
		client = new Jbpm6ClientImpl(server, username, password, true);
		//new Jbpm6ClientObjects(server, username, password);
		clientObjects = new Jbpm6ClientObjects(server, username, password);
	}

	private int generateRandomNumber() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(100);
	}

	public String starBusinessProcess(HashMap<String, String> hm) {

		String response = null;

		Thread t = Thread.currentThread();
		String threadName = "thread-no-"+generateRandomNumber();
		t.setName(threadName);

		init();
		System.out.println(Thread.currentThread());

		applicantName = hm.get("applicantName");
		emailAddress = hm.get("emailAddress");
		numberOfTravelers = hm.get("numberOfTravelers");
		fromDestination = hm.get("fromDestination");
		toDestination = hm.get("toDestination");
		preferredDateOfArrival = hm.get("preferredDateOfArrival");
		preferredDateOfDeparture = hm.get("preferredDateOfDeparture");
		otherDetails = hm.get("otherDetails");

		try {
			response = client.startProcess("org.specialtripsagency:specialtripsagencyproject:2.0.0",
					"org.specialtripsagency.specialtripsagencyprocess",
					"applicantName="+applicantName+
					",emailAddress="+emailAddress+
					",numberOfTravelers="+numberOfTravelers+
					",preferredDateOfArrival="+preferredDateOfArrival+
					",preferredDateOfDeparture="+preferredDateOfDeparture+
					",otherDetails="+otherDetails+
					",fromDestination="+fromDestination+
					",toDestination="+toDestination);

			System.out.println("Process started 1: " + response);

			Document doc = null;
			try {
				doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(response.toString().getBytes()));
				System.out.println("Process  2: " + doc!=null?doc.getDoctype():"");
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			XPath xpath = XPathFactory.newInstance().newXPath();

			try {
				//processId = (String) xpath.evaluate("/process-instance-response/id", doc, STRING);
				processId = (String) xpath.evaluate("/process-instance-with-vars-response/processInstance/id", doc, STRING);
				
				ApplicantData applicantData=getApplicantData(response.toString());
				//Applicant applicant=new Applicant(hm.get("applicantName"), hm.get("emailAddress"), 2, hm.get("otherDetails"));
				bookingService=new BookingService();
				String dbresponse="";
				try {
					dbresponse = bookingService.storeApplicantData(applicantData, processId);
				} catch (Exception e) {
					e.printStackTrace();
					//throw new Exception(e.getLocalizedMessage());
				}
				System.out.println("Applicant details are stored :"+dbresponse);
				
			} catch (XPathExpressionException e) {
				System.out.println("Unable to get /process-instance-with-vars-response/processInstance/id");
				e.printStackTrace();
			}

		} catch (HttpException e) {
			e.printStackTrace();
		}

		System.out.println("Process 3: [" + processId + "]"); // Display the string.  
		return processId;
	}

	 public BookingObject getBookingObject(String responseString) throws HttpException {
		    
		  BookingObject bookingObject=new BookingObject();
		  try {
			  responseString=responseString!=null&&responseString.trim().length()>0?responseString:"";
		      System.out.println("Response string :"+responseString);
		      Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(responseString.getBytes()));
		      XPath xpath=XPathFactory.newInstance().newXPath();
		     
		      //processId = (String) xpath.evaluate("/process-instance-with-vars-response/processInstance/id", doc, STRING);
		      
		    Applicant applicant=new Applicant();
			Flight flight=new Flight();
			Hotel hotel=new Hotel();
			
			Boolean bookingConfirmed=false;;
			Integer bookingId=0;
			PaymentDetails paymentDetails=new PaymentDetails();
			String reviewerComment="";
			Integer totalPrice=0;
			TravelDetails travelDetails=new TravelDetails();
			  
		    String expression = "/process-instance-with-vars-response/variables/entry";
		      
		    NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node nNode = nodeList.item(i);
					System.out.println("\nCurrent entry Element :"
							+ nNode.getNodeName());
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						String key = eElement.getElementsByTagName("key").item(0)
								.getTextContent();
						System.out.println("Key : " + key);
						if ("totalPrice".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							
							totalPrice = Integer.parseInt(value);
						} else if ("toDestination".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);

							travelDetails.setToDestination(value);

						} else if ("preferredDateOfDeparture".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							travelDetails.setPreferredDateOfDeparture(value);
						} else if ("emailAddress".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicant.setEmailAddress(value);

						} else if ("fromDestination".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							travelDetails.setFromDestination(value);

						} else if ("numberOfTravelers".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicant.setNumberOfTravelers(Integer
									.parseInt((value != null && value.trim()
											.length() > 0) ? value : "0"));

						} else if ("additionalPriceDiscount".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							bookingObject.setAdditionalPriceDiscount(Integer
									.parseInt((value != null && value.trim()
											.length() > 0) ? value : "0"));

						} else if ("preferredDateOfArrival".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							travelDetails.setPreferredDateOfArrival(value);

						} else if ("applicantName".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicant.setName(value);

						}else if ("otherDetails".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicant.setOtherDetails(value);
						}
					}
				}
		 		bookingObject.setApplicant(applicant);
				bookingObject.setAvailableFlights(flight);
				bookingObject.setAvailableHotels(hotel);
				bookingObject.setBookingConfirmed(bookingConfirmed);
				bookingObject.setBookingId(bookingId);
				bookingObject.setPaymentDetails(paymentDetails);
				bookingObject.setReviewerComment(reviewerComment);
				bookingObject.setTotalPrice(totalPrice);
				bookingObject.setTravelDetails(travelDetails);
				
		      System.out.println(" >>>>>> Returning bookingObject:"+bookingObject);
		      return bookingObject;
		    } catch (Exception e) {
		      throw new HttpException(e.getMessage(), e);
		    }
		  }
	  
	  
	  public ApplicantData getApplicantData(String responseString) throws HttpException {
		    
		  ApplicantData applicantData=new ApplicantData();
		  try {
		      
			  responseString=responseString!=null&&responseString.trim().length()>0?responseString:"";
		      System.out.println("Response string :"+responseString);
		      
		      Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(responseString.getBytes()));
		      XPath xpath=XPathFactory.newInstance().newXPath();
		      //processId = (String) xpath.evaluate("/process-instance-with-vars-response/processInstance/id", doc, STRING);
		      
		    String expression = "/process-instance-with-vars-response/variables/entry";
		      
		    NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node nNode = nodeList.item(i);
					System.out.println("\nCurrent entry Element :"
							+ nNode.getNodeName());
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						String key = eElement.getElementsByTagName("key").item(0)
								.getTextContent();
						System.out.println("Key : " + key);
						if ("totalPrice".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							
							applicantData.setTotalPrice(Integer
									.parseInt((value != null && value.trim()
									.length() > 0) ? value : "0"));
						} else if ("toDestination".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);

							applicantData.setToDestination(value);

						} else if ("preferredDateOfDeparture".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicantData.setPreferredDateOfDeparture(value);
						} else if ("emailAddress".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicantData.setEmailAddress(value);

						} else if ("fromDestination".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicantData.setFromDestination(value);

						} else if ("numberOfTravelers".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicantData.setNumberOfTravelers(Integer
									.parseInt((value != null && value.trim()
											.length() > 0) ? value : "0"));

						} else if ("additionalPriceDiscount".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicantData.setAdditionalPriceDiscount(Integer
									.parseInt((value != null && value.trim()
											.length() > 0) ? value : "0"));

						} else if ("preferredDateOfArrival".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicantData.setPreferredDateOfArrival(value);

						} else if ("applicantName".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicantData.setName(value);

						}else if ("otherDetails".equals(key)) {
							String value = eElement.getElementsByTagName("value")
									.item(0).getTextContent();
							System.out.println("Value : " + value);
							applicantData.setOtherDetails(value);
						}
					}
				}
		 		
		      System.out.println(" >>>>>> Returning applicantData:"+applicantData);
		      return applicantData;
		    } catch (Exception e) {
		      throw new HttpException(e.getMessage(), e);
		    }
		  }
	  

	public Task getNextTaskByProcessId(String processId,String status,int retries){

		List<Task> tasks = null;
		Task task = null;
		System.out.println(Thread.currentThread());
		System.out.println("Process ["+processId+"]: next task");

		init();

		for (int i= 0; i<retries; i++){
			try {
				System.out.println("Try:"+i);
				tasks = clientObjects.getTasks(TasksBy.processInstanceId, processId);

			} catch (HttpException e) {
				e.printStackTrace();
			}

			task = getTaskByStatus(tasks,status);

			System.out.println(">>>> Task Ready found : "+task);
			if (task != null) break;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return task;

	}


	public List<Task> getAllTaskaByUser(String user,int retries){

		List<Task> tasks = null;
		System.out.println(Thread.currentThread());
		System.out.println("User ["+processId+"]: all tasks");

		init();

		for (int i= 0; i<retries; i++){
			try {
				System.out.println("Try:"+i);
				tasks = clientObjects.getTasks(TasksBy.potentialOwner, user);

			} catch (HttpException e) {
				e.printStackTrace();
			}

			System.out.println(">>>> User ["+user+"] Tasks found : "+tasks);
			if (tasks != null) break;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return tasks;

	}
	private Task getTaskByStatus (List<Task> tasks, String status) {

		if (tasks == null) return null;

		for(Task task_i : tasks) {

			System.out.println(">>>> Task found : "+task_i);
			if (task_i!=null && status.contains(task_i.getStatus()))  {
				return task_i;
			}
		}
		return null;
	}

	public List<Task> getAllTasks(String processId)
	{
		List<Task> tasks = null;
		try {
			tasks = clientObjects.getTasks(TasksBy.processInstanceId, processId);

		} catch (HttpException e) {
			e.printStackTrace();
		}
		return tasks!=null&&tasks.size()>0?tasks:new ArrayList<Task>();
	}
	
	public String claimTask(String taskId)
	{
		init();
		try {
			return client.claimTask(taskId);
		} catch (HttpException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String startTask(String taskId)
	{
		init();
		try {
			return client.startTask(taskId);
		} catch (HttpException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String completeTask(String taskId,String commaSeparatedListOfParams)
	{
		init();
		try {
			return client.completeTask(taskId, commaSeparatedListOfParams);
		} catch (HttpException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		
		String response="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> <process-instance-with-vars-response> 	<status>SUCCESS</status> 	<url>/business-central/rest/runtime/org.specialtripsagency:specialtripsagencyproject:2.0.0/withvars/process/org.specialtripsagency.specialtripsagencyprocess/start</url> 	<variables> 		<entry> 			<key>flight</key> 			<value>org.specialtripsagency.Flight@1b11d0</value> 		</entry> 		<entry> 			<key>travelDetails</key> 			<value>org.specialtripsagency.TravelDetails@126531d</value> 		</entry> 		<entry> 			<key>totalPrice</key> 			<value>2991</value> 		</entry> 		<entry> 			<key>toDestination</key> 			<value>Edinburgh</value> 		</entry> 		<entry> 			<key>otherDetails</key> 			<value>N/A</value> 		</entry> 		<entry> 			<key>initiator</key> 			<value>erics</value> 		</entry> 		<entry> 			<key>preferredDateOfDeparture</key> 			<value>2016-12-26</value> 		</entry> 		<entry>			<key>requestHotel</key> 			<value>acme.service.soap.hotelws.HotelRequest@ad6337</value>		</entry>		<entry>			<key>reviewRequired</key>			<value>true</value>		</entry>		<entry>			<key>emailAddress</key>			<value>sree3@sree.com</value>		</entry>		<entry>			<key>originalTotalPrice</key>			<value>2991</value>		</entry>		<entry>			<key>fromDestination</key>			<value>London</value>		</entry>		<entry>			<key>hotel</key>			<value>org.specialtripsagency.Hotel@a2531e</value>		</entry>		<entry>			<key>paymentDetails</key>			<value>org.specialtripsagency.PaymentDetails@1178b38</value>		</entry>		<entry>			<key>requestFlight</key>			<value>com.jboss.soap.service.acmedemo.FlightRequest@16740c2</value>		</entry>		<entry>			<key>bookingObject</key>			<value>org.specialtripsagency.BookingObject@313dde</value>		</entry>		<entry>			<key>numberOfTravelers</key>			<value>3</value>		</entry>		<entry>			<key>resultHotel</key>			<value>acme.service.soap.hotelws.Resort@241259</value>		</entry>		<entry>			<key>additionalPriceDiscount</key>			<value>0</value>		</entry>		<entry>			<key>preferredDateOfArrival</key>			<value>2016-12-25</value>		</entry>		<entry>		<key>applicantName</key>			<value>sree3</value>		</entry>		<entry>			<key>reviewRequiredDetails</key>			<value>Initial review</value>		</entry>		<entry>			<key>applicant</key>			<value>org.specialtripsagency.Applicant@1e7beed</value>		</entry>		<entry>			<key>resultFlight</key>			<value>com.jboss.soap.service.acmedemo.Flight@10e62b7</value>		</entry>		<entry>			<key>memFlight</key>			<value>com.jboss.soap.service.acmedemo.Flight@10e62b7</value>		</entry>		<entry>			<key>memHotel</key>			<value>acme.service.soap.hotelws.Resort@241259</value>		</entry>	</variables>	"
				+ "<processInstance>		<process-id>org.specialtripsagency.specialtripsagencyprocess</process-id>		<id>1</id>		<state>1</state>		<parentProcessInstanceId>0</parentProcessInstanceId>	</processInstance></process-instance-with-vars-response>";
		try {
			new BpmsClientThread().getApplicantData(response);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
