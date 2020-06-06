<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="/top" />

<script type="text/javascript">
	$(function() {
		$('#btnBoard').click(function() {
			var pw = $('input[name="pwd"]').val();
			if (!pw) {
				alert('비밀번호를 입력하세요.');
				$('input[name="pwd"]').pocus();
				return;
			}

			var md = $('#mode').val();
			if (md == '1') {
				//편집
				$('#pF').prop('action', 'updateBoard');
			} else if (md == '2') {
				//삭제
				$('#pF').prop('action', 'deleteBoard');
			}
			$('#pF').prop('method', 'POST');
			$('#pF').submit();
		})//click end---
	})//$() end
</script>


<div class="single-product-area section-padding-100 clearfix">
	<div class="section">
		<div class="container text-center">
			<h1>문의게시판</h1>
		</div>
	</div>
	<br>
	<div class="row">
		<div align="center" id="bbs" class="col-md-10 offset-md-1">
			<table class="text-center">
				<tr>
					<th>
						<h3 style="color: red;">${title}</h3> <br>
					</th>
				</tr>
				<tr>
					<td>

						<h4 class="text-center">게시글 비밀번호를 입력하세요.</h4>
						<form name="pF" id="pF" class="form-inline" action="">
							<input type="hidden" name="mode" id="mode" value="${mode}">
							<input type="hidden" name="idx" id="idx" value="${idx}">
							<!-- type="text"가 하나면 엔터를 눌렀을때 자동 submit되므로 안보이는 더미 input하나 생성 -->
							<input type="text" style="display: none;" />
					</td>
				</tr>
				<tr>
					<td><br> <input type="password" name="pwd"
						class="form-control" placeholder="Password"></td>
				</tr>
				<tr>
					<td><br>
						<button type="button" id="btnBoard" class="btn btn-primary">확인</button>
					</td>
				</tr>
				</form>
			</table>
		</div>
	</div>

</div>
</div>
<!-- ##### Main Content Wrapper End ##### -->
<c:import url="/foot" />