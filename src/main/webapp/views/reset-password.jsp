<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
    <jsp:include page="topbar.jsp"/>
    <div class="container">
        <h2>Đặt lại mật khẩu</h2>
        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/reset-password">
            <div class="form-group">
                <label>Mật khẩu mới</label>
                <input type="password" name="password" required />
            </div>
            <div class="form-group">
                <label>Xác nhận mật khẩu</label>
                <input type="password" name="confirm" required />
            </div>
            <button type="submit">Cập nhật</button>
            <a href="${pageContext.request.contextPath}/forgot-password">Quay lại</a>
        </form>
    </div>
</body>
</html>