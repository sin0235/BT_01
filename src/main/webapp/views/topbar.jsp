<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="topbar">
  <div class="logo">
    <i class="fas fa-user-shield"></i> MyApp
  </div>
  <div class="nav-links">
    <c:choose>
      <c:when test="${empty sessionScope.account}">
        <a href="${pageContext.request.contextPath}/login">
          <i class="fas fa-sign-in-alt"></i> Đăng nhập
        </a>
        <a href="${pageContext.request.contextPath}/register">
          <i class="fas fa-user-plus"></i> Đăng ký
        </a>
      </c:when>
      <c:otherwise>
        <span>
          <i class="fas fa-user"></i> Xin chào, ${sessionScope.account.fullname}
        </span>
        <a href="${pageContext.request.contextPath}/logout">
          <i class="fas fa-sign-out-alt"></i> Đăng xuất
        </a>
      </c:otherwise>
    </c:choose>
  </div>
</div>