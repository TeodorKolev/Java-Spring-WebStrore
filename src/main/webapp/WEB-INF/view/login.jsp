<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="title" value="Login"/>
</jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 

<div class="row">
    <div class="col-md-6">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <form class="show-grid" name="login" action="${contextPath}/login" method="POST">
            <fieldset class="form-group">
                <label for="username">Username</label>
                <input class="form-control" id="username" name="username" type="text"/>
            </fieldset>
            <fieldset class="form-group">
                <label for="password">Password</label>
                <input class="form-control" id="password" name="password" type="password"/>
            </fieldset>
            <input class="btn btn-primary" name="submit" type="submit" value="Login"/>
        </form>
        <br>
        <div class="alert alert-warning">
            <strong>Hint!</strong> admin: <strong>admin</strong> pass: <strong>asa</strong>; user: <strong>asa</strong>, pass: <strong>asa</strong>.
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
