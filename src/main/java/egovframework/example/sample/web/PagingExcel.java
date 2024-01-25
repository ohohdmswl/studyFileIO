/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import egovframework.example.sample.service.FileVO;
import egovframework.example.sample.service.PagingExcelService;
import egovframework.example.sample.service.PagingExcelVO;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
@Controller
@RequestMapping(value = "/board")	//-> 컨트롤러 mapping 값은 바꾸지 않아도 되지만 jsp의 경로는 앞에 board 붙여줘야함
public class PagingExcel {

	/** EgovSampleService */
	@Resource(name = "pagingExcelService")
	private PagingExcelService pagingExcelService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

   // Set logger
   private final Logger logger = LogManager.getLogger(this.getClass());
   // Get class name for logger
   private final String className = this.getClass().toString();
	
	/**
	 * 글 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/pagingExcelLoad.do")
	public String selectSampleList(@ModelAttribute("pagingExcelVO") PagingExcelVO pagingExcelVO, ModelMap model) throws Exception {

		logger.info("+ Start " + className + "페이지 로드 확인");

		/* PageHelper + model 사용
		 * 
		List<Map<Object, Object>> resultList = pagingExcelService.selectDataList();
		logger.info("결과 확인 : " + resultList);
		
		PageHelper.startPage(pagingExcelVO.getPageIndex(),pagingExcelVO.getPageUnit());
		PageInfo<Map<Object,Object>> pageInfo = new PageInfo<Map<Object,Object>>(pagingExcelService.selectPDataList(pagingExcelVO), 10);
		logger.info("페이지네이션 세부 확인 : " + pagingExcelVO.getPageIndex());
		logger.info("페이지네이션 확인 : " + pagingExcelVO.getPageUnit());
		logger.info("페이지네이션 확인 : " + pageInfo);
		
		model.addAttribute("pageInfo",pageInfo);		
		 */
		
		return "board/PagingExcel";
	}
	
	/**
	 * 표, 페이지네이션(pageHelper)
	 * 표 내용과 페이지네이션을 Ajax로 받아 표출
	 * 클릭한 페이지 인덱스 인자로 받아오면 페이징헬퍼가 자동으로 조회가능하게 해줌
	 * @param pagingExcelVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/pagingExcelBoardLoad.do")
	public Map<String, Object> selectBoardList(@ModelAttribute("pagingExcelVO") PagingExcelVO pagingExcelVO, ModelMap model) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		/*startPage : 페이지 지정시 사용 메소드 (조회하고 싶은 페이지 번호 , 페이지 당 보여줄 아이템 개수_게시글 개수)*/
		PageHelper.startPage(pagingExcelVO.getPageIndex(),pagingExcelVO.getPageUnit());
		
		/*페이지네이션에 필요한 값을 알아서 계산해서 pageInfo에 담아줌
		페이지네이션 블록 개수 : 10으로 설정
		PageInfo(게시판 정보 List, 페이지네이션 블록개수)*/
		PageInfo<Map<Object,Object>> pageInfo = new PageInfo<Map<Object,Object>>(pagingExcelService.selectPDataList(pagingExcelVO), 10);
		logger.info("chk pagination ajax: " + pageInfo);
		
		result.put("pageInfo", pageInfo);
		
		return result;
	}
	
	//상세보기
	@RequestMapping(value = "/pagingExcelBoardDetail.do")
	public String pagingExcelBoardDetail(@ModelAttribute("pagingExcelVO") PagingExcelVO pagingExcelVO, ModelMap model) throws Exception {
		
		logger.info("detail chk : " + pagingExcelVO);

		
//		model.addAttribute("", );

		
		return "board/boardDetail";
	}

	
	
	
	
	/**
	 * 표 액샐 다운로드(exceljs, filesaver)
	 * excelDownload1, excelDownload2 연결
	 * @param pagingExcelVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/pagingExcelBoardDownload.do")
	public Map<String, Object> pagingExcelBoardDownload(@ModelAttribute("pagingExcelVO") PagingExcelVO pagingExcelVO, ModelMap model) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();

		List<Map<Object, Object>> resultList = pagingExcelService.selectExcelDownload(pagingExcelVO);
		logger.info("엑셀 다운로드 결과 확인 : " + resultList);
		
		result.put("list", resultList);
		
		return result;
	}
	
	/**
	 * 표 액샐 다운로드(exceljs, filesaver), LinkedHashMap(순서보장)
	 * excelDownload3, excelDownload4 연결
	 * @param pagingExcelVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/pagingExcelBoardDownload3.do")
	public Map<String, Object> pagingExcelBoardDownload3(@ModelAttribute("pagingExcelVO") PagingExcelVO pagingExcelVO, ModelMap model) throws Exception {
		//날짜 데이터 - jsp에서 타임스탬프 타입 아닌 String으로 바로 사용할 수 있도록  
		//1. LinkedHashMap으로 받아와서 컨트롤러에서 date 값을 (Date -> String) 변환해서 다시 set하고 jsp로 전달
		//2. 쿼리에서 Tochar 사용해서 처음부터 String으로 받아오기
		
        // Date Format 설정       
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");       
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<LinkedHashMap<Object, Object>> resultList = pagingExcelService.selectExcelDownloadLinked(pagingExcelVO);
		logger.info("엑셀 다운로드 결과 확인3 : " + resultList);
		
		/*1. LinkedHashMap으로 받아와서 컨트롤러에서 date 값을 (Date -> String) 변환해서 다시 set하고 jsp로 전달
//		Date로 바꾸고 String으로 변환하는 이유
//		-> 데이터를 Date 객체로 변환하여 날짜를 쉽게 조작하고 포맷팅하기 위함
		for (LinkedHashMap<Object, Object> map : resultList) {
		    for (Map.Entry<Object, Object> entry : map.entrySet()) {
		        if (entry.getKey().equals("board_date")) {
		            // 날짜 형식 변경
		            String formattedDate = dateFormat.format((Date) entry.getValue());
		            entry.setValue(formattedDate);
		        }
		    }
		}
		*/
		result.put("list", resultList);
		
		return result;
	}
	
	
	/**
	 * 표 액샐 다운로드(Poi)
	 * excelDownload5
	 * @param pagingExcelVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/pagingExcelBoardDownloadPoi.do")
	public Map<String, Object> pagingExcelBoardDownloadPoi(@ModelAttribute("pagingExcelVO") PagingExcelVO pagingExcelVO, ModelMap model, HttpServletResponse response, @RequestParam(value="headColumns[]") List<String> arrayParams) throws Exception {
		
		 String filePath = "C:\\poi_temp";
		 String fileNm = "poi_making_file_test.xlsx";
		
		// 빈 Workbook 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		// 빈 Sheet를 생성
		XSSFSheet sheet = workbook.createSheet("SheetOne");
		
		// Sheet를 채우기 위한 게시판 정보 데이터들을 Map에 저장
		Map<String, Object> result = new HashMap<String, Object>();
		List<LinkedHashMap<Object, Object>> resultList = pagingExcelService.selectExcelDownloadLinked(pagingExcelVO);
		logger.info("resultList확인 : " + resultList);
		
		
		//LinkedHashMap의  값 가져오기
		//map의 값만 가져오려고 작성함
		//-> map의 값 부분이 Object여서 List<List<Object>> 이렇게 설정
		//-> map의 값들을 가져와 그 리스트가 리스트형태로 있으니 해당 유형으로 설정
		//처리한거긴 한데 다른 방법을 좀 찾아야겠다
		//값이 null일때 어떻게 처리할지 생각해야겠다 -> 널 그대로 들어가나? 그러면 작성할 때 null 처리를 할 수 있도록 해야하나
		List<List<Object>> allValueList = new ArrayList<>();
		for (LinkedHashMap<Object, Object> map : resultList) {
		    List<Object> valuesList = new ArrayList<>(map.values());
		    allValueList.add(valuesList);
		}
		logger.info("allValueList 확인 : " + allValueList);
		logger.info("allValueList 확인2 : " + allValueList.get(0).get(2));
		
		//LinkedHashMap의 키 가져오기
		//첫번째 줄만 가져와서 새로운 LinkedHashMap에 넣고 거기서 키값을 추출
		List<Object> keyList = new ArrayList<>();
		LinkedHashMap<Object, Object> firstMap = resultList.get(0);
	    keyList.addAll(firstMap.keySet());
	    logger.info("keyList 확인 : " + keyList);
	    logger.info("keyList 확인 : " + keyList.get(0));
		
	    Row row = null;
	    Cell cell = null;
	    int rownum = 0;
	    int cellnum = 0;
	    

	    /*제목 스타일 */
	    Font titleFont = workbook.createFont();
		titleFont.setBold(true);
		titleFont.setFontHeightInPoints((short)14);
		
		CellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFont(titleFont);
		titleStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
		titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		titleStyle.setBorderBottom(BorderStyle.THIN);
		titleStyle.setBorderTop(BorderStyle.THIN);
		titleStyle.setBorderLeft(BorderStyle.THIN);
		titleStyle.setBorderRight(BorderStyle.THIN);
		
		titleStyle.setAlignment(HorizontalAlignment.CENTER);
