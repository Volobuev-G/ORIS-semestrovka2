<%--
  Created by IntelliJ IDEA.
  User: Volobuev
  Date: 30.11.2022
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:mainLayout title ="Profile">
    <div>${message}</div>
    <div class="container">
        <h1>Edit Profile</h1>
        <hr>
        <div class="row">
            <!-- edit form column -->
            <div class="col-md-9 personal-info">

                <h3>Personal info</h3>

                <form class="form-horizontal" action="<c:url value="/profile"/>" method="POST">
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Email:</label>
                        <div class="col-lg-8">
                            <input class="form-control" name="email" type="text" value="${user.getEmail()}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Username:</label>
                        <div class="col-md-8">
                            <input class="form-control" name="username" type="text" value="${user.getUsername()}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Password:</label>
                        <div class="col-md-8">
                            <input class="form-control" name="password" type="password" value="${user.getPassword()}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-8">
                            <button type="submit" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <hr>
</t:mainLayout>

