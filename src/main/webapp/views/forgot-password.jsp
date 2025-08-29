<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
    <jsp:include page="topbar.jsp"/>
    <div class="container">
        <h2>Quên mật khẩu</h2>
        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/forgot-password">
            <div class="form-group">
                <label>Tên đăng nhập</label>
                <input type="text" name="username" required />
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" required />
            </div>
            <button type="submit">Tiếp tục</button>
            <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
        </form>
    </div>
</body>
</html>