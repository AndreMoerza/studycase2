<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Book</title>

	<!-- <link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">-->
	<link rel="stylesheet"
			 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	

	<div class="container">
		<div class="jumbotron">
		    <h3>Save/Update Book</h3>      
		  </div>
		<form:form action="saveBook" modelAttribute="book" method="POST" class="form-horizontal">
			<!-- need to associate this data with customer id -->
			<form:hidden path="id"/>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">Book Name</label>
				<div class="col-sm-4">
					<form:input path="book_name" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">Creator</label>
				<div class="col-sm-4">
					<form:input path="creator" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label for="message" class="col-sm-2 control-label">Publisher</label>
				<div class="col-sm-4">
					<form:input path="publisher" class="form-control"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-10 col-sm-offset-2">
					<button type="submit" class="btn btn-primary">
	                	<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span> Save
	            	</button>
	            	<a href="${pageContext.request.contextPath}/book/list" class="btn btn-primary">
	                	<span class="glyphicon glyphicon-step-backward" aria-hidden="true"></span> Back To List
	            	</a>
				</div>
			</div>
			
		</form:form>
	
	</div>

</body>

</html>










