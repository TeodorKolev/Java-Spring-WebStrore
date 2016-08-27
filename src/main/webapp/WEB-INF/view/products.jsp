<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="title" value="Products"/>
</jsp:include>
<fmt:setLocale value="en_US"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form:form method="GET" action="${contextPath}${productsUrl}" modelAttribute="ProductSearch">
    <div class="row">
        <div class="col-md-3">
            <form:label path="name" class="control-label"><spring:message text="Name" /></form:label>
            <form:input type="text" class="form-control" path="name"/>
        </div>
        <div class="col-md-3">
            <form:label path="price" class="control-label"><spring:message text="Price" /></form:label>
            <form:input type="number" class="form-control" path="price" min="1"/>
        </div>
        <div class="col-md-3">
            <form:label path="type" class="control-label"><spring:message text="Type" /></form:label>
            <form:input type="text" class="form-control" path="type"/>
        </div>
        <div class="col-md-3">
            <form:label path="quantity" class="control-label"><spring:message text="Quantity" /></form:label>
            <form:input type="number" class="form-control" path="quantity" min="0"/>
        </div>
    </div>
    <br>
    <input type=submit id="btn1" class="btn btn-primary" name="Search" value="Search">
    <button class="btn btn-default" type="button" onclick="location = '${contextPath}${productsUrl}'">Clear</button>
</form:form>
<br>
<table class="table table-striped">
    <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Type</th>
            <th>Price</th>
            <th>Quantity</th>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th>Edit</th>
                <th>Delete</th>
            </sec:authorize>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.type}</td>
                <td><fmt:formatNumber value="${product.price}" type="currency"/></td>
                <td>${product.quantity}</td>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td><a href="${contextPath}${productEditNoIdUrl}${product.id}" class="btn btn-primary btn-xs">Edit</a></td>
                    <td><a href="${contextPath}${productDeleteNoIdUrl}${product.id}" class="btn btn-danger btn-xs">Delete</a></td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </tbody>
</table>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <button class="btn btn-primary" type="button" onclick="location = '${contextPath}${productAddUrl}'">Add Product</button>
</sec:authorize>
<jsp:include page="footer.jsp"></jsp:include>