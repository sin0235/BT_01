<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh mục</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
    <jsp:include page="topbar.jsp"/>
    <div class="container">
        <h2>
            <c:choose>
                <c:when test="${empty item}">Thêm danh mục</c:when>
                <c:otherwise>Sửa danh mục</c:otherwise>
            </c:choose>
        </h2>

        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/category/<c:out value='${empty item ? "create" : "edit"}'/>">
            <c:if test="${not empty item}">
                <input type="hidden" name="id" value="${item.id}"/>
            </c:if>

            <div class="form-group">
                <label>Tên danh mục</label>
                <input type="text" name="name" required value="${empty item ? '' : item.name}"/>
            </div>

            <div class="form-group">
                <label>Mô tả</label>
                <input type="text" name="description" value="${empty item ? '' : item.description}"/>
            </div>

            <button type="submit">
                <c:out value='${empty item ? "Tạo" : "Cập nhật"}'/>
            </button>
            <a href="${pageContext.request.contextPath}/category/list">Huỷ</a>
        </form>
    </div>
</body>
</html>