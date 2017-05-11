package com.externalui.example.client.jbpm;

import static com.externalui.example.client.jbpm.Jbpm6ClientImpl.Http.GET;
import static com.externalui.example.client.jbpm.Jbpm6ClientImpl.Http.POST;
import static com.jayway.restassured.RestAssured.given;

import java.util.Map;

import org.apache.http.HttpException;

import com.externalui.example.client.jbpm.interfaces.Jbpm6Client;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

/**
 * Simple rest client to interact with JBPM6s REST API for process management
 * 
 * @author mallen@redhat.com
 *
 */
public class Jbpm6ClientImpl implements Jbpm6Client{
	private final String server;
	private final String username;
	private final String password;
	private final boolean debug;
	public enum Http{GET,POST}
	
	public Jbpm6ClientImpl(String serverUrl, String username, String password, boolean debug){
		Preconditions.checkArgument(serverUrl!=null);
		Preconditions.checkArgument(serverUrl.endsWith("/"), "the serverUrl must end with a / character");
		this.server=serverUrl;
		this.username=username;
		this.password=password;
		this.debug=debug;
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>> user Id:"+username+ " Password>>>>>>>"+password);
		
	}
	
  public String getTasks(TasksBy by, String value) throws HttpException{
    return send(GET, "rest/task/query?"+by.name()+"="+value);
  }
	
	
	public String startProcess(String deploymentId, String processId, String mapOfParams) throws HttpException{
		return startProcessWithMap(deploymentId, processId, queryStringToMap(mapOfParams));
	}
	protected String startProcessWithMap(String deploymentId, String processId, Map<String,Object> params) throws HttpException{
		Preconditions.checkArgument(deploymentId.split(":").length==3);
		//without variables
		//return send(POST, "rest/runtime/"+deploymentId+"/process/"+processId+"/start"+mapToQueryString(params));
		
		//with variables
		return send(POST, "rest/runtime/"+deploymentId+"/withvars/process/"+processId+"/start"+mapToQueryString(params));
		
		///runtime/{deploymentId}/withvars/process/instance/{procInstId}
		//return send(POST, "rest/runtime/"+deploymentId+"/withvars/process/instance/"+processId);
		///runtime/{deploymentId}/withvars/process/instance/{procInstId}/signal
		//return send(POST, "rest/runtime/"+deploymentId+"/withvars/process/instance/"+processId+"/signal);
		
		/*17.1.2.2. Process calls "with variables"

		[POST] /runtime/{deploymentId}/withvars/process/{processDefId}/start
		
		Starts a process and retrieves the list of variables associated with the process instance
		Returns a JaxbProcessInstanceWithVariablesResponse that contains:
		Information about the process instance (with the same fields and behaviour as the JaxbProcessInstanceResponse
		A key-value list of the variables available in the process instance.
		The processDefId component of the URL must conform to the following regex: [_a-zA-Z0-9-:\.]+
		
		[POST] /runtime/{deploymentId}/withvars/process/instance/{procInstId}
		
		Starts a process and retrieves the list of variables associated with the process instance
		Returns a JaxbProcessInstanceWithVariablesResponse (see the above REST call)
		The processInstId component of the URL must conform to the following regex: [0-9]+
		
		[POST] /runtime/{deploymentId}/withvars/process/instance/{procInstId}/signal
		
		Signals a process instance and retrieves the list of variables associated it
		Returns a JaxbProcessInstanceWithVariablesResponse (see above)
		The processInstId component of the URL must conform to the following regex: [0-9]+*/
		
	}
	
	
	public String startTask(String id) throws HttpException{
		return send(POST, "rest/task/"+id+"/start");
	}

    public String claimTask(String id) throws HttpException{
        //return send(POST, "rest/task/"+id+"/claim");
    	return send(POST, "rest/task/"+id+"/claim");
    	
    }

	public String completeTask(String id, String commaSeparatedListOfParams) throws HttpException{
		return completeTaskWithMap(id, queryStringToMap(commaSeparatedListOfParams));
	}
	public String completeTaskWithMap(String id, Map<String, Object> params) throws HttpException{
		return send(POST, "rest/task/"+id+"/complete"+mapToQueryString(params));
	}
	
	private Response send(String url, Http httpType){
		RequestSpecification rs=
				given().contentType(ContentType.JSON).redirects().follow(true)
				.auth().preemptive().basic(username,password)
				.when();
		Response response;
		switch (httpType){
			case POST:response = rs.contentType(ContentType.JSON).post(server+url); break;
			case GET:response  = rs.contentType(ContentType.JSON).get (server+url); break;
			default:response   = rs.contentType(ContentType.JSON).get (server+url);
		}
		//System.out.println(">>>>>>>>> RESPONSE JSON:"+response);
		return response;
	}
	
	
	private String send(Http httpType, String url) throws HttpException{
		Response response=send(url, httpType);
		if (response.getStatusCode()!=200)
			throw new HttpException("Failed to "+httpType.name()+" to "+url+" - http status line = "+ response.getStatusLine() +"; response content = "+ response.asString());
		
		// add the status line for info/debugging purposes
		String result=response.asString();
		if (debug){
		  result=new StringBuffer(result)
  		.insert(result.indexOf(">")+1, "<!-- "+response.getStatusLine()+" -->")
  		.toString();
		}
		//System.out.println(">>>>>>>>> RESPONSE result:"+result);
		return result;
	}

	/**
	 * Convert a Map object into the jbpm6 queryString equivalent of a map of values but prepending the text "map_"
	 */
	private String mapToQueryString(Map<String,Object> params){
		StringBuffer sb=new StringBuffer();
		for(Map.Entry<String, Object> e:params.entrySet()){
			sb.append("&map_"+e.getKey()+"="+e.getValue().toString());
      if (Integer.class.isAssignableFrom(e.getValue().getClass()))
        sb.append("i"); // force the Integer data type once its been received by Jbpm server
      if (Long.class.isAssignableFrom(e.getValue().getClass()))
        sb.append("l"); // force the Long data type once its been received by Jbpm server
		}
		if (sb.length()>0)sb.replace(0,1,"?");
		return sb.toString();
	}

	public static Map<String, Object> queryStringToMap(String queryString) {
	    Map<String, Object> result = Maps.newLinkedHashMap();
	    String[] pairs = queryString.split(",");
	    for (String pair : pairs) {
	    	String[] keyValue=pair.split("=");
	    	if (keyValue.length==2){
	    		result.put(keyValue[0], keyValue[1]);
	    	}
//	        int idx = pair.indexOf("=");/
//	        result.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
	    return result;
	}

}