//		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		
	    
	    /*헤더 스타일*/
		CellStyle headerStyle = workbook.createCellStyle();
		Font boldFont = workbook.createFont();
		boldFont.setBold(true);
		headerStyle.setFont(boldFont);
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		 
		/*상세값 스타일*/
		CellStyle dataStyle = workbook.createCellStyle();
		dataStyle.setAlignment(HorizontalAlignment.CENTER);
		dataStyle.setBorderBottom(BorderStyle.THIN);
		dataStyle.setBorderTop(BorderStyle.THIN);
		dataStyle.setBorderLeft(BorderStyle.THIN);
		dataStyle.setBorderRight(BorderStyle.THIN);
		
	    /*제목 삽입*/
	    row = sheet.createRow(rownum++);
	    
	    //arrayParams : Ajax로 받아온 화면에 표출된 표의 헤더 값
	    for(int c =0; c<arrayParams.size(); c++) {
	    	cell=row.createCell(cellnum++);
	    	cell.setCellStyle(titleStyle);	//셀 병합시 스타일이 병합에 맞게 적용되지 않음 -> 각각 셀에 스타일 지정 후 병합
	    	
	    	if(c==0) {
	    		cell.setCellValue("자유게시판");
	    	}
	    }
	    
	    
	    sheet.addMergedRegion(new CellRangeAddress(0,0,0,arrayParams.size()-1));//시작 행, 마지막 행, 시작 열, 마지막 열 (자바배열과 같이 0부터 시작)
	    rownum++;
	    
	    
	    /*header 삽입*/
	    cellnum = 0;
	    row = sheet.createRow(rownum++);
	    for(int h = 0; h <arrayParams.size(); h++) {
	    	cell=row.createCell(cellnum++);
	    	cell.setCellValue(arrayParams.get(h));
	    	cell.setCellStyle(headerStyle);
	    }

	    /*표 값 삽입*/
		for(int i=0; i<resultList.size(); i++) {
			row = sheet.createRow(rownum++);
			cellnum = 0;
			for(int j=0; j<keyList.size(); j++) {
				cell = row.createCell(cellnum++);
				cell.setCellValue(allValueList.get(i).get(j).toString());
				cell.setCellStyle(dataStyle);
				sheet.autoSizeColumn(j);
				sheet.setColumnWidth(j, (sheet.getColumnWidth(j))+(short)1000 );
			}
		}
		 
        try (FileOutputStream out = new FileOutputStream(new File(filePath, fileNm))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	/*흐으으음 -> 복붙
	 * 작동을 하는데 이해못함*/
	@ResponseBody
    @RequestMapping(value = "/excelUploadAjax.do", method = RequestMethod.POST)
        public ModelAndView excelUploadAjax(MultipartFile testFile, MultipartHttpServletRequest request) throws  Exception{
        
        logger.info("업로드 진행");
        
        MultipartFile excelFile = request.getFile("excelFile");
        
        if(excelFile == null || excelFile.isEmpty()) {
            throw new RuntimeException("엑셀파일을 선택해 주세요");
        }
        logger.info("업로드 진행2");
        
        File destFile = new File("C:\\upload\\"+excelFile.getOriginalFilename());
        logger.info("업로드 진행3");
        
        try {
            //내가 설정한 위치에 내가 올린 파일을 만들고 
            excelFile.transferTo(destFile);
        }catch(Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        
        //업로드를 진행하고 다시 지우기
        pagingExcelService.excelUpload(destFile);
        
        destFile.delete();
        
        ModelAndView view = new ModelAndView();
//        view.setViewName("/egovSampleList.do");
        return view;
    }
	
	
	@ResponseBody
	@RequestMapping(value = "/csvUploadAjax.do", method = RequestMethod.POST)
	public Map<String, Object> csvUploadAjax(MultipartFile testFile, MultipartHttpServletRequest request) throws  Exception{
		
//		String filePath = "C:\\poi_temp";
//		String fileNm = "poi_making_file_test.xlsx";
		
		logger.info("csv업로드 진행");
		
        // uuid 생성 
        UUID uuid = UUID.randomUUID();
		
		MultipartFile csvFile = request.getFile("csvFile");
		
		if(csvFile == null || csvFile.isEmpty()) {
			throw new RuntimeException("csv파일을 선택해 주세요");
		}
		logger.info("업로드 진행2");
		
		File destFile = new File("C:\\upload\\"+uuid.toString()+"_"+csvFile.getOriginalFilename());
		logger.info("업로드 진행3");
		logger.info("파일이름확인 : " + destFile.getName());
		logger.info("파일경로확인 : " + destFile.getPath());
		
		try {
			//내가 설정한 위치에 내가 올린 파일을 만들고 
			csvFile.transferTo(destFile);
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		//업로드를 진행하고 다시 지우기
//		pagingExcelService.excelUpload(destFile);
		
//		destFile.delete();
		
		 Map<String, Object> returnmap = new HashMap<String, Object>();
		 returnmap.put("fileNm", destFile.getName());
		 returnmap.put("filePath", destFile.getPath());
		
		
		return returnmap;
	}

	@ResponseBody
	@RequestMapping(value = "/csvReadAjax.do", method = RequestMethod.POST)
	public Map<String, Object> csvReadAjax(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
	         HttpServletResponse response) throws  Exception{
		
		logger.info("123456789");
		logger.info("- paramMap : " + paramMap);
		
		String filePath = (String)paramMap.get("filePath");
		String fileNm = (String)paramMap.get("fileNm");
		
		logger.info("csv read 진행");
		
        // 반환용 리스트 변수
        List<List<String>> ret = new ArrayList<>();
        // 입력 스트림 오브젝트 생성
        BufferedReader br = null;

        try {
            // 대상 CSV 파일의 경로 설정(UTF-8로 가져올 때 업로드된 csv파일 인코딩이 ansi로 되어있어서 ansi 읽을 수 있게 Charset.forName("EUC-KR")로 변경
            br = Files.newBufferedReader(Paths.get("C:\\upload\\e0b9f444-95d3-4251-9b8a-49ef67f00825_전국 약국 정보 - 복사본.csv"),Charset.forName("EUC-KR"));
            // CSV파일에서 읽어들인 1행분의 데이터
            String line = "";

            while((line = br.readLine()) != null) {
                // CSV 파일의 1행을 저장하는 리스트 변수
                List<String> tmpList = new ArrayList<>();
                //CSV 파일의 한 행을 쉼표로 구분하여 필드로 나눔
                String array[] = line.split(",");
                // 배열에서 리스트로 변환(배열 -> 리스트), 리스트에는 CSV 파일 한 행의 데이터
                tmpList = Arrays.asList(array);
                // 리스트 내용 출력
                logger.info(tmpList);
                // 반환용 리스트에 1행의 데이터를 저장
                ret.add(tmpList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
		 Map<String, Object> returnmap = new HashMap<String, Object>();
		
		
		return returnmap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/csvReadnWriteAjax.do", method = RequestMethod.POST)
	public Map<String, Object> csvReadnWriteAjax(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
	         HttpServletResponse response) throws  Exception{
	
		String filePath = (String)paramMap.get("filePath");
		String fileNm = (String)paramMap.get("fileNm");
		
		logger.info("csv readnWrite 진행");
		
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = Files.newBufferedWriter(Paths.get("C:\\upload\\전국 약국 정보 - 복사본_작성.csv"),
                    Charset.forName("EUC-KR"));

            // CSV 파일 읽기
            List<List<String>> allData = readCSV();

            for (List<String> newLine : allData) {

                // 1행분의 데이터
                List<String> list = newLine;

                for (String data : list) {
                    bufferedWriter.write(data);
                    bufferedWriter.write(",");
                }

                // 추가할 주소 데이터 추가
                bufferedWriter.write("주소");

                // 개행 코드 추가
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> returnmap = new HashMap<String, Object>();
        return returnmap;
    }
		

		


	static  List<List<String>> readCSV() {
		
	    List<List<String>> ret = new ArrayList<>();
	    // 입력 스트림 오브젝트 생성
	    BufferedReader br = null;
	
	    try {
	        // 대상 CSV 파일의 경로 설정
	        br = Files.newBufferedReader(Paths.get("C:\\upload\\e0b9f444-95d3-4251-9b8a-49ef67f00825_전국 약국 정보 - 복사본.csv"), Charset.forName("EUC-KR"));
	        // CSV파일에서 읽어들인 1행분의 데이터
	        String line = "";
	
	        while ((line = br.readLine()) != null) {
	            // CSV 파일의 1행을 저장하는 리스트 변수
	            List<String> tmpList = new ArrayList<>();
	            String array[] = line.split(",");
	            // 배열에서 리스트로 변환
	            tmpList = Arrays.asList(array);
	            // 리스트 내용 출력
	            System.out.println(tmpList);
	            // 반환용 리스트에 1행의 데이터를 저장
	            ret.add(tmpList);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (br != null) {
	                br.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return ret;
	}

	
	//파일 업로드 및 읽기
	@ResponseBody
	@RequestMapping(value = "/uploadUpDownAjax1.do", method = RequestMethod.POST)
	public Map<String, Object> uploadUpDownAjax1(List<MultipartFile> upfile, MultipartHttpServletRequest request) throws  Exception{
		
		logger.info("파일업다운 섭밋됨111");
//		int resuldata1 = pagingExcelService.insertFile1(upfile, request);
		
		//업로드를 진행하고 다시 지우기
//		pagingExcelService.excelUpload(destFile);
		
//		destFile.delete();
        
        Map<String, Object> returnmap = new HashMap<String, Object>();
        
//		 returnmap.put("fileNm", destFile.getName());
//		 returnmap.put("filePath", destFile.getPath());
        
        return returnmap;
    }
	//파일 업로드 및 읽기
	@ResponseBody
	@RequestMapping(value = "/uploadUpDownAjax2.do", method = RequestMethod.POST)
	public Map<String, Object> uploadUpDownAjax2(@RequestParam Map<String, Object> paramMap, FileVO fileVO, HttpServletRequest request) throws  Exception{
		
		logger.info("파일업다운 섭밋됨222");
		
		
		
		int groupNo = pagingExcelService.selectFileGroupNo();
		
		int resuldata1 = pagingExcelService.insertFile2(paramMap, request, groupNo);
		
		//업로드를 진행하고 다시 지우기
//		pagingExcelService.excelUpload(destFile);
		
//		destFile.delete();
		
		Map<String, Object> returnmap = new HashMap<String, Object>();
		
//		 returnmap.put("fileNm", destFile.getName());
//		 returnmap.put("filePath", destFile.getPath());
		
		return returnmap;
	}
	
	/**
	 * select box 내용 보이기
	 * @param paramMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/selectFileList.do", method = RequestMethod.POST)
	public Map<String, Object> selectFileList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request) throws  Exception{
		
		logger.info("selectfileList##");
		
		List<Map<String, Object>> fileList = pagingExcelService.selectfileList();
		Map<String, Object> returnmap = new HashMap<String, Object>();
		returnmap.put("fileList", fileList);
		
		return returnmap;
	}
	
	/**
	 * 파일 상세 콤보박스 list 조회
	 * @param paramMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/selectFileDetailList.do", method = RequestMethod.POST)
	public Map<String, Object> selectFileDetailList(@RequestParam Map<String, Object> paramMap, FileVO fileVO, HttpServletRequest request) throws  Exception{
		
		logger.info("selectFileDetailList##");
		logger.info("파라미터로 받아오는지 확인읋 하자 : " + fileVO);
		
		
		List<Map<String, Object>> fileDList = pagingExcelService.selectfileDetailList(fileVO);
		Map<String, Object> returnmap = new HashMap<String, Object>();
		returnmap.put("fileDList", fileDList);
		
		return returnmap;
	}
	
	//submit으로 파일 다운로드
	@RequestMapping(value = "/selectFileDownload.do", method = RequestMethod.POST)
	public void selectFileDownload(@RequestParam Map<String, Object> paramMap, FileVO fileVO, HttpServletRequest request, HttpServletResponse response) throws  Exception{
		
		logger.info("selectFileDownload##");
		logger.info("파라미터로 받아오는지 확인읋 하자22 : " + fileVO);
		
		//선택한 파일 정보 가져오기
		FileVO fileInfo = pagingExcelService.selectFileDownload(fileVO);
		
		logger.info("파일 확인 : " + fileInfo.getFile_path() + " + " + fileInfo.getFile_name());
		
		File src = new File(fileInfo.getFile_path()); 	//TRUE -> path만 작성하니까 됨(파일 이름 빼고)
		logger.info("파일 존재 여부 : " + src.exists());
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));

		long fileSize = src.length();
		
		String outputName = URLEncoder.encode(fileInfo.getFile_name(), "UTF-8"); //한글 인코딩 처리 
		logger.info("아웃풋네임 : " + outputName);
		
		response.setContentLength( (int) fileSize ); 
		response.setHeader("Content-Length", String.valueOf( src.length() ) ); 
		response.setHeader("Content-Disposition", "attachment; filename=\"" + outputName + "\""); 
		response.setContentType("application/octet-stream; charset=utf-8"); 
		response.setCharacterEncoding("UTF-8"); 
		
		try {
			// 서버의 파일 입력 스트림 
			FileInputStream fis = new FileInputStream(src); 
			// 클라이언트 응답 출력 스트림 
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(fis, out); 	//FileCopyUtils : 자동으로 flush, close 됨
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}//end
	
/*	//Ajax로 파일 다운로드 return void(XMLHttpRequest의 responseType 을 blob으로 설정 후 진행)
	@ResponseBody
	@RequestMapping(value = "/selectFileDownloadAjax.do", method = RequestMethod.POST)
	public void selectFileDownloadAjax(@RequestParam Map<String, Object> paramMap, FileVO fileVO, HttpServletRequest request, HttpServletResponse response) throws  Exception{
		
		logger.info("selectFileDownload##");
		logger.info("파라미터로 받아오는지 확인읋 하자22@@@ : " + fileVO);
		
		//선택한 파일 정보 가져오기
		FileVO fileInfo = pagingExcelService.selectFileDownload(fileVO);
		logger.info("파일 확인 : " + fileInfo.getFile_path() + " + " + fileInfo.getFile_name());
		
		File src = new File(fileInfo.getFile_path()); 	//TRUE -> path만 작성하니까 됨(파일 이름 빼고)
		logger.info("파일 존재 여부 : " + src.exists());
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));

		String outputName = null; //한글 인코딩 처리 
	    String browser = request.getHeader("User-Agent");
	    
	    //파일 인코딩
	    if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){
	      //브라우저 확인 파일명 encode  		             
	    	outputName = URLEncoder.encode(fileInfo.getFile_name(), "UTF-8").replaceAll("\\+", "%20");		             
	    }else{		             
	    	outputName = new String(fileInfo.getFile_name().getBytes("UTF-8"), "ISO-8859-1");
	    } 

	    long fileSize = src.length();
		
		logger.info("아웃풋네임 : " + outputName);
		
		response.setContentLength( (int) fileSize ); 
		response.setHeader("Content-Length", String.valueOf( src.length() ) ); 
		response.setHeader("Content-Disposition", "attachment; filename=\"" + outputName + "\""); 
		response.setContentType("application/octet-stream;"); 
		response.setCharacterEncoding("UTF-8"); 
		response.setHeader("Content-Transfer-Encoding", "binary;");
		
		logger.info("response 설정 : ");
		
		try {
			// 서버의 파일 입력 스트림 
			FileInputStream fis = new FileInputStream(src); 
			logger.info("FileInputStream 설정 : ");
			
			// 클라이언트 응답 출력 스트림 
			OutputStream out = response.getOutputStream();
			logger.info("OutputStream 설정 : ");
			
			FileCopyUtils.copy(fis, out); 	//FileCopyUtils : 자동으로 flush, close 됨
			logger.info("FileCopyUtils 설정 : ");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}//end
	*/
	
	/*
	//Ajax로 파일 다운로드 return String -> jsonView 
	//(XMLHttpRequest의 responseType 을 blob으로 설정 후 진행)
	@RequestMapping(value = "/selectFileDownloadAjax.do", method = RequestMethod.POST)
	public String selectFileDownloadAjax(@RequestParam Map<String, Object> paramMap, FileVO fileVO, HttpServletRequest request, HttpServletResponse response) throws  Exception{
		
		logger.info("selectFileDownload##");
		logger.info("파라미터로 받아오는지 확인읋 하자22@@@jsonView : " + fileVO);
		
		//선택한 파일 정보 가져오기
		FileVO fileInfo = pagingExcelService.selectFileDownload(fileVO);
		logger.info("파일 확인 : " + fileInfo.getFile_path() + " + " + fileInfo.getFile_name());
		
		File src = new File(fileInfo.getFile_path()); 	//TRUE -> path만 작성하니까 됨(파일 이름 빼고)
		logger.info("파일 존재 여부 : " + src.exists());
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));

		String outputName = null; //한글 인코딩 처리 
	    String browser = request.getHeader("User-Agent");
	    
	    //파일 인코딩
	    if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){
	      //브라우저 확인 파일명 encode  		             
	    	outputName = URLEncoder.encode(fileInfo.getFile_name(), "UTF-8").replaceAll("\\+", "%20");		             
	    }else{		             
	    	outputName = new String(fileInfo.getFile_name().getBytes("UTF-8"), "ISO-8859-1");
	    } 

	    long fileSize = src.length();
		
		logger.info("아웃풋네임 : " + outputName);
		
		response.setContentLength( (int) fileSize ); 
		response.setHeader("Content-Length", String.valueOf( src.length() ) ); 
		response.setHeader("Content-Disposition", "attachment; filename=\"" + outputName + "\""); 
		response.setContentType("application/octet-stream;"); 
		response.setCharacterEncoding("UTF-8"); 
		response.setHeader("Content-Transfer-Encoding", "binary;");
		
		logger.info("response 설정 : ");
		
		try {
			// 서버의 파일 입력 스트림 
			FileInputStream fis = new FileInputStream(src); 
			logger.info("FileInputStream 설정 : ");
			
			// 클라이언트 응답 출력 스트림 
			OutputStream out = response.getOutputStream();
			logger.info("OutputStream 설정 : ");
			
			FileCopyUtils.copy(fis, out); 	//FileCopyUtils : 자동으로 flush, close 됨
			logger.info("FileCopyUtils 설정 : ");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		return "jsonView";
	}//end
	*/
	
	
	//Ajax로 파일 다운로드 return jsonView (XMLHttpRequest의 responseType 을 blob으로 설정 후 진행)
	@RequestMapping(value = "/selectFileDownloadAjax.do", method = RequestMethod.POST)
	public ModelAndView selectFileDownloadAjax(@RequestParam Map<String, Object> paramMap, FileVO fileVO, HttpServletRequest request, HttpServletResponse response) throws  Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		
		logger.info("selectFileDownload##");
		logger.info("파라미터로 받아오는지 확인읋 하자22@@@jsonView : " + fileVO);
		
		//선택한 파일 정보 가져오기
		FileVO fileInfo = pagingExcelService.selectFileDownload(fileVO);
		logger.info("파일 확인 : " + fileInfo.getFile_path() + " + " + fileInfo.getFile_name());
		
		File src = new File(fileInfo.getFile_path()); 	//TRUE -> path만 작성하니까 됨(파일 이름 빼고)
		logger.info("파일 존재 여부 : " + src.exists());
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
		
		String outputName = null; //한글 인코딩 처리 
		String browser = request.getHeader("User-Agent");
		
		//파일 인코딩
		if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){
			//브라우저 확인 파일명 encode  		             
			outputName = URLEncoder.encode(fileInfo.getFile_name(), "UTF-8").replaceAll("\\+", "%20");		             
		}else{		             
			outputName = new String(fileInfo.getFile_name().getBytes("UTF-8"), "ISO-8859-1");
		} 
		
		long fileSize = src.length();
		
		logger.info("아웃풋네임 : " + outputName);
		
		response.setContentLength( (int) fileSize ); 
		response.setHeader("Content-Length", String.valueOf( src.length() ) ); 
		response.setHeader("Content-Disposition", "attachment; filename=\"" + outputName + "\""); 
		response.setContentType("application/octet-stream;"); 
		response.setCharacterEncoding("UTF-8"); 
		response.setHeader("Content-Transfer-Encoding", "binary;");
		
		logger.info("response 설정 : ");
		
		try {
			// 서버의 파일 입력 스트림 
			FileInputStream fis = new FileInputStream(src); 
			logger.info("FileInputStream 설정 : ");
			
			// 클라이언트 응답 출력 스트림 
			OutputStream out = response.getOutputStream();
			logger.info("OutputStream 설정 : ");
			
			FileCopyUtils.copy(fis, out); 	//FileCopyUtils : 자동으로 flush, close 됨
			logger.info("FileCopyUtils 설정 : ");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		mav.addObject("result", 1);	//ajax return data를 blob로 설정해서 해당 값은 jsp에서 확인 불가
		//mav는 파일 업로드, 삭제와 같이 따로 blob와 같은 리턴 데이터가 필요없을 때 사용해야하나봐
		//그냥 ajax 사용해서 다운로드할 땐 void여야하나봐
		
		return mav;
	}//end
	
	
	@ResponseBody
	@RequestMapping(value = "/uploadUpDownCsv.do", method = RequestMethod.POST)
	public void uploadUpDownCsv(@RequestParam Map<String, Object> paramMap, FileVO fileVO, HttpServletRequest request, HttpServletResponse response) throws  Exception{
	
		//1. 업로드
		logger.info("@ OpenCSV 사용 + 업로드 + submit @");
		int groupNo = pagingExcelService.selectFileGroupNo();
		Map<String, Object> resultdata = pagingExcelService.insertCsvFile(paramMap, request, groupNo);
		//업로드 정상동작 확인
		
		
		//2. 읽기
		//제일 최신 그룹넘버 확인
		//최신 그룹넘버의 파일 순서 desc로 가져오기
		//한개씩 읽기, 수정, 출력 해야할 것 같은걸..?
		logger.info("csv insert 정보 리스트 조회 : " + ((List<FileVO>) resultdata.get("listfile")).get(0));		
		logger.info("csv insert 정보 리스트 조회 : " + ((List<FileVO>) resultdata.get("listfile")));	
		int listfileSize = ((List<FileVO>) resultdata.get("listfile")).size();		
		logger.info("csv insert 정보 리스트 조회 : " + listfileSize);	
		
		List<String> pathList = new ArrayList<>();
		
		if(!((List<FileVO>) resultdata.get("listfile")).isEmpty()) {
			for(int i = 0; i<listfileSize; i++) {
				logger.info("for내부 정보 리스트 조회 : " + ((List<FileVO>) resultdata.get("listfile")).get(i).getFile_path());	
				pathList.add(((List<FileVO>) resultdata.get("listfile")).get(i).getFile_path());
			}
		}
		logger.info("pathList 정보 리스트 조회 : " + pathList);
		
		
		for(String path : pathList){
		    logger.info("pathList  : " + path);
		    
		    String filePath = path;
		    String updateNm = updateCsvFileNm(filePath);
		    logger.info("updateNm  : " + updateNm);
		    
		    
		    
		    List<String[]> readAll = readDataFromCsv(filePath);
			//readAll 내용물 확인
//			for(String[] data : readAll) {
//				System.out.println("readAll  : " + Arrays.toString(data));
//			}
		    
		    
		    //하나씩 작성, 덮어써서 작성(true) 선택 가능 => 새파일 만들래 그냥.. ~.수정.csv
		    CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(updateNm), "EUC-KR"));
		    	
		    //csv Header
	    	ArrayList<String> csvHeader = new ArrayList<String>(Arrays.asList(readAll.get(0)));
	    	csvHeader.add("서울여부");
	    	writer.writeNext(csvHeader.toArray(new String[0]));
		    
		    int endRow = 0;
		    endRow = readAll.size();
		    
		    List<String> csvRowData = null; 
		    
			for (int j = 1; j < endRow; j++) {//csv 모든 행
				String chkWord = "";
				csvRowData = new ArrayList<String>(Arrays.asList(readAll.get(j)));
				
				logger.info("csvRowData.get(5)  : " + csvRowData.get(5));
				csvRowData.get(5);
				
				//3. 수정
				if(csvRowData.get(5).equals("서울")) {
					csvRowData.add("Y");
				}else {
					csvRowData.add("N");
				}
				
				//4. 출력
				logger.info("추가한 csvRowData : " + csvRowData);
				writer.writeNext(csvRowData.toArray(new String[0]));
				
			}//for
		    //csv출력
		    writer.close();
		    
		    
		    
		    
		    
		    
		    
		    
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	//openCSV 사용해서 csv 읽기
    public static List<String[]> readDataFromCsv(String filePath) throws IOException {

    	
    		//csv 한글 꺠짐
//			CSVReader reader = new CSVReader(new FileReader(filePath));
    		//csv 한글 꺠짐 방지 - 한글처리
			CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "EUC-KR"));
			//VO List로 받기
//			List<PharmacyCsvVO> voList = 
			
			//String List로 받기
//			List<String> strList = 
			
			//reader.readAll() 사용
			
			List<String[]> readAll = null; 
			try {
				readAll= reader.readAll();
			} catch (CsvException e1) {
				e1.printStackTrace();
			}
			
			//readAll 내용물 확인
			/*
			for(String[] data : readAll) {
				System.out.println("readAll  : " + Arrays.toString(data));
			}
			*/
			
		    

			
			
			//String []로 받기, reader.readNext
			/*
			String [] pArr;

			//csv 한줄씩 읽기
			try {
				while ((pArr = reader.readNext()) != null) {   // 2
				    for (int i = 0; i < pArr.length; i++) {
				        System.out.println(i + " " + pArr[i]);
				    }
				    System.out.println();
				}
			} catch (CsvValidationException e) {
				e.printStackTrace();
			}
			*/
			
			reader.close();
			
			return readAll;
			
		}//end
	
    public String updateCsvFileNm(String filePath) throws IOException {
    	
    	String saveCsvNm = "";
    	
    	int idx = filePath.lastIndexOf(".");
    	String subExten = filePath.substring(0, idx);
    	logger.info("subExten  : " + subExten);
    	
    	saveCsvNm = subExten + "_수정.csv";
    	
    	return saveCsvNm;
    
    }
	

}
