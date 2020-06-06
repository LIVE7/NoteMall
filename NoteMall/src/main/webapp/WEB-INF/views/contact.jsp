<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- top -->
<c:import url="/top"/>  

<!-- 여기에 추가 -->
<div class="single-product-area section-padding-100 clearfix">
       <div class="container-fluid">
            <div class="row">
                <!-- Widget Title -->
            	<div class="col-md-12">
                <h6 class="widget-title mb-30">Contact</h6>
                </div>
                <div class="col-md-10 offset-md-1">
                	<div id="map_canvas"
					style="width: 100%; height: 400px; margin: 0px;"></div>
					<br>
					<div class="row" >
					<div class="col-md-6">
					NoteMall은 경기도 고양시 일산서구에 위치해 있습니다.
					<br>
					영업시간은 평일 오전 10시부터 오후 5시까지 이며,
					<br>
					점심시간은 오후 12시 부터 1시까지 입니다.
					<br>
					토요일, 일요일, 공휴일은 쉽니다.
					<br>
					감사합니다.
					</div>
					<div class="col-md-2">
						
						<img
							src="${pageContext.request.contextPath}/img/core-img/me.jpg"
							class="rounded-circle img-fluid">
					</div>
					<div class="col-md-4">
						<h4 class="text-left">최 원 영</h4>
						<span class="text-left text-success">010 - **** - ****</span><br>
						<span class="text-left text-primary">androidapk@naver.com</span><br>
						<span class="text-left">
							(주)노트몰을 이용해 주셔서 감사합니다. <br>좋은 제품 항상 정직한 가격으로 드리겠습니다.
						</span>
					</div>
					</div>
                </div>
            </div>
        </div>
</div>

</div>
<!-- ##### Main Content Wrapper End ##### -->

<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCKsUUTbcO8B_OqKncUxmnpnpZ3QnrSttg&callback=initMap"
	async defer></script>

<script>
	function initialize() {
		var myLatlng = new google.maps.LatLng(37.673328, 126.753973);
		var mapOptions = {
			zoom : 14,
			center : myLatlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}
		var map = new google.maps.Map(document.getElementById('map_canvas'),
				mapOptions);
		var marker = new google.maps.Marker({
			position : myLatlng,
			map : map,
			title : "map"
		});
	}
	window.onload = initialize;
</script>

<!-- foot -->
<c:import url="/foot"/>