package com.externalui.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.specialtripsagency.Applicant;
import org.specialtripsagency.ApplicantData;

import com.externalui.booking.BookingService;
import com.externalui.example.client.jbpm.BpmsClientThread;

public class ClaimServlet extends HttpServlet { 
	
	/**
	 * Auto generated code
	 */
	private static final long serialVersionUID = 1L;
	private BookingService bookingService = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 

		try {
			String taskId=request.getParameter("taskId");
			String taskName=request.getParameter("taskName");
			
			if(taskId!=null && taskId.trim().length() > 0)
			{
				claimMyTask(request, response,taskId,taskName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void claimMyTask(HttpServletRequest request, HttpServletResponse response, String taskId, String taskName) throws Exception
	{ 
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String content = "";
		
		System.out.println(">>>>>>>>>>>>>>>>>> claiming TASK Id:"+taskId+ " Task Name:"+taskName);
		

		BpmsClientThread bpmsClient = new BpmsClientThread();
		String claimResponse = bpmsClient.claimTask(taskId);

		System.out.println("=====> claim response : "+claimResponse);
		
		String processId=request.getParameter("processId");
		System.out.println(">>>>>>>>>>>>>>>>>> claiming processId:"+processId);

		if (claimResponse == null) {
			content = BookingUtil.getContent(getServletContext().getRealPath(File.separator) + "task-error.html");
			content = content.replace("No tasks found","Unable to claim the process");
			content = content.replace("./tasks","claim");
			content = content.replace("PROCESSID", taskId);
		}
		else if (claimResponse !=null && "Employee  Booking".equalsIgnoreCase(taskName)){
			System.out.println("fetch applicant details on Employee Booking!!");
			content = BookingUtil.getContent(getServletContext().getRealPath(File.separator) + "EmployeeBookingForm.jsp");
			bookingService=new BookingService();
			
			ApplicantData applicant=bookingService.fetchApplicantData(processId);
			content = content.replace("name=\"applicantName\"","name=\"applicantName\" value=\""+applicant.getName()+"\" readonly");
			content = content.replace("name=\"emailAddress\"","name=\"emailAddress\" value=\""+applicant.getEmailAddress()+"\" readonly");
			content = content.replace("name=\"numberOfTravellers\"","name=\"numberOfTravellers\" value=\""+applicant.getNumberOfTravelers()+"\" readonly");
			content = content.replace("name=\"travellingFrom\"","name=\"travellingFrom\" value=\""+applicant.getFromDestination()+"\" readonly");
			content = content.replace("name=\"travellingTo\"","name=\"travellingTo\" value=\""+applicant.getToDestination()+"\" readonly");
			content = content.replace("name=\"dateOfDeparture\"","name=\"dateOfDeparture\" value=\""+applicant.getPreferredDateOfDeparture()+"\" readonly");
			content = content.replace("name=\"dateOfArrival\"","name=\"dateOfArrival\" value=\""+applicant.getPreferredDateOfArrival()+"\" readonly");
			content = content.replace("name=\"discountPrice\"","name=\"discountPrice\" value=\""+applicant.getAdditionalPriceDiscount()+"\" readonly");
			content = content.replace("name=\"totalPrice\"","name=\"totalPrice\" value=\""+applicant.getTotalPrice()+"\" readonly");
			content = content.replace("name=\"reviewerComment\"","name=\"reviewerComment\" value=\""+applicant.getReviewerComment()+"\" readonly");
			
		}
		else if (claimResponse !=null && "Price Review".equalsIgnoreCase(taskName)){
			System.out.println("fetch applicant details on Price Review!!");
			content = BookingUtil.getContent(getServletContext().getRealPath(File.separator) + "ReviewQuoteForm.jsp");
			bookingService=new BookingService();
			ApplicantData applicant=bookingService.fetchApplicantData(processId);
			content = content.replace("name=\"travellingFrom\"","name=\"travellingFrom\" value=\""+applicant.getFromDestination()+"\" readonly");
			content = content.replace("name=\"travellingTo\"","name=\"travellingTo\" value=\""+applicant.getToDestination()+"\" readonly");
			content = content.replace("name=\"dateOfDeparture\"","name=\"dateOfDeparture\" value=\""+applicant.getPreferredDateOfDeparture()+"\" readonly");
			content = content.replace("name=\"dateOfArrival\"","name=\"dateOfArrival\" value=\""+applicant.getPreferredDateOfArrival()+"\" readonly");
			content = content.replace("name=\"discountPrice\"","name=\"discountPrice\" value=\""+applicant.getAdditionalPriceDiscount()+"\" readonly");
			content = content.replace("name=\"totalPrice\"","name=\"totalPrice\" value=\""+applicant.getTotalPrice()+"\"");
			
		}
		content = content.replace("name=\"processId\"","name=\"processId\" value=\""+processId+"\" readonly");
		content = content.replace("name=\"taskId\"","name=\"taskId\" value=\""+taskId+"\" readonly");
		content = content.replace("name=\"taskName\"","name=\"taskName\" value=\""+taskName+"\" readonly");
		out.println (content); 
	}

}
