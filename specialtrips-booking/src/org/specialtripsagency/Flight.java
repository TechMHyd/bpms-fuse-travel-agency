package org.specialtripsagency;

/**
 * This class was automatically generated by the data modeler tool.
 * $HASH(0d9925a8db0111365d22a9ba6cf94816)
 */
public class Flight extends java.lang.Object implements java.io.Serializable {

    static final long serialVersionUID = 1L;
    
    private java.lang.String company;
    private java.lang.Integer planeId;
    private java.lang.Integer priceDiscount;
    private java.lang.Integer ratePerPerson;
    
    private java.lang.String returnDate;
    
    private java.lang.String startCity;
    
    private java.lang.String targetCity;
    
    private java.lang.String travelDate;

    public Flight() {
    }

    public Flight(java.lang.String company, java.lang.Integer planeId, java.lang.Integer ratePerPerson, java.lang.String startCity, java.lang.String targetCity, java.lang.String travelDate, java.lang.String returnDate, java.lang.Integer priceDiscount) {
        this.company = company;
        this.planeId = planeId;
        this.ratePerPerson = ratePerPerson;
        this.startCity = startCity;
        this.targetCity = targetCity;
        this.travelDate = travelDate;
        this.returnDate = returnDate;
        this.priceDiscount = priceDiscount;
    }


    
    public java.lang.String getCompany() {
        return this.company;
    }

    public void setCompany(java.lang.String company) {
        this.company = company;
    }
    
    public java.lang.Integer getPlaneId() {
        return this.planeId;
    }

    public void setPlaneId(java.lang.Integer planeId) {
        this.planeId = planeId;
    }
    
    public java.lang.Integer getPriceDiscount() {
        return this.priceDiscount;
    }

    public void setPriceDiscount(java.lang.Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }
    
    public java.lang.Integer getRatePerPerson() {
        return this.ratePerPerson;
    }

    public void setRatePerPerson(java.lang.Integer ratePerPerson) {
        this.ratePerPerson = ratePerPerson;
    }
    
    public java.lang.String getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(java.lang.String returnDate) {
        this.returnDate = returnDate;
    }
    
    public java.lang.String getStartCity() {
        return this.startCity;
    }

    public void setStartCity(java.lang.String startCity) {
        this.startCity = startCity;
    }
    
    public java.lang.String getTargetCity() {
        return this.targetCity;
    }

    public void setTargetCity(java.lang.String targetCity) {
        this.targetCity = targetCity;
    }
    
    public java.lang.String getTravelDate() {
        return this.travelDate;
    }

    public void setTravelDate(java.lang.String travelDate) {
        this.travelDate = travelDate;
    }
}