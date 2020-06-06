<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/top"/>    

<!-- Product Details Area Start -->
        <div class="single-product-area section-padding-100 clearfix">
            <div class="container-fluid">

                <div class="row">
			<div class="col-md-10 offset-md-1 table-responsive">
				<h1>${loginUser.name} [${loginUser.userid}] 님의 장바구니</h1>
				<!-- 주문 폼 시작--------- -->
				<form name="orderF" id="orderF">
				<table class="table table-bordered">
					<tr class="info">
						<th class="text-center">상품번호</th>
						<th class="text-center">상품명</th>
						<th class="text-center">수량</th>
						<th class="text-center">단가</th>
						<th class="text-center">금액</th>
						<th class="text-center">삭제</th>
					</tr>
					<!-- ---------------- 
					변수 p는 CartVO객체를 참조
					-->
					<c:forEach var="p" items="${cartList}" varStatus="st">
					<tr>
						<td>
						<input type="checkbox" name="opnum" value="${p.pnum}">
						${p.pnum}
						</td>
						<td>
						<img width="160px"
						  class="img img-responsive"
						 src="../img/notebook/${p.pimage}">
						<br>
						<p>${p.pname}</p>
						</td>
						<td>
						<!-- oqty1, oqty1, ... index가 0부터 붙는다. -->
						<input type="number" name="oqty"
							id="oqty${st.index}"
							size="3"
						 value="${p.oqty}" min="0" max="10" class="text-center">개&nbsp;
						<button type="button"
							onclick="edit('${p.cartNum}','${st.index}')"
						 class="bbtn btn-dark">수정</button> 
						</td>
						<td class="text-right">
				<fmt:formatNumber value="${p.saleprice}"
				 pattern="#,###"/>원<br>
				 <span class="text-primary">
				 <fmt:formatNumber value="${p.point}"
				 pattern="#,###"/> POINT</span>
						</td>
				<td class="text-right">
				<b>
				<fmt:formatNumber value="${p.totalPrice}"
				 pattern="#,###"/>원<br>
				 <span class="text-success">
				 <fmt:formatNumber value="${p.totalPoint}"
				 pattern="#,###"/> POINT</span>
				</b>	
					</td>
					<td>
			<a href="javascript:del('${p.cartNum}')"
			class="btn btn-danger" role="button">삭제</a>
					</td>
					</tr>
					</c:forEach>
					<!-- ---------------- -->
					<tr>
						<td colspan="3" class="text-right">
						<b>장바구니 총액:</b>
						<span style="color:red">
					<fmt:formatNumber
					 value="${cartSum.cartTotalPrice}"
					 pattern="#,###"/>
						원
						</span><br>
						<b>적립예정 포인트:</b>
					<span class="label label-info"><fmt:formatNumber
					 value="${cartSum.cartTotalPoint}"
					 pattern="#,###"/></span> 점
						</td>
						<td colspan="3">
						<button type="button"
						 class="btn btn-warning"
						 onclick="goOrder()">주문하기</button>
						<button type="button"
							onclick="history.go(-2)"
						 class="btn btn-success">계속쇼핑</button> 
						
						</td>
					</tr>
					
				</table>
				
				
				
				</form>
				<!-- 주문 폼 끝------------ -->
				
			</div>
		
		</div>
	
	</div>
</div>
</div>
<!-- ##### Main Content Wrapper End ##### -->


<!-- 삭제 form 시작------------ -->
<form name="df" id="df" method="POST" action="cartDel">
	<input type="hidden" name="cartNum" id="cartNum">
</form>
<!-- -------------------------- -->

<!-- 수정 form 시작---------------- -->
<form name="ef" id="ef" method="POST" action="cartEdit">
	<input type="hidden" name="cartNum" id="edit_cartNum">
	<input type="hidden" name="oqty" id="editOqty">
</form>
<!-- -------------------------- -->
<script type="text/javascript">
	/*체크박스에 체크한 상품(상품번호,주문수량) 정보를
	가지고 주문 폼 페이지로 이동*/
	var goOrder=function(){
		//0. 장바구니에 담긴 상품이 없는 경우
		var chk=$('input[name="opnum"]');
		//alert(chk.length);//체크박스 갯수
		if(chk.length==0){
			//담긴 상품이 없다면
			alert('장바구니에 담긴 상품이 없어요');
			return;
		}
		//$.each(배열, 함수(i, 요소))
		//체크박스 갯수만큼 반복문 돌면서, 체크한 상품 정보 알아내기
		//체크가 안된 상품은 주문폼으로 전송되지 않도록 주문수량을 disabled되도록 
		//하자.
		var cnt=0;
		$.each(chk, function(i, ch){
			//alert(i+": "+$(ch).is(':checked'));
			if($(ch).is(':checked')){
				cnt++;//체크박스에 체크한 갯수 세기		
				$('#oqty'+i).prop('disabled',false);
				//oqty활성화
			}else{
				//체크 안된 상품의 주문수량을 비활성화한다.
				$('#oqty'+i).prop('disabled',true);
				//-readonly와는 다르다 readonly는 서버에 전송함
			}
		})
		
		//1. 체크박스에 체크를 안한 경우
		if(cnt==0){
			alert('주문할 상품을 체크하세요');
			$('input[name^=oqty]').prop('disabled',false);
			return;
		}
		
		//2. 체크한 상품만 주문 폼에 넘어가도록 처리
		$('#orderF').prop('action','orderSheet');
		$('#orderF').prop('method','POST');
		$('#orderF').submit();

		
	}//goOrder()----------------


	var del=function(cartNum){
		var yn=confirm('상품을 정말 삭제할까요?')
		if(yn){
			$('#cartNum').val(cartNum);
			$('#df').submit();
		}

	}//---------------------
	
	var edit=function(cartNum, index){
		//alert(cartNum+"/"+index);//-장바구니 번호와, 인덱스 번호
		//수정할 상품의 수량 알아내기
		var qty=$('#oqty'+index).val();
		//alert(qty);
		if(!qty){
			alert('수량을 입력하세요');
			$('#oqty'+index).focus();
			return;
		}
		$('#edit_cartNum').val(cartNum);
		$('#editOqty').val(qty);
		$('#ef').prop('method', 'POST');
		$('#ef').submit();
	
	}//---------------------
	
</script>

<c:import url="/foot"/>





