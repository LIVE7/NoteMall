<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- top -->
<c:import url="/top" />

<!-- 여기에 추가 -->
<div class="single-product-area section-padding-100 clearfix">
	<div class="section">
		<div class="container text-center">
			<h1>등록된 이메일 주소</h1>
		</div>
	</div>
	<div class="row">

		<div class="col-md-10 offset-md-1">
			<table
				class="table table-bordered table-hover table-striped text-center"
				id="table">
				<thead>
					<tr>
						<th width="15%" class="text-center">Index</th>
						<th width="70%" class="text-center">E-mail</th>
						<th width="15%" class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="emailArr" items="${emailArr}" varStatus="st">
						<tr>
							<td>${emailArr.idx}</td>
							<td>${emailArr.email}</td>
							<td><a href="emailDelete?idx=${emailArr.idx}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

</div>
<!-- ##### Main Content Wrapper End ##### -->

<!-- foot -->
<c:import url="/foot" />