package org.specialtripsagency;

/**
 * This class was automatically generated by the data modeler tool.
 * $HASH(788741b3bb842225785b43cfc9aecba5)
 */
public class TravelDetails extends java.lang.Object implements java.io.Serializable {

    static final long serialVersionUID = 1L;
    
    private java.lang.String fromDestination;
    
    private java.lang.String preferredDateOfArrival;
    
    private java.lang.String preferredDateOfDeparture;
    
    private java.lang.String toDestination;

    public TravelDetails() {
    }

    public TravelDetails(java.lang.String fromDestination, java.lang.String toDestination, java.lang.String preferredDateOfDeparture, java.lang.String preferredDateOfArrival) {
        this.fromDestination = fromDestination;
        this.toDestination = toDestination;
        this.preferredDateOfDeparture = preferredDateOfDeparture;
        this.preferredDateOfArrival = preferredDateOfArrival;
    }


    
    public java.lang.String getFromDestination() {
        return this.fromDestination;
    }

    public void setFromDestination(java.lang.String fromDestination) {
        this.fromDestination = fromDestination;
    }
    
    public java.lang.String getPreferredDateOfArrival() {
        return this.preferredDateOfArrival;
    }

    public void setPreferredDateOfArrival(java.lang.String preferredDateOfArrival) {
        this.preferredDateOfArrival = preferredDateOfArrival;
    }
    
    public java.lang.String getPreferredDateOfDeparture() {
        return this.preferredDateOfDeparture;
    }

    public void setPreferredDateOfDeparture(java.lang.String preferredDateOfDeparture) {
        this.preferredDateOfDeparture = preferredDateOfDeparture;
    }
    
    public java.lang.String getToDestination() {
        return this.toDestination;
    }

    public void setToDestination(java.lang.String toDestination) {
        this.toDestination = toDestination;
    }
}