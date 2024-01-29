<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/egovframework/example/board/header.jsp"%>

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
        
//         var item = document.createElement('div');
//         item.id= "files" + [i];
//         	div.id = 'ss'; // 아이디 추가div.classname = 'a'; // 클래스 추가
        
//         var fileName = document.createTextNode(files[i].name);
//         var deleteButton = document.createElement('button');
        
        
        var html = "<div id='item"+i+"' class='fileitems'>";
		html += "<span>"+files[i].name + "</span>";
		html += "<button class='fileDelBtn' type='button' onclick='fndelFile(" + i + ")'>삭제</button>";
		html += "</div>"
        
        
        
        
//         deleteButton.addEventListener('click', function (event) {
//             item.remove();
//             event.preventDefault();
//             deleteFile(files[i]);
//         });

        console.log("지금 selectedFiles 확인 : " + selectedFiles);
        
//         deleteButton.innerText = "X";
//         item.appendChild(fileName);
//         item.appendChild(deleteButton);
//         fileList.appendChild(item);

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

    fileArray.splice(fileIndex, 1); // 해당하는 index의 파일을 배열에서 제거

    fileArray.forEach(function (file) {
        dataTransfer.items.add(file);
    });
    // 남은 배열을 dataTransfer로 처리(Array -> FileList)

    $('#board_file')[0].files = dataTransfer.files; // 제거 처리된 FileList를 돌려줌
	
    
    console.log(dataTransfer.files);
    
}


//선택된 파일 목록에서 특정 파일을 삭제하고, 업데이트된 목록을 파일 입력 요소에 반영하여 파일 목록을 갱신
/*function deleteFile(deleteFile) {
    var inputFile = document.querySelector('input[name="board_file"]');
    console.log("d지금 inputFile 확인 : " + inputFile);

    
    var dataTransfer = new DataTransfer();
    
    //deleteFile과 일치하지 않는 파일들만을 남기고, 배열을 업데이트
    selectedFiles = selectedFiles.filter(function(file) {
        return file !== deleteFile;
    });
    console.log("d지금 selectedFiles 확인 : " + selectedFiles);

    //업데이트된  selectedFiles 배열을 돌며 각 파일을 dataTransfer에 추가
    selectedFiles.forEach(function(file) {
        dataTransfer.items.add(file);
    });
    console.log("d지금 dataTransfer.items 확인 : " + dataTransfer.items);

    //파일 입력 요소의 `files` 속성에 수정된 파일 목록을 할당하여 화면에 반영
    inputFile.files = dataTransfer.files;
    console.log("d지금 inputFile 확인 : " + inputFile.files);
}
*/

function deleteFile(fileNum) {
    var dataTransfer = new DataTransfer();

    var files = $('#file')[0].files; // 사용자가 입력한 파일을 변수에 할당

    var fileArray = Array.from(files); // 변수에 할당된 파일을 배열로 변환(FileList -> Array)

    fileArray.splice(fileNum, 1); // 해당하는 index의 파일을 배열에서 제거

    fileArray.forEach(function (file) {
        dataTransfer.items.add(file);
    });
    // 남은 배열을 dataTransfer로 처리(Array -> FileList)

    $('#file')[0].files = dataTransfer.files; // 제거 처리된 FileList를 돌려줌
}


</script>



</html>