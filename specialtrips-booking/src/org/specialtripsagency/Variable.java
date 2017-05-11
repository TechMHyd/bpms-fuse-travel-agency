package org.specialtripsagency;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement  
public class Variable {
	
private List<MapEntry> entries;

@XmlElement	
public List<MapEntry> getEntries() {
	return entries;
}
public void setEntries(List<MapEntry> entries) {
	this.entries = entries;
}

}
