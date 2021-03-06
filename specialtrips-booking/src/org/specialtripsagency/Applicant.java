package org.specialtripsagency;

/**
 * This class was automatically generated by the data modeler tool.
 * $HASH(2c06ba0e4d7108439ea12be040df563c)
 */
public class Applicant extends java.lang.Object implements java.io.Serializable {

    static final long serialVersionUID = 1L;
    
    private java.lang.String emailAddress;
    
    private java.lang.String name;
    
    private java.lang.Integer numberOfTravelers;
    
    private java.lang.String otherDetails;

    public Applicant() {
    }

    public Applicant(java.lang.String name, java.lang.String emailAddress, java.lang.Integer numberOfTravelers, java.lang.String otherDetails) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.numberOfTravelers = numberOfTravelers;
        this.otherDetails = otherDetails;
    }


    
    public java.lang.String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    public java.lang.Integer getNumberOfTravelers() {
        return this.numberOfTravelers;
    }

    public void setNumberOfTravelers(java.lang.Integer numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }
    
    public java.lang.String getOtherDetails() {
        return this.otherDetails;
    }

    public void setOtherDetails(java.lang.String otherDetails) {
        this.otherDetails = otherDetails;
    }
}