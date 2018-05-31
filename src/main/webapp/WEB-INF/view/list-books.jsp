<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Customers</title>
	
	<!-- reference our style sheet -->

	<link rel="stylesheet"
			 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
<div class="row">
	<div class="col-md-12">
		<div class="col-md-6">
			User: <security:authentication property="principal.username" />, Role(s): <security:authentication property="principal.authorities" />
		</div>
		<div class="col-md-6 text-right">
			<!-- Add a logout button -->

			<form:form action="${pageContext.request.contextPath}/logout" 
					   method="POST">
				<button type="submit" onclick="if (!(confirm('Are you sure you want to logout?'))) return false">
                	<span class="glyphicon glyphicon-off" aria-hidden="true"></span> Logout
            	</button>
			</form:form>
		</div>
	</div>
    <div class="col-md-12">
    
        <div class="panel panel-primary list-panel" id="list-panel">
            <div class="panel-heading list-panel-heading">
                <h3 class="panel-title list-panel-title">Book Library</h3>
				
            </div>
            <div class="panel-body">
                <!--  add a search box -->
	         <form:form action="search" method="POST">
	             <input type="text" name="theSearchName" placeholder="Type Book Name"/>
	             <button type="submit">
                	<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            	</button>
	         </form:form>
			<!--  add our html table here -->
		
			<table class="table table-hover">
				<tr>
					<th>Book Name</th>
					<th>Creator</th>
					<th>Publisher</th>
					
					<%-- Only show "Action" column for managers or admin --%>
					<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
					
						<th>Action</th>
					
					</security:authorize>
					
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempBook" items="${books}">
				
					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/book/showFormForUpdate">
						<c:param name="bookId" value="${tempBook.id}" />
					</c:url>					

					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/book/delete">
						<c:param name="bookId" value="${tempBook.id}" />
					</c:url>					
					
					<tr>
						<td> ${tempBook.book_name} </td>
						<td> ${tempBook.creator} </td>
						<td> ${tempBook.publisher} </td>

						<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
						
							<td>
								<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
									<!-- display the update link -->
									<a href="${updateLink}">Edit</a>
								</security:authorize>
								
								<security:authorize access="hasAnyRole('ADMIN')">
									| <a href="${deleteLink}"
									   onclick="if (!(confirm('Are you sure you want to delete this book?'))) return false">Delete</a>
								</security:authorize>
							</td>

						</security:authorize>
												
					</tr>
				
				</c:forEach>
						
			</table>
				
		</div>
            </div>
            <div class="panel-footer">
	            <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
				
					<!-- put new button: Add Customer -->
				
					<input type="button" value="Add Book" class="btn btn-primary btn-md"
						   onclick="window.location.href='showFormForAdd'; return false;"
						   class="add-button"
					/>
				
				</security:authorize>
				
            </div>
            <!-- <img src="~/Content/Images/ajax-loader.gif" class="loading-indicator" id="loading-indicator" /> -->
        </div>
    </div>
	

		
	

</body>

</html>









