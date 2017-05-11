<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review Quote</title>

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
		<h1>REVIEW QUOTE - FORM</h1>
	</div>

	<div id="section">
		<form action="complete">
			<br>
			<br>
			
			<div>
				<label for = "bookingId">BookingId (Agency Related)</label> 
				<div>
					<input type="text" id = "bookingId" name="bookingId" size="20px" />
				</div>
			</div>
			
			
			<div>
				<label for = "airlines">Airlines</label> 
				<div>
					<input type="text" id = "airlines" name="airlines" size="20px" />
				</div>
			</div>

			<div>
				<label for = "ratePerPerson">Rate per Person</label>
				<div>
					<input type="text" id = "ratePerPerson" name="ratePerPerson" size="20px" />
				</div>
			</div>

				
			<div>
				<label for = "hotelName">Hotel Name</label>
			   <div>
					<input type="text" id = "hotelName" name="hotelName" size="20px" /> 
				</div>
			</div>
			
			
			<div>
				<label for = "hotelLocation">Hotel Location/City</label>
			   <div>
					<input type="text" id = "hotelLocation" name="hotelLocation" size="20px" /> 
				</div>
			</div>
			
			<div>
				<label for = "hotelRoomPrice">Hotel Room Price (per Person)</label>
			   <div>
					<input type="text" id = "hotelRoomPrice" name="hotelRoomPrice" size="20px" /> 
				</div>
			</div>
			<div>
				<label for = "stayDuration">Duration of stay (in days)</label>
			   <div>
					<input type="text" id = "stayDuration" name="stayDuration" size="20px" /> 
				</div>
			</div>
			<div>
				<label for = "hotelRoomType">Hotel Room Type</label>
			   <div>
					<input type="text" id = "hotelRoomType" name="hotelRoomType" size="20px" /> 
				</div>
			</div>
			
			<div>
				<label for = "travellingFrom">Travelling From</label>
				<div>
					<input type="text" id = "travellingFrom" name="travellingFrom" size="20px" />
				</div>
			</div>
			
			<div>
				<label for = "travellingTo">Travelling To</label>
				<div>
					<input type="text" id = "travellingTo" name="travellingTo" size="20px" />
				</div>
			</div>
		<div>
				<label for = "dateOfDeparture">Date of Departure</label>

				<div>
					<input type="text" id = "dateOfDeparture" name="dateOfDeparture" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "dateOfArrival">Date of Arrival</label>

				<div>
					<input type="text" id = "dateOfArrival" name="dateOfArrival" size="50px" />
				</div>
		</div>
		
		<div>
				<div>
					<input type="checkbox" id = "wasReviewRequested" name="wasReviewRequested" size="50px" />
				</div>
				<label for = "wasReviewRequested">Was Review Requested</label>
		</div>

		<div>
				<label for = "reasonReviewRequest">Reason for Review Request</label>

				<div>
					<input type="text" id = "reasonReviewRequest" name="reasonReviewRequest" size="50px" />
				</div>
		</div>
				
		<div>
				<label for = "originalPackagePrice">Original Total Package Price</label>

				<div>
					<input type="text" id = "originalPackagePrice" name="originalPackagePrice" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "discountPrice">Total Discount Provided</label>

				<div>
					<input type="text" id = "discountPrice" name="discountPrice" size="50px" />
				</div>
		</div>
						
		<div>
				<label for = "totalPrice">Total Price (Quote Price)</label>

				<div>
					<input type="text" id = "totalPrice" name="totalPrice" size="50px" />
				</div>
		</div>
		
		<div>
				<label for = "reviewComment">*Review Comment (Please summarise the changes for this review)</label>

				<div>
					<input type="text" id = "reviewComment" name="reviewComment" size="50px" />
				</div>
		</div>
			
		<input type="hidden" id="processId" name="processId" >
		<input type="hidden" id="taskId" name="taskId" >
		<input type="hidden" id="taskName" name="taskName" >
		
		<input type="submit" value="SUBMIT"><br>
		</form>
	</div>

	<div id="footer">Powered by <strong>ADMS@Tech Mahindra</strong></div>

</body>
</html>
