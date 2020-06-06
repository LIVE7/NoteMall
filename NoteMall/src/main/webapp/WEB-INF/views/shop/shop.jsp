<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- top -->
<c:import url="/top"/>  

<!-- 여기에 추가 -->
        
        <div class="shop_sidebar_area">

            <!-- ##### Single Widget ##### -->
            <div class="widget brands mb-10">
                <!-- Widget Title -->
                <h6 class="widget-title mb-30">노트북</h6>

                <!--  Catagories  -->
                <div class="catagories-menu">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4">모든상품</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=ACER">ACER</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=AORUS">AORUS</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=APPLE">APPLE</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=ASUS">ASUS</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=DELL">DELL</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=GIGABYTE">GIGABYTE</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=Hansung">Hansung</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=HP">HP</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=JooyonTech">JooyonTech</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=LG">LG</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=MSI">MSI</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=Microsoft">Microsoft</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=Razer">Razer</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=Renovo">Renovo</a></li>
                        <li><a href="${pageContext.request.contextPath}/shop?pageSize=4&findType=1&findKeyword=Samsung">Samsung</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="amado_product_area section-padding-100">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-12">
                            <!-- Total Products -->
                            <div class="text-right">
                                <h5>${totalCount}개의 상품이 있습니다.</h5>
                            </div>
                    </div>
                </div>

                <div class="row">

                    <!-- Single Product Area -->
                    <c:forEach var="prod" items="${prodList}">
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                    
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                            	<a href="${pageContext.request.contextPath}/prodView?pnum=${prod.pnum}">
                                <img src="${pageContext.request.contextPath}/img/notebook/${prod.pimage1}" alt="">
                                <!-- Hover Thumb -->
                                <img class="hover-img" src="img/notebook/${prod.pimage2}" alt=""></a>
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                     <span style="font-size: 20px; font-weight:bold; text-shadow: -1px 0 #ffffff, 0 1px #ffffff, 1px 0 #ffffff, 0 -1px #ffffff;"><del><fmt:formatNumber value="${prod.price}" pattern="###,###"/> 원</del></span><br>
                                    <a href="${pageContext.request.contextPath}/prodView?pnum=${prod.pnum}">
                                    <p class="product-price"><fmt:formatNumber value="${prod.saleprice}" pattern="###,###"/> 원</p>
                                        <h6>${prod.pname}</h6>
                                    </a>
                                </div>
                                <!-- Ratings & Cart -->
                                <div class="ratings-cart text-right">
                                    <div class="ratings">
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>

                <div class="row">
                    <div class="col-12">
                        <!-- Pagination -->
                        ${navi}
                    </div>
                </div>
            </div>
        </div>

</div>
<!-- ##### Main Content Wrapper End ##### -->

<!-- foot -->
<c:import url="/foot"/>