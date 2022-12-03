<%--
  Created by IntelliJ IDEA.
  User: Volobuev
  Date: 30.11.2022
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:mainLayout title ="Registration">
    <div>${message}</div>
    <form id="loginForm" class="form-horizontal" action="<c:url value="/registration"/>" method="POST">
        <div class="form-group">
            <div class="form-group">
                <label class="control-label col-sm-3" for="email">Email</label>
                <div class="controls col-sm-9">
                    <input id="email" name="email" class="form-control" type="text" value=""/>
                </div>
            </div>
            <label class="control-label col-sm-3" for="username">Username</label>
            <div class="controls col-sm-9">
                <input id="username" name="username" class="form-control" type="text" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3" for="password">Password</label>
            <div class="controls col-sm-9">
                <input id="password" name="password" class="form-control" type="password" value=""/>
            </div>
        </div>
        <button type="submit" class="btn btn-success">Register</button>
    </form>
</t:mainLayout>
