<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>Home</title>
</head>
<body  onload="checkProjectRadio()">
	<h1>Add new Collaborator</h1>
	<div class="container">
		<div class="row">
			<form action="add" method="POST">

				<input class="form-control" placeholder="Name" type="text"
					name="name"><br> <select class="form-control"
					name="departmentId">
					<option value="0">Departments</option>
					<c:forEach var="dp" items="${dp}">
						<option value="${dp.id}">${dp.name}</option>
					</c:forEach>
				</select><br>
				
				
				



				<div class="m-3">
				
				<c:forEach var="type" items="${types}">

					<div class="form-check form-check-inline">
						<input onclick="checkProjectRadio()" class="form-check-input" type="radio"
							name="type" value="${type.getKey()}"> <label
							class="form-check-label" for="inlineRadio1">${type.getValue()}</label>
					</div>
				</c:forEach>
</div>
				 <select class="form-control m-3"
					name="projectId" id="projectId">
					<option value="">Projects</option>
					<c:forEach var="pj" items="${pj}">
						<option value="${pj.id}">${pj.name}</option>
					</c:forEach>
				</select>




				<input class="btn btn-primary" type="submit" value="Save">
			</form>
		</div>
	</div>
	<button type="button" class="btn btn-primary m-2" name="back"
		onclick="history.back()">Back</button>

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
		
		<script>
		
		function checkProjectRadio(){
			let regular = document.getElementsByName("type")[0];
			let manager = document.getElementsByName("type")[1];
			document.getElementById("projectId").selectedIndex = 0;
			
			if(!regular.checked && !manager.checked){
			regular.checked=true;
			}
			else if(manager.checked){
			//combo proj disable
			document.getElementById("projectId").disabled=true;
			
			}
			else if(regular.checked){
			//combo proj enable
			document.getElementById("projectId").disabled=false;
			}
		}
		</script>
</body>
</html>
