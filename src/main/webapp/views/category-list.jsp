<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh mục của tôi</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
    <jsp:include page="topbar.jsp"/>
    <div class="container">
        <h2>Danh mục của tôi</h2>
        <p><a href="${pageContext.request.contextPath}/category/create">+ Thêm danh mục</a></p>
        <table border="1" cellpadding="6">
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Mô tả</th>
                <th>Ngày tạo</th>
                <th>Thao tác</th>
            </tr>
            <c:forEach var="c" items="${items}">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                    <td>${c.description}</td>
                    <td>${c.createddate}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/category/edit?id=${c.id}">Sửa</a>
                        <form action="${pageContext.request.contextPath}/category/delete" method="post" style="display:inline">
                            <input type="hidden" name="id" value="${c.id}"/>
                            <button type="submit" onclick="return confirm('Xoá danh mục này?')">Xoá</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${empty items}">
            <p>Bạn chưa có danh mục nào. <a href="${pageContext.request.contextPath}/category/create">Tạo danh mục đầu tiên</a></p>
        </c:if>
    </div>
</body>
</html>