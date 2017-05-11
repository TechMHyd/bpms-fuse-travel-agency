package com.externalui.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.specialtripsagency.ApplicantData;

import com.externalui.booking.BookingService;
import com.externalui.example.client.jbpm.BpmsClientThread;

public class CompleteServlet extends HttpServlet { 
	
	/**
	 * Auto generated code
	 */
	private static final long serialVersionUID = 1L;
	private BookingService bookingService = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 

		try {
			String taskId=request.getParameter("taskId");
			System.out.println(">>>>>>>>> going to complete taskId:"+taskId);
			
			
			String taskName=request.getParameter("taskName");
			
			if(taskId!=null && taskId.trim().length() > 0)
			{
				completeMyTask(request, response,taskId,taskName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void completeMyTask(HttpServletRequest request, HttpServletResponse response, String taskId, String taskName) throws Exception
	{ 
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String content = "";

		System.out.println(">>>>>>>>>>>>>>>>>> complete TASK Id:"+taskId+ " Task Name:"+taskName);

		String processId=request.getParameter("processId");
		System.out.println(">>>>>>>>>>>>>>>>>> claiming processId:"+processId);


		String requestForReview=request.getParameter("requestForReview");
		System.out.println(">>>>>>>>>> requestForReview:"+requestForReview);
		String reviewComment=request.getParameter("reviewComment");
		System.out.println(">>>>>>>>>> reviewComment:"+reviewComment);

			BpmsClientThread bpmsClient = new BpmsClientThread();
			bookingService=new BookingService();
			ApplicantData applicantData=bookingService.fetchApplicantData(processId);
			
			String taskCompleteResponse="";
			String taskCompleteRequestParams="";
			
			if ("Price Review".equalsIgnoreCase(taskName)) {
				System.out.println("Considering it as task.getName().contains(Price Review)....");
                String priceString = request.getParameter("totalPrice") + "i";
                 taskCompleteRequestParams="totalPriceOut=" + priceString+ ",reviewerCommentOut="+request.getParameter("reviewComment");
                System.out.println("["+taskName+"]  priceReviewRequestParams:"+taskCompleteRequestParams);
                
                //String additionalDiscount=applicantData.getToDestination()-request.getParameter("totalPrice");
                bookingService.updateApplicantData(processId,request.getParameter("totalPrice"),request.getParameter("reviewComment")) ;               
                String startResponse = bpmsClient.startTask(taskId);
                taskCompleteResponse = bpmsClient.completeTask(taskId,taskCompleteRequestParams);
                System.out.println("Price Review Complete Response:::"+taskCompleteResponse);
            }
            else if ("Employee  Booking".equalsIgnoreCase(taskName)) {
            	
            	boolean errorPage=false;
            	
            	if(requestForReview==null || requestForReview.trim().length() == 0)
            	{
            		requestForReview="false";
            		errorPage=true;
            	}
            	else
            		requestForReview="true";
            	
            	String isBookingConfirmed=request.getParameter("isBookingConfirmed");
            	System.out.println(">>>>>isBookingConfirmed:"+isBookingConfirmed);
            	
            	if(isBookingConfirmed==null || isBookingConfirmed.trim().length() == 0)
            	{
            		isBookingConfirmed="false";
            		errorPage=true;
            	}
            	else
            		isBookingConfirmed="true";
            	
            	String isBookingCancelled=request.getParameter("isBookingCancelled");
            	System.out.println(">>>>>isBookingCancelled:"+isBookingCancelled);
            	if(isBookingCancelled==null || isBookingCancelled.trim().length() == 0)
            	{
            		isBookingCancelled="false";
            		errorPage=true;
            	}
            	else
            		isBookingCancelled="true";
            	
            	/*if(errorPage){
            		content = BookingUtil.getContent(getServletContext().getRealPath(File.separator) + "error.html");
            		content=content.replace("Failed to start Business Process", "Failed to submit the Business Process,Check your input");
            	}else
            	{*/
            		System.out.println("Considering it as task.getName().contains(Employee Booking)");
            		
            		if("true".equalsIgnoreCase(requestForReview)){
            			taskCompleteRequestParams="reviewRequiredOut="+requestForReview+",reviewRequiredDetailsOut="+request.getParameter("reasonForReview")+",bookingConfirmedOut=NO,bookingCancelledOut=No";
            		}else if("true".equalsIgnoreCase(isBookingConfirmed))
            		{
            			String strCreditCardNo=request.getParameter("creditCardNumber");
						taskCompleteRequestParams="reviewRequiredOut="+requestForReview+",reviewRequiredDetailsOut="+request.getParameter("reasonForReview")+",bookingConfirmedOut="+isBookingConfirmed+",bookingCancelledOut=No,creditCardNumberOut="+strCreditCardNo;
            		}else if("true".equalsIgnoreCase(isBookingCancelled))
            		{
            			taskCompleteRequestParams="reviewRequiredOut="+requestForReview+",reviewRequiredDetailsOut="+request.getParameter("reasonForReview")+",bookingConfirmedOut="+isBookingConfirmed+",bookingCancelledOut="+isBookingCancelled;
            		}
            		System.out.println("["+taskName+"]  employeeBookingRequestParams:"+taskCompleteRequestParams);

            		String startResponse = bpmsClient.startTask(taskId);
            		taskCompleteResponse = bpmsClient.completeTask(taskId,taskCompleteRequestParams);
            		System.out.println(">>>>>>>>empBookingResponse::"+taskCompleteResponse);
            	//}
            }
            else {
            	System.out.println("Considering it as task.getName().contains(Booking Complete)");

            	String startResponse = bpmsClient.startTask(taskId);

            	taskCompleteResponse = bpmsClient.completeTask(taskId, "");
            	System.out.println(">>>>>>>>bookingCompleteResponse::"+taskCompleteResponse);
            }
			
			if(taskCompleteResponse !=null && taskCompleteResponse.contains("<status>SUCCESS</status>")){
            	content = BookingUtil.getContent(getServletContext().getRealPath(File.separator) + "success_response.html");
    			content = content.replace("CHANGEMEPROCESSID", processId);
            }else
            {
    			content = BookingUtil.getContent(getServletContext().getRealPath(File.separator) + "error.html");
            }
			out.println (content);
	}

}
