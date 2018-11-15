<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section class="main-content">
	<div class="park-detail-page">
		
		<h1 class="detail-name">${park.parkName}, ${park.state}</h1>
		<p class="detail-founded">Founded in ${park.yearFounded}</p>
		<p class="detail-quote">${park.quote}</p>
		<p class="detail-quotesource">~ ${park.quoteSource}</p>
		
		<c:url var="parkUrl" value="/img/parks/${park.parkCode.toLowerCase()}.jpg" />
		<img class="detail-img" src="${parkUrl}"/>
	
		<table>
			<tr>
				<td>${park.acreage} Acres</td>
				<td>${park.milesOfTrail} Miles of Trail</td>
			</tr>
			<tr>
				<td>${park.climate}</td>
				<td>${park.numSpecies} Unique Species</td>
			</tr>
			<tr>
				<td>Elevation ${park.elevation}ft.</td>
				<td>${park.numberOfCampsites} Campsites</td>
			</tr>
			<tr>
				<td>${park.annualVisitors} Annual Visitors</td>
				<td>Entry Fee: $${park.entryFee}</td>
			</tr>
		</table>
		
		<p class="detail-description">${park.parkDescription}</p>
		
		
<%--WISHLIST when modelmap is C/F, C/F is checked--%>		
		<form name="tempUnit" method=POST action="parkdetails">
		<input type="radio" name="tempUnit" value="F" onclick="this.form.submit();">Fahrenheit<br>
		<input type="radio" name="tempUnit" value="C" onclick="this.form.submit();">Celcius
		<input type="hidden" name="parkcode" value="${park.parkCode}" />
		</form>
		
<%--WISHLIST display Celsius to only 1 decimal point --%>	
<%--WISHLIST display F or C after temp value --%>	
		<div class="details-weather">
			<div class="weather-today">
				<h2>Today</h2>
				<c:set var="dayOne" value="${forecastList.get(0)}" />
				<c:url var="weatherImg" value="/img/weather/${dayOne.imgName}" />
				<img src="${weatherImg}" />
				<p class="weather-high">High: ${dayOne.high}${tempModel.get("tempUnit")}
				<span class="weather-low">Low: ${dayOne.low}${tempModel}</span></p>
				<p class="messages">${dayOne.tempMessage}${dayOne.weatherMessage}</p>
			</div>
			<div class="weather-future">
			<table>
				<tr>
					<c:forEach begin="1" end="4" var="forecast" items="${forecastList}">
						<td>
							<c:url var="forecastImg" value="/img/weather/${forecast.imgName}" />
							<img class="future-img" src="${forecastImg}" />
						</td>
					</c:forEach>
				</tr>
				
				
				<tr>
					<c:forEach begin="1" end="4" var="forecast" items="${forecastList}">
						<td>
							<p class="high">High: ${forecast.high}${tempUnit}</p>
						</td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach begin="1" end="4" var="forecast" items="${forecastList}">
						<td>
							<p class="low">Low: ${forecast.low}${tempUnit}</p>
						</td>
					</c:forEach>
				</tr>
			</table>
			</div>
		</div>
	</div>
</section>
<%--WISHLIST add img to footer --%>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>