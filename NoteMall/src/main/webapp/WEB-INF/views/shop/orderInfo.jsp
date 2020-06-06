<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/top"/>
<div class="single-product-area section-padding-100 clearfix">
	<div class="container-fluid text-center">
		<h1>${loginUser.userid}(${loginUser.name})님의 주문내역</h1>
	

		<div class="col-md-10 offset-md-1">
		
			<table class="table table-bordered table-hover text-center">
				<tr>
					<th>주문번호</th>
					<th>주문일자</th>
					<th>주문총액</th>
					<th>결제상태</th>
					<th>배송상태</th>
				</tr>
				<!-- --------------- -->
				<c:forEach var="orderInfo" items="${orderInfo}">
				<tr>

					<td>
						<a href="orderDetail?onum=${orderInfo.onum}">${orderInfo.onum}</a>
					</td>
					<td>
						${orderInfo.orderDate}
					</td>
					<td>
						${orderInfo.ototalPrice}
					</td>
					<td>
						${orderInfo.opaystate}
					</td>
					<td>
						${orderInfo.odeliver}
					</td>
				</tr>
				</c:forEach>
				<!-- --------------- -->
			</table>
		</div>
	</div>
</div>

</div>
<!-- ##### Main Content Wrapper End ##### -->
<!-- 상세 관리 form ----------------------- -->
<form name="mf" id="mf" action="orderAdminMgr">
	<input type="hidden" name="onum" id="onum">
	<input type="hidden" name="midx_fk" id="midx_fk">
</form>
<!-- ------------------------------------ -->
<script type="text/javascript">
	function goMgr(num, idx){
		$('#onum').val(num);
		$('#midx_fk').val(idx);
		$('#mf').prop("method","POST");
		$('#mf').submit();
	}

</script>

<c:import url="/foot"/>



