<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/egovframework/example/board/header.jsp"%>

	<!-- 게시글 form -->
	<form id="boardDetailForm" method="post" enctype="multipart/form-data">
		<!-- 제목 -->
		<div id="titleDiv">
			<span><c:out value="${pagingExcelInfo.board_title }" /></span>
		</div>
		<!-- 정보 -->
		<div id="extraDiv">
			<div class="extraData">
				<span>작성자</span>
				<span><c:out value="${pagingExcelInfo.board_writer }" /></span>
			</div>
			<div class="extraData">
				<span>작성일</span>
				<span><c:out value="${pagingExcelInfo.board_date }" /></span>
			</div>
			<div class="extraData">
				<span>조회수</span>
				<span><c:out value="${pagingExcelInfo.board_hit }" /></span>
			</div>
		
		</div>
		<!-- 본문-->
		<div id="contDiv">
			<textarea rows="" cols="" id="board_cont" name="board_cont"></textarea>
		</div>
		<!-- 첨부파일 -->
		<div id="filesDiv">
			<div id="fileTitle">
				<span>첨부파일</span>
				<label for="board_file" style="float: right;">csv업로드</label>
				<input type="file" id="board_file" name="board_file" multiple="multiple" onchange="fileUpload(this.files)"> 
			</div>
			
			<div id = "fileList">
			</div>
			
		</div>
		<!-- //filesDiv -->
		
		
		<!-- 버튼 -->
		<div>
			<button type="button" class="updateBtn" onclick="javascript:fnWrite();">수 정</button>
			<button type="button" class="cancelBtn" onclick="javascript:fnList();">목 록</button>
		</div>
	</form>
	<!-- //게시글 form -->


	</div>
	<!-- //flex -->
</div>
<!-- //cont -->


</body>
</html>