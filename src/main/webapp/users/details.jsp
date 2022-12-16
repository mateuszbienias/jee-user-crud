<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/users/header.jsp" %>

<!-- my box to use ever i need -->

<div class="col-xl-8 col-lg-7">
  <div class="card shadow mb-4">
    <!-- Card Header - Dropdown -->
    <div
            class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
      <h6 class="m-0 font-weight-bold text-primary">ADD USER CART</h6>

    </div>
    <!-- Card Body -->
    <div class="card-body">
      <div class="chart-area">

        <table class="table">
          <tr>
            <th scope="row">ID</th>
            <td>${user.getUserId()}</td>
          </tr>
          <tr>
            <th scope="row">User Name</th>
            <td>${user.getUserName()}</td>
          </tr>
          <tr>
            <th scope="row">Email</th>
            <td>${user.getEmail()}</td>
          </tr>
          </tbody>
        </table>

        <!--END Card Body -->
      </div>
    </div>
  </div>
</div>
<!-- end my box to use ever i need -->

<%@ include file="/users/footer.jsp" %>

