<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/egovframework/example/board/header.jsp"%>

hello write

	<!-- 게시글 form -->
	<form id="boardWriteForm" method="post" enctype="multipart/form-data">
		<!-- 제목 -->
		<div id="titleDiv">
			<input type="text" id="board_title" name="board_title" placeholder="제목"/>
		</div>
		<!-- 본문-->
		<div id="contDiv">
			<textarea rows="" cols="" id="board_cont" name="board_cont"></textarea>
		</div>
		<!-- 첨부파일 -->
		<div id="filesDiv">
			<div id="fileTitle">
				<p>첨부파일</p>
			</div>
			
			<div id="filesListDiv">
				<div>
					<input type="file" id="files" name="files" > 
				</div>
			</div>
			<!-- //filesListDiv -->
		</div>
		<!-- //filesDiv -->
		
		
		<!-- 제목 -->
		<div>
		</div>
		<!-- 제목 -->
		<div>
		</div>
	</form>
	<!-- //게시글 form -->

	</div>
	<!-- //flex -->
</div>
<!-- //cont -->


</body>
</html>