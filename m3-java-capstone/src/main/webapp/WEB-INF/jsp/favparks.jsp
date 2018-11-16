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
				<div class="park-details">
				<p>${favparks.parkname}</p><br>
				<p>${favparks.numSurveys} votes</p><br>
				
				</div>
			</div>
		</c:forEach>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>

<%-- WISHLIST make a bar graph for results 
	https://meyerweb.com/eric/css/edge/bargraph/demo-table.html --%>