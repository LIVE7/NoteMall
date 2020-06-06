<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>노트북은 NoteMall</title>

    <!-- Favicon  -->
    <link rel="icon" href="${pageContext.request.contextPath}/img/core-img/notemallicon.png">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/core-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    

</head>

<script src="${pageContext.request.contextPath}/js/jquery/jquery-3.4.1.min.js"></script>

<script type="text/javascript">
	var check=function(){
		
		if(!sf.findKeyword.value){
			alert('검색어를 입력하세요');
			sf.findKeyword.focus();
			return false;
		}
		sf.submit();
	}


</script>


<style>

#pre_large{
	padding: 0pt;
	border:0pt;
 	background-color: #ffffff;
    width:100%;
    overflow:hidden;
    word-break:break-all;
    word-break:break-word;
    line-height:21px;
    white-space: pre-wrap;       /* CSS 3 */
    white-space: -moz-pre-wrap;  /* Mozilla, since 1999 */
    white-space: -pre-wrap;      /* Opera 4-6 */
    white-space: -o-pre-wrap;    /* Opera 7 */
    word-wrap: break-word;       /* Internet Explorer 5.5+ */
    font-size: 12pt;
    font-weight: normal;

}

#pre_small{
	padding: 0pt;
	border:0pt;
 	background-color: #ffffff;
    width:100%;
    overflow:hidden;
    word-break:break-all;
    word-break:break-word;
    line-height:21px;
    white-space: pre-wrap;       /* CSS 3 */
    white-space: -moz-pre-wrap;  /* Mozilla, since 1999 */
    white-space: -pre-wrap;      /* Opera 4-6 */
    white-space: -o-pre-wrap;    /* Opera 7 */
    word-wrap: break-word;       /* Internet Explorer 5.5+ */
    font-size: 11pt;
    font-weight: normal;

}

.pop_lay{
            width:250px;
            height:150px;
            position:fixed;
            top:200px; left:100px;
            padding:20px;
            border:2px solid black;
            background-color: #ffffdd;
        }

        .pop_lay h1{
            text-align: center;
        }

        .pop_lay p{
            font-size:12px;
        }

        .pop_lay .footer{
            position:absolute;
            right: 0; bottom: 0;
            width:100%;
            text-align: right;
            background-color: #d4d4d4;
        }

</style>

<body>
    <!-- Search Wrapper Area Start -->
    <div class="search-wrapper section-padding-100">
        <div class="search-close">
            <i class="fa fa-close" aria-hidden="true"></i>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="search-content">
                        <form name="sf" action="shop" onsubmit="return check()">
                        	<input type="hidden"  name="pageSize" class="form-control" value="4">
                        	<input type="hidden"  name="findType" class="form-control" value="2">
                            <input type="search" name="findKeyword" id="search" placeholder="검색하고 싶은 상품명 또는 브랜드를 입력하신 후 엔터를 누르세요">
                            <button type="button" onclick="check()"><img src="${pageContext.request.contextPath}/img/core-img/search.png" alt=""></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Search Wrapper Area End -->

    <!-- ##### Main Content Wrapper Start ##### -->
    <div class="main-content-wrapper d-flex clearfix">

        <!-- Mobile Nav (max width 767px)-->
        <div class="mobile-nav">
            <!-- Navbar Brand -->
            <div class="amado-navbar-brand">
                <a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/img/core-img/logo.gif" alt=""></a>
            </div>
            <!-- Navbar Toggler -->
            <div class="amado-navbar-toggler">
                <span></span><span></span><span></span>
            </div>
        </div>

        <!-- Header Area Start -->
        <header class="header-area clearfix">
            <!-- Close Icon -->
            <div class="nav-close">
                <i class="fa fa-close" aria-hidden="true"></i>
            </div>
            <!-- Logo -->
            <div class="logo" style="margin-bottom: 10px;">
                <a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/img/core-img/logo.gif" alt=""></a>
            </div>
            
        	<div style="margin-bottom: 20px;">
            <c:if test="${loginUser eq null }">
            	<span style="color: blue; font-weight: bold">&nbsp;</span>
            </c:if>
            <c:if test="${loginUser ne null}">
            	<span style="color: blue; font-weight: bold">${loginUser.name} 님</span>
            </c:if>
            </div>

        <!-- Amado Nav -->
            <nav class="amado-nav">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/home">메인페이지</a></li>
                    <li><a href="${pageContext.request.contextPath}/shop?pageSize=4">노트북</a></li>
                    <li><a href="${pageContext.request.contextPath}/notice">공지사항</a></li>
                    <li><a href="${pageContext.request.contextPath}/event">이벤트</a></li>
                    <li><a href="${pageContext.request.contextPath}/board/list?pageSize=10">문의게시판</a></li>
                </ul>
            </nav>

            <!-- Button Group -->
            <c:if test="${loginUser eq null }">
            <div class="amado-btn-group mt-30 mb-30">
                <a href="#loginModal" data-toggle="modal" class="btn amado-btn mb-15">로그인</a>
                <a href="#joinModal" data-toggle="modal" class="btn amado-btn active">회원가입</a>
            </div>
            </c:if>
            <c:if test="${loginUser ne null}">
            <div class="amado-btn-group mt-30 mb-30">
                <a href="${pageContext.request.contextPath}/user/orderInfo?idx=${loginUser.idx}" class="btn amado-btn active mb-15">주문내역</a>
                <a href="${pageContext.request.contextPath}/logout" class="btn amado-btn">로그아웃</a>
            </div>
            </c:if>
            
            <!-- Cart Menu -->
            <div class="cart-fav-search mb-100">
                <a href="#" class="search-nav"><img src="${pageContext.request.contextPath}/img/core-img/search.png" alt=""> 상품명 검색</a>
                <a href="${pageContext.request.contextPath}/user/cartList" class="cart-nav"><img src="${pageContext.request.contextPath}/img/core-img/cart.png" alt=""> 장바구니</a>
                <a href="#" class="fav-nav" id="favorite"><img src="${pageContext.request.contextPath}/img/core-img/favorites.png" alt=""> 북마크에 등록</a>
            </div>
            <!-- Social Button -->
            <div class="social-info d-flex justify-content-between">
                <a href="https://www.pinterest.co.kr/"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                <a href="https://www.instagram.com/"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                <a href="https://www.facebook.com/"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                <a href="https://twitter.com/"><i class="fa fa-twitter" aria-hidden="true"></i></a>
            </div>
        </header>
        <!-- Header Area End -->

            
      
      