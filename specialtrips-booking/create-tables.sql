CREATE TABLE APPLICANT(applicantid int primary key, name varchar(255),emailAddress varchar(255),numberOfTravelers int,otherDetails varchar(255))

CREATE TABLE FLIGHT(flightid int primary key, name varchar(255),emailAddress varchar(255),numberOfTravelers int,otherDetails varchar(255))

CREATE TABLE HOTEL(hotelid int primary key, name varchar(255),emailAddress varchar(255),numberOfTravelers int,otherDetails varchar(255))

CREATE TABLE PAYMENT_DETAILS(paymentid int primary key, name varchar(255),emailAddress varchar(255),numberOfTravelers int,otherDetails varchar(255))

CREATE TABLE TRAVEL_DETAILS(travelid int primary key, name varchar(255),emailAddress varchar(255),numberOfTravelers int,otherDetails varchar(255))


CREATE TABLE BOOKING_DETAILS(bookingid int primary key, applicantid int,flightid int, hotelid int,paymentid int,travelid int,bookingConfirmed char(1), reviewerComment varchar(255),totalPrice int)


 /*private com.externalui.booking.data.Applicant applicant;
    private com.externalui.booking.data.Flight availableFlights;
    private com.externalui.booking.data.Hotel availableHotels;
    private java.lang.Boolean bookingConfirmed;
    private java.lang.Integer bookingId;
    private com.externalui.booking.data.PaymentDetails paymentDetails;
    
    private java.lang.String reviewerComment;
    
    private java.lang.Integer totalPrice;
    
    private com.externalui.booking.data.TravelDetails travelDetails;*/

/*ALTER TABLE PUBLIC.EMPLOYEE
ADD FOREIGN KEY (DNO) 
REFERENCES PUBLIC.DEPARTMENT(DNUMBER)*/
