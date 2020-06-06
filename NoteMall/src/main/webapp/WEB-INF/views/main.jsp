<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<%
	//쿠키 꺼내기 => 쿠키의 키값이 "saveId"가 있다면 해당 value값(사용자아아디)
	//				 을 꺼내서 input name이 userid인 곳에 value값으로 넣어준다.

	Cookie cks[] = request.getCookies();
	String uid = "";
	boolean isChk = false;
	if (cks != null) {
		for (Cookie c : cks) {
			String key = c.getName();//key값
			if (key.equals("saveId")) {
				uid = c.getValue();
				isChk = true;
				break;
			}
		}
	}
%>

<script src="http://code.jquery.com/jquery-1.9.0.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<c:import url="/top" />



<c:import url="/prodPspec">
	<c:param name="pspec" value="NEW" />
</c:import>

<div id="layer-popup1"
	style="position: absolute;  z-index: 220; visibility: hidden;">
	<a href="javascript:changePage1();"><img src="${pageContext.request.contextPath}/img/about/about.png"
		class="img-thumbnail" width="640" height="427"></a>
	<div class="close-bar" align="right" style="background-color: white; border: 3px;">
		<form name="popform1" method="post" action="">
			<span class="left"><input type="checkbox" name="popup" />&nbsp;오늘
				하루 이 창을 열지 않음</span> <a href="javascript:closeLayer1();" title="닫기"
				class="right"><B>&nbsp;[닫기]&nbsp;</B></a>
		</form>
	</div>
</div>
<div id="layer-popup2"
	style="position: absolute;  z-index: 210; visibility: hidden;">
	<a href="javascript:changePage2();"><img
		src="${pageContext.request.contextPath}/img/event/totalevent.png" class="img-thumbnail" width="320"
		height="638"></a>
	<div class="close-bar" align="right" style="background-color: white; border: 3px;">
		<form name="popform2" method="post" action="">
			<span class="left"><input type="checkbox" name="popup" />&nbsp;오늘
				하루 이 창을 열지 않음</span> <a href="javascript:closeLayer2();" title="닫기"
				class="right"><B>&nbsp;[닫기]&nbsp;</B></a>
		</form>
	</div>
</div>
<div id="layer-popup3"
	style="position: absolute;  z-index: 200; visibility: hidden;">
	<a href="javascript:changePage3();"><img
		src="${pageContext.request.contextPath}/img/notice/notice.png" class="img-thumbnail" width="640"
		height="427"></a>
	<div class="close-bar" align="right" style="background-color: white; border: 3px;">
		<form name="popform3" method="post" action="">
			<span class="left"><input type="checkbox" name="popup" />&nbsp;오늘
				하루 이 창을 열지 않음</span> <a href="javascript:closeLayer3();" title="닫기"
				class="right"><B>&nbsp;[닫기]&nbsp;</B></a>
		</form>
	</div>
</div>
<div id="layer-popup4"
	style="position: absolute;  z-index: 250; visibility: hidden;">
	<a href="javascript:changePage4();"><img
		src="${pageContext.request.contextPath}/img/notice/warning.png" class="img-thumbnail" width="640"
		height="480"></a>
	<div class="close-bar" align="right" style="background-color: white; border: 3px;">
		<form name="popform4" method="post" action="">
			<span class="left"><input type="checkbox" name="popup" />&nbsp;오늘
				하루 이 창을 열지 않음</span> <a href="javascript:closeLayer4();" title="닫기"
				class="right"><B>&nbsp;[닫기]&nbsp;</B></a>
		</form>
	</div>
</div>


</div>
<!-- ##### Main Content Wrapper End ##### -->
<!-- /row -->

<script src="http://code.jquery.com/jquery-1.9.0.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<script>
//Popup
if(getCookie("popup_check1") != "done" ){  // popup_check가 설정안되어있으면 보여주기
     document.getElementById("layer-popup1").style.visibility = "visible"
     $("#layer-popup1").draggable();
}
if(getCookie("popup_check2") != "done" ){  // popup_check가 설정안되어있으면 보여주기
    document.getElementById("layer-popup2").style.visibility = "visible"
    $("#layer-popup2").draggable();
}
if(getCookie("popup_check3") != "done" ){  // popup_check가 설정안되어있으면 보여주기
    document.getElementById("layer-popup3").style.visibility = "visible"
    $("#layer-popup3").draggable();
}
if(getCookie("popup_check4") != "done" ){  // popup_check가 설정안되어있으면 보여주기
    document.getElementById("layer-popup4").style.visibility = "visible"
    $("#layer-popup4").draggable();
}

function getCookie( name ) //저장된 쿠키구하기
 { 
     var nameOfCookie = name + "="; 
     var x = 0; 
     while ( x <= document.cookie.length ) 
     { 
         var y = (x+nameOfCookie.length); 
         if ( document.cookie.substring( x, y ) == nameOfCookie ) 

         { 
             if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 ) 
                 endOfCookie = document.cookie.length;
             return unescape( document.cookie.substring( y, endOfCookie ) ); 
         } 
         x = document.cookie.indexOf( " ", x ) + 1; 
         if ( x == 0 ) 
             break; 
     } 
     return ""; 
 }

 function setCookie( name, value, expiredays ){ //쿠키 설정
      var todayDate = new Date();
      todayDate.setDate( todayDate.getDate() + expiredays );
      document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString()  + ";"
      //document.cookie = name + "=" + value + "; path=/; expires=" + todayDate.toGMTString() + ";" 
     }

 function closeLayer1(){ // 오늘하루보기가 체크 유무에 따른 쿠기 설정
     if (document.popform1.popup.checked){
    	setCookie("popup_check1", "done" ,1);} 
     	$("div#layer-popup1").hide(); 
 }
 function closeLayer2(){ // 오늘하루보기가 체크 유무에 따른 쿠기 설정
     if (document.popform2.popup.checked){
    	setCookie("popup_check2", "done" ,1);} 
     	$("div#layer-popup2").hide(); 
 }
 function closeLayer3(){ // 오늘하루보기가 체크 유무에 따른 쿠기 설정
     if (document.popform3.popup.checked){
    	setCookie("popup_check3", "done" ,1);} 
     	$("div#layer-popup3").hide(); 
 }
 function closeLayer4(){ // 오늘하루보기가 체크 유무에 따른 쿠기 설정
     if (document.popform4.popup.checked){
    	setCookie("popup_check4", "done" ,1);} 
     	$("div#layer-popup4").hide(); 
 }



 function changePage1() {  //만약 팝업1 바로 눌렀을때           
     location.href="about";
     $("div#layer-popup1").hide(); 
 }
 function changePage2() {  //만약 팝업2 바로 눌렀을때           
     location.href="event";
     $("div#layer-popup2").hide(); 
 }
 function changePage3() {  //만약 팝업3 바로 눌렀을때           
     location.href="notice";
     $("div#layer-popup3").hide(); 
 }
 function changePage4() {  //만약 팝업4 바로 눌렀을때           
     location.href="#";
     $("div#layer-popup4").hide(); 
 }

</script>




<c:import url="/foot" />