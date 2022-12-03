<%--
  Created by IntelliJ IDEA.
  User: Volobuev
  Date: 21.11.2022
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:mainLayout title ="Detail">
    <div class="container mt-5 mb-5">
        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <div class="card">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="text-center p-4"> <img id="main-image" src="https://q-xx.bstatic.com/xdata/images/hotel/840x460/166841139.jpg?k=30c83e588229e98701cb12f5e265f8dcf788d2339cca4b3fb5930fe988a786cd&o=" width="250" /> </div>
                        </div>
                        <div class="col-md-6">
                            <div class="product p-4">
                                <h1 class="text-uppercase">${flat.getFlatName()}</h1>
                                <h5 class="text-uppercase">${flat.getStatus()}</h5>
                                <h6 class="text-uppercase">${flat.getLocation()}</h6>
                                <div class="price d-flex flex-row align-items-center"> <span class="act-price">${flat.getCost()}</span>
                                </div>
                            </div>
                            <p class="about">There are many variations of passages of Lorem Ipsum available, but the
                                majority have suffered alteration in some form, by injected humour, or
                                randomised words which don't look even slightly believable.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
<%--    <div class="book-page">--%>
<%--        <h1 class="book-name">${flat.getFlatName()}</h1>--%>
<%--        <div class="book-field">--%>
<%--            <span class="field-name">Status:</span>--%>
<%--            <span class="field-value">${flat.getStatus()}</span><br>--%>
<%--            <span class="field-name">Location:</span>--%>
<%--            <span class="field-value">${flat.getLocation()}</span><br>--%>
<%--            <span class="field-name">Cost:</span>--%>
<%--            <span class="field-value">${flat.getCost()}</span><br>--%>
<%--        </div>--%>
<%--            <div class="book-description">Some description for the flat</div>--%>
<%--    </div>--%>
    <div>
        <c:if test="${user != null}">
            <div>${message}</div>
            <form id="loginForm" class="form-horizontal" action="<c:url value="/flat/detail"/>" method="POST">
                <div class="form-group">
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="email">Leave your comment:</label>
                        <div class="controls col-sm-9">
                            <input id="email" name="content" class="form-control" type="text" placeholder="Your text"/>
                            <input type="hidden" name="value" value="${flat.getId()}" />
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-success">Post comment</button>
            </form>
        </c:if>
    </div>
    <div>
        <section style="background-color: #e7effd;">
            <div class="container my-5 py-5 text-dark">
                <div class="row d-flex justify-content-center">
                    <div class="col-md-11 col-lg-9 col-xl-7">
                        <c:forEach items="${comments}" var="comment">
                            <c:if test="${flat.getId() == comment.getIdFlat()}">
                            <div class="d-flex flex-start mb-4">
                                <div class="card w-100">
                                    <div class="card-body p-4">
                                        <div class="">
                                            <h5>${comment.getAuthor()}</h5>
                                            <p class="small">${comment.getDate()}</p>
                                            <p>
                                                    ${comment.getContent()}
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
    </div>
</t:mainLayout>


