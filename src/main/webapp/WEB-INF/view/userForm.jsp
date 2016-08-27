<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="title" value="User Operations"/>
</jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:url var="addAction" value="${contextPath}${userEditNoIdUrl}" ></c:url>

<div class="row">
    <div class="col-md-6">
        <c:if test="${errorMessage != null}">
            <div class="alert alert-danger" role="alert">
                <strong><c:out value="${errorMessage}"/></strong>
            </div>
        </c:if>
        <form:form action="${contextPath}${userUrl}" commandName="user" class="form-horizontal">
            <form:hidden path="id" />
            <div class="form-group">
                <form:label path="username" class="col-lg-3 control-label"><spring:message text="Username" /></form:label>
                <div class="col-lg-9">
                    <form:input path="username" class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="password" class="col-lg-3 control-label"><spring:message text="Password" /></form:label>
                <div class="col-lg-9">
                    <form:password path="password" class="form-control" id="password" value="${user.password}" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="name" class="col-lg-3 control-label"><spring:message text="Name" /></form:label>
                <div class="col-lg-9">
                    <form:input type="text" path="name" class="form-control" />
                </div>
            </div>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="form-group">
                <form:label path="role" class="col-lg-3 control-label"><spring:message text="Role" /></form:label>
                <div class="col-lg-9">
                    <form:select path="role" class="form-control">
                        <option disabled selected value>
                        <form:options items="${roles}" itemValue="id" itemLabel="name" />
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <form:label path="status" class="col-lg-3 control-label"><spring:message text="Status" /></form:label>
                <div class="col-lg-9">
                    <form:select path="status" class="form-control">
                        <option disabled selected value>
                        <form:options items="${statuses}" itemValue="id" itemLabel="name" />
                    </form:select>
                </div>
            </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <form:hidden path="role" />
                <form:hidden path="status" />
            </sec:authorize>
            <div class="form-group">
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
