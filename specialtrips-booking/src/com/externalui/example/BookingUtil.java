package com.externalui.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BookingUtil {

	public static String getHTMLBodyContent(String title) {
		
		String htmlBodyContent="<!DOCTYPE html PUBLIC -//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
				+ "<html>"
				+ "<head>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
				+ "<title>"+title+"</title>"
				+ "<style>"
				+ "body {"
				+ "	font-family: sans-serif;"
				+ "}"
				+ "#header {"
				+ "	background-color: black;"
				+ "	color: white;"
				+ "	text-align: center;"
				+ "	padding: 5px;"
				+ "}"
				+ "#footer {"
				+ "	text-align: center;"
				+ "	padding: 1em;"
				+ "}"
				+ "table {"
				+ "    font-family: arial, sans-serif;"
				+ "    border-collapse: collapse;"
				//+ "    width: 100%;"
				+ "}"
				+ "td, th {"
				+ "    border: 1px solid #dddddd;"
				+ "    text-align: left;"
				+ "    padding: 8px;"
				+ "}"
				+ "tr:nth-child(even) {"
				+ "    background-color: #dddddd;"
				+ "}"
				+ "</style>"
				+ "</head>"
				+ "<body>"
				+ "<br><br><h4>"+title+"</h4>"
				+ "<br>";
		
		/*<style>

		
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

		</head>*/
		
		return htmlBodyContent;
	}
	
public static String getHTMLBodyEndContent() {
		String htmlBodyContent="</body>"
				+ "</html>";
		return htmlBodyContent;
	}

public static String getHTMLTaskTableContent() {
	return "<tr><td>Task</td><td>Description</td><td>Actions</td></tr>";
}

public static String getContent(String file) {
	String tempContent = ""; 
	try {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String str;
        
        while ((str = in.readLine()) != null) {
            tempContent +=str;
        }
        in.close();
    } catch (IOException e) {
    	System.out.println("IOException caught: ");
    	e.printStackTrace();
    }
	return tempContent;
}


}
