<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/top"/>

<!-- Product Details Area Start -->
        <div class="single-product-area section-padding-100 clearfix">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-12">
                    <c:if test="${prod eq null}">
						<h3>해당 상품은 존재하지 않아요</h3>
					</c:if>
					
					<c:if test="${prod ne null}">
                        <nav aria-label="breadcrumb">
                            <hr>
                            상품정보
                            <hr>
                        </nav>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12 col-lg-7">
                        <div class="single_product_thumb">
                            <div id="product_details_slider" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li class="active" data-target="#product_details_slider" data-slide-to="0" style="background-image: url(img/notebook/${prod.pimage1});">
                                    </li>
                                    <li data-target="#product_details_slider" data-slide-to="1" style="background-image: url(img/notebook/${prod.pimage2});">
                                    </li>
                                    <li data-target="#product_details_slider" data-slide-to="2" style="background-image: url(img/notebook/${prod.pimage3});">
                                    </li>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <a class="gallery_img" href="img/notebook/${prod.pimage1}">
                                            <img class="d-block w-100" src="img/notebook/${prod.pimage1}" alt="First slide">
                                        </a>
                                    </div>
                                    <div class="carousel-item">
                                        <a class="gallery_img" href="img/notebook/${prod.pimage2}">
                                            <img class="d-block w-100" src="img/notebook/${prod.pimage2}" alt="Second slide">
                                        </a>
                                    </div>
                                    <div class="carousel-item">
                                        <a class="gallery_img" href="img/notebook/${prod.pimage3}">
                                            <img class="d-block w-100" src="img/notebook/${prod.pimage3}" alt="Third slide">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-lg-5">
                        <div class="single_product_desc">
                            <!-- Product Meta Data -->
                            <div class="product-meta-data">
                                <div class="line"></div>
                                <p class="product-price"><fmt:formatNumber value="${prod.saleprice}"
						 pattern="###,###"/> 원</p>
                                    <h3>${prod.pname}</h3>
                                <!-- Ratings & Review -->
                                <div class="ratings-review mb-15 d-flex align-items-center justify-content-between">
                                    <div class="ratings">
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                    </div>
                                </div>
                                <!-- Avaiable -->
                                <p class="avaibility"><i class="fa fa-circle"></i> 재고 있음</p>
                            </div>

                            <div class="short_overview my-5">
                                <p>${prod.pcontents}</p>
						상품정가:<span>
						<del>
					<fmt:formatNumber value="${prod.price}" pattern="###,###"/> 원
						</del>
						</span><br>
						판매가:<span class="text-danger">
						<fmt:formatNumber value="${prod.saleprice}"
						 pattern="###,###"/> 원
						</span><br>
						할인율:
						<span class="text-info">
						${prod.percent}%
						</span><br>
						포인트: <span class="text-success">
						${prod.point}</span>점 적립
						<br>
                            </div>

                            <!-- Add to Cart Form -->
                            <form class="cart clearfix" method="GET" name="cartfrm">
                                <div class="cart-btn d-flex mb-50">
                                    <p>수량</p>
                                    <div class="quantity">
                                        <span class="qty-minus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty ) &amp;&amp; qty &gt; 1 ) effect.value--;return false;"><i class="fa fa-caret-down" aria-hidden="true"></i></span>
                                        <!--form시작 상품수량-------------------  -->
											<input type="hidden" name="pnum" value="${prod.pnum}">
											<input type="hidden" name="opnum" value="${prod.pnum}">
                                        	<input type="number" class="qty-text" id="qty" step="1" min="1" max="10" name="oqty" value="1">
										</form>
                                        <span class="qty-plus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty )) effect.value++;return false;"><i class="fa fa-caret-up" aria-hidden="true"></i></span>
                                    </div>
                                </div>
                                <button name="addtocart" class="btn amado-btn" onclick="goCart()">장바구니</button>
                                <br><br>
                                <button name="addtocart" class="btn amado-btn" style="background-color:chocolate" onclick="goOrder()">주문하기</button>
                        </div>
                    </div>
                </div>    
                <div class="row">
                    <div class="col-12">
                        <hr>
                            상품설명
                        <hr>
                        <img class="d-block w-100" src="img/notebook/${prod.pimage4}" alt="First slide">
                     </div>
                </div>
                </c:if>
            </div>

        </div>
        <!-- Product Details Area End -->
        
        </div>
<!-- ##### Main Content Wrapper End ##### -->


<script>
	
	var goCart=function(){
		cartfrm.action="user/cartAdd";
		cartfrm.method="POST";
		cartfrm.submit();
	}
	
	var goOrder=function(){
		cartfrm.action="user/orderSheet";
		cartfrm.method="POST";
		cartfrm.submit();
	}
	
</script>

<c:import url="/foot"/>
 