<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="title" value="ERROR"/>
</jsp:include>
<div class="row">
    <div class="col-md-6">
        <div class="alert alert-danger" role="alert">
            <strong>This page is unavailable</strong>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <button class="btn btn-primary" type="button" onclick="location = '${contextPath}/'">Back To Home</button>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
