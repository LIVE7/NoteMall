<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/top" />

<!-- Product Details Area Start -->
<div class="single-product-area section-padding-100 clearfix">
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-10 offset-md-1">
				<h1>${loginUser.name} [${loginUser.userid}] 님의 주문정보</h1>

				<table class="table">
					<tr>
						<th colspan="5">주문 상품 정보</th>
					</tr>
					<tr bgcolor="#efefef" align="center">
						<td width="10%">상품번호</td>
						<td width="25%">상품명</td>
						<td width="30%">판매가</td>
						<td width="10%">수 량</td>
						<td width="25%">합계금액</td>
					</tr>
					<c:set var="sumPrice" value="0" />
					<!-- 총주문가 -->
					<c:set var="sumPoint" value="0" />
					<!-- 총주문 누적 포인트 -->

					<!-- p는 ProductVO--------------------- -->
					<c:forEach var="p" items="${orderList}">
						<c:set var="sumPrice" value="${sumPrice+p.totalPrice}" />
						<c:set var="sumPoint" value="${sumPoint+p.totalPoint}" />
						<tr>
							<td>${p.pnum}</td>
							<td align="center">${p.pname}<br> <a
								href="../prodView?pnum=${p.pnum}" target="_blank"><img
									src="../img/notebook/${p.pimage1}" width="100" height="100"
									border="0" /> </a>
							</td>
							<td align="right" style="padding-right: 20px"><fmt:formatNumber
									value="${p.saleprice}" pattern="###,###" /> 원<br>
								[<fmt:formatNumber
										value="${p.point}" pattern="###,###" />] POINT</td>

							<td align="center">${p.pqty}개</td>

							<td align="right" style="padding-right: 20px"><b> <fmt:formatNumber
										value="${p.totalPrice}" pattern="###,###" /> 원<br />
							</b> <b> [<fmt:formatNumber
										value="${p.totalPoint}" pattern="###,###" />] POINT </b></td>
						</tr>
					</c:forEach>
					<!-- ----------------------- -->

					<tr bgcolor="#efefef">
						<td colspan="2">주문일자<br /> <b> <fmt:formatDate
									value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd HH:mm:ss" />
						</b></td>
						<td colspan="3" align="right" style="font-weight: bold;">총 주문금액<br>
							<span id="total" class="text-danger" style="font-size: 40px;">
								<fmt:formatNumber value="${sumPrice}" pattern="###,###" />
							</span><span class="text-danger" style="font-size: 40px;">원</span> <br>총 [<font color=blue><b> <fmt:formatNumber
										value="${sumPoint}" pattern="###,###" /></b></font> ]점의 POINT가 적립됩니다.<br/>

						</td>
					</tr>
					<!-- --------------------- -->
				</table>

				<!-- form 시작=================== -->
				<form name="custF" action="orderAdd" method="POST">
					<!-- ----------------------------------------------------- -->
					<input type="hidden" name="ototalPrice" id="ototalPrice"
						value="${sumPrice}" /> <input type="hidden" name="ototalPoint"
						value="${sumPoint}" /> <input type="hidden" name="midx_fk"
						value="${loginUser.idx}">
					<!-- hidden으로 주문총액과 총포인트, 주문자 회원번호를 넘기자-------------------- -->

					<!-- 마일리지 사용 부가결제 금액------------------------------------------------ -->
					<!-- 마일리지 사용 부가결제금액 -->
					<div id="pointInfo" style="margin-top: 20px;">
						<table class="table">
							<!-- 적립금 -->
							<tbody>
								<tr>
									<th scope="row" width="150px">사용가능 POINT</th>
									<td style="padding-left: 10px;">
										<p>
											<input name="opointuse" id="opointuse" type="text" dir="rtl"
												size="10" oninput="chkPoint(this,'${mileage}')" value="0">
											원 (사용가능 POINT : <span style="font-weight: bold; color: red">${mileage}점&nbsp;</span>
											<input type="checkbox" id="chkAll"
												onclick="useAllPoint('${mileage}', '${sumPrice}')">&nbsp;전부사용하기)
											<input type="button" value="사용하기"
												onclick="calcToPrice(opointuse.value,'${sumPrice}')">
										</p>
										<p>
											포인트는 최소 1점 이상일 때 사용이 가능합니다. 1회 구매시 포인트 최대 사용점수는 제한이 없습니다.
											 <br> 포인트로만 결제할 경우, 결제금액이 0으로 보여지는 것은 정상이며 <b>[결제하기]</b>
											버튼을 누르면 주문이 완료됩니다.<br> 포인트 사용 시 배송비는 포인트로 사용 할 수 없습니다.
										</p>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- -------------------------------------------------------------------------- -->
					<table class="table">
						<tr>
							<th width="20%" class="text-center">배송지 정보</th>
							<th width="80%">
							<input type="radio" name="info"
								id="uinfo1" value="1" checked>주문자 정보와 동일 &nbsp;&nbsp;&nbsp;<input
								type="radio" name="info" id="uinfo2" value="2">새로운 수령자
								정보
							</th>
						</tr>
						<tr>
							<td class="text-center"><b>주문자</b></td>
							<td class="text-left">${loginUser.name} [${loginUser.userid}]</td>
						</tr>
						<tr>
							<td class="text-center"><b>수령자</b></td>
							<td class="text-left">
							<div class="col-md-3" style="padding-left:0">
							<input type="text" name="receiver.name" value="${loginUser.name}" class="form-control">
							</div>
							</td>
						</tr>
						<tr>
							<td class="text-center"><b>연락처</b></td>
							<td class="text-left"><input type="text" name="receiver.hp1"
								value="${loginUser.hp1}" size="3" maxlength="3" class="text-center"/> - <input
								type="text" name="receiver.hp2" value="${loginUser.hp2}"
								size="4" maxlength="4" class="text-center"/>- <input type="text"
								name="receiver.hp3" value="${loginUser.hp3}" size="4"
								maxlength="4" class="text-center"/></td>
						</tr>

						<tr>
							<td class="text-center"><b>우편번호</b></td>
							<td class="text-left">
							<div class="col-md-3" style="padding-left:0">
									<input type="text" name="receiver.post"
										value="${loginUser.post}" class="form-control" maxlength="5" />
							</div>
							</td>
						</tr>
						<tr>
							<td class="text-center"><b>주소</b></td>
							<td class="text-left"><input type="text"
								name="receiver.addr1" value="${loginUser.addr1}"
								class="form-control" /></td>
						</tr>
						<tr>
							<td class="text-center"><b>나머지 주소</b></td>
							<td class="text-left"><input type="text"
								name="receiver.addr2" value="${loginUser.addr2}"
								class="form-control" /></td>
						</tr>
						<!-- 결제수단 -->
						<tr>
							<td class="text-center" rowspan="2"><b>결제방법</b></td>
							<td class="text-left">
							<input type="radio" name="payMethod" checked value="100" onclick="showSelect(this.value)">무통장입금
							&nbsp;&nbsp;&nbsp;<input type="radio" name="payMethod" value="200" onclick="showSelect(this.value)">카드 결제 
							
							</td>
						</tr>
						<tr>
							
							<td class="text-left">
							<span id="c1">
								<select name="bank" id="bank">
									<option value="1">국민 [123-4567-890 (주)노트몰]</option>
									<option value="2">우리 [1234-56-7890 (주)노트몰]</option>
									<option value="3">신한 [12-345-67890 (주)노트몰]</option>
								</select>
							</span> 
							<span id="c2" style="display: none"> <select name="card" id="card">
									<option value="1">국민카드 (일반결제)</option>
									<option value="2">BC카드 (ISP)</option>
									<option value="3">현대카드 (일반결제)</option>
									<option value="4">농협카드 (일반결제)</option>
								</select>
							</span>
							</td>
						</tr>

						<tr>
							<td colspan="2" class="text-center">
								<button type="submit" class="btn amado-btn">결제하기</button>
								</td>
						</tr>

					</table>

				</form>

			</div>
		</div>
	</div>
