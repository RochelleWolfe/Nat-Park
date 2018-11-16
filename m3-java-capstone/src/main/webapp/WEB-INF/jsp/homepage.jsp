<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section class="main-content">
 <h2>Explore our Parks</h2> 
        <p> Click on a park's image to discover great details! </p> 
	<div class="all-parks">
	<c:forEach var="parks" items="${parkList}">
		<div class="park">
			<c:url var="parkUrl" value="/img/parks/${parks.parkCode.toLowerCase()}.jpg" />
			<c:url var="parkDetailUrl" value="/parkdetails">
			<c:param name="parkcode" value="${parks.parkCode}" />
			</c:url>
			
			<a href="${parkDetailUrl}">
			<img class="park-img" src="${parkUrl}" /></a>
			
			<div class="park-details">
				<p id="park-name"><strong>${parks.parkName}, ${parks.state}</strong></p>
				<p id="park-description">${parks.parkDescription}</p>
			</div>
		</div>
	</c:forEach>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>