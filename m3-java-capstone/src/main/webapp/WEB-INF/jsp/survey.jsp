<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section id="main-content">

	<h2 class="vote-park">Vote for Your Favorite Park</h2>

	<c:url value="/favparks" var="formAction" />
	<form:form method="POST" action="${formAction}" modelAttribute="Survey">

		<div>
			<label for="parkcode">Please choose a park</label> <select
				id="parkcode" name="parkcode">
				<c:forEach var="park" items="${parkList}">
					<option value="${park.parkCode}">${park.parkName}</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<label for="email">Email</label>
			  	<form:input  path="email" class="form-control"/>
			  	<form:errors path="email" cssClass="error"/>
			  	<form:errors path="emailMatching" cssClass="error"/>
		</div>

		<div>
			<label for="confirmEmail">Confirm Email</label>
			<form:input path="confirmEmail" class="form-control" />
			<form:errors path="confirmEmail" cssClass="error" />
		</div>

		<div>
			<label for="state">State of Residence</label>
			<form:input path="state" class="form-control" />
			<form:errors path="state" cssClass="error" />
		</div>

		<div>
			<label for="activity">Activity</label> <select id="activity"
				name="activity">
				<option value="extremelyactive">Extremely Active</option>
				<option value="active">Active</option>
				<option value="mildlyactive">Mildly Active</option>
				<option value="sedentary">Sedentary</option>
			</select>
		</div>

		<div>
			<input type="submit" value="Vote!" />
		</div>
	</form:form>

</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />