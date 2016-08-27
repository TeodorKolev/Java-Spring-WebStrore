<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="title" value="Customer Form"/>
</jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:url var="addAction" value="${contextPath}${customerEditNoIdUrl}" ></c:url>

<div class="row">
    <div class="col-md-6">
        <c:if test="${errorMessage != null}">
            <div class="alert alert-danger" role="alert">
                <strong><c:out value="${errorMessage}"/></strong>
            </div>
        </c:if>
        <form:form action="${contextPath}${customerUrl}" commandName="customer" class="form-horizontal">
            <form:hidden path="id" />
            <div class="form-group">
                <form:label path="name" class="col-lg-3 control-label"><spring:message text="Name" /></form:label>
                <div class="col-lg-9">
                    <form:input path="name" class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="personalId" class="col-lg-3 control-label"><spring:message text="Personal Id" /></form:label>
                <div class="col-lg-9">
                    <form:input path="personalId" type="number"  class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="birthDate" class="col-lg-3 control-label"><spring:message text="Birth Date" /></form:label>
                <div class="col-lg-9">
                    <form:input path="birthDate" type="date"  class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="address" class="col-lg-3 control-label"><spring:message text="Address" /></form:label>
                <div class="col-lg-9">
                    <form:input path="address" type="text" class="form-control"/>
                </div>
            </div>
            <c:choose>
                <c:when test="${customer.id != null}">
                    <div class="form-group">
                        <form:label path="status" class="col-lg-3 control-label"><spring:message text="Status"/></form:label>
                        <div class="col-lg-9">
                            <form:select path="status" class="form-control">
                                <option disabled selected value>
                                <form:options items="${statuses}" itemValue="id" itemLabel="name"/>
                            </form:select>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <form:hidden path="status" />
                </c:otherwise>
            </c:choose>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
