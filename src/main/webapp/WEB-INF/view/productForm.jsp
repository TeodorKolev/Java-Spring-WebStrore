<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="title" value="Product Operations"/>
</jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:url var="addAction" value="${contextPath}${productEditNoIdUrl}" ></c:url>
<div class="row">
    <div class="col-md-6">
        <c:if test="${errorMessage != null}">
            <div class="alert alert-danger" role="alert">
                <strong><c:out value="${errorMessage}"/></strong>
            </div>
        </c:if>
        <form:form action="${contextPath}${productUrl}" commandName="product" class="form-horizontal">
            <form:hidden path="id" />
            <div class="form-group">
                <form:label path="name" class="col-lg-3 control-label"><spring:message text="Name" /></form:label>
                <div class="col-lg-9">
                    <form:input path="name" class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="type" class="col-lg-3 control-label"><spring:message text="Type" /></form:label>
                <div class="col-lg-9">
                    <form:input path="type" class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="price" class="col-lg-3 control-label"><spring:message text="Price" /></form:label>
                <div class="col-lg-9">
                    <form:input path="price" type="number" min="0.01" step="0.01" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="quantity" class="col-lg-3 control-label"><spring:message text="Quantity" /></form:label>
                <div class="col-lg-9">
                    <form:input path="quantity" type="number" min="1" class="form-control"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
