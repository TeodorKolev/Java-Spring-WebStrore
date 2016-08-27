<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="title" value="Orders"/>
</jsp:include>
<fmt:setLocale value="en_US"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form:form method="GET" action="${contextPath}${ordersUrl}" modelAttribute="OrderSearch">
    <div class="row">
        <div class="col-md-4">
            <form:label path="productName" class="control-label"><spring:message text="Product Name" /></form:label>
            <form:input type="text" class="form-control" path="productName"/>
        </div>
        <div class="col-md-4">
            <form:label path="customerName" class="control-label"><spring:message text="Customer Name" /></form:label>
            <form:input type="text" class="form-control" path="customerName" />
        </div>
        <div class="col-md-4">
            <form:label path="purchaseDate" class="control-label"><spring:message text="Purchase Date" /></form:label>
            <form:input type="date" class="form-control" path="purchaseDate" />
        </div>
    </div>
    <br>
    <input type="submit" id="btn2" class="btn btn-primary" value="Search" />
    <button class="btn btn-default" type="button" onclick="location = '${contextPath}${ordersUrl}'">Clear</button>
</form:form>
<br>
<table class="table table-striped">
    <thead>
        <tr>
            <th>Id</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Customer Name</th>
            <th>Created By:</th>
            <th>Date of Creation</th>
            <th>Edit</th>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th>Deactivate</th>
            </sec:authorize>
        </tr>
    </thead>
    <tbody>
        <c:if test="${not empty orders}">
            <c:forEach var="order" items="${orders}">
                <tr ${order.status == 2 ? 'class="danger"' : ''}>
                    <td>${order.id}</td>
                    <td>${order.product.name}</td>
                    <td>${order.quantity}</td>
                    <td>${order.customer.name}</td>
                    <td>${order.user.username}</td>
                    <td><spring:eval expression="order.purchaseDate" /></td>
                    <td><fmt:formatNumber value="${order.quantity * order.product.price}" type="currency"/></td>
                    <td><a href="${contextPath}${orderEditNoIdUrl}${order.id}" class="btn btn-primary btn-xs">Edit</a></td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <c:if test="${order.status != 2}" >
                                <a href="${contextPath}${orderDeactivateNoIdUrl}${order.id}" class="btn btn-danger btn-xs">Deactivate</a>
                            </c:if>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </c:if>
    </tbody>
</table>
<button class="btn btn-primary" type="button" onclick="location = '${contextPath}${orderAddUrl}'">Add Order</button>
<jsp:include page="footer.jsp"></jsp:include>

