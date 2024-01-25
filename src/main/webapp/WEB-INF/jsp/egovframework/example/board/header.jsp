<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2.0.5/FileSaver.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/exceljs/4.4.0/exceljs.min.js"></script>

<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/pagingExcel.css'/>"/>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

header

	<div id = "cont">
	<!-- flex -->
		<div id="imageDiv">
			<img src="${pageContext.request.contextPath}/images/egovframework/pagingExcel/vase.png" style="width: 4rem; height: 4rem;" />
		</div>
		<!-- //imageDiv -->
		
		<div id="flexDiv">
		
		<div id="menuDiv">
			<ul class="menuList">
				<li class="menuItem">
					<a class="menuA" href="/board/dashboardList.do">첫번째</a>
				</li>
				<li class="menuItem">
					<a class="menuA" href="/dash/dashboardSelect.do">두번째</a>	
				</li>
				<li class="menuItem">
					<a class="menuA" href="/board/boardView.do">세번째</a>
				</li>
			</ul>
		
		</div>
		<!-- //menuDiv -->

		

</body>
</html>