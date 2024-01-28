<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/egovframework/example/board/header.jsp"%>


<!-- 	<div id = "cont"> -->
	<!-- flex -->
<!-- 		<div id="imageDiv"> -->
<%-- 			<img src="${pageContext.request.contextPath}/images/egovframework/pagingExcel/vase.png" style="width: 4rem; height: 4rem;" /> --%>
<!-- 		</div> -->
		<!-- //imageDiv -->
<!-- 	<div id="flexDiv"> -->
	
		
		<div id="pageTitle">
			<p class="pageTitle">HELLO</p>
		</div>
		<!-- pageTitle -->
		
		<div id="secondDiv">
			<div id="fileDiv">
				<form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data" accept-charset="UTF-8" method="post" action= "${pageContext.request.contextPath}/excelUploadAjax.do" >
					<input class="upload-name" value="첨부파일" placeholder="첨부파일" />
					<label for="excelFile">excel업로드</label>
					<input id="excelFile" type="file" name="excelFile" />
				</form>	
			</div>
			<div id="csvFileDiv">
				<form id="csvUploadForm" name="csvUploadForm" enctype="multipart/form-data" accept-charset="UTF-8" method="post" action= "${pageContext.request.contextPath}/csvUploadAjax.do">
					<input class="csvupload-name" value="첨부파일" placeholder="첨부파일" />
					<label for="csvFile">csv업로드</label>
					<input id="csvFile" type="file" name="csvFile" />
				</form>	
			</div>
		</div>		
		<!-- //secondDiv -->
		
		<!-- thirdDiv -->
		<div id="thirdDiv">
			<div id="fileUpDiv">
<%-- 				<form id="fileUpDownForm" name="fileUpDownForm" enctype="multipart/form-data" accept-charset="UTF-8" method="post" action= "${pageContext.request.contextPath}/uploadUpDownAjax1.do"> --%>
				<form id="fileUpDownForm" name="fileUpDownForm" enctype="multipart/form-data" accept-charset="UTF-8" method="post" action= "${pageContext.request.contextPath}/uploadUpDownAjax2.do">
					<input class="fileUpDown-name" value="첨부파일" placeholder="첨부파일" />
					<label for="fileUpDown">csv업로드(1)</label>
					<input id="fileUpDown" type="file" name="fileUpDown" multiple/>
				</form>	
			</div>
			<div id = "fileDownDiv">
				<form id="fileDownForm" name="fileDownForm" enctype="multipart/form-data" accept-charset="UTF-8" method="post" action= "${pageContext.request.contextPath}/selectFileDownload.do">
					<select id="file_group" name="file_group">
						<option value="" >파일구분</option>
					</select>
					<select id="file_no" name="file_no">
						<option value="" >파일상세</option>
					</select>
<!-- 					<div class="downBTN" onclick="javascript:selectFileDownload();">다운로드</div> -->
<!-- 					<div class="downBTN" onclick="javascript:selectFileDownloadAjax();">다운로드</div> -->
					<div class="downBTN" id="downBTN">다운로드</div>
				</form>	
			</div>
		</div>		
		<!-- //thirdDiv -->
		
		<!-- fourthDiv -->
		<div id="fourthDiv">
			<div id="fileUpCsvDiv">
<%-- 				<form id="fileUpDownCsvForm" name="fileUpDownCsvForm" enctype="multipart/form-data" accept-charset="UTF-8" method="post" action= "${pageContext.request.contextPath}/uploadUpDownCsv.do"> --%>
				<form id="fileUpDownCsvForm" name="fileUpDownCsvForm" enctype="multipart/form-data" accept-charset="UTF-8" method="post" action= "${pageContext.request.contextPath}/uploadUpDownCsv.do">
					<input class="fileUpDownCsv-name" value="첨부파일" placeholder="첨부파일" />
					<label for="fileUpDownCsv">csv업로드</label>
					<input id="fileUpDownCsv" type="file" name="fileUpDownCsv" multiple/>
				</form>	
			</div>
			<div id = "fileDownCsvDiv">
				<form id="fileDownCsvForm" name="fileDownCsvForm" enctype="multipart/form-data" accept-charset="UTF-8" method="post" action= "${pageContext.request.contextPath}/selectFileDownloadCsv.do">
					<select id="csvfile_group" name="csvfile_group" class = "firstSelect">
						<option value="" >파일구분</option>
					</select>
					<select id="csvfile_no" name="csvfile_no" class="secondSelect">
						<option value="" >파일상세</option>
					</select>
<!-- 					<div class="downBTN" onclick="javascript:selectCsvFileDownload();">다운로드</div> -->
<!-- 					<div class="downBTN" onclick="javascript:selectCsvFileDownloadAjax();">다운로드</div> -->
					<div class="downCsvBTN" id="downCsvBTN">다운로드</div>
				</form>	
			</div>
		</div>		
		<!-- //fourthDiv -->
		
		
		
		<form id = "boardForm" name="boardForm" method="post">
			<input type="hidden" id="pageIndex" name="pageIndex" value="" /> 
			<input type="hidden" id="board_no" name="board_no" value="" /> 
<!-- 			<input type="hidden" id="pageUnit" name="pageUnit" value="" />  -->
		
			<!-- fivethDiv -->
			<div id="fivethDiv">
				<div id="tb">
					<table class="" id="boardTb" >
						<thead class="thead_green" >
							<tr>
								<th class="w10">번호</th>
								<th class="w50">제목</th>
								<th class="w10">작성자</th>
								<th class="w20">작성일</th>
								<th class="w10">조회수</th>
							</tr>
						</thead>
						<tbody >
						</tbody>
					</table>
				</div>
			</div>
			<!-- //fivethDiv -->
			
			<div id="btnDiv">
				<div class='excelDownBtn' onclick=''><p>엑셀 다운로드</p></div>
				<div class='boardWrite' id="boardWriteBtn" onclick="fnWrite()"><p>글작성</p></div>
			</div>
			
			<!-- sixthDiv -->
			<div id="sixthDiv">
			</div>
			<!-- //sixthDiv -->
		</form>
		<!-- //boardForm -->
		
		
		<!-- 액샐 다운로드 모달 -->
		<div class="modal excelDownModal hidden" id="excelDownModal">
			
			<div class="modalTitle">
				<img id ="modalCloseBtn" class="modalClose" alt="" src="${pageContext.request.contextPath}/images/egovframework/pagingExcel/Xicon2.png">
				<span>엑셀 다운로드 💾</span>
			</div>
			<!-- 다운 버튼 모음 -->
			<div id ="downBtnAll">
				<div class="excelDown modalEd" onclick="excelDownload1()">
					<p>엑셀 다운로드1</p>
				</div>
				<div class="excelDown modalEd" onclick="excelDownload2()">
					<p>엑셀 다운로드2</p>
				</div>
				<div class="excelDown modalEd" onclick="excelDownload3()">
					<p>엑셀 다운로드3</p>
				</div>
				<div class="excelDown modalEd" onclick="excelDownload4()">
					<p>엑셀 다운로드4</p>
				</div>
				<div class="excelDown modalEd" onclick="excelDownload5()">
					<p>엑셀 다운로드5</p>
				</div>
			</div>
			<!-- //다운 버튼 모음 -->
			<button type="button" id="modalCloseBtn2" class="modalClose modalCloseBtn2">닫기</button>
			
		</div>
		<!-- //액샐 다운로드 모달 -->
		
	</div>
	<!-- //flex -->
</div>
<!-- //cont -->



</body>


<script>






$(document).ready(function(){
    
	//페이지네이션
	boardPage();
	
// 	selectfileList();
	
	//모달
	showhideModal();
	
	
});


/*모달*/
function showhideModal() {
	// 	e.preventDefault();	//Anchor 태그의 기본 동작(hash link)을 차단
	
	$('.excelDownBtn').click(function name() {
		$('#excelDownModal').removeClass('hidden');
		$('.overlay').removeClass('hidden');
	});
	
	$('.modalClose').click(function name() {
		$('#excelDownModal').addClass('hidden');
		$('.overlay').addClass('hidden');
	})
}




/*
 * 페이지네이션 bold + 테이블 내용 삽입
 */
function boardPage(pnum) {
	
	//pnum이 없으면 1페이지로 설정
	pnum = pnum || 1;
	
    //tb 내용 삽입
	$.ajax({
		url: "${pageContext.request.contextPath}/board/pagingExcelBoardLoad.do",
		method:"POST",
		data:{pageIndex : pnum},	//클릭한 페이지 인덱스
		async: false,
		success:function(result){
			
			//페이지번호(pnum) -> input hidden 설정
			$("#pageIndex").val(pnum);
			
			console.log("ajax 내용물 확인 : " + JSON.stringify(result));
			
			var html = "";
			for(var i=0;i<result.pageInfo.list.length;i++){
				html += '<tr>';
				html += '<td class="w10">'+result.pageInfo.list[i].rownum+'</td>';
				html += '<td class="w50"><a href="#" onclick="fnDetail(' + result.pageInfo.list[i].board_no + ');">' + result.pageInfo.list[i].board_title + '</a></td>';
				html += '<td class="w10">'+result.pageInfo.list[i].board_writer+'</td>';
				html += '<td class="w20">'+result.pageInfo.list[i].board_date+'</td>';
				html += '<td class="w10">'+result.pageInfo.list[i].board_hit+'</td>';
				html += '</tr>';
			}
			$("#boardTb tbody").html(html);
			
			var pagination = "";
			
			//엑셀 다운로드 버튼(게시글 하단)
// 			pagination += "<div class='excelDownBtn' onclick=''><p>엑셀 다운로드</p></div>";
			
			//페이지네이션 변수, div
			pagination += "<div class='paging_area pagination p1'  id='boardPagination'>";
			pagination += "<ul>"
			
			/* 첫번째 페이지 번호에서도 처음, 이전 보이게 하는 코드
			pagination +="<li><a href='#' onclick='boardPage(" + 1 +")'>처음</a></li>"
			pagination +="<li><a href='#' onclick='boardPage(" + result.pageInfo.prePage +")'>이전</a></li>"
			*/
			
			if(result.pageInfo.pageNum == 1){//첫번째 페이지일 때 처음, 이전 안보이게 하는 코드
				pagination +=""
			} else {//첫번째 페이지가 아닐 때
				pagination +="<li><a href='#' onclick='boardPage(1)'>처음</a></li>"
				pagination +="<li><a href='#' onclick='boardPage(" + result.pageInfo.prePage +")'>이전</a></li>"
			}
			
			
			// 페이지네이션 숫자 -> el로 못가져옴^^ result에서 가져와라
			// 페이징헬퍼에서 자동으로 계산해주는 페이지네이션 범위 값 : navigatepageNums
			for (var i = 0; i < result.pageInfo.navigatepageNums.length; i++) {
			    console.log("[페이지네이션 숫자 확인] : " + result.pageInfo.navigatepageNums[i]);
			    
			 	// pageNum : 현재 페이지
			 	// ajax로 받아온 현재페이지 값과 페이지네이션 범위 값의 값이 같을 때, 페이지네이션 css 위한 class 추가 
			 	// 현재페이지와 페이지네이션 범위 값의 값이 다를 때, 반복문 통해 페이지네이션 블록 개수만큼 값을 li로 표출
			    if (result.pageInfo.pageNum == result.pageInfo.navigatepageNums[i]) {	 
			        pagination += "<li><a href='#' onclick='boardPage(" + result.pageInfo.navigatepageNums[i] + ")' class='is-active'><strong>" + result.pageInfo.navigatepageNums[i] + "</strong></a></li>";
			    } else {
			        pagination += "<li><a href='#' onclick='boardPage(" + result.pageInfo.navigatepageNums[i] + ")'>" + result.pageInfo.navigatepageNums[i] + "</a></li>";
			    }
			}
			
			// >, >>
			/* 마지막 페이지 번호에서도 다음, 끝 보이게 하는 코드
			if(result.pageInfo.pageNum == result.pageInfo.pages){//현재 페이지가 마지막 페이지일 경우 nextPage값 == 0 => 마지막 페이지 다시 가져오도록 하기
				pagination +="<a href='#' onclick='boardPage(" + result.pageInfo.pages +")'><li>다음</li></a>"
			} else {//현재 페이지가 마지막 페이지 아닐 경우 다음 페이지 값 가져오도록 하기
				pagination +="<a href='#' onclick='boardPage(" + result.pageInfo.nextPage +")'><li>다음</li></a>"
			}
			*/
			
			/*마지막 페이지 번호에서 다음, 끝 안보이게 하는 코드 */
			if(result.pageInfo.pageNum == result.pageInfo.pages){//마지막 페이지일 때
				pagination +=""
			} else {//마지막 페이지가 아닐 때
				pagination +="<li><a href='#' onclick='boardPage(" + result.pageInfo.nextPage +")'>다음</a></li>"
				pagination +="<li><a href='#' onclick='boardPage(" + result.pageInfo.pages +")'>끝</li></a></li>"
			}
			
			//div 닫기			
			pagination += "</ul>"
			pagination += "</div>";
			
			
			
			$("#sixthDiv").html(pagination);
		}//success
	})
}


function fnDetail(board_no){
	$("#board_no").val(board_no);
	
	$("#boardForm").attr("enctype", "");
	$("#boardForm").attr({"action": "<c:out value='${pageContext.request.contextPath}/board/pagingExcelBoardDetail.do'/>", "method": "post"}).submit();
}

function fnWrite(){
	$("#boardForm").attr("enctype", "");
	$("#boardForm").attr({"action": "<c:out value='${pageContext.request.contextPath}/board/pagingExcelBoardWrite.do'/>", "method": "post"}).submit();
}


/**
 * 파일 그룹번호 콤보박스 list 조회
 */
function selectfileList() {
	$.ajax({
		url: "${pageContext.request.contextPath}/selectFileList.do",
		method:"POST",
		data:{},
		success:function(result){
			console.log("ajax 내용물 확인  selectfileList : " + JSON.stringify(result));
			
			for(var i=0; i<result.fileList.length; i++){
				var filegroup = result.fileList[i].file_group;
				$("#file_group").append("<option value='" + filegroup + "'>"+ filegroup +"</option>");
			}
		}//success
	})
}			

/**
 * 파일 상세 콤보박스 list 조회
 */
function selectFileDetailList(Gnum) {
	$.ajax({
		url: "${pageContext.request.contextPath}/selectFileDetailList.do",
		method:"POST",
		data:{file_group : Gnum},
		success:function(result){
			console.log("ajax 내용물 확인  selectfileList : " + JSON.stringify(result));
			
			for(var i=0; i<result.fileDList.length; i++){
				var filegroup = result.fileDList[i].file_group;
				var fileno = result.fileDList[i].file_no;
				var original_name = result.fileDList[i].original_name;
				$("#file_no").append("<option value='" + fileno + "'>"+ original_name +"</option>");
			}
		}//success
	})
}			
			
// 파일그룹번호 값 변경될 경우 이전 옵션 값 다 지움
// 파일그룹번호  null, 빈 값일 때 파일list 옵션 변경 
$("#file_group").on('change',function(){
	  var Gnum = $("#file_group").val();
	  $('#file_no option').remove();
	  
	  if(Gnum == null || Gnum == ''){
		  $("#file_no").append("<option value=''>"+ '파일상세 '+"</option>");
	  } else {
	  //파일 그룹넘버 선택 후 상세 콤보박스 표출 함수
	  selectFileDetailList(Gnum);
	  }
});

//submit해서 파일 다운로드 진행
//selectFileDownload.do
function selectFileDownload() {

	if( $('#file_group').val() == ''){
		alert("파일 구분을 선택해주세요");
	}
	
	groupNo = $('#file_group').val();
	fileNo = $('#file_no').val();
	console.log("[file_group, file_no 값 확인] : " + groupNo + "   " + fileNo);
	
	var formDown = $("form[name='fileDownForm']");
	formDown.submit();

} 


//jquery ajax로 blob로 응답받아 파일 다운로드 진행하면 IE에서 동작 오류 발생 or 용량 커지며 다운로드 되는 오류 발생(파일깨짐) ->xhr(XMLHttpRequest) 사용
//XMLHttpRequest의 responseType 을 blob으로 설정해야함
$("#downBTN").on('click',function(){
	
	if( $('#file_group').val() == ''){
		alert("파일 구분을 선택해주세요");
	}
	
	groupNo = $('#file_group').val();
	fileNo = $('#file_no').val();
	console.log("[file_group, file_no 값 확인] : " + groupNo + "   " + fileNo);

	var downUrl = "${pageContext.request.contextPath}/selectFileDownloadAjax.do";
	
	var request = new XMLHttpRequest();
    request.open('POST', downUrl, true);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    request.responseType = 'blob';
    
    request.onload = function(e) {

        var filename = "";
        var disposition = request.getResponseHeader('Content-Disposition');
        if (disposition && disposition.indexOf('attachment') !== -1) {
            var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
            var matches = filenameRegex.exec(disposition);
            if (matches != null && matches[1])
                filename = decodeURI( matches[1].replace(/['"]/g, '') );
        }
        console.log("FILENAME: " + filename);

        if (this.status === 200) {
        	
        	//정상 연결시 처리
            var blob = this.response;
            if(window.navigator.msSaveOrOpenBlob) {
                window.navigator.msSaveBlob(blob, filename);
            }
            else{
                var downloadLink = window.document.createElement('a');
                var contentTypeHeader = request.getResponseHeader("Content-Type");
                downloadLink.href = window.URL.createObjectURL(new Blob([blob], { type: contentTypeHeader }));	//ex) createObjectURL 확인 : blob:http://localhost:8088/c52be668-73f7-4ef7-b90a-aeb4f608d752
                downloadLink.download = filename;
                document.body.appendChild(downloadLink);
                downloadLink.click();
                document.body.removeChild(downloadLink);
            }
        } else {
        	//실패시 처리
        	alert("다운로드에 실패했습니다. HTTP 응답 상태 코드: " + this.status);
        }
    };

    //ajax 의  data : 와 같은 기능
    var params = 'file_group=' + groupNo + '&file_no=' + fileNo;
    request.send( params);

});//end
	
	
	
	
	
	
/*	
function selectFileDownload() {
	
	
	if( $('#fileGList').val() == ''){
		alert("파일 구분을 선택해주세요");
	}
	
	groupNo = $('#fileGList').val();
	fileNo = $('#fileList').val();
	
	console.log("값 확인 : " + groupNo + "   " + fileNo);
	
//	 $('#fileGList option').remove();
//	 $('#fileList option').remove();
	
	$.ajax({
		url: "${pageContext.request.contextPath}/selectFileDownload.do",
		method:"POST",
		data:{file_group : $("#fileGList").val(), file_no : $("#fileList").val()},
        xhrFields: {responseType: 'arraybuffer'},
		success:function(data){
			
			console.log("ajax 내용물 확인  selectfileList : " + JSON.stringify(data));
			

			
			
			
			
			

// 	location.href = "${pageContext.request.contextPath}/selectFileDownload.do?file_group=" + groupNo + "&file_no=" + fileNo;
			
		}//success
  	}).done(function(data, textStatus, jqXhr) {//ajax
	
	if (!data) {
        return;
    }
	
    // 동작 시도
    try {
        // 응답의 데이터를 바이너리로 만듦. 타입은 응답 헤더의 Content-Type 참조
        var blob = new Blob([data], { type: jqXhr.getResponseHeader('content-type') });
        // getFileName 함수에 Content-Type 헤더 값을 전달하여 파일명 추출
        var fileName = getFileName(jqXhr.getResponseHeader('content-disposition'));
        // URL 형태로 전달되었으므로, URI 디코딩 수행
        fileName = decodeURI(fileName);

        // IE 10일 경우 생성한 blob을 fileName의 이름으로 다운로드 수행
        if (window.navigator.msSaveOrOpenBlob) { // IE 10+
            window.navigator.msSaveOrOpenBlob(blob, fileName);
        }

        // IE를 제외한 브라우저일 경우
        else { // not IE
            // a 태그 동적 생성
            var link = document.createElement('a');
            // blob 바이너리 파일의 URL 경로 생성
            var url = window.URL.createObjectURL(blob);

            // 위에서 동적 생성한 a 태그의 URL에 blob 바이너리 파일 URL 기입
            link.href = url;
            // a 태그 클릭 시 새 창이 아닌 현재창에서 수행
            link.target = '_self';

            // 파일이름이 유효할 경우, 다운로드 이름을 파일이름으로 기본 지정
            if (fileName) link.download = fileName;

            // 문서에 설정한 a 태그 생성
            document.body.append(link);

            // a 태그 클릭 이벤트 (다운로드 실행)
            link.click();

            // a 태그 삭제
            link.remove();

            // 생성한 blob 파일의 경로 제거 (다시 요청해도 다운로드 불가)
            window.URL.revokeObjectURL(url);
        }
    } catch (e) {
        console.error(e)
    }
        });//done

}
	
function getFileName(contentDisposition) {
	var fileName = contentDisposition
	.splt(';')
	.filter(function(ele) {
		return ele.indexOf('filename') > -1
	})
	.map(function(ele){
		return ele
		.replace(/"/g, '')
		.split('=')[1]
	});
	return fileName[0] ? fileName[0] : null
}
	
	
	
// }	

*/




/**
 * map(순서보장X), 헤더 값 일일이 삽입해서 진행
 * excelJS 사용
 */
function excelDownload1() {
	
	alert("함수 실행 확인@@");

		$.ajax({
		url: "${pageContext.request.contextPath}/board/pagingExcelBoardDownload.do",
		method:"POST",
		data:{},
		success:function(result){
			console.log("ajax 엑셀 내용물 확인 : " + JSON.stringify(result));
			
			alert("날짜 데이터 재확인 : " + new Date(result.list[0]['board_date']));
			
			/*엑셀 다운로드 */
			//workbook 생성
			var workbook;
			workbook = new ExcelJS.Workbook();
				
			// 생성자
			workbook.creator = '작성자';
			// 최종 수정자
			workbook.lastModifiedBy = '최종 수정자';
			// 생성일(현재 일자로 처리)
			workbook.created = new Date();
			// 수정일(현재 일자로 처리)
			workbook.modified = new Date();
			
			//엑셀 시트 추가
			var SheetOne = workbook.addWorksheet('SheetOne');
			
			//작업할 엑셀 시트
			var worksheet =  workbook.getWorksheet ( 'SheetOne' ) ;
			
			//헤더 값 가져올 테입르 선택
			var thisTb = document.getElementById('boardTb');
			
			//헤더 값 가져오기
			var rowList = thisTb.rows[0];	//헤더(표의 0번째 줄)
			var rowListCnt= rowList.childElementCount	//컬럼 개수(헤더 컬럼 개수), 가져온 헤더의 자식요소 개수
			
			//헤더 배열 값, 헤더 값 반복문 통해 가져오기
			var headColumns = [];
			for (var j = 0; j < rowListCnt; j++){
				headColumns.push(rowList.cells[j].innerHTML);
			}
			console.log("헤더 배열 확인 : " + headColumns);
			
			//헤더 작성(addRow : 행추가)
			worksheet.addRow(headColumns);
			
			//본문 내용 가져오기
			//list 확인
			console.log("ajax 결과 사이즈 체크 : " + result.list.length);
			console.log("리스트 키 확인 : " + Object.keys(result.list[0]));	//rownum,board_title,board_date,board_writer,board_hit
			console.log("리스트 키 확인 : " + JSON.stringify(Object.keys(result.list[0])));	//["rownum","board_title","board_date","board_writer","board_hit"]
			
			var headColumnNm = [];
			headColumnNm.push(Object.keys(result.list[0]));	//rownum,board_title,board_date,board_writer,board_hit
			console.log("헤더컬럼 : " + headColumnNm);
			
			//본문 입력
			//result.list.length : 게시글 총 개수
			for(var c = 0; c < result.list.length; c++){
				
				//그냥 map 사용, 컬럼명 직접 삽입해서 추출(날짜 포멧 변환)
				//아래 dateFormat 함수로 날짜포맷 변경
				var numberToStr = dateFormat(result.list[c]['board_date']); 
				//아예 컬럼명 박제해서 값 가져올 수 있도록 하기
				worksheet.addRow([result.list[c]['rownum'], result.list[c]['board_title'], result.list[c]['board_writer'],  numberToStr, result.list[c]['board_hit'] ]);
			}
			
			//파일 생성([workbook.xlsx.writeBuffer()] 함수는 프로미스 반환 -> then() 사용 필요)
			workbook.xlsx.writeBuffer().then(function (fileData) {
			    var blob = new Blob([fileData], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

			    //'엑셀제목_2023-12-13.xlsx
			    /*날짜 포맷(참고 : https://gent.tistory.com/413)*/
			    var today = new Date();
			    var year = today.getFullYear();
			    var month = ('0' + (today.getMonth() + 1)).slice(-2);
			    var day = ('0' + today.getDate()).slice(-2);
			    var hours = ('0' + today.getHours()).slice(-2); 
			    var minutes = ('0' + today.getMinutes()).slice(-2);
			    var seconds = ('0' + today.getSeconds()).slice(-2); 
			    
			    var datetime = year + month  + day + '_' + hours + minutes  + seconds;
			    
			    saveAs(blob, '엑셀제목_' + datetime + '.xlsx');
				});
			}//success
		})//ajax	
}

/**
 * map(순서보장X), 컬럼명 배열로 받아서 진행, 컬럼명에 date있을 경우 포맷진행 
 * excelJS 사용
 */
function excelDownload2() {
	
	alert("함수 실행 확인2@@");
	
		$.ajax({
		url: "${pageContext.request.contextPath}/board/pagingExcelBoardDownload.do",
		method:"POST",
		data:{},
		success:function(result){
			console.log("ajax 엑셀 내용물 확인 : " + JSON.stringify(result));
			
			/*엑셀 다운로드 */
			//workbook 생성
			var workbook;
			workbook = new ExcelJS.Workbook();
				
			// 생성자
			workbook.creator = '작성자';
			// 최종 수정자
			workbook.lastModifiedBy = '최종 수정자';
			// 생성일(현재 일자로 처리)
			workbook.created = new Date();
			// 수정일(현재 일자로 처리)
			workbook.modified = new Date();
			
			//엑셀 시트 추가
			var SheetOne = workbook.addWorksheet('SheetOne');
			//작업할 엑셀 시트
			var worksheet =  workbook.getWorksheet ( 'SheetOne' ) ;
			
			var thisTb = document.getElementById('boardTb');
			
			//헤더 값 가져오기
			var rowList = thisTb.rows[0];	//헤더(표의 0번째 줄)
			var rowListCnt= rowList.childElementCount	//컬럼 개수(헤더 컬럼 개수)
			
			//헤더 배열 값
			var headColumns = [];
			for (var j = 0; j < rowListCnt; j++){
				headColumns.push(rowList.cells[j].innerHTML);
			}
			console.log("헤더 배열 확인 : " + headColumns);
			
			//헤더 입력
			worksheet.addRow(headColumns);
			
			//본문 내용 가져오기
			var headColumnNm = [];
			headColumnNm.push(Object.keys(result.list[0]));
			console.log("헤더컬럼 : " + headColumnNm);
			
			//컬럼명 배열로 받아서 추출하기
			var dbCol = ['rownum','board_title','board_writer','board_date','board_hit'];
			//'date'가 들어가는 요소의 인덱스 찾기
			var resultIndex = findIndex(dbCol, 'date');	//함수 결과 확인 : 3
			
			//본문 입력
			//c : 총 게시글 수, d : 컬럼 개수
			for(var c = 0; c < result.list.length; c++){
				var temp = [];
				for(var d = 0; d < dbCol.length; d++) {
					//각 컬럼명 반복문으로 찾기
					var colKey = dbCol[d];
					
					/*그냥 map 사용, 컬럼명 직접 삽입해서 추출(날짜 포멧 변환)*/
					//아래 dateFormat 함수로 날짜포맷 변경
					//ajax로 반환된 게시글 정보 list를 순서대로 접근하며 접근한 게시글 정보 중 컬럼이 resultIndex 인 것 -> 날짜 포맷 변경
					var numberToStr = dateFormat(result.list[c][dbCol[resultIndex]]); 
					
					if(d == resultIndex){	//d가 날짜 표출하는 컬럼일 경우 -> 날짜포맷 변경한 값 입력
						temp.push(numberToStr);
					} else {	//날짜 아닌 경우 컬럼 값 그대로 입력
						temp.push(result.list[c][colKey]);
					}
				}//innerfor
					worksheet.addRow(temp);
			}//outerfor
			
			//파일 생성([workbook.xlsx.writeBuffer()] 함수는 프로미스 반환 -> then() 사용 필요)
			workbook.xlsx.writeBuffer().then(function (fileData) {
			    var blob = new Blob([fileData], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

			    //'엑셀제목_2023-12-13.xlsx
			    /*날짜 포맷(참고 : https://gent.tistory.com/413)*/
			    var today = new Date();
			    var year = today.getFullYear();
			    var month = ('0' + (today.getMonth() + 1)).slice(-2);
			    var day = ('0' + today.getDate()).slice(-2);
			    var hours = ('0' + today.getHours()).slice(-2); 
			    var minutes = ('0' + today.getMinutes()).slice(-2);
			    var seconds = ('0' + today.getSeconds()).slice(-2); 
			    
			    var datetime = year + month  + day + '_' + hours + minutes  + seconds;
			    
			    saveAs(blob, '엑셀제목_' + datetime + '.xlsx');
			});
			}//success
		})//ajax	
}

/**
 * linkedhashmap(순서보장O), 컬럼 순서 for문으로 진행
 */
function excelDownload3() {
	
	alert("함수 실행 확인3@@");
	
		$.ajax({
		url: "${pageContext.request.contextPath}/board/pagingExcelBoardDownload3.do",
		method:"POST",
		data:{},
		success:function(result){
			
			console.log("ajax 엑셀 내용물 확인 : " + JSON.stringify(result));
			
			/*엑셀 다운로드 */
			//workbook 생성
			var workbook;
			workbook = new ExcelJS.Workbook();
				
			// 생성자
			workbook.creator = '작성자';
			// 최종 수정자
			workbook.lastModifiedBy = '최종 수정자';
			// 생성일(현재 일자로 처리)
			workbook.created = new Date();
			// 수정일(현재 일자로 처리)
			workbook.modified = new Date();
			
			//엑셀 시트 추가
			var SheetOne = workbook.addWorksheet('SheetOne');
			//작업할 엑셀 시트
			var worksheet =  workbook.getWorksheet ( 'SheetOne' ) ;
			
			//엑셀시트에 제목 작성(범위 -> 병합, 값, 폰트, 정렬, 셀배경, 테두리)
			SheetOne.mergeCells('A1:E1');
			SheetOne.getCell('A1').value = '자유게시판';
			SheetOne.getCell('A1').font = { size: 20, bold: true };
			SheetOne.getCell('A1').alignment = { horizontal: 'center' };
			SheetOne.getCell('A1').fill = {
			    type: 'pattern',
			    pattern: 'solid',
			    fgColor: { argb: 'FFFFFF00' },
			    bgColor: { argb: 'FF0000FF' },
			  };
			
			  // 테두리
			  SheetOne.getCell('A1').border = {//.master는 exceljs에서 셀 병합(머지)된 셀의 속성을 참조시 사용하는 속성(worksheet.getCell('A1').master)
			    top: { style: 'thin', color: { argb: '000000' } },
			    bottom: { style: 'thin', color: { argb: '000000' } },
			    left: { style: 'thin', color: { argb: '000000' } },
			    right: { style: 'thin', color: { argb: '000000' } },
			  };
			
			  //빈 행 삽입(제목 아래 빈 행 삽입)
			  SheetOne.spliceRows(2 , 0, []);
			  
			//화면에 표출한 테이블 요소
			var thisTb = document.getElementById('boardTb');
			
			//헤더 값 가져오기
			var rowList = thisTb.rows[0];	//헤더(표의 0번째 줄)
			var rowListCnt= rowList.childElementCount	//컬럼 개수(헤더 컬럼 개수)
			
			//헤더 배열 값
			var headColumns = [];
			for (var j = 0; j < rowListCnt; j++){
				headColumns.push(rowList.cells[j].innerHTML);
			}
			console.log("헤더 배열 확인 : " + headColumns);
			
			//헤더 입력
			worksheet.addRow(headColumns);
			
			//3번째줄 -> 헤더 값 입력시 셀 배경, 테두리, 폰트 설정
			worksheet.getRow(3).eachCell({ includeEmpty: true }, function (cell) { 
				cell.fill = {
							    type: 'pattern',
							    pattern: 'solid',
							    fgColor: { argb: 'FFFFFF00' } // 배경색 설정 
				  };
				cell.border = {
								top: {style:'thin'},
								left: {style:'thin'},
								bottom: {style:'thin'},
								right: {style:'thin'}
				};
				cell.font = {
								size: 12,
								bold: true
				};
			});
			
			//본문 내용 가져오기
			var headColumnNm = [];
			headColumnNm.push(Object.keys(result.list[0]));
			
			console.log("헤더컬럼 : " + headColumnNm);
			
			//본문 입력
			for(var c = 0; c < result.list.length; c++){
				var temp = [];
				for(var d = 0; d < rowListCnt; d++) {
						temp.push(result.list[c][Object.keys(result.list[c])[d]]);
				}//innerfor
					console.log("temp값 : " + temp);
				
					//각각의 셀 테두리 설정
					worksheet.addRow(temp).eachCell(function(cell) {
						cell.border = {
							top: {style:'thin'},
							left: {style:'thin'},
							bottom: {style:'thin'},
							right: {style:'thin'}
						};
						//셀 정렬 (가운데정렬)
						cell.alignment = { vertical: 'middle', horizontal: 'center' };
					})
			}//outerfor
			
			// 각 셀을 가운데 정렬로 설정
			worksheet.eachRow(function (row) {
			  row.eachCell(function (cell) {
			    cell.alignment = { horizontal: 'center', vertical: 'middle' };
			  });
			});
			
			//파일 생성([workbook.xlsx.writeBuffer()] 함수는 프로미스 반환 -> then() 사용 필요)
			workbook.xlsx.writeBuffer().then(function (fileData) {
			    var blob = new Blob([fileData], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

			    //'엑셀제목_2023-12-13.xlsx
			    /*날짜 포맷(참고 : https://gent.tistory.com/413)*/
			    var today = new Date();
			    var year = today.getFullYear();
			    var month = ('0' + (today.getMonth() + 1)).slice(-2);
			    var day = ('0' + today.getDate()).slice(-2);
			    var hours = ('0' + today.getHours()).slice(-2); 
			    var minutes = ('0' + today.getMinutes()).slice(-2);
			    var seconds = ('0' + today.getSeconds()).slice(-2); 
			    
			    var datetime = year + month  + day + '_' + hours + minutes  + seconds;
			    
			    saveAs(blob, '엑셀제목_' + datetime + '.xlsx');
			});
			}//success
		})//ajax	
}


/**
 * linkedhashmap(순서보장O), 컬럼 순서 for문으로 진행
 */
function excelDownload4() {
	
	alert("공통화@!@@@@");
	
		$.ajax({
		url: "${pageContext.request.contextPath}/board/pagingExcelBoardDownload3.do",
		method:"POST",
		data:{},
		success:function(result){
			console.log("ajax 엑셀 내용물 확인 : " + JSON.stringify(result));
			exportToExcel('boardTb', '자유게시판' ,result);
			}//success
		})//ajax	
}



/**
 * poi사용해보기
 */
function excelDownload5() {
	
	alert("poi사용 @@@");
	
	
	var thisTb = document.getElementById('boardTb');
	
	//헤더 값 가져오기
	var rowList = thisTb.rows[0];	//헤더(표의 0번째 줄)
	var rowListCnt= rowList.childElementCount	//컬럼 개수(헤더 컬럼 개수)
	
	//헤더 배열 값
	var headColumns = [];
	for (var j = 0; j < rowListCnt; j++){
		headColumns.push(rowList.cells[j].innerHTML);
	}
	console.log("헤더 배열 확인 : " + headColumns);
	
		$.ajax({
		url: "${pageContext.request.contextPath}/board/pagingExcelBoardDownloadPoi.do",
		method:"POST",
		data:{headColumns : headColumns},	//헤더값을 컨트롤러로 전달
		success:function(result){
// 			console.log("ajax 엑셀 내용물 확인 : " + JSON.stringify(result));
			}//success
		})//ajax	
}



//공통화하기
function exportToExcel(tbId, excelTitle, result) {
	
	/*엑셀 다운로드 */
	//workbook 생성
	var workbook;
	workbook = new ExcelJS.Workbook();
		
	// 생성자
	workbook.creator = '작성자';
	// 최종 수정자
	workbook.lastModifiedBy = '최종 수정자';
	// 생성일(현재 일자로 처리)
	workbook.created = new Date();
	// 수정일(현재 일자로 처리)
	workbook.modified = new Date();
	
	//엑셀 시트 추가
	var SheetOne = workbook.addWorksheet('SheetOne');
	//작업할 엑셀 시트
	var worksheet =  workbook.getWorksheet ( 'SheetOne' ) ;
	

	SheetOne.mergeCells('A1:E1');
	SheetOne.getCell('A1').value = excelTitle;
	SheetOne.getCell('A1').font = { size: 20, bold: true };
	SheetOne.getCell('A1').alignment = { horizontal: 'center' };
	SheetOne.getCell('A1').fill = {
	    type: 'pattern',
	    pattern: 'solid',
	    fgColor: { argb: 'FFFFFF00' },
	    bgColor: { argb: 'FF0000FF' },
	  };	
	
	// 테두리
	SheetOne.getCell('A1').border = {//.master는 exceljs에서 셀 병합(머지)된 셀의 속성을 참조시 사용하는 속성(worksheet.getCell('A1').master)
		top: { style: 'thin', color: { argb: '000000' } },
		bottom: { style: 'thin', color: { argb: '000000' } },
		left: { style: 'thin', color: { argb: '000000' } },
		right: { style: 'thin', color: { argb: '000000' } },
	};	

	//빈 행 삽입
	SheetOne.spliceRows(2 , 0, []); 
	  
	//아이디 값은 인자로 받아오기
	var thisTb = document.getElementById(tbId);
	//헤더 값 가져오기
	var rowList = thisTb.rows[0];	//헤더(표의 0번째 줄)
	var rowListCnt= rowList.childElementCount	//컬럼 개수(헤더 컬럼 개수)
	
	//헤더 배열 값
	var headColumns = [];
	for (var j = 0; j < rowListCnt; j++){
		headColumns.push(rowList.cells[j].innerHTML);
	}
	console.log("헤더 배열 확인 : " + headColumns);
	
	//헤더 입력
	worksheet.addRow(headColumns);
	
	worksheet.getRow(3).eachCell({ includeEmpty: true }, function (cell) { 
		cell.fill = {
					    type: 'pattern',
					    pattern: 'solid',
					    fgColor: { argb: 'FFFFFF00' } // 배경색 설정 
		  };
		cell.border = {
						top: {style:'thin'},
						left: {style:'thin'},
						bottom: {style:'thin'},
						right: {style:'thin'}
		};
		cell.font = {
						size: 12,
						bold: true
		};
	});
	
	//본문 내용 가져오기
	var headColumnNm = [];
	headColumnNm.push(Object.keys(result.list[0]));
	
	console.log("헤더컬럼 : " + headColumnNm);
	
	//본문 입력
	for(var c = 0; c < result.list.length; c++){
		var temp = [];
		for(var d = 0; d < rowListCnt; d++) {
			temp.push(result.list[c][Object.keys(result.list[c])[d]]);
		}//innerfor
			console.log("temp값 : " + temp);
			worksheet.addRow(temp).eachCell(function(cell) {
				cell.border = {
					top: {style:'thin'},
					left: {style:'thin'},
					bottom: {style:'thin'},
					right: {style:'thin'}
				};
				cell.alignment = { vertical: 'middle', horizontal: 'center' };
			})
	}//outerfor
	
	// 각 셀을 가운데 정렬로 설정
	worksheet.eachRow(function (row) {
	  row.eachCell(function (cell) {
	    cell.alignment = { horizontal: 'center', vertical: 'middle' };
	  });
	});
	
	//파일 생성([workbook.xlsx.writeBuffer()] 함수는 프로미스 반환 -> then() 사용 필요)
	workbook.xlsx.writeBuffer().then(function (fileData) {
	    var blob = new Blob([fileData], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

	    //'엑셀제목_2023-12-13.xlsx
	    /*날짜 포맷(참고 : https://gent.tistory.com/413)*/
	    var today = new Date();
	    var year = today.getFullYear();
	    var month = ('0' + (today.getMonth() + 1)).slice(-2);
	    var day = ('0' + today.getDate()).slice(-2);
	    var hours = ('0' + today.getHours()).slice(-2); 
	    var minutes = ('0' + today.getMinutes()).slice(-2);
	    var seconds = ('0' + today.getSeconds()).slice(-2); 
	    
	    var datetime = year + month  + day + '_' + hours + minutes  + seconds;
	    
	    saveAs(blob, excelTitle + datetime + '.xlsx');
	});
}

$("#excelFile").on('change',function(){
	  var fileName = $("#excelFile").val();
	  $(".upload-name").val(fileName);
	  
	  //파일업로드 직후
	  check();
	});
	
$("#csvFile").on('change',function(){
	  var fileName = $("#csvFile").val();
	  $(".csvupload-name").val(fileName);
	  
	  //파일업로드 직후
	  csvcheck();
	});
	
$("#fileUpDown").on('change',function(){
	  var fileName = $("#fileUpDown").val().split('/').pop().split('\\').pop();
	  $(".fileUpDown-name").val(fileName);
	  
	  //파일업로드 직후
	  fileUpDowncheck();
	});

$("#fileUpDownCsv").on('change',function(){
	  var fileName = $("#fileUpDownCsv").val().split('/').pop().split('\\').pop();
	  $(".fileUpDownCsv-name").val(fileName);
	  
	  //파일업로드 직후
	  fileUpDownCsvcheck();
	});
	

function checkFileType(filePath) {
	alert("파일 타입 체크 함수");
    var fileFormat = filePath.split(".");
    if (fileFormat.indexOf("xls") > -1 || fileFormat.indexOf("xlsx") > -1) {
      return true;
      } else {
      return false;
    }
}

function csvcheckFileType(filePath) {
	alert("csv파일 타입 체크 함수");
    var fileFormat = filePath.split(".");
    if (fileFormat.indexOf("csv") > -1) {
      return true;
      } else {
      return false;
    }
}


function check() {
	 
	alert("체크체크");
    var file = $("#excelFile").val();

    if (file == "" || file == null) {
    	alert("파일을 선택해주세요.");
    	return false;
    } else if (!checkFileType(file)) {
    	alert("엑셀 파일만 업로드 가능합니다.");
    	return false;
    }

    if (confirm("업로드 하시겠습니까?")) {
      var options = {

	        success : function(data) {
	            	  console.log(data);
	          		  alert("모든 데이터가 업로드 되었습니다.");
	          		  },
	        type : "POST"
      };
      
      $("#excelUploadForm").ajaxSubmit(options);
      
    }
  }
function csvcheck() {
	 
	alert("csv체크체크");
    var file = $("#csvFile").val();

    if (file == "" || file == null) {
    	alert("파일을 선택해주세요.");
    	return false;
    } else if (!csvcheckFileType(file)) {
    	alert("csv 파일만 업로드 가능합니다.");
    	return false;
    }

    if (confirm("업로드 하시겠습니까?")) {
      var options = {

	        success : function(data) {
	            	  console.log(data);
	          		  alert("모든 데이터가 업로드 되었습니다.");
	          		  
	          		  console.log("에이젝스 결과 확인 : " + data.fileNm);
	          		  console.log("에이젝스 결과 확인 : ", data.filePath);
	          		  
// 	          		readCsvAjax(data);
	          		
	          		$.ajax({
	          			url: "${pageContext.request.contextPath}/csvReadAjax.do",
	          			method:"POST",
	          			data:	{fileNm : data.fileNm,
								 filePath : data.filePath},
	          			success:function(result){
	          	 			console.log("ajax 엑셀 내용물 확인 : " + JSON.stringify(result));
	          	 			
	    	          		$.ajax({
	    	          			url: "${pageContext.request.contextPath}/csvReadnWriteAjax.do",
	    	          			method:"POST",
	    	          			data:	{fileNm : data.fileNm,
	    								 filePath : data.filePath},
	    	          			success:function(result){
	    	          	 			console.log("ajax 엑셀 내용물 확인123 : " + JSON.stringify(result));
	    	          	 			
	    	          	 			//오류 -> 계속 F.OK 이상함
	    	          	 			loadFromFile(); 
	    	          	 			
	    	          	 			
	    	          				}//success
	    	          			})//ajax	
	          	 			
	          	 			
	          				}//success
	          			})//ajax	
	          		  
	          		  
	          		  },
	        type : "POST"
      };
      
      $("#csvUploadForm").ajaxSubmit(options);
      return false; 
    }
  }

function fileUpDowncheck() {
	 
	alert("fileUpDown체크체크");
    var file = $("#fileUpDown").val();

    if (file == "" || file == null) {
    	alert("파일을 선택해주세요.");
    	return false;
    } else if (!csvcheckFileType(file)) {
    	alert("csv 파일만 업로드 가능합니다.");
    	 $('#fileUpDown').val(''); 
  		 $(".fileUpDown-name").val('');
    	return false;
    }

    if (confirm("업로드 하시겠습니까?")) {
      var options = {

	        success : function(data) {
	            	  console.log(data);
	          		  alert("모든 데이터가 업로드 되었습니다.");
	          		  
	          		  //파일, 파일이름 삭제
	          		 $('#fileUpDown').val(''); 
	          		 $(".fileUpDown-name").val('');
	          		  
	          		  console.log("에이젝스 결과 확인 : " + data.fileNm);
	          		  console.log("에이젝스 결과 확인 : "+ data.filePath);
	          		  },
	        type : "POST"
      };
      $("#fileUpDownForm").ajaxSubmit(options);
    }
  }

function fileUpDownCsvcheck() {
	 
	//해보고 오래걸릴거 같으니까 로딩바를 추가해보자
	
	alert("fileUpDownCsv체크체크");
    var file = $("#fileUpDownCsv").val();

    if (file == "" || file == null) {
    	alert("파일을 선택해주세요.");
    	return false;
    } else if (!csvcheckFileType(file)) {
    	alert("csv 파일만 업로드 가능합니다.");
    	 $('#fileUpDownCsv').val(''); 
  		 $(".fileUpDownCsv-name").val('');
    	return false;
    }

    if (confirm("업로드 하시겠습니까?")) {
     
    	var options = {

	        success : function(data) {
	            	  console.log(data);
	          		  alert("모든 데이터가 업로드 되었습니다.");
	          		  
	          		  //파일, 파일이름 삭제
	          		 $('#fileUpDownCsv').val(''); 
	          		 $(".fileUpDownCsv-name").val('');
	          		  
	          		  console.log("에이젝스 결과 확인 : " + data.fileNm);
	          		  console.log("에이젝스 결과 확인 : "+ data.filePath);
	          		  },
	        type : "POST"
      };
    
      $("#fileUpDownCsvForm").ajaxSubmit(options);
      
      
      	
		//submit 진행
    	/*
      	$("#fileUpDownCsvForm").submit();
      	alert("확인확인");
      	*/
    }//if confirm
  }




function uploadUpDownAjax(data) {
	
	alert("123");
	
		$.ajax({
  			url: "${pageContext.request.contextPath}/csvReadAjax.do",
  			method:"POST",
  			data:	{fileNm : data.fileNm,
					 filePath : data.filePath},
  			success:function(result){
//   	 			console.log("ajax 엑셀 내용물 확인 : " + JSON.stringify(result));
  	 			console.log("ajax 엑셀 내용물 확인 : " + result);
  				}//success
  			})//ajax	
}



/*
function uploadUpDownAjax(data) {
	
	alert("123");
	
		$.ajax({
  			url: "${pageContext.request.contextPath}/csvReadAjax.do",
  			method:"POST",
  			data:	{fileNm : data.fileNm,
					 filePath : data.filePath},
  			success:function(result){
//   	 			console.log("ajax 엑셀 내용물 확인 : " + JSON.stringify(result));
  	 			console.log("ajax 엑셀 내용물 확인 : " + result);
  				}//success
  			})//ajax	
}

*/


 function loadFromFile() {
    let workbook = new ExcelJS.Workbook();
    let filenm = 'C:/Upload/test.xlsx'
    workbook.xlsx.readFile(filenm)
        .then(function(workbook, arg2) {
            //                    var worksheet = workbook.getWorksheet(sheet);
            console.log('workbook :', workbook);
            workbook.eachSheet(function(worksheet, sheetId) {
                worksheet.eachRow({
                    includeEmpty: true
                }, function(row, rowNumber) {
                    console.log("Row " + rowNumber + " = " + JSON.stringify(row.values));
                });

            });
        });
}


    
    
//     var workbook = new ExcelJS.Workbook();
//     var filenm = 'C:\\upload\\전국 약국 정보.csv';
    
//     var worksheet = await workbook.csv.readFile(filenm);
    
//     console.log("worksheet");
    
    
    
    /*
    wb.csv.readFile(filenm).then(function(ws) {
        console.log(
            'Sheet ' + ws.id + ' - ' + ws.name + ', Dims=' + JSON.stringify(ws.dimensions)
        );

        for (let i = 1; i <= ws.actualRowCount; i++) {
            for (let j = 1; j <= ws.actualColumnCount; j++) {
                const val = ws.getRow(i).getCell(j);
                consol.log(val + ' ');
            }
            console.log();
        }

    }).then(function() {
//         writeData();

    });
*/
    
// }











//배열에서 특정 문자열이 있는 값의 인덱스 조회
function findIndex(arr, substring) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].includes(substring)) {
            return i;
        }
    }
	    return -1;
}



//날짜 데이터 포맷
function dateFormat(date) {
	
    var today = new Date(date);
    var year = today.getFullYear();
    var month = ('0' + (today.getMonth() + 1)).slice(-2);
    var day = ('0' + today.getDate()).slice(-2);
    var hours = ('0' + today.getHours()).slice(-2); 
    var minutes = ('0' + today.getMinutes()).slice(-2);
    var seconds = ('0' + today.getSeconds()).slice(-2); 

    var timeStr = year + '-' +  month + '-' + day + '_' +hours+ ':' + minutes; 
    
    return timeStr;
}












</script>



</html>
