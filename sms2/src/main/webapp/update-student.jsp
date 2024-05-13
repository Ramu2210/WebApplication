<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
	</head>
	
	
	<body>
		
		<div class="container">
		
			<div class="card" style="width:32rem ; margin:3rem auto">
				<div class="card-body">
				
					<h5 class="card-title text-center mb-3 text-primary">Student Registration Form</h5>
					
					
					<form action="HomeController" method="GET">
					
					
						<input type="hidden" name="command" value="UPDATE">
						<input type="hidden" name="sid" value="${THE_STUDENT.sid }">
					
					
						<!-- creating fields for web page -->
						<div class="mb-3">
							<label class="form-label">First Name</label>
							<input class="form-control" name="fname" value="${THE_STUDENT.firstname }">
						</div>
					
						<div class="mb-3">
							<label class="form-label">Last Name</label>
							<input class=" form-control" name="lname" value="${THE_STUDENT.lastname }">
						</div>
					
						<div class="mb-3">
							<label class="form-label">Email   </label>
							<input class="form-control" name="email" value="${THE_STUDENT.email }">
						</div>
						
						<div >							
							<input type="Submit" value="Registrtaion" class="btn btn-primary" style="width:100%">
						</div>
						
						
					</form>
			
				</div>
		
			</div>
		</div>	
		
	
	</body>
</html>