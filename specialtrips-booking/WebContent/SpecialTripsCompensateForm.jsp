<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compensate Form</title>

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
		<h1>COMPENSATE - FORM</h1>
	</div>

	<div id="section">
		<form action="SimpleServlet">
			<br>
			<br>
			<div>
				<label for = "bookingRef">Flight Booking Reference</label> 
				<div>
					<input type="text" id = "bookingRef" name="bookingRef" size="20px" />
				</div>
			</div>

			<div>
				<label for = "hotelReservationNumber">Hotel Reservation Number</label>
				<div>
					<input type="text" id = "hotelReservationNumber" name="hotelReservationNumber" size="20px" />
				</div>
			</div>
					
			<input type="submit" value="SUBMIT"><br>
		</form>
	</div>

	<div id="footer">Powered by <strong>ADMS@Tech Mahindra</strong></div>

</body>
</html>
