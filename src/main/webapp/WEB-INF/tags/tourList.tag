<%@ attribute name="coll" type="java.util.Collection" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="tour-table">

	<table>
		<c:forEach items="${tours}" var="tour">
			<tr>
			<td>${tour.id}</td>
				<td>${tour.name}</td>
				<td><fmt:formatNumber value="${tour.price}" type="currency" currencyCode="USD" /></td>
				<td>${tour.description}</td>
				<c:if test="${loggedInUser.role == 'admin' }">
					<form action="deleteTour"  method="POST">
				<td><button type="submit" name="delete" value="${tour.id}">delete</button></td>
				</form>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</div>
