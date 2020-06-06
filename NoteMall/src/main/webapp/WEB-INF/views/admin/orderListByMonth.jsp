<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/top"/>
<div class="single-product-area section-padding-100 clearfix">
	<div class="container-fluid text-center">
		<h1>월별 주문 목록</h1>
	
		<div class="col-md-10 offset-md-1">
		<table class="table table-bordered table-hover text-center">
			<form name="df" id="df" action="orderAdmin">
			<tr>
				<th width="25%">
					<label for="yy" style="font-size: 30px;">연도</label>
				</th>
				<th width="25%">
					<select name="yy" id="yy">
					<option value="">::연도 선택::</option>
					<c:forEach var="year" begin="2017" end="2027">
					
					<c:if test="${param.yy eq year }">	
						<option value="${year}" selected >${year}</option>
					</c:if>
					<c:if test="${param.yy ne year }">	
						<option value="${year}"  >${year}</option>
					</c:if>
					</c:forEach>
				</select>
				</th>
				<th width="25%">
					<label for="mm" style="font-size: 30px;">월</label>
				</th>
				<th width="25%">
					<select name="mm" id="mm" >
					<option value="">::월 선택::</option>
					<c:forEach var="month" begin="1" end="12">
						<c:if test="${param.mm eq month }">
						<option value="${month}" selected >${month}</option>
						</c:if>
						<c:if test="${param.mm ne month }">
						<option value="${month}">${month}</option>
						</c:if>
					</c:forEach>
					</select>
				</th>
			</tr>
			<tr>
				<td colspan="4">
				<button class="btn amado-btn">월별 주문 목록 보기</button>
				</td>
			</tr>
			</form>
			</table>
		</div>
		<div class="col-md-10 offset-md-1">
		
			<table class="table table-bordered table-hover text-center" style="margin-top:30px">
				<tr>
					<th colspan="5" class="text-center">
					<h1>${month} 월 주문자 목록</h1>
					</th>
				</tr>
				<tr>
					<th>주문자 ID</th>
					<th>주문번호</th>
					<th>주문총액</th>
					<th>주문일자</th>
					<th>상세관리</th>
				</tr>
				<!-- --------------- -->
				<c:set var="osum" value="0"/><!-- 주문 총액 -->
				<c:forEach var="ovo" items="${olist}">
					<c:set var="osum" value="${osum+ovo.ototalPrice}"/>
				<tr>
					<td> ${ovo.userid}</td>
					<td>${ovo.onum}</td>
					<td class="text-center">
						<fmt:formatNumber value="${ovo.ototalPrice}"
							pattern="###,###"/> 원
					</td>
					<td>${ovo.orderDate}</td>
					<td>
						<a href="#" onclick="goMgr('${ovo.onum}','${ovo.midx_fk}')">상세관리</a>
					</td>
				</tr>
				</c:forEach>
				<!-- --------------- -->
				<tr>
					<td colspan="5">
						<h3 class="text-center text-danger">
						${month} 월의 주문 총액:
						<fmt:formatNumber value="${osum}" pattern="###,###"/> 원
						</h3>
					</td>
				</tr>
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



