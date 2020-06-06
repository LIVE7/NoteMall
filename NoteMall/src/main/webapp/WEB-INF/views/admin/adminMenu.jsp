<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/top" />
<div class="single-product-area section-padding-100 clearfix">
	<div class="container-fluid text-center">

		<div class="row">
			<div class="col-md-10 offset-md-1">

				<h1>Admin Page</h1>
				<br> <br> <span><a href="orderAdmin"
					style="font-size: 30px; color: purple;">월별 주문 목록</a></span> <br> <br>
				<br> <span><a href="statistic"
					style="font-size: 30px; color: navy;">연도별/월별 매출 통계</a></span> <br> <br>
				<br> <span><a href="emailList"
					style="font-size: 30px; color: olive;">등록된 이메일 주소</a></span>
			</div>
		</div>
	</div>
</div>

</div>
<!-- ##### Main Content Wrapper End ##### -->

<c:import url="/foot" />