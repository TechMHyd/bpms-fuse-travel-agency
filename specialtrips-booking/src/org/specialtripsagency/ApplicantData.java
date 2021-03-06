package org.specialtripsagency;

/**
 * This class was automatically generated by the data modeler tool.
 * $HASH(2c06ba0e4d7108439ea12be040df563c)
 */
public class ApplicantData extends java.lang.Object implements java.io.Serializable {

    static final long serialVersionUID = 1L;
    
    private java.lang.String emailAddress;
    
    private java.lang.String name;
    
    private java.lang.Integer numberOfTravelers;
    
    private java.lang.String otherDetails;
    
    private Integer totalPrice;
    private String toDestination;
    private String preferredDateOfDeparture;
    
    private String fromDestination;
    private Integer additionalPriceDiscount;
    
    private String preferredDateOfArrival;
    
    private String reviewerComment;
    
    

    public ApplicantData() {
    }

    public ApplicantData(java.lang.String name, java.lang.String emailAddress, java.lang.Integer numberOfTravelers, java.lang.String otherDetails) {
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

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getToDestination() {
		return toDestination;
	}

	public void setToDestination(String toDestination) {
		this.toDestination = toDestination;
	}

	public String getPreferredDateOfDeparture() {
		return preferredDateOfDeparture;
	}

	public void setPreferredDateOfDeparture(String preferredDateOfDeparture) {
		this.preferredDateOfDeparture = preferredDateOfDeparture;
	}

	public String getFromDestination() {
		return fromDestination;
	}

	public void setFromDestination(String fromDestination) {
		this.fromDestination = fromDestination;
	}

	public Integer getAdditionalPriceDiscount() {
		return additionalPriceDiscount;
	}

	public void setAdditionalPriceDiscount(Integer additionalPriceDiscount) {
		this.additionalPriceDiscount = additionalPriceDiscount;
	}

	public String getPreferredDateOfArrival() {
		return preferredDateOfArrival;
	}

	public void setPreferredDateOfArrival(String preferredDateOfArrival) {
		this.preferredDateOfArrival = preferredDateOfArrival;
	}

	public String getReviewerComment() {
		return reviewerComment;
	}

	public void setReviewerComment(String reviewerComment) {
		this.reviewerComment = reviewerComment;
	}

	@Override
	public String toString() {
		return "ApplicantData [emailAddress=" + emailAddress + ", name=" + name
				+ ", numberOfTravelers=" + numberOfTravelers
				+ ", otherDetails=" + otherDetails + ", totalPrice="
				+ totalPrice + ", toDestination=" + toDestination
				+ ", preferredDateOfDeparture=" + preferredDateOfDeparture
				+ ", fromDestination=" + fromDestination
				+ ", additionalPriceDiscount=" + additionalPriceDiscount
				+ ", preferredDateOfArrival=" + preferredDateOfArrival
				+ ", reviewerComment=" + reviewerComment + "]";
	}
	
}