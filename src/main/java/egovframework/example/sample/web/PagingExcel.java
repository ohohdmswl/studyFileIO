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
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.FileVO;
import egovframework.example.sample.service.PagingExcelService;
import egovframework.example.sample.service.PagingExcelVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.sl.usermodel.VerticalAlignment;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
public class PagingExcel {

	/** EgovSampleService */
	@Resource(name = "pagingExcelService")
	private PagingExcelService pagingExcelService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	
	
	/**
	 * 글 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/pagingExcelLoad.do")
	public String selectSampleList(@ModelAttribute("pagingExcelVO") PagingExcelVO pagingExcelVO, ModelMap model) throws Exception {

		System.out.println("페이지 로드 확인");

		
//		List<Map<Object, Object>> resultList = pagingExcelService.selectDataList();
//		System.out.println("결과 확인 : " + resultList);
//		
//		PageHelper.startPage(pagingExcelVO.getPageIndex(),pagingExcelVO.getPageUnit());
//		PageInfo<Map<Object,Object>> pageInfo = new PageInfo<Map<Object,Object>>(pagingExcelService.selectPDataList(pagingExcelVO), 10);
//
//		System.out.println("페이지네이션 세부 확인 : " + pagingExcelVO.getPageIndex());
//		System.out.println("페이지네이션 확인 : " + pagingExcelVO.getPageUnit());
//
//		System.out.println("페이지네이션 확인 : " + pageInfo);
		
		
//		model.addAttribute("pageInfo",pageInfo);		

		
		return "sample/PagingExcel";
	}
	
	/**
	 * 표 페이지네이션(pageHelper)
	 * @param pagingExcelVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/pagingExcelBoardLoad.do")
	public Map<String, Object> selectBoardList(@ModelAttribute("pagingExcelVO") PagingExcelVO pagingExcelVO, ModelMap model) throws Exception {

		System.out.println("페이지 로드 ajax 확인");

		Map<String, Object> result = new HashMap<String, Object>();
		
		PageHelper.startPage(pagingExcelVO.getPageIndex(),pagingExcelVO.getPageUnit());
		PageInfo<Map<Object,Object>> pageInfo = new PageInfo<Map<Object,Object>>(pagingExcelService.selectPDataList(pagingExcelVO), 10);
		
		System.out.println("페이지네이션 확인  ajax: " + pageInfo);
		result.put("pageInfo", pageInfo);
		
		return result;
	}

	/**
	 * 표 액샐 다운로드(exceljs, filesaver)
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
		System.out.println("엑셀 다운로드 결과 확인 : " + resultList);
		
		result.put("list", resultList);
		
		return result;
	}
	
	/**
	 * 표 액샐 다운로드(exceljs, filesaver), LinkedHashMap(순서보장)
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
		System.out.println("엑셀 다운로드 결과 확인3 : " + resultList);
		
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
		
		System.out.println("되냐되냐" + resultList);
		*/
		result.put("list", resultList);
		
