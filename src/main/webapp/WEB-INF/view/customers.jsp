<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="title" value="Customers"/>
</jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form:form method="GET" action="${contextPath}${customersUrl}" modelAttribute="CustomerSearch">
    <div class="row">
        <div class="col-md-3">
            <form:label path="name" class="control-label"><spring:message text="Name" /></form:label>
            <form:input type="text" class="form-control" path="name"/>
        </div>
        <div class="col-md-3">
            <form:label path="personalId" class="control-label"><spring:message text="Personal ID" /></form:label>
            <form:input type="number" class="form-control" path="personalId" />
        </div>
        <div class="col-md-3">
            <form:label path="birthDate" class="control-label"><spring:message text="Birth Date" /></form:label>
            <form:input type="date" class="form-control" path="birthDate" />
        </div>
        <div class="col-md-3">
            <form:label path="address" class="control-label"><spring:message text="Address" /></form:label>
            <form:input type="text" class="form-control" path="address" />
        </div>
    </div>
    <br>
    <input type="submit" id="btn2" class="btn btn-primary" value="Search" />
    <button class="btn btn-default" type="button" onclick="location = '${contextPath}${customersUrl}'">Clear</button>
</form:form>
<br>
<table class="table table-striped">
    <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Personal Id</th>
            <th>Birth Date</th>
            <th>Address</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
    <c:if test="${not empty customers}">
        <c:forEach var="customer" items="${customers}">
            <tr ${customer.status == 2 ? 'class="danger"' : ''}>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.personalId}</td>
                <td><spring:eval expression="customer.birthDate" /></td>
                <td>${customer.address}</td>
                <td><a href="${contextPath}${customerEditNoIdUrl}${customer.id}" class="btn btn-primary btn-xs">Edit</a></td>
                <td>
                    <c:if test="${customer.status != 2}" >
                        <a href="${contextPath}${customerDeactivateNoIdUrl}${customer.id}" class="btn btn-danger btn-xs">Deactivate</a>
                     </c:if>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<button class="btn btn-primary" type="button" onclick="location = '${contextPath}${customerAddUrl}'">Add Customer</button>
<jsp:include page="footer.jsp"></jsp:include>