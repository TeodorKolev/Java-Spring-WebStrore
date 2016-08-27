<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="title" value="Users"/>
</jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form:form method="GET" action="${contextPath}${usersUrl}" modelAttribute="UserSearch">
    <div class="row">
        <div class="col-md-3">
            <form:label path="username" class="control-label"><spring:message text="Username" /></form:label>
            <form:input type="text" class="form-control" path="username"/>
        </div>
        <div class="col-md-3">
            <form:label path="name" class="control-label"><spring:message text="Name" /></form:label>
            <form:input type="text" class="form-control" path="name" />
        </div>
        <div class="col-md-3">
            <form:label path="role" class="control-label"><spring:message text="Role" /></form:label>
            <form:select path="role" class="form-control">
                <option disabled selected value>
                <form:options items="${roles}" itemValue="id" itemLabel="name" />
            </form:select>
        </div>
        <div class="col-md-3">
            <form:label path="status" class="control-label"><spring:message text="Status" /></form:label>
            <form:select path="status" class="form-control">
                <option disabled selected value>
                <form:options items="${statuses}" itemValue="id" itemLabel="name" />
            </form:select>
        </div>
    </div>
    <br>
    <input type="submit" id="btn2" class="btn btn-primary" value="Search" />
    <button class="btn btn-default" type="button" onclick="location = '${contextPath}${usersUrl}'">Clear</button>
</form:form>
<br>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Id</th>
        <th>Username</th>
        <th>Name</th>
        <th>Status</th>
        <th>Role</th>
        <th>Edit</th>
        <th>Deactivate</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty users}">
        <c:forEach var="user" items="${users}">
            <tr ${user.status == 2 ? 'class="danger"' : ''}>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.name}</td>
                <td>${user.userStatus.name}</td>
                <td>${user.userRole.name}</td>
                <td><a href="${contextPath}${userEditNoIdUrl}${user.id}" class="btn btn-primary btn-xs">Edit</a></td>
                <td>
                    <c:if test="${user.status != 2}" >
                        <a href="${contextPath}${userDeactivateNoIdUrl}${user.id}" class="btn btn-danger btn-xs">Deactivate</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<button class="btn btn-primary" type="button" onclick="location = '${contextPath}${userAddUrl}'">Add User</button>
<jsp:include page="footer.jsp"></jsp:include>


