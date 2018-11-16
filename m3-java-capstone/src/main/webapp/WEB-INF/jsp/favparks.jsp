<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section class="main-content">
	<div class="allFavParks">
		<c:forEach var="favparks" items="${favParksList}">
			<div class="park">
				<c:url var="favParkUrl" value="/img/parks/${favparks.parkcode.toLowerCase()}.jpg" />
				<c:url var="parkDetailUrl" value="/parkdetails">
				<c:param name="parkcode" value="${favparks.parkcode}" />
				</c:url>
				
				<a href="${parkDetailUrl}"><img class="park-img" src="${favParkUrl}" /></a>
				<p>${favparks.parkname}</p>
				<p>${favparks.numSurveys} votes</p>
			</div>
		</c:forEach>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>