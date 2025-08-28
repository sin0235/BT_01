<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="jakarta.servlet.jsp.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Đăng nhập</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css"/>
</head>
<body>
  <%@ include file="topbar.jsp" %>
  <div class="container">
    <h2>Đăng nhập</h2>
    <c:if test="${alert != null}">
      <h3 class="alert alert-danger">${alert}</h3>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post" accept-charset="UTF-8">
      <div class="form-group">
        <label for="username">Tài khoản</label>
        <input type="text" id="username" name="username" class="form-control" required />
      </div>

      <div class="form-group">
        <label for="password">Mật khẩu</label>
        <input type="password" id="password" name="password" class="form-control" required />
      </div>

      <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" id="remember" name="remember" />
        <label class="form-check-label" for="remember">Ghi nhớ đăng nhập</label>
      </div>

      <button type="submit" class="btn btn-primary">Đăng nhập</button>
    </form>
  </div>
</body>
</html>