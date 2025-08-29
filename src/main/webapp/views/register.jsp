<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Đăng ký tài khoản - BT LT-WEB</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
</head>
<body>
  <jsp:include page="topbar.jsp"/>
  <div class="container">
    <h2><i class="fas fa-user-plus"></i> Tạo tài khoản mới</h2>

    <c:if test="${alert != null}">
      <div class="alert alert-danger">
        <i class="fas fa-exclamation-triangle"></i> ${alert}
      </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post" accept-charset="UTF-8">
      <div class="form-group">
        <label for="fullname"><i class="fas fa-id-card"></i> Họ và tên</label>
        <input type="text" id="fullname" name="fullname" class="form-control" placeholder="Nhập họ và tên đầy đủ" required />
      </div>

      <div class="form-group">
        <label for="email"><i class="fas fa-envelope"></i> Email</label>
        <input type="email" id="email" name="email" class="form-control" placeholder="Nhập địa chỉ email" required />
      </div>

      <div class="form-group">
        <label for="username"><i class="fas fa-user"></i> Tên đăng nhập</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="Chọn tên đăng nhập" required />
      </div>

      <div class="form-group">
        <label for="password"><i class="fas fa-lock"></i> Mật khẩu</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Tạo mật khẩu mạnh" required />
      </div>

      <div class="form-group">
        <label for="confirm"><i class="fas fa-lock"></i> Xác nhận mật khẩu</label>
        <input type="password" id="confirm" name="confirm" class="form-control" placeholder="Nhập lại mật khẩu" required />
      </div>

      <div class="form-group">
        <label for="phone"><i class="fas fa-phone"></i> Điện thoại (tuỳ chọn)</label>
        <input type="text" id="phone" name="phone" class="form-control" placeholder="Số điện thoại liên hệ" />
      </div>

      <button type="submit" class="btn btn-primary">
        <i class="fas fa-user-plus"></i> Đăng ký tài khoản
      </button>
    </form>

    <p>Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">
      <i class="fas fa-sign-in-alt"></i> Đăng nhập ngay
    </a></p>
  </div>
</body>
</html>