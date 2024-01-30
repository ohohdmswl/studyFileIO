<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/egovframework/example/board/header.jsp"%>

	<!-- 게시글 form -->
	<form id="boardWriteForm" method="post" enctype="multipart/form-data">
		<input type="hidden" id="board_no" name="board_no" value="0" /> 
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
			<button type="button" class="saveBtn" onclick="javascript:fnWrite();">저 장</button>
			<button type="button" class="cancelBtn" onclick="javascript:fnList();">목 록</button>
		</div>
	</form>
	<!-- //게시글 form -->

	</div>
	<!-- //flex -->
</div>
<!-- //cont -->

</body>

<script>
/*
$("#board_file").on('change',function(){
	  var fileName = $("#board_file").val();
	  
	  var selectedFiles = [];
	  
	  function fileUpload() {
		console.log()
	}
	  
	  
	  
	});
*/

var selectedFiles = [];

function fileUpload(files) {
    console.log("확인 : " + files);

    var fileList = document.getElementById('fileList');
    alert("개수확인 : " + files.length);
    
    for (var i = 0; i < files.length; i++) {
        selectedFiles.push(files[i]);
        console.log('File ' + (i + 1) + ' : ', files[i].name, files[i].size, files[i].type);
        
        var html = "<div id='item"+i+"' class='fileitems'>";
		html += "<span>"+files[i].name + "</span>";
		html += "<button class='fileDelBtn' type='button' onclick='fndelFile(" + i + ")'>삭제</button>";
		html += "</div>"
        console.log("지금 selectedFiles 확인 : " + selectedFiles);

		$("#fileList").append(html);
        console.log("지금 fileList 확인 : " + fileList);
    }
}

function fndelFile(fileIndex) {
	console.log("삭제될 파일 인덱스 : " + fileIndex);
	
	$("#item" + fileIndex ).remove();
	
    var dataTransfer = new DataTransfer();

    
    var fileInput = document.getElementById('board_file'); // 여기서 'fileInput'은 실제 사용하는 ID로 변경 필요
    console.log(fileInput.files);
    var files = fileInput.files

    var fileArray = Array.from(files); // 변수에 할당된 파일을 배열로 변환(FileList -> Array)
    console.log(fileArray);

    fileArray.splice(fileIndex, 1); // 해당하는 index의 파일을 배열에서 제거 (제거하면서 인덱스가 바뀌어서 이런가본데)

    fileArray.forEach(function (file) {
        dataTransfer.items.add(file);
    });
    // 남은 배열을 dataTransfer로 처리(Array -> FileList)

    $('#board_file')[0].files = dataTransfer.files; // 제거 처리된 FileList를 돌려줌
	
    console.log(dataTransfer.files);
    
}

function fnWrite(){
	
	/*
	if(confirm("저장 하시겠습니까?")){
		oEditors.getById["bbs_cn"].exec("UPDATE_CONTENTS_FIELD", []);
		
		<c:choose>
		<c:when test="${result.bbs_sn gt 0}">
		$("#boardWriteForm").attr({"action": "<c:out value='${pageContext.request.contextPath}/board/pagingExcelBoardWrite.do'/>"
			, "method": "post"}).submit();
		</c:when>
		<c:otherwise>
		$("#frm").attr({"action": "<c:out value='${pageContext.request.contextPath}/mng/bbsFaq/faqRegist.do?menuCode=${param.menuCode}'/>"
			, "method": "post"}).submit();
		</c:otherwise>
		</c:choose>
	}
	*/
	
	if(confirm("저장 하시겠습니까?")){
		$("#boardWriteForm").attr({"action": "<c:out value='${pageContext.request.contextPath}/board/pagingExcelBoardWrite.do'/>"
			, "method": "post"}).submit();
	}	
	
	
	
}




</script>



</html>