package com.externalui.booking;

import org.specialtripsagency.Applicant;
import org.specialtripsagency.ApplicantData;


public class BookingService {

	private BookingDao bookingDao;
	
	public BookingService() {
		super();
		bookingDao=new BookingDao();
	}

	public String storeApplicant(Applicant applicant,String processId)throws Exception
	{
		return bookingDao.storeApplicant(applicant,processId);
	}
	public Applicant fetchApplicant(String processId)throws Exception
	{
		return bookingDao.fetchApplicant(processId);
	}
	
	public String storeApplicantData(ApplicantData applicant,String processId)throws Exception
	{
		return bookingDao.storeApplicantData(applicant,processId);
	}
	public ApplicantData fetchApplicantData(String processId)throws Exception
	{
		return bookingDao.fetchApplicantData(processId);
	}

	public String updateApplicantData(String processId, String totalPrice,	String reviewComment) throws Exception {
		
		return bookingDao.updateApplicantData(processId, totalPrice,reviewComment);
	}
	
}
