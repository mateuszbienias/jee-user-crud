<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/users/header.jsp" %>

<!-- my box to use ever i need -->

<div class="col-xl-8 col-lg-7">
    <div class="card shadow mb-4">
        <!-- Card Header - Dropdown -->
        <div
                class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
            <h6 class="m-0 font-weight-bold text-primary">EDIT USER CART</h6>

        </div>
        <!-- Card Body -->
        <div class="card-body">
            <div class="chart-area">

                <form action="/edit" method="post">
                    <div class="form-group">
                        <label for="userid">User ID</label>
                        <input type="number" class="form-control" name="id" id="userid" aria-describedby="username" value="${user.getUserId()}" readonly/>
                    </div>
                    <div class="form-group">
                    <div class="form-group">
                        <label for="username1">User Name</label>
                        <input type="text" class="form-control" name="username" id="username1" aria-describedby="username" value="${user.getUserName()}">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <input type="email" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" value="${user.getEmail()}">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" class="form-control" name="password" id="exampleInputPassword1" value="${user.getPassword()}">
                    </div>
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                </form>
                <!--END Card Body -->
            </div>
        </div>
    </div>
</div>
<!-- end my box to use ever i need -->

<%@ include file="/users/footer.jsp" %>
