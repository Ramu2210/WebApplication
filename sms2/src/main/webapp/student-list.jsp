<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link href="css/bootstrap.min.css" rel="Stylesheet">
		<script src="ops.js"></script>
	</head>
	<body>
		<div class="container">
			<h2 class="text-primary mt-5 mb-4">Student List</h2>
			
			<div class="mb-3">
				<button class="btn btn-primary" onclick="openStudentRegForm()">Add Student</button>
				<button class="btn btn-danger float-end" onclick="window.location='login.html'">LogOut</button>	
											
			</div>
			
			
		</div>
			<table class="table" >
				<thead class="table-dark">
				    <tr>
				      <th scope="col">Student ID</th>
				      <th scope="col">First Name</th>
				      <th scope="col">Last Name</th>
				      <th scope="col">Email</th>
				      <th scope="col">Action</th>
				    </tr>
			    </thead>
			    
			    <tbody>
			    	<c:forEach var ="tempStudent" items="${LIST_STUDENTS}">
			    	
			    		<c:url var="deleteUrl" value="/HomeController">
			     			<c:param name="command" value="DELETE"></c:param>
			    			<c:param name="sid" value="${tempStudent.sid}"></c:param>
			    		</c:url>	
			    		
			    		<c:url var="updateUrl" value="/HomeController">
			    			<c:param name="command" value="LOAD"></c:param>
			    			<c:param name="sid" value="${tempStudent.sid }"></c:param>
			    		</c:url>	
			    		
			    		
			    		<tr>
			    		  <th scope="row">${tempStudent.sid}</th>
			    		  
					      <td>${tempStudent.firstname }</td>
					      <td>${tempStudent.lastname }</td>
					      <td>${tempStudent.email }</td>    
					      <td>
					      	<a href="${updateUrl}">Update</a>
					      	|
					      	<a href="${deleteUrl}" >Delete</a>
					      </td>
					      
					      	
					      
		            	</tr> 	
			    	
			    	</c:forEach>
		        </tbody>
			 </table>
			 
			 
		
		
	</body>
</html>