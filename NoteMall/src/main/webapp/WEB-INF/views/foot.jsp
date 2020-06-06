<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/login/login.jsp" %>
    <%@ include file="/WEB-INF/views/join/join.jsp" %>
    
    
    <!-- foot -->
    

    
      <!-- ##### Newsletter Area Start ##### -->
    <section class="newsletter-area section-padding-100-0">
        <div class="container">
            <div class="row align-items-center">
                <!-- Newsletter Text -->
                <div class="col-12 col-lg-6 col-xl-7">
                    <div class="newsletter-text mb-100">
                        <h2>지금 이메일 주소를 등록하시면 <br><span>특가 정보</span>를 알려드립니다!</h2>
                    </div>
                </div>
                <!-- Newsletter Form -->
                <div class="col-12 col-lg-6 col-xl-5">
                    <div class="newsletter-form mb-100">
                        <form role="form" name="emailfrm" method="post"
							action="emailAdd" onsubmit="return onSubmit2()">
							<input class="nl-email" type="email" name="email" id="email"
								placeholder="이메일 주소를 입력하세요.">
							<input type="submit" value="등록">
						</form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Newsletter Area End ##### -->

    <!-- ##### Footer Area Start ##### -->
    <footer class="footer_area clearfix">
        <div class="container">
            <div class="row align-items-center">
                <!-- Single Widget Area -->
                <div class="col-12 col-lg-4">
                    <div class="single_widget_area">
                        <!-- Logo -->
                        <div class="footer-logo mr-50">
                            <a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/img/core-img/logo2.png" alt=""></a>
                        </div>
                        <!-- Copywrite Text -->
                        <p class="copywrite"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy; <script>document.write(new Date().getFullYear());</script> NoteMall - All rights reserved
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                    </div>
                </div>
                <!-- Single Widget Area -->
                <div class="col-12 col-lg-8">
                    <div class="single_widget_area">
                        <!-- Footer Menu -->
                        <div class="footer_menu">
                            <nav class="navbar navbar-expand-lg justify-content-end">
                                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#footerNavContent" aria-controls="footerNavContent" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars"></i></button>
                                <div class="collapse navbar-collapse" id="footerNavContent">
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/shop">Shop</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/user/cartList">Cart</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/contact">Contact</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/about">About</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/mgr">Admin</a>
                                        </li>
                                    </ul>
                                </div>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- ##### Footer Area End ##### -->

    <!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
<%--     <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.4.1.min.js"></script> --%>
    <!-- Popper js -->
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <!-- Plugins js -->
    <script src="${pageContext.request.contextPath}/js/plugins.js"></script>
    <!-- Active js -->
    <script src="${pageContext.request.contextPath}/js/active.js"></script>
    
    <script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#favorite')
								.on(
										'click',
										function(e) {
											var bookmarkURL = window.location.href;
											var bookmarkTitle = document.title;
											var triggerDefault = false;

											if (window.sidebar
													&& window.sidebar.addPanel) {
												// Firefox version < 23
												window.sidebar
														.addPanel(
																bookmarkTitle,
																bookmarkURL,
																'');
											} else if ((window.sidebar && (navigator.userAgent
													.toLowerCase()
													.indexOf('firefox') > -1))
													|| (window.opera && window.print)) {
												// Firefox version >= 23 and Opera Hotlist
												var $this = $(this);
												$this.attr('href',
														bookmarkURL);
												$this.attr('title',
														bookmarkTitle);
												$this.attr('rel',
														'sidebar');
												$this.off(e);
												triggerDefault = true;
											} else if (window.external
													&& ('AddFavorite' in window.external)) {
												// IE Favorite
												window.external
														.AddFavorite(
																bookmarkURL,
																bookmarkTitle);
											} else {
												// WebKit - Safari/Chrome
												//alert((navigator.userAgent.toLowerCase().indexOf('mac') != -1 ? 'Cmd': 'Ctrl')+ '+D 키를 눌러 즐겨찾기에 등록하실 수 있습니다.');
												alert((navigator.userAgent.toLowerCase().indexOf('mac') != -1 ? 'Cmd': 'Ctrl')+ '+D 키를 눌러 즐겨찾기에 등록하실 수 있습니다.');
											}

											return triggerDefault;
										});
					});
</script>
<script type="text/javascript">
	var onSubmit2 = function() {

		if (!emailfrm.email.value) {
			alert('이메일 주소를 입력하세요.');
			emailfrm.email.focus();
			return false;
		} else {
			document.getElementById('emailfrm').submit();
			return true;
		}

	}
</script>

<script type="text/javascript">
	//마우스 우클릭시 alert메시지를 보내도록 만든다
	/* document.oncontextmenu = function() {
		alert('마우스 오른쪽버튼은 사용할 수 없습니다.');
		return false;
	}; */
	/* document.onselectstart = function(){ 
	 alert( "Ctrl + C 기능은 사용할 수 없습니다" ); 
	 return false; 
	 };  
	 document.ondragstart = function(){ 
	 alert( "드래그 기능은 사용할 수 없습니다" ); 
	 return false; 
	 };  */
</script>

</body>

</html>