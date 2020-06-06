<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  -->
<c:import url="/top" />


<div class="single-product-area section-padding-100 clearfix">
	<div class="container-fluid text-center">
		<div align="center" class="col-md-10 offset-md-1">
			<!-- 공통적인 정보를 추출하여 변수에 할당 --- -->
			<c:set var="ovo" value="${orderList[0]}" />
			<!-- ---------------------------------- -->

			<h2>주문내역서 [ADMIN 모드]</h2>
			<table class="table table-condensed">
				<tr height="30">
					<th colspan="4" class="text-center">
						<h2>${orderUser.name} [${orderUser.userid}] 님 주문 내역서</h2>
					</th>
				</tr>
				<tr>
					<td width="20%" bgcolor="#efefef">주문번호</td>
					<td width="30%"><b>${ovo.onum}</b></td>
					<td width="20%" bgcolor="#efefef">주문자<br> [ 수령자 ]
					</td>
					<td width="30%">${orderUser.name}<br> [
						${ovo.receiver.name} ]
					</td>
				</tr>
				<tr>
					<td width="20%" bgcolor="#efefef">총 구매가격</td>
					<td width="30%"><fmt:formatNumber value="${ovo.ototalPrice}"
							pattern="###,###" /> 원</td>
					<td width="20%" bgcolor="#efefef">총 누적포인트</td>
					<td width="30%">[ <fmt:formatNumber value="${ovo.ototalPoint}"
							pattern="###,###" /> ] 점
					</td>
				</tr>
				<tr>
					<td width="20%" bgcolor="#efefef">주문자 연락처</td>
					<td width="30%">${orderUser.hp1}-${orderUser.hp2}-${orderUser.hp3}</td>
					<td width="20%" bgcolor="#efefef">수령자 연락처</td>
					<td width="30%">
						${ovo.receiver.hp1}-${ovo.receiver.hp2}-${ovo.receiver.hp3}</td>
				</tr>
				<tr>
					<td width="20%" bgcolor="#efefef">배송지</td>
					<td colspan="3">[ ${ovo.receiver.post}]<br>
						${ovo.receiver.addr1}&nbsp; ${ovo.receiver.addr2}
					</td>
				</tr>
			</table>
			<br>
			<table class="table table-condensed" id="ex">
				<tr>
					<td class="text-center" style="font-size: 30px;">
						<form name="payF" method="GET" action="orderAdminMgrEnd">
							<input type="hidden" name="midx_fk" value="${ovo.midx_fk}">
							<input type="hidden" name="onum" value="${ovo.onum}">
							결제정보:<font color="red"> <b>${ovo.opaystate}</b></font>
					</td>
					<td><select name="opaystate" onchange="submit()">
							<option value="">::선택::</option>
							<option value="결제완료">결제완료</option>
							<option value="미결제">미결제</option>
							<option value="결제취소">결제취소</option>
					</select>
						</form></td>
					<td class="text-center" style="font-size: 30px;">
						<form name="dF" method="GET" action="orderAdminMgrEnd">
							<input type="hidden" name="midx_fk" value="${ovo.midx_fk}">
							<input type="hidden" name="onum" value="${ovo.onum}">
							배송상태:<font color="red"><b> ${ovo.odeliver}</b></font>
					</td>
					<td><select name="odeliver" onchange="submit()">
							<option value="">::선택::</option>
							<option value="미배송">미배송</option>
							<option value="배송중">배송중</option>
							<option value="배송완료">배송완료</option>
							<option value="주문취소">주문취소</option>
					</select>
						</form></td>
				</tr>
			</table>
			<br>
			<table class="table table-condensed">
				<tr>
					<th class="text-center">상품번호</th>
					<th class="text-center">상품명</th>
					<th class="text-center">판매가</th>
					<th class="text-center">수량</th>
					<th class="text-center">총 상품금액</th>
					<th class="text-center">결제상태</th>
					<th class="text-center">배송상태</th>
				</tr>
				<!-- ---------------- -->
				<c:forEach var="pd" items="${ovo.orderList}">
					<tr>
						<td class="text-center">${pd.pnum}</td>
						<td><img src="../img/notebook/${pd.pimage1}" width="70"
							height="70" /><br> ${pd.pname}</td>
						<td class="text-center"><fmt:formatNumber
								value="${pd.saleprice}" pattern="###,###" />원</td>
						<td class="text-center">${pd.pqty}개</td>
						<td class="text-center"><fmt:formatNumber
								value="${pd.saleprice * pd.pqty}" pattern="###,###" />원</td>
						<td class="text-center">${ovo.opaystate}</td>
						<td class="text-center">${ovo.odeliver}</td>
					</tr>
				</c:forEach>
				<!-- ---------------- -->
			</table>

		</div>
	</div>
</div>

</div>
<!-- ##### Main Content Wrapper End ##### -->
<c:import url="/foot" />