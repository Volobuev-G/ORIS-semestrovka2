<%--
  Created by IntelliJ IDEA.
  User: Volobuev
  Date: 01.12.2022
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:mainLayout title ="Create">
    <div>${message}</div>
    <div class="container">
        <hr>
        <div class="row">
            <!-- edit form column -->
            <div class="col-md-9 personal-info">

                <h3>Add flat</h3>

                <form class="form-horizontal" action="<c:url value="/flat/create"/>" method="POST">
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Name:</label>
                        <div class="col-lg-8">
                            <input class="form-control" name="name" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Status:</label>
                        <div class="col-md-8">
                            <input class="form-control" name="status" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Location</label>
                        <div class="col-md-8">
                            <input class="form-control" name="location" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Cost</label>
                        <div class="col-md-8">
                            <input class="form-control" name="cost" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-8">
                            <button type="submit" class="btn btn-primary">Add</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <hr>
</t:mainLayout>
