<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	//쿠키 꺼내기 => 쿠키의 키값이 "saveId"가 있다면 해당 value값(사용자아아디)
	//				 을 꺼내서 input name이 userid인 곳에 value값으로 넣어준다.
	
	Cookie cks [] = request.getCookies();
	String uid="";
	boolean isChk=false;
	if(cks!=null){
		for(Cookie c:cks){
			String key=c.getName();//key값
			if(key.equals("saveId")){
				uid=c.getValue();
				isChk=true;
				break;
			}
		}
	}
	
%>

<!-- Modal Dialog시작------------------------------- -->
<div class="modal" id="loginModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">로그인</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<!-- Modal Body -->
			<div class="modal-body">
				<form action="login" method="POST">
					<div class="form-group">
						<label for="_userid">아이디</label> <input type="text" id="_userid"
							name="userid" placeholder="User ID" 
							value="<%=uid%>"
							class="form-control" required>
					</div>
					<div class="form-group">
						<label for="_pwd">비밀번호</label> <input type="password"
							placeholder="Password" id="_pwd" name="pwd" class="form-control"
							required>
					</div>
					
			</div>
			<!-- Modal Footer -->
			<div class="modal-footer">
				<button class="btn btn-primary">Login</button>
				</form>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- Modal Dialog End-------------------------------  -->