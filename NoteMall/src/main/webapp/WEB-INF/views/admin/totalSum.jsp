<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/top" />
<!--jqplot다운로드: http://www.jqplot.com  -->
<!--jqplot plugin 참조-------------------------------------  -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/jquery.jqplot.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/plugins/jqplot.barRenderer.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/plugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/plugins/jqplot.pieRenderer.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/plugins/jqplot.categoryAxisRenderer.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/plugins/jqplot.pointLabels.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/dist/jquery.jqplot.css" />
<!-- ----------------------------------------------------- -->
<script type="text/javascript">
$(document).ready(function(){
    var line1 = [['Nissan', 4],['Porche', 6],['Acura', 2],['Aston Martin', 5],['Rolls Royce', 6]];
    var line2 = [ ${chartData} ];
 
    $('#mychart').jqplot([line2], {
        title:'${oyear}년도 월별 매출 총액',
        seriesDefaults:{
            renderer:$.jqplot.BarRenderer,
            rendererOptions: {
                // Set the varyBarColor option to true to use different colors for each bar.
                // The default series colors are used.
                varyBarColor: true,//바 색상을 다양하게
                barWidth:30,//막대바 폭
                highlightMouseDown:true//마우스다운시 하이라이트 효과
            }
        },
        axes:{
            xaxis:{ // x축 (년도-월)
                renderer: $.jqplot.CategoryAxisRenderer
            },
        	yaxis:{ // y축 (매출액)
            	tickOptions:{
            		formatString:'%d만원',
            		fontSize: '8pt'
            	}
    	    }
        }
    });
});
</script>

<div class="single-product-area section-padding-100 clearfix">
	<div class="section">
		<div class="container text-center">
			<h1>연도별/월별 매출 통계 [ADMIN 모드]</h1>

		</div>
	</div>
	<div class="row">
		<div class="col-md-10 offset-md-1">
			<form name="sumF" id="sumF">
				<table class="table">
					<tr>
						<td width="25%" class="text-center" style="font-size: 30px;">
							연도</td>
						<td width="25%" class="text-center"><select name="oyear"
							onchange="submit()">
								<option value="">:::연도 선택:::</option>
								<c:forEach var="svo" items="${oyearSum}">
									<c:if test="${oyear eq svo.oyear }">
										<option selected value="${svo.oyear}">${svo.oyear}</option>
									</c:if>
									<c:if test="${oyear ne svo.oyear }">
										<option value="${svo.oyear}">${svo.oyear}</option>
									</c:if>
								</c:forEach>
						</select></td>
						<td width="50%" class="text-center">
							<button class="btn btn-info" style="font-weight: bold;">해당
								연도 월별 매출 통계 확인</button>
						</td>
					</tr>
					<tr>
						<td colspan="3"><c:forEach var="svo" items="${oyearSum}">
								<c:if test="${oyear eq svo.oyear }">
									<h2>${oyear}
										년도 매출 총액: <span class="text-danger"><b> <fmt:formatNumber
													value="${svo.osum}" pattern="#,###" /> 원
										</b></span>
									</h2>
								</c:if>
							</c:forEach></td>
					</tr>
				</table>
			</form>
			<!-- form end--------------------------- -->
		</div>
		<!--col  -->
	</div>
	<!--row  -->

	<!-- 월별 매출 통계 테이블 (그래프 형태로) -->
	<div class="section">
		<div class="row">
			<div class="col-md-10 offset-md-1">
				<table class="table table-condensed">
					<!-- --------------- -->
					<c:forEach var="svo" items="${omonthSum}" varStatus="st">
						<c:set var="barW" value="${svo.osum/100000}" />
						<tr>
							<td width="25%">${svo.omonth}월</td>
							<td width="75%">
								<%-- <div style="background-color: blue" width="${barW}px" height="10px"></div> --%>
								<img src="../img/core-img/bar${st.count%3+1}.png"
								style="height: 10px;" width="${barW}px" height="10px"> <span
								class="pull-right"><b> <fmt:formatNumber
											value="${svo.osum}" pattern="#,###" /> 원
								</b></span>
							</td>
						</tr>
					</c:forEach>
					<!------------------  -->
				</table>
			</div>
		</div>
	</div>

	<!--jqplot 플러그인을 이용해서 차트 보여주기-----------  -->
	<div class="section">
		<div class="row">
			<div class="col-md-10 offset-md-1">
				<div id="mychart">
					<!--아이디: mychart  -->
				</div>
				<div id="info"></div>
			</div>
		</div>
	</div>
</div>

</div>
<!-- ##### Main Content Wrapper End ##### -->

<c:import url="/foot" />







