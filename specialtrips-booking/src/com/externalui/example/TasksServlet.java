package com.externalui.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.externalui.example.client.jbpm.BpmsClientThread;
import com.externalui.example.client.jbpm.Task;

public class TasksServlet extends HttpServlet { 
	
	/**
	 * Auto generated code
	 */
	private static final long serialVersionUID = 1L;
	private Task task = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 

		try {
			String processId=request.getParameter("processId");
			if(processId ==null || processId.trim().length() == 0 ){

				fetchAllTaskByUser(request, response,"erics");
			}
			
			if(processId!=null && processId.trim().length() > 0)
			{
				processMyTask(request, response,processId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void processMyTask(HttpServletRequest request, HttpServletResponse response, String processId) throws Exception
	{ 
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String content = "";
		
		System.out.println(">>>>>>>>>>>>>>>>>> Process Id:"+processId);

		BpmsClientThread bpmsClient = new BpmsClientThread();
		task = bpmsClient.getNextTaskByProcessId(processId,"Ready", 5);

		System.out.println("=====> Ready Task available : "+task);

		if(task!=null)
			System.out.println("=====> Ready Task Name : "+task.getName());


		if (task == null) {
			content = BookingUtil.getContent(getServletContext().getRealPath(File.separator) + "task-error.html");
			content = content.replace("PROCESSID", processId);
		}
		else if (task !=null){
			System.out.println("11111");
			
			content+=BookingUtil.getHTMLBodyContent("User Ready Tasks");
			content+="<table>";
			content+=BookingUtil.getHTMLTaskTableContent();
			content+="<tr><td>"+task.getName()+"</td><td>"+task.getDescription()+"</td><td><a href='./claim?taskId="+task.getId()+"&taskName="+task.getName()+"&processId="+task.getProcessInstanceId()+"'>Claim</a></td></tr>";
			content+="</table>";
			content+=BookingUtil.getHTMLBodyEndContent();
		}
		out.println (content); 
	}

	public void fetchAllTaskByUser(HttpServletRequest request, HttpServletResponse response, String user) throws Exception
	{ 
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String content = "";
		System.out.println(">>>>>>>>>>>>>>>>>> fetchAllTaskByUser:"+user);

		BpmsClientThread bpmsClient = new BpmsClientThread();
		List <Task>tasks = bpmsClient.getAllTaskaByUser(user, 5);

		System.out.println("=====> Ready Task available : "+tasks);

		if(tasks!=null)
			System.out.println("=====> User Tasks found are : "+tasks);

		if (tasks == null) {
			content = BookingUtil.getContent(getServletContext().getRealPath(File.separator) + "task-error.html");
			content = content.replace("PROCESSID", user);
			
		} else{
			System.out.println("11111");
			content+=BookingUtil.getHTMLBodyContent("User Ready Tasks");
			content+="<table>";
			content+=BookingUtil.getHTMLTaskTableContent();
			for(Task task:tasks)
			{
				content+="<tr><td>"+task.getName()+"</td><td>"+task.getDescription()+"</td><td><a href='./claim?taskId="+task.getId()+"&taskName="+task.getName()+"&processId="+task.getProcessInstanceId()+"'>Claim</a></td></tr>";
			}
			content+="</table>";
			content+=BookingUtil.getHTMLBodyEndContent();
		}
		out.println (content); 
	}
}
