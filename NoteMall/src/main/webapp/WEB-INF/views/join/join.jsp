<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Modal Dialog시작------------------------------- -->
<style type="text/css">
	.joinCheck {
		padding-top: 5px;
		color: red;
		font-size: 12px;
	}
</style>

<div class="modal" id="joinModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">회원가입</h4>&nbsp;<span style="color: red;"> * 표시는 필수항목입니다.</span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<!-- Modal Body -->
			<div class="modal-body">
				<form name="frm" action="join" method="post" onsubmit="return onSubmit()">
				<table class="table">
					<tr>
						<th width="25%" class="text-right"><label for="name"><span style="color: red;">* </span>이름</label></th>
						<td width="75%">
							<input type="text" name="name" id="name" placeholder="이름을 입력하세요" class="form-control">
							<span id="nameCheck" class="pull-right joinCheck"></span>
						</td>
					</tr>
					<tr>
						<th class="text-right"><label for="userid"><span style="color: red;">* </span>아이디</label></th>
						<td>
							<input type="text" name="userid" id="userid" placeholder="아이디를 입력하세요" class="form-control">
							<span id="idCheck" class="pull-right joinCheck"></span>
						</td>
					</tr>
					<tr>
						<th class="text-right"><label for="pwd"><span style="color: red;">* </span>비밀번호</label></th>
						<td>
							<input type="password" name="pwd" id="pwd" placeholder="비밀번호를 입력하세요" class="form-control">
							<span id="pwdCheck" class="pull-right joinCheck"></span>
						</td>
					</tr>
					<tr>
						<th class="text-right"><label for="hp1"><span style="color: red;">* </span>연락처</label></th>
						<td>
							<div>
								<input type="text" name="hp1" id="hp1" maxlength="3" style="width:10%" class="text-center">
								 - 
								<input type="text" name="hp2" id="hp2" maxlength="4" style="width:15%" class="text-center">
								 - 
								<input type="text" name="hp3" id="hp3" maxlength="4" style="width:15%" class="text-center">
								<span id="hpCheck" class="pull-right joinCheck"></span>
							</div>
						</td>
					</tr>
					<tr>
						<th class="text-right"><label for="post">우편번호</label></th>
						<td>
							<div class="col-md-8" style="padding-left:0">
								<input type="text" name="post" id="post" placeholder="우편번호를 입력하세요" maxlength="5" class="form-control">
							</div>
						</td>
					</tr>
					<tr>
						<th class="text-right"><label for="addr1">주소</label></th>
						<td>
							<input type="text" name="addr1" id="addr1" placeholder="읍, 면, 동까지 입력하세요" class="form-control"><br>
							<input type="text" name="addr2" id="addr2" placeholder="나머지 주소를 입력하세요" class="form-control">
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<iframe src="${pageContext.request.contextPath}/html/agree.html" width="100%" height="120px" style="border:1px solid silver"></iframe>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-right">
							<div class="checkbox">
								<label><input type="checkbox" name="agree" id="agree"><span style="color: red;"> * </span>이용약관에 동의합니다.</label>
								<span id="agreeCheck" class="pull-right joinCheck"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<button type="submit" class="btn btn-warning">회원가입</button>
							<button type="reset" class="btn btn-danger">다시쓰기</button>
						</td>
					</tr>
				</table>
			</form>	
			</div>
			<!-- Modal Footer -->
			
		</div>
	</div>
</div>
<!-- Modal Dialog End-------------------------------  -->

<script type="text/javascript">

var userid_regex = /^[a-zA-Z0-9]{4,20}$/;
var pwd_regex = /^[a-zA-Z0-9!@#$%^&*()]{4,20}$/;
var name_regex = /^[a-zA-Z가-힣]{2,15}$/;
var hp1_regex = /^[0-9]{3,3}$/;
var hp2_regex = /^[0-9]{3,4}$/;
var hp3_regex = /^[0-9]{4,4}$/;

/* ID 유효성 중복 검사 */
var idCheck = function () {
    var bool = false;
	var userid = $('#userid').val();

    if (!userid) {
    	$('#idCheck').text("아이디를 입력하세요.");
        $('#idCheck').css('color', 'red');
    }
    else if (!userid_regex.test(userid)) {
    	$('#idCheck').text("4~20자의 영문과 숫자로만 사용 가능합니다.");
        $('#idCheck').css('color', 'red');
    }
    else {
    	$.ajax({
            type: "GET",
            url: "/notemall/join/idCheck?userid=" + userid,
            dataType: "JSON",
            async: false,
            success: function (response) {
                var isExistID = response.isExistID;

                if (isExistID == 1) {
                    $('#idCheck').text("사용중인 아이디 입니다.");
                    $('#idCheck').css('color', 'red');
                } else if (isExistID == 0) {
                	$('#idCheck').text("사용 가능한 아이디 입니다.");
                    $('#idCheck').css('color', 'blue');
                    bool = true;
                }
            },
            error: function (error) {
                console.error(error);
            }
        });
    }
    return bool;
};

/* 비밀번호 유효성 검사 */
var pwdCheck = function () {
	var pwd = $('#pwd').val();
    
	if (!pwd) {
		$('#pwdCheck').text("비밀번호를 입력하세요.");
	}
	else if (!pwd_regex.test(pwd)) {
    	$('#pwdCheck').text("4~20자의 영문과 숫자 특수문자를 사용하세요.");
	}
	else {
		$('#pwdCheck').text("");
		return true;
	}
	return false;
};

/* 이름 유효성 검사 */
var nameCheck = function () {
    var name = $('#name').val();
    
	if (!name) {
    	$('#nameCheck').text("이름을 입력하세요.");
    }
	else if (!name_regex.test(name)) {
    	$('#nameCheck').text("2자 이상 한글과 영문 대 소문자를 사용하세요.");
	}
	else {
    	$('#nameCheck').text("");
    	return true;
	}
	return false;
};

/* 연락처 유효성 검사 */
var hpCheck = function () {
    var hp1 = $('#hp1').val();
    var hp2 = $('#hp2').val();
    var hp3 = $('#hp3').val();
    
	if (!hp1 || !hp2 || !hp3) {
    	$('#hpCheck').text("연락처를 입력하세요.");
    }
	else if (!hp1_regex.test(hp1) || !hp2_regex.test(hp2) ||!hp3_regex.test(hp3)) {
    	$('#hpCheck').text("제대로 입력하세요");
	}
	else {
    	$('#hpCheck').text("");
    	return true;
	}
	return false;
};

/* 이용약관 동의 검사 */
var agreeCheck = function () {
    var agree = $('#agree').is(':checked');
    
    if (agree) {
    	$('#agreeCheck').text("");
    	return true;
    } else {
    	$('#agreeCheck').text("NoteMall 이용약관에 동의해주세요.");
    	return false;
    }
}

$("#userid").blur(function () {
	let result = idCheck();
    console.log(result);
});

$("#pwd").blur(function () {
	let result = pwdCheck();
    console.log(result);
});

$("#name").blur(function () {
    let result = nameCheck();
    console.log(result);
});


var onSubmit = function() {
	if (idCheck() & pwdCheck() & nameCheck() & hpCheck() & agreeCheck()) {
        return true;
    } else {
    	return false;
    }
}
</script>