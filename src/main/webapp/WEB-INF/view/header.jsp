<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
    <title>${param.title}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <sec:authorize access="isAuthenticated()">
        <div class="navbar-header">
            <a class="navbar-brand" href="${contextPath}/orders">Orders</a>
            <a class="navbar-brand" href="${contextPath}/customers">Customers</a>
            <a class="navbar-brand" href="${contextPath}/products">Products</a>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a class="navbar-brand" href="${contextPath}/users">Users</a>
            </sec:authorize>
        </div>
        <div class="navbar-header navbar-right">
            <a class="navbar-brand" href="${contextPath}/user/edit/own">Edit</a>
            <a class="navbar-brand" href="${contextPath}/logout">Logout</a>
        </div>
        </sec:authorize>
    </div>
</nav>
<div class="container" id="bascontainer">
    <div class="row">
        <div class="col-md-12">
            <h3>${param.title}</h3>
