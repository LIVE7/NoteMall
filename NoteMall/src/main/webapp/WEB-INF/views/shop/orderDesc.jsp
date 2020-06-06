<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  -->

<c:import url="/top"/>    

<!-- Product Details Area Start -->
<div class="single-product-area section-padding-100 clearfix">
	<div class="container-fluid">

		<div class="row">
		<div class="col-md-10 offset-md-1">
			<hgroup>
				<h1>주문 내역 정보</h1>
			</hgroup>
			<%-- ${orderDesc } --%>
			<!-- 주문관련 공통정보를 추출하기 위해 order변수 선언 -->
			<c:set var="order" value="${orderDesc[0]}" />

			<br />

			<table class="table">
				<tr height="30" bgcolor="fbb710">
					<th colspan="4" class="text-center"><span
						class="glyphicons glyphicons-notes"></span>
						<h3>${loginUser.name} [${loginUser.userid}] 님의 주문 내역서</h3></th>
				</tr>
				<tr>
					<td>주문번호</td>
					<td><b>${order.onum}</b></td>
					<td>수령자</td>
					<td><b>${order.receiver.name}</b></td>
				</tr>

				<tr>
					<td>총구매가격</td>
					<td><b> <fmt:formatNumber value="${order.ototalPrice}"
								pattern="###,###" /> 원
					</b> <br>[ 사용한 포인트: <b><fmt:formatNumber value="${order.opointuse}" pattern="###,###" /></b> POINT ]</td>
					<td>총 누적 포인트</td>
					<td><b><fmt:formatNumber value="${order.ototalPoint}" pattern="###,###" /> POINT</b></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><b>${order.receiver.hp1}-${order.receiver.hp2}-${order.receiver.hp3}
					</b></td>
					<td>기타사항</td>
					<td><b>${order.orderMemo}</b></td>
				</tr>
				<tr>
					<td>배송지</td>
					<td colspan="3"><b>[ ${order.receiver.post}] &nbsp;
							${order.receiver.addr1} ${order.receiver.addr2} </b></td>
				</tr>
			</table>
			<br />
			<table width="95%" class="table table-bordered">
				<tr bgcolor="fbb710" height="30">
					<th class="text-center">상품번호</th>
					<th class="text-center">상품명</th>
					<th class="text-center">판매가</th>
					<th class="text-center">수량</th>
					<th class="text-center">총 상품금액</th>
					<th class="text-center">결제상태</th>
					<th class="text-center">배송상태</th>
				</tr>
				<!-- ---------------- -->
				<c:forEach var="pd" items="${order.orderList}">
					<tr>
						<td class="text-center">${pd.pnum}</td>
						<td align="center"><a href="../prodView?pnum=${pd.pnum}"
							target="_blank"> <img src="../img/notebook/${pd.pimage1}"
								width="80" height="80" /></a> <br /> ${pd.pname}</td>
						<td align="center"><fmt:formatNumber value="${pd.saleprice}"
								pattern="###,###" /> 원</td>
						<td class="text-center">${pd.pqty}개</td>
						<td align="center"><fmt:formatNumber
								value="${pd.saleprice * pd.pqty}" pattern="###,###" /> 원</td>
						<td class="text-center">${order.opaystate}</td>
						<td class="text-center">${order.odeliver}</td>
					</tr>
				</c:forEach>
				<!-- ---------------- -->

			</table>
		</div>
	</div>
</div>
</div>

</div>
<!-- ##### Main Content Wrapper End ##### -->

<c:import url="/foot" />