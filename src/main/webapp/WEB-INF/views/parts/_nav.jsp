<%--
  Created by IntelliJ IDEA.
  User: Volobuev
  Date: 21.11.2022
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/flat/list/"/>">Home</a></li>
                <c:if test="${user != null}">
                    <c:if test="${user.getRole() == 'admin'}">
                        <li><a href="<c:url value="/flat/create"/>">New Flat</a></li>
                    </c:if>
                    <li><a class="nav-link disabled" href="<c:url value="/profile"/>">${user.getUsername()}</a></li>
                    <li><a href="<c:url value="/logout"/>">Log out</a></li>
                </c:if>
                <c:if test="${user == null}">
                    <li><a href="<c:url value="/signin"/>">Log in</a></li>
                    <li><a href="<c:url value="/registration"/>">Registration</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