</div>

</div>
<!-- ##### Main Content Wrapper End ##### -->


<!-- ============================ -->
<script type="text/javascript">
	$(function() {
		$('#card').prop("disabled", true);
		$('#bank').prop("disabled", false);
	});

	function chkPoint(input, mileage) {
		var usePoint = parseInt(input.value);
		console.log(usePoint);
		if (usePoint > mileage) {
			alert('사용가능한 적립금보다 많습니다.\n초기화 합니다');
			input.value = '0';
		}
	}//-------------------------------
	function useAllPoint(mileage, sumPrice) {
		if ($('#chkAll').is(':checked')) {
			$('#opointuse').val(mileage);
			calcToPrice(mileage, sumPrice);
		} else {
			$('#opointuse').val('0');
			calcToPrice(0, sumPrice);
		}
	}//-------------------------------
	function calcToPrice(usePoint, sumPrice) {
		var total = parseInt(sumPrice) - parseInt(usePoint);
		//alert(total);
		$('#total').html(addComma(total));
		$('#ototalPrice').val(total);
		//서버에 총주문금액 전송하기 위해 hidden필드에 값을 넣어줌.
	}
	function addComma(num) {
		var regexp = /\B(?=(\d{3})+(?!\d))/g;
		return num.toString().replace(regexp, ',');
	}

	function showSelect(pay) {
		//alert(pay);

		if (pay == '100') {//무통장 입금
			$('#c1').show();
			$('#c2').hide();
			$('#bank').prop("disabled", false);//은행이 서버로 넘어가도록
			$('#card').prop("disabled", true);
		} else if (pay == '200') {
			//카드결제
			$('#c1').hide();
			$('#c2').show();
			$('#card').prop("disabled", false);//카드가 서버로 넘어가도록
			$('#bank').prop("disabled", true);
		}
	}

	$(function() {
		$('#uinfo1').click(function() {
			custF['receiver.name'].value = "${loginUser.name}";
			custF['receiver.hp1'].value = "${loginUser.hp1}";
			custF['receiver.hp2'].value = "${loginUser.hp2}";
			custF['receiver.hp3'].value = "${loginUser.hp3}";
			custF['receiver.post'].value = "${loginUser.post}";
			custF['receiver.addr1'].value = "${loginUser.addr1}";
			custF['receiver.addr2'].value = "${loginUser.addr2}";
		});

		$('#uinfo2').click(function() {
			custF['receiver.name'].value = "";
			custF['receiver.hp1'].value = "";
			custF['receiver.hp2'].value = "";
			custF['receiver.hp3'].value = "";
			custF['receiver.post'].value = "";
			custF['receiver.addr1'].value = "";
			custF['receiver.addr2'].value = "";
		});
	});
</script>

<c:import url="/foot" />




