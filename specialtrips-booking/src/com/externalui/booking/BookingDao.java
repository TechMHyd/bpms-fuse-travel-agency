package com.externalui.booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.specialtripsagency.Applicant;
import org.specialtripsagency.ApplicantData;


public class BookingDao {
	
	private static final String DB_DRIVER = "org.h2.Driver";
    /*private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";*/
	//private static final String DB_CONNECTION = "jdbc:h2:~/testdb";
	private static final String DB_CONNECTION = "jdbc:h2:./bookingdb";
	
	//private static final String DB_CONNECTION = "jdbc:h2:file:~/h2/travelagency";
	
			
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(">>>>> Got DB Connection with DB_CONNECTION"+DB_CONNECTION);
        return dbConnection;
    }
    

	public String storeApplicant(Applicant applicant, String processId) throws Exception {

        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;
        PreparedStatement insertPreparedStatement = null;
        PreparedStatement selectPreparedStatement = null;
        
        String CreateQuery = "CREATE TABLE APPLICANT(id int primary key, name varchar(255),emailAddress varchar(255),numberOfTravelers int,otherDetails varchar(255))";
        String InsertQuery = "INSERT INTO APPLICANT" + "(id, name, emailAddress, numberOfTravelers, otherDetails) values" + "(?,?,?,?,?)";
        String SelectQuery = "select * from APPLICANT";

        try {
            connection.setAutoCommit(false);

            //TODO not required once created
            try{
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
            
            System.out.println(">>>> executed create query:"+CreateQuery);
            }catch(Exception e)
            {
            	e.printStackTrace();
            	System.out.println(">>>>>>>>>>>>>>>>>> create table error BUT continuing....!!");
            }

            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setInt(1, Integer.parseInt(processId));
            insertPreparedStatement.setString(2, applicant.getName());
            insertPreparedStatement.setString(3, applicant.getEmailAddress());
            insertPreparedStatement.setInt(4, applicant.getNumberOfTravelers());
            insertPreparedStatement.setString(5, applicant.getOtherDetails());
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
            
            System.out.println(">>>> executed InsertQuery:"+InsertQuery);
            System.out.println(">>>> Applicant details as below::"+InsertQuery);

            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            System.out.println("H2 In-Memory Database inserted through PreparedStatement");
            while (rs.next()) {
                System.out.println("Id :" + rs.getInt("id") + " Name :" + rs.getString("name")+ " EmailAddress :" + rs.getString("emailAddress")+ " NumberOfTravelers :" + rs.getInt("numberOfTravelers")+ " OtherDetails :" + rs.getString("otherDetails"));
            }
            selectPreparedStatement.close();

            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
            	if(connection !=null)
            		connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
        }
        return "Success";
	}

	
	public Applicant fetchApplicant(String processId)throws Exception {

        Connection connection = getDBConnection();
        PreparedStatement selectPreparedStatement = null;

        String SelectQuery = "select * from APPLICANT where id="+processId;
        Applicant applicant=null;

            try{
            connection.setAutoCommit(false);
            	
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            System.out.println("H2 In-Memory Database fetch applicant data through PreparedStatement");
            applicant=new Applicant();
            
            while (rs!=null && rs.next()) {
                System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
                applicant.setName(rs.getString("name"));
                applicant.setEmailAddress(rs.getString("emailAddress"));
                applicant.setNumberOfTravelers(rs.getInt("numberOfTravelers"));
                applicant.setOtherDetails(rs.getString("otherDetails"));
            }
            selectPreparedStatement.close();
            connection.commit();
            
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
            	if(connection !=null)
            		connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
        }
        return applicant;
	}


	public String storeApplicantData(ApplicantData applicantData, String processId) throws Exception {

        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;
        PreparedStatement insertPreparedStatement = null;
        PreparedStatement selectPreparedStatement = null;
        
        //emailAddress=sree3@sree.com, name=sree3, numberOfTravelers=3, otherDetails=N/A, totalPrice=2991, toDestination=Edinburgh, preferredDateOfDeparture=2016-12-26, fromDestination=London, additionalPriceDiscount=0, preferredDateOfArrival=2016-12-25]
        		
        String CreateQuery = "CREATE TABLE IF NOT EXISTS APPLICANT_DETAILS(id int primary key, name varchar(255),emailAddress varchar(255),numberOfTravelers int,otherDetails varchar(255),totalPrice int,toDestination varchar(50),preferredDateOfDeparture varchar(50),fromDestination varchar(50),additionalPriceDiscount int,preferredDateOfArrival varchar(50), reviewerComment varchar(150))";
        String InsertQuery = "INSERT INTO APPLICANT_DETAILS" + "(id, name, emailAddress, numberOfTravelers, otherDetails, totalPrice, toDestination, preferredDateOfDeparture, fromDestination, additionalPriceDiscount, preferredDateOfArrival, reviewerComment) values" + "(?,?,?,?,?,?,?,?,?,?,?,?)";
        String SelectQuery = "select * from APPLICANT_DETAILS";

        try {
            connection.setAutoCommit(false);

            //TODO not required once created //IF NOT EXISTS
            try{
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
            
            System.out.println(">>>> executed create query:"+CreateQuery);
            }catch(Exception e)
            {
            	e.printStackTrace();
            	System.out.println(">>>>>>>>>>>>>>>>>> create table error BUT continuing....!!");
            }

            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            //id, name, emailAddress, numberOfTravelers, otherDetails,
            insertPreparedStatement.setInt(1, Integer.parseInt(processId));
            insertPreparedStatement.setString(2, applicantData.getName());
            insertPreparedStatement.setString(3, applicantData.getEmailAddress());
            insertPreparedStatement.setInt(4, applicantData.getNumberOfTravelers());
            insertPreparedStatement.setString(5, applicantData.getOtherDetails());
            //totalPrice,toDestination,preferredDateOfDeparture,fromDestination,additionalPriceDiscount,preferredDateOfArrival
            insertPreparedStatement.setInt(6, applicantData.getTotalPrice());
            insertPreparedStatement.setString(7, applicantData.getToDestination());
            insertPreparedStatement.setString(8, applicantData.getPreferredDateOfDeparture());
            insertPreparedStatement.setString(9, applicantData.getFromDestination());
            insertPreparedStatement.setInt(10, applicantData.getAdditionalPriceDiscount());
            insertPreparedStatement.setString(11, applicantData.getPreferredDateOfArrival());
            insertPreparedStatement.setString(12, applicantData.getReviewerComment());
            
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
            
            System.out.println(">>>> executed InsertQuery:"+InsertQuery);
            System.out.println(">>>> Applicant details as below::"+InsertQuery);

            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            System.out.println("H2 In-Memory Database inserted through PreparedStatement");
            while (rs.next()) {
                System.out.println("Id :" + rs.getInt("id") + " Name :" + rs.getString("name")+ " EmailAddress :" + rs.getString("emailAddress")+ " NumberOfTravelers :" + rs.getInt("numberOfTravelers")+ " OtherDetails :" + rs.getString("otherDetails"));
            }
            selectPreparedStatement.close();

            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
            	if(connection !=null)
            		connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
        }
        return "Success";
	}

	
	public ApplicantData fetchApplicantData(String processId)throws Exception {

        Connection connection = getDBConnection();
        PreparedStatement selectPreparedStatement = null;

        String SelectQuery = "select * from APPLICANT_DETAILS where id="+processId;
        ApplicantData applicantData=null;

            try{
            connection.setAutoCommit(false);
            	
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            System.out.println("H2 In-Memory Database fetch applicant data through PreparedStatement");
            applicantData=new ApplicantData();
            
            while (rs!=null && rs.next()) {
                System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
                applicantData.setName(rs.getString("name"));
                applicantData.setEmailAddress(rs.getString("emailAddress"));
                applicantData.setNumberOfTravelers(rs.getInt("numberOfTravelers"));
                applicantData.setOtherDetails(rs.getString("otherDetails"));
              //totalPrice,toDestination,preferredDateOfDeparture,fromDestination,additionalPriceDiscount,preferredDateOfArrival
                applicantData.setTotalPrice(rs.getInt("totalPrice"));
                applicantData.setToDestination(rs.getString("toDestination"));
                applicantData.setPreferredDateOfDeparture(rs.getString("preferredDateOfDeparture"));
                applicantData.setFromDestination(rs.getString("fromDestination"));
                applicantData.setAdditionalPriceDiscount(rs.getInt("additionalPriceDiscount"));
                applicantData.setPreferredDateOfArrival(rs.getString("preferredDateOfArrival"));
                applicantData.setReviewerComment(rs.getString("reviewerComment"));
                
            }
            selectPreparedStatement.close();
            connection.commit();
            
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
            	if(connection !=null)
            		connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
        }
        return applicantData;
	}
	
	public String updateApplicantData(String processId,String totalPrice,String reviwerComment)throws Exception {

        Connection connection = getDBConnection();
        PreparedStatement insertPreparedStatement = null;
        		
        String updateQuery = "UPDATE APPLICANT_DETAILS" + " set totalPrice=?, reviewerComment=? where id=?";

        try {
            connection.setAutoCommit(false);

            
            insertPreparedStatement = connection.prepareStatement(updateQuery);
            //id, name, emailAddress, numberOfTravelers, otherDetails,
            insertPreparedStatement.setInt(1, Integer.parseInt(totalPrice));
            insertPreparedStatement.setString(2, reviwerComment);
            insertPreparedStatement.setInt(3, Integer.parseInt(processId));
            
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
            
            System.out.println(">>>> executed updateQuery:"+updateQuery);
            System.out.println(">>>> Applicant details as below::"+updateQuery);

            /*selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            System.out.println("H2 In-Memory Database inserted through PreparedStatement");
            while (rs.next()) {
                System.out.println("Id :" + rs.getInt("id") + " Name :" + rs.getString("name")+ " EmailAddress :" + rs.getString("emailAddress")+ " NumberOfTravelers :" + rs.getInt("numberOfTravelers")+ " OtherDetails :" + rs.getString("otherDetails"));
            }
            selectPreparedStatement.close();*/

            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
            	if(connection !=null)
            		connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
        }
        return "Success";
	}
	

}
