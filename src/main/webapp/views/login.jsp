<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="jakarta.servlet.jsp.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Đăng nhập - BT LT-WEB</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
</head>
<body>
  <%@ include file="topbar.jsp" %>
  <div class="container">
    <h2><i class="fas fa-sign-in-alt"></i> Đăng nhập</h2>
    
    <c:if test="${success != null}">
      <div class="alert alert-success">
        <i class="fas fa-check-circle"></i> ${success}
      </div>
    </c:if>
    
    <c:if test="${not empty sessionScope.reset_success}">
      <div class="alert alert-success">
        <i class="fas fa-check-circle"></i> ${sessionScope.reset_success}
      </div>
      <c:remove var="reset_success" scope="session"/>
    </c:if>
    
    <c:if test="${alert != null}">
      <div class="alert alert-danger">
        <i class="fas fa-exclamation-triangle"></i> ${alert}
      </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post" accept-charset="UTF-8">
      <div class="form-group">
        <label for="username"><i class="fas fa-user"></i> Tài khoản</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="Nhập tên đăng nhập" required />
      </div>

      <div class="form-group">
        <label for="password"><i class="fas fa-lock"></i> Mật khẩu</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required />
      </div>

      <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" id="remember" name="remember" />
        <label class="form-check-label" for="remember">
          <i class="fas fa-remember"></i> Ghi nhớ đăng nhập
        </label>
      </div>

      <button type="submit" class="btn btn-primary">
        <i class="fas fa-sign-in-alt"></i> Đăng nhập
      </button>
    </form>
    
    <p>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">
      <i class="fas fa-user-plus"></i> Đăng ký ngay
    </a></p>
    
    <p><a href="${pageContext.request.contextPath}/forgot-password">
      <i class="fas fa-key"></i> Quên mật khẩu?
    </a></p>
  </div>
</body>
</html>