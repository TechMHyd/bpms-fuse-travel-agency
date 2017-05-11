<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Booking Form</title>

<style>

body {
	font-family: sans-serif;
}

#header {
	background-color: black;
	color: white;
	text-align: center;
	padding: 5px;
}

#footer {
	text-align: center;
	padding: 1em;
}

form {
	border: 2px solid #f0f0f0;
}

form > div {
	padding: .5em;
}

form > div:nth-child(odd) {
	background-color: #f0f0f0;
}

form div label {
	display: inline-block;
	min-width: 25%;
}

form > div > div {
	display: inline-block;
}

input[type="submit"] {
	padding: .5em 1em .5em 1em;
	background-color: red;
	font-weight: bold;
	color: white;
	border: 0;
	margin: 1em;
	font-size: large;
}

h2 {
	font-size: large;
	color: red;
	margin: 0;
	padding: .4em;
	padding-top: 1.5em;
}

</style>

</head>
<body>

	<div id="header">
		<h1>EMPLOYEE BOOKING - FORM</h1>
	</div>

	<div id="section">
		<form action="complete">
			<h2>Customer Details:</h2>
			
			
			<div>
				<label for = "bookingId">BookingId (Agency Related)</label> 
				<div>
					<input type="text" id = "bookingId" name="bookingId" size="20px" />
				</div>
			</div>
			
			
			<div>
				<label for = "applicantName">Applicant Name</label> 
				<div>
					<input type="text" id = "applicantName" name="applicantName" size="20px" />
				</div>
			</div>

			<div>
				<label for = "emailAddress">Email Address</label>
				<div>
					<input type="text" id = "emailAddress" name="emailAddress" size="20px" />
				</div>
			</div>

			<h2>Travel Details:</h2>
			<div>
				<label for = "numberOfTravellers">Number Of Travelers</label>
				<div>
					<input type="text" id = "numberOfTravellers" name="numberOfTravelers" size="20px" /> 
				</div>
			</div>
				
				
			<div>
				<label for = "flightCompanyName">Flight Company Name</label>
			   <div>
					<input type="text" id = "flightCompanyName" name="flightCompanyName" size="20px" /> 
				</div>
			</div>
			
			<div>
				<label for = "travelDate">Travel Date</label>
				<div>
					<input type="text" id = "travelDate" name="travelDate" size="20px" />
				</div>
			</div>
			
			<div> 
				<label for = "returnDate">Return Date</label>
			
				<div>	
					<input type="text" id = "returnDate" name="returnDate" size="20px" />
				</div>
			</div>

			<div>
				<label for = "hotelName">Hotel Name</label>

				<div>
					<input type="text" id = "hotelName" name="hotelName" size="50px" />
				</div>
			</div>
			

		<div>
				<label for = "hotelLocation">Location</label>

				<div>
					<input type="text" id = "hotelLocation" name="hotelLocation" size="50px" />
				</div>
		</div>

		<div>
				<label for = "hotelRoomType">Hotel Room Type</label>

				<div>
					<input type="text" id = "hotelRoomType" name="hotelRoomType" size="50px" />
				</div>
		</div>
		
		<div>
				<div>
					<input type="checkbox" id = "isBreakFast" name="isBreakFast" size="50px" />
				</div>
				<label for = "isBreakFast">Is break fast included?</label>
		</div>
		
		
		<div>
				<label for = "dateOfDeparture">Date of Departure/Travel (Outbound)</label>

				<div>
					<input type="text" id = "dateOfDeparture" name="dateOfDeparture" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "dateOfArrival">Date of Arrival (Inbound)</label>

				<div>
					<input type="text" id = "dateOfArrival" name="dateOfArrival" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "travelFrom">Travel From</label>

				<div>
					<input type="text" id = "travelFrom" name="travelFrom" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "travelTo">Travel To</label>

				<div>
					<input type="text" id = "travelTo" name="travelTo" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "totalPrice">Total Price (Quote Price)</label>

				<div>
					<input type="text" id = "totalPrice" name="totalPrice" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "additionalDiscountPrice">Additional Price Discount Provided</label>

				<div>
					<input type="text" id = "additionalDiscountPrice" name="additionalDiscountPrice" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "originalPackagePrice">Original Total Package Price</label>

				<div>
					<input type="text" id = "originalPackagePrice" name="originalPackagePrice" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "reviewerComment">Reviewer's Comment</label>

				<div>
					<input type="text" id = "reviewerComment" name="reviewerComment" size="50px" />
				</div>
		</div>
		<H2>NOTE: Please fill <U>ONLY ONE</U> of the following options:</H2>
		<H3>OPTION 1 (SEND FOR REVIEW):</H3>
		<div>
				<div>
					<input type="checkbox" id = "requestForReview" name="requestForReview" size="50px" />
				</div>
				<label for = "requestForReview">Request For Review from manager/reviewer</label>
		</div>

		<div>
				<label for = "reasonForReview">Reason for Review Request (To assist you better please specify the reason why this review has been requested)</label>

				<div>
					<input type="textarea" id = "reasonForReview" name="reasonForReview" size="50px" />
				</div>
		</div>
		

		
		<H3>OPTION 2 (BOOKING CONFIRMED)</H3>
		<H6>Note: Please fill Payment details as well</H6>
		<div>
				<div>
					<input type="checkbox" id = "isBookingConfirmed" name="isBookingConfirmed" size="50px" />
				</div>
				<label for = "isBookingConfirmed">Is Booking Confirmed? Check this box only if the booking is completed</label>
		</div>
		
		<div>
				<label for = "creditCardNumber">Credit Card Number</label>

				<div>
					<input type="text" id = "creditCardNumber" name="creditCardNumber" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "expiryDate">Expiry Date (MM/YY)</label>

				<div>
					<input type="text" id = "expiryDate" name="expiryDate" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "cardHolderName">Name Of Card Holder (as appears on the card)</label>

				<div>
					<input type="text" id = "cardHolderName" name="cardHolderName" size="50px" />
				</div>
		</div>
			
		<H3>OPTION 3 (BOOKING CANCELLED):</H3>
		<div>
				<div>
					<input type="checkbox" id = "isBookingCancelled" name="isBookingCancelled" size="50px" />
				</div>
				<label for = "isBookingCancelled">Is Booking Cancelled? Check this box if booking is Cancelled</label>
		</div>
		
		<H2>NOTE: Please fill <U>ONLY ONE</U> of the above options:</H2>
		
			<input type="hidden" id="processId" name="processId" >
			<input type="hidden" id="taskId" name="taskId" >
			<input type="hidden" id="taskName" name="taskName" >
			
			<input type="submit" value="SUBMIT"><br>
		</form>
	</div>

	<div id="footer">Powered by <strong>ADMS@Tech Mahindra</strong></div>

</body>
</html>
