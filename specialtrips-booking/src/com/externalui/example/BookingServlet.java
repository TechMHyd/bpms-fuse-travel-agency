package com.externalui.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.specialtripsagency.Applicant;

import com.externalui.booking.BookingService;
import com.externalui.example.client.jbpm.BpmsClientThread;

public class BookingServlet extends HttpServlet { 
	
	/**
	 * Auto generated code
	 */
	private static final long serialVersionUID = 1L;
	private String processId = null;
	
	private BookingService bookingService;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		
		// reading user inputs 
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("applicantName", request.getParameter("applicantName"));
		hm.put("emailAddress", request.getParameter("emailAddress"));
		hm.put("numberOfTravelers", request.getParameter("numberOfTravelers") + "i"); // special case where the integer value should be submitted by appending "i" in the end to avoid java.lang.ClassCastException
		hm.put("fromDestination", request.getParameter("fromDestination"));
		hm.put("toDestination", request.getParameter("toDestination"));
		hm.put("preferredDateOfArrival", request.getParameter("preferredDateOfArrival"));
		hm.put("preferredDateOfDeparture", request.getParameter("preferredDateOfDeparture"));
		hm.put("otherDetails", request.getParameter("otherDetails"));
		
		System.out.println("=====> Before sending request: HashMap values are: \n" + hm);
		BpmsClientThread t = new BpmsClientThread();
		processId = t.starBusinessProcess(hm);
		System.out.println("=====> After sending request: ");
		
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String content = "";
		
		if (processId == null) {
			content = BookingUtil.getContent(getServletContext().getRealPath(File.separator) + "error.html");
		} else {
			content = BookingUtil.getContent(getServletContext().getRealPath(File.separator) + "success_response.html");
			content = content.replace("CHANGEMEPROCESSID", processId);
		}
		
		out.println (content); 
	}
	
}
