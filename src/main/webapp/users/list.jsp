<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/users/header.jsp" %>
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Users Crud</h1>
    <a href="/add" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
            class="fas fa-download fa-sm text-white-50"></i> Add user</a>
</div>
<br>

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">DataTables Users</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
                </tfoot>
                <tbody>
                <tbody>
                <!--   for (Todo todo: todos) {  -->
                <c:forEach  items="${users}" var="user">

                    <tr>
                        <td><c:out value="${user.getUserId()}" /></td>
                        <td><c:out value="${user.getUserName()}" /></td>
                        <td><c:out value="${user.getEmail()}" /></td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/edit?id=<c:out value='${user.getUserId()}'/>" class="btn btn-warning btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-exclamation-triangle"></i>
                                        </span>
                                <span class="text">Edit</span>
                            </a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/delete?id=<c:out value='${user.getUserId()}'/>" class="btn btn-danger btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-trash"></i>
                                        </span>
                                <span class="text">Delete</span>
                            </a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/details?id=<c:out value='${user.getUserId()}'/>" class="btn btn-info btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-info-circle"></i>
                                        </span>
                                <span class="text">Show details</span>
                            </a>



                        </td>
                    </tr>
                </c:forEach>
                <!-- } -->
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- /.container-fluid -->




<%@ include file="/users/footer.jsp" %>

