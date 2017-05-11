package org.specialtripsagency;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
  
@XmlRootElement  
public class ProcessResponse {  

private Variable variables;

@XmlElement
public Variable getVariables() {
	return variables;
}
public void setVariables(Variable variables) {
	this.variables = variables;
}
}  
