<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style type="text/css">
.google-logo {
	padding: 20px 0;
}

.google-search {
	padding: 20px 10px;
}

.google-search:focus {
	box-shadow: silver 0 2px 10px;
	border-color: silver;
}

.google-form .btn-group {
	padding: 20px 0;
}

.btn-group>.btn {
	border-radius: 0;
	margin: 0 10px;
}

.btn {
	background-color: #f2f2f2;
	color: #757575;
	font-weight: 900;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<title>Spring Auto-complete</title>
<script type="text/javascript">
	$(function() {
		$("#tagsName").autocomplete({
			source : function(request, response) {
				$.getJSON("${pageContext.request.contextPath}/getUniversity", {
					term : request.term
				}, response);
			}
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
				<div class="row google-logo text-center">
					<img src="download.png" alt="Search" width="200" height="200">

				</div>
				<div class="row google-form text-center">
					<form:form method="post" action="/showPaginationDemo">
						<div class="form-group">

							<input type="text" class="form-control google-search"
								id="tagsName" name="college">
							<div class="btn-group">
								<button type="submit" class="btn btn-default">Search</button>


							</div>
						</div>
					</form:form>

				</div>


			</div>
		</div>
	</div>

	 
	<c:if test="${totalItems>0}">   
	<div align="center">
			       
			<center>
				<h5>University List</h5>
				 
			</center>
			<table border="1" cellpadding="5">
				                         
				<tr>
					                
					<th>University Name</th>                             
				</tr>
				            
				<c:forEach var="listUniveristy" items="${listUniveristy}">
                <tr>
						                    
						<td><c:out value="${listUniveristy.universityName}" /></td>
						                                    
					</tr>
            </c:forEach>
				        
			</table>
			    
		</div>
	
	    
	<div align="center">
			Total Items: ${totalItems} <br>
			Current Page:${currentPage}</br>

			<c:if test="${currentPage>5}">
				<a href="/getUnivList/${currentPage -1}">Previous</a>
			</c:if>
			<c:forEach var="i" begin="${#numbers.sequence(1, totalPages)}"
				end="5">
				<a href="/getUnivList/${i}">${i}</a>
			</c:forEach>

			<c:if test="${currentPage < totalPages}">
				<a href="/getUnivList/${currentPage + 1}">Next</a>
			</c:if>
		</div>
	</c:if>
</body>
</html>