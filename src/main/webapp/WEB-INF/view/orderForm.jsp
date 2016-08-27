<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="title" value="Order Form"/>
</jsp:include>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:url var="addAction" value="${contextPath}${orderEditNoIdUrl}" ></c:url>
<div class="row">
    <div class="col-md-6">
        <c:if test="${errorMessage != null}">
            <div class="alert alert-danger" role="alert">
                <strong><c:out value="${errorMessage}"/></strong>
            </div>
        </c:if>
        <form:form action="${contextPath}${orderUrl}" commandName="order" class="form-horizontal">
            <form:hidden path="id" />
            <div class="form-group">
                <form:label path="productId" class="col-lg-3 control-label"><spring:message text="Product" /></form:label>
                <div class="col-lg-9">
                    <form:select path="productId" class="form-control">
                        <c:forEach var="product" items="${products}">
                            <form:option value="${product.id}"><c:out value="${product.name} (${product.quantity})"/></form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <form:label path="quantity" class="col-lg-3 control-label"><spring:message text="Quantity" /></form:label>
                <div class="col-lg-9">
                    <form:input path="quantity" type="number" class="form-control" min="1"  />
                </div>
            </div>
            <div class="form-group">
                <form:label path="customerId" class="col-lg-3 control-label"><spring:message text="Qustomer" /></form:label>
                <div class="col-lg-9">
                    <form:select path="customerId" class="form-control">
                        <form:options items="${customers}" itemValue="id" itemLabel="name" />
                    </form:select>
                </div>
            </div>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <c:choose>
                    <c:when test="${order.id != null}">
                        <div class="form-group">
                            <form:label path="status" class="col-lg-3 control-label"><spring:message text="Status" /></form:label>
                            <div class="col-lg-9">
                                <form:select path="status" class="form-control">
                                    <option disabled selected value>
                                    <form:options items="${statuses}" itemValue="id" itemLabel="name" />
                                </form:select>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <form:hidden path="status" />
                    </c:otherwise>
                </c:choose>
            </sec:authorize>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
