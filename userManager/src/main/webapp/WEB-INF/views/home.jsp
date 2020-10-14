<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"	crossorigin="anonymous">

<title>Home</title>
</head>
<body>
	<h1>User management</h1>

	<div class="row">
		<div class="col-sm">
			<c:if test="${!empty error}">

				<div class="alert alert-warning" role="alert">${error}</div>

			</c:if>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Employee ID</th>
						<th scope="col">Name</th>
						<th scope="col">Department</th>
						<th scope="col">Project</th>
						<th scope="col">Employee Type</th>
						<th scope="col" />
						<th scope="col" />
					</tr>
				</thead>
				<c:forEach var="collab" items="${collab}" varStatus="loop">
					<tr>
						<%-- 				<th scope="row">${loop.index}</th> --%>
						<td><c:out value="${collab.id}" /></td>
						<td><c:out value="${collab.name}" /></td>
						<td><c:out value="${collab.departmentName}" /></td>


						<c:choose>
							<c:when test="${collab.type.equals('M')}">
<%-- 								<td><c:out value="${collab.projectName}" /></td> --%>
									<td><c:out value="NOT APPLICABLE" /></td>
							</c:when>
							
							<c:when test="${collab.type.equals('R') and  (empty collab.projectName)}">
									<td><c:out value="NOT ALLOCATED" /></td>
							</c:when>
							
							
							
							
							<c:otherwise>
								<td><c:out value="${collab.projectName}" /></td>
							</c:otherwise>
						</c:choose>





						<c:if test="${collab.type}">no users added yet.</c:if>




















						<c:set var="string1"
							value="${fn:replace(collab.type, 'R', 'REGULAR')}" />
						<c:set var="string2"
							value="${fn:replace(string1, 'M', 'MANAGER')}    " />
						<td><c:out value="${string2}" /></td>
						
						
						<td><a class="fas fa-pencil-alt" 
							href="<c:url value="/updatecollaborator/${collab.id}"/>"></a></td>
						
						
						
						<td><a class="fas fa-trash-alt" style="color:red"
							href="<c:url value="/delete/${collab.id}"/>"></a></td>
					</tr>
				</c:forEach>
				<c:if test="${empty collab}">
			no users added yet.
		</c:if>

			</table>
			<a class="btn btn-primary" href="<c:url value="/newcollaborator"/>">Add
				new Colaborator</a>
		</div>
		<div class="col-sm">

			<!-- 					<button class="btn btn-danger">Te amo</button></div> -->
		</div>


		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
			integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
			integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
			crossorigin="anonymous"></script>
		<script src="https://kit.fontawesome.com/a596a1c73d.js"
			crossorigin="anonymous"></script>
</body>
</html>
