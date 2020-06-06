<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Product Catagories Area Start -->
        <div class="products-catagories-area clearfix">
            <div class="amado-pro-catagory clearfix">
            
                <c:if test="${prodList eq null or empty prodList}">
                <div class="col-md-12">
					<h3>상품 준비 중...</h3>
				</div>
				</c:if>
	
				<c:if test="${prodList ne null and not empty prodList}">
				<c:forEach var="pd" items="${prodList}" varStatus="status">
				<!-- varStatus속성을 이용하면 반복문의 상태정보를 알아낼수 있다.
					반복문 횟수: ${status.count}
					반복문 인덱스:${status.index}
				 -->
                <!-- Single Catagory -->
                <div class="single-products-catagory clearfix">
                    <a href="${pageContext.request.contextPath}/prodView?pnum=${pd.pnum}">
                        <img alt="상품이미지" src="${pageContext.request.contextPath}/img/notebook/${pd.pimage1}">
                        <!-- Hover Content -->
                        <div class="hover-content">
                            <div class="line"></div>
                            <h4 style="text-shadow: -1px 0 #ffffff, 0 1px #ffffff, 1px 0 #ffffff, 0 -1px #ffffff;">${pd.pname}</h4>
                            <span style="font-size: 18px; font-weight:bold; text-shadow: -1px 0 #ffffff, 0 1px #ffffff, 1px 0 #ffffff, 0 -1px #ffffff;">정가: <del><fmt:formatNumber value="${pd.price}" pattern="###,###"/>원</del></span><br>
                            <span style="color:red; font-weight:bold; font-size: 20px; text-shadow: -1px 0 #ffffff, 0 1px #ffffff, 1px 0 #ffffff, 0 -1px #ffffff;">
							판매가: <fmt:formatNumber value="${pd.saleprice}" pattern="###,###"/>원</span>
							<span class="badge badge-danger" style="font-size: 18px;"> ${pd.percent}% 할인</span><br>
							<div class="badge badge-success" style="font-size: 16px;"><b>포인트 <fmt:formatNumber value="${pd.point}" pattern="###,###"/></b>점 적립</div>
							<c:if test="${status.count mod 3 == 0}">
						</div>
                    </a>
						<div class="row"><!-- 줄바꿈. 기존행을 닫고 새로운 행을 시작 -->
							</c:if>
						</div>
                </div>
				</c:forEach>
				</c:if>
			</div>
        </div>
<!-- Product Catagories Area End -->