		return result;
	}
	
	
	/**
	 * 표 액샐 다운로드(Poi)
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
		
		// Sheet를 채우기 위한 데이터들을 Map에 저장
		Map<String, Object> result = new HashMap<String, Object>();
		List<LinkedHashMap<Object, Object>> resultList = pagingExcelService.selectExcelDownloadLinked(pagingExcelVO);
		System.out.println("엑셀 다운로드 결과 확인 : " + resultList);
		
		
		//LinkedHashMap의  값 가져오기
		List<List<Object>> allValueList = new ArrayList<>();
		for (LinkedHashMap<Object, Object> map : resultList) {
		    List<Object> valuesList = new ArrayList<>(map.values());
		    allValueList.add(valuesList);
		}
		System.out.println("allValueList 확인 : " + allValueList);
		System.out.println("allValueList 확인2 : " + allValueList.get(0).get(2));
		
		//LinkedHashMap의 키 가져오기
		List<Object> keyList = new ArrayList<>();
		LinkedHashMap<Object, Object> firstMap = resultList.get(0);
	    keyList.addAll(firstMap.keySet());
	    System.out.println("keyList 확인 : " + keyList);
	    System.out.println("keyList 확인 : " + keyList.get(0));
		
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
	
	@ResponseBody
    @RequestMapping(value = "/excelUploadAjax.do", method = RequestMethod.POST)
        public ModelAndView excelUploadAjax(MultipartFile testFile, MultipartHttpServletRequest request) throws  Exception{
        
        System.out.println("업로드 진행");
        
        MultipartFile excelFile = request.getFile("excelFile");
        
        if(excelFile == null || excelFile.isEmpty()) {
            throw new RuntimeException("엑셀파일을 선택해 주세요");
        }
        System.out.println("업로드 진행2");
        
        File destFile = new File("C:\\upload\\"+excelFile.getOriginalFilename());
        System.out.println("업로드 진행3");
        
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
		
		System.out.println("csv업로드 진행");
		
        // uuid 생성 
        UUID uuid = UUID.randomUUID();
		
		MultipartFile csvFile = request.getFile("csvFile");
		
		if(csvFile == null || csvFile.isEmpty()) {
			throw new RuntimeException("csv파일을 선택해 주세요");
		}
		System.out.println("업로드 진행2");
		
		File destFile = new File("C:\\upload\\"+uuid.toString()+"_"+csvFile.getOriginalFilename());
		System.out.println("업로드 진행3");
		System.out.println("파일이름확인 : " + destFile.getName());
		System.out.println("파일경로확인 : " + destFile.getPath());
		
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
		
		System.out.println("123456789");
		System.out.println("- paramMap : " + paramMap);
		
		String filePath = (String)paramMap.get("filePath");
		String fileNm = (String)paramMap.get("fileNm");
		
		System.out.println("csv read 진행");
		
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
                System.out.println(tmpList);
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
		
		System.out.println("csv readnWrite 진행");
		
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
		
		System.out.println("파일업다운 섭밋됨111");
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
		
		System.out.println("파일업다운 섭밋됨222");
		
		
		
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
		
		System.out.println("selectfileList##");
		
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
		
		System.out.println("selectFileDetailList##");
		System.out.println("파라미터로 받아오는지 확인읋 하자 : " + fileVO);
		
		
		List<Map<String, Object>> fileDList = pagingExcelService.selectfileDetailList(fileVO);
		Map<String, Object> returnmap = new HashMap<String, Object>();
		returnmap.put("fileDList", fileDList);
		
		return returnmap;
	}
	
	//submit으로 파일 다운로드
	@RequestMapping(value = "/selectFileDownload.do", method = RequestMethod.POST)
	public void selectFileDownload(@RequestParam Map<String, Object> paramMap, FileVO fileVO, HttpServletRequest request, HttpServletResponse response) throws  Exception{
		
		System.out.println("selectFileDownload##");
		System.out.println("파라미터로 받아오는지 확인읋 하자22 : " + fileVO);
		
		//선택한 파일 정보 가져오기
		FileVO fileInfo = pagingExcelService.selectFileDownload(fileVO);
		
		System.out.println("파일 확인 : " + fileInfo.getFile_path() + " + " + fileInfo.getFile_name());
		
		File src = new File(fileInfo.getFile_path()); 	//TRUE -> path만 작성하니까 됨(파일 이름 빼고)
		System.out.println("파일 존재 여부 : " + src.exists());
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));

		long fileSize = src.length();
		
		String outputName = URLEncoder.encode(fileInfo.getFile_name(), "UTF-8"); //한글 인코딩 처리 
		System.out.println("아웃풋네임 : " + outputName);
		
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
	
	//Ajax로 파일 다운로드(XMLHttpRequest의 responseType 을 blob으로 설정 후 진행)
	@ResponseBody
	@RequestMapping(value = "/selectFileDownloadAjax.do", method = RequestMethod.POST)
	public void selectFileDownloadAjax(@RequestParam Map<String, Object> paramMap, FileVO fileVO, HttpServletRequest request, HttpServletResponse response) throws  Exception{
		
		System.out.println("selectFileDownload##");
		System.out.println("파라미터로 받아오는지 확인읋 하자22@@@ : " + fileVO);
		
		//선택한 파일 정보 가져오기
		FileVO fileInfo = pagingExcelService.selectFileDownload(fileVO);
		System.out.println("파일 확인 : " + fileInfo.getFile_path() + " + " + fileInfo.getFile_name());
		
		File src = new File(fileInfo.getFile_path()); 	//TRUE -> path만 작성하니까 됨(파일 이름 빼고)
		System.out.println("파일 존재 여부 : " + src.exists());
		
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
		
		System.out.println("아웃풋네임 : " + outputName);
		
		response.setContentLength( (int) fileSize ); 
		response.setHeader("Content-Length", String.valueOf( src.length() ) ); 
		response.setHeader("Content-Disposition", "attachment; filename=\"" + outputName + "\""); 
		response.setContentType("application/octet-stream;"); 
		response.setCharacterEncoding("UTF-8"); 
		response.setHeader("Content-Transfer-Encoding", "binary;");
		
		System.out.println("response 설정 : ");
		
		try {
			// 서버의 파일 입력 스트림 
			FileInputStream fis = new FileInputStream(src); 
			System.out.println("FileInputStream 설정 : ");
			
			// 클라이언트 응답 출력 스트림 
			OutputStream out = response.getOutputStream();
			System.out.println("OutputStream 설정 : ");
			
			FileCopyUtils.copy(fis, out); 	//FileCopyUtils : 자동으로 flush, close 됨
			System.out.println("FileCopyUtils 설정 : ");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}//end
	
	
	
	
	

}