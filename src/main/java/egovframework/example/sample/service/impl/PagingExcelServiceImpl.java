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
package egovframework.example.sample.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.FileVO;
import egovframework.example.sample.service.PagingExcelService;
import egovframework.example.sample.service.PagingExcelVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
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

@Service("pagingExcelService")
public class PagingExcelServiceImpl extends EgovAbstractServiceImpl implements PagingExcelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PagingExcelServiceImpl.class);

	/** SampleDAO */
	// TODO ibatis 사용
//	@Resource(name = "sampleDAO")
//	private PagingExcelDAO sampleDAO;
	
	
	// TODO mybatis 사용
	  @Resource(name="pagingExcelDAO")
		private PagingExcelDAO pagingExcelDAO;

	/** ID Generation */
//	@Resource(name = "egovIdGnrService")
//	private EgovIdGnrService egovIdGnrService;

	
	@Override
	public List<Map<Object, Object>> selectDataList() {
		return pagingExcelDAO.selectDataList();
	}
	
	@Override
	public List<Map<Object, Object>> selectPDataList(PagingExcelVO pagingExcelVO) {
		return pagingExcelDAO.selectPDataList(pagingExcelVO);
	}
	
	@Override
	public List<Map<Object, Object>> selectExcelDownload(PagingExcelVO pagingExcelVO) {
		return pagingExcelDAO.selectExcelDownload(pagingExcelVO);
	}
	
	@Override
	public List<LinkedHashMap<Object, Object>> selectExcelDownloadLinked(PagingExcelVO pagingExcelVO) {
		return pagingExcelDAO.selectExcelDownloadLinked(pagingExcelVO);
	}
	
	@Override
	public List<PagingExcelVO> selectExcelDownloadList(PagingExcelVO pagingExcelVO) {
		return pagingExcelDAO.selectExcelDownloadList(pagingExcelVO);
	}
	
	/*엑셀 업로드*/
	@Override
	public void excelUpload(File destFile) {

		ExcelReadOption excelReadOption = new ExcelReadOption();
		
        //파일경로 추가
        excelReadOption.setFilePath(destFile.getAbsolutePath());
        
        //추출할 컬럼명 추가
        excelReadOption.setOutputColumns("A", "B", "C", "D", "E");
//        excelReadOption.setOutputColumns("번호", "제목", "작성자", "작성일", "조회수");

        //시작행
        excelReadOption.setStartRow(4);
        
        List<Map<String, String>>excelContent  = ExcelRead.read(excelReadOption);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("excelContent", excelContent);
        
        try {
        	pagingExcelDAO.insertExcel(paramMap);
        }catch(Exception e) {
            e.printStackTrace();
        }
	
	}
	
	//csv파일 업로드
	@Override
	public int insertFile1(MultipartFile upfile, MultipartHttpServletRequest request) {
		//날짜 포멧(제목에 붙일)
		Date now = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		String nowTime1 = sdf1.format(now);
		System.out.println(nowTime1);

		/*파일 업로드*/
		MultipartFile upDownFile = request.getFile("fileUpDown");

		if(upDownFile == null || upDownFile.isEmpty()) {
            throw new RuntimeException("csv 파일을 선택해 주세요");
        }
        System.out.println("업로드 진행2");
        
        //저장할 파일이름 변경(확장자 제거)
        String fileName = upDownFile.getOriginalFilename();
        int idx = fileName.lastIndexOf(".");
        String NfileName =fileName.substring(0,idx);
        System.out.println("NfileName 확인 : " + NfileName);
        
        File destFile = new File("C:\\Upload\\"+NfileName + "_" + nowTime1 + ".csv" );
        System.out.println("업로드 진행3");
        

        
        try {
            //내가 설정한 위치에 내가 올린 파일을 만들고 
        	upDownFile.transferTo(destFile);
        }catch(IOException e) {
        	 e.printStackTrace(); // 예외가 발생했을 때 에러 로그 출력
        	    throw new RuntimeException("파일 전송 중 오류 발생: " + e.getMessage(), e);
        }
		
        return 0;
		
	}
	
	//파일 그룹넘버 조회
	@Override
	public int selectFileGroupNo() {
		return pagingExcelDAO.selectFileGroupNo();
	}
	
	
	//csv파일 업로드
	@Override
	public int insertFile2(Map<String, Object> paramMap, HttpServletRequest request, int groupNo) {
		
		//날짜 포멧(제목에 붙일)
		Date now = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String nowTime1 = sdf1.format(now);
		System.out.println(nowTime1);

		System.out.println("insertFile2 @@@");
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

		List<FileVO> listfile = new ArrayList<>();
		
		List<MultipartFile> fileList = multipartHttpServletRequest.getFiles("fileUpDown");
		for(int i=0; i<fileList.size(); i++) {
		    MultipartFile file = fileList.get(i);
		    String fileName = file.getOriginalFilename();
		    System.out.println("파일뽑기 fileList : " + fileName);
		}
		
        Iterator<String> files = multipartHttpServletRequest.getFileNames();	//출력값 : fileUpDown

        int resultcval = 0;
        
        for (MultipartFile mf : fileList) {
        	
        	if (!fileList.isEmpty()) {
        		FileVO fileVO = new FileVO();

        		fileVO.setFile_group(groupNo); 
        		fileVO.setOriginal_name(mf.getOriginalFilename()); 
        		
        		int idx = fileVO.getOriginal_name().lastIndexOf(".");
        		String fileNm = fileVO.getOriginal_name().substring(0,idx);
        		String fileExten = fileVO.getOriginal_name().substring(fileVO.getOriginal_name().lastIndexOf(".")+1);
        		String saveFileNm = fileNm + "_" +  nowTime1 +"." + fileExten;
        		
        		fileVO.setFile_name(saveFileNm);
        		
        		fileVO.setFile_extension(fileExten);
        		String filepath = "C:\\Upload\\multifile\\" + saveFileNm;
        		fileVO.setFile_path(filepath);
        		
        		
        		System.out.println("넣은 값 확인 : " + fileVO);
        		listfile.add(fileVO);
        		
                File orgFile = new File(filepath);
                try {
                	mf.transferTo(orgFile);
    			} catch (IllegalStateException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				e.printStackTrace();
    				throw new RuntimeException("파일 전송 중 오류 발생: " + e.getMessage(), e);
    			}
                resultcval += pagingExcelDAO.insertFileUp(fileVO); 
        	}//if
        }//for
        
        
        System.out.println("listfile 확인! : " + listfile);
        System.out.println("resultcval 확인! : " + resultcval);
        
        
        return resultcval;
        
        
        
        
        
//        return 0;
		
	}

	//콤보박스  list 뽑기
	@Override
	public List<Map<String, Object>> selectfileList() {
		return pagingExcelDAO.selectfileList();
	}
	

	//파일 상세 콤보박스 list 조회
	@Override
	public List<Map<String, Object>> selectfileDetailList(FileVO fileVO) {
		return pagingExcelDAO.selectfileDetailList(fileVO);
	}
	
	@Override
	public FileVO selectFileDownload(FileVO fileVO) {
		return pagingExcelDAO.selectFileDownload(fileVO);
	}
	
	
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
//	@Override
//	public String insertSample(SampleVO vo) throws Exception {
//		LOGGER.debug(vo.toString());
//
//		/** ID Generation Service */
//		String id = egovIdGnrService.getNextStringId();
//		vo.setId(id);
//		LOGGER.debug(vo.toString());
//
//		sampleDAO.insertSample(vo);
//		return id;
//	}

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
//	@Override
//	public void updateSample(SampleVO vo) throws Exception {
//		sampleDAO.updateSample(vo);
//	}

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
//	@Override
//	public void deleteSample(SampleVO vo) throws Exception {
//		sampleDAO.deleteSample(vo);
//	}

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
//	@Override
//	public SampleVO selectSample(SampleVO vo) throws Exception {
//		SampleVO resultVO = sampleDAO.selectSample(vo);
//		if (resultVO == null)
//			throw processException("info.nodata.msg");
//		return resultVO;
//	}

	/**
	 * 글 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
//	@Override
//	public List<?> selectSampleList(SampleDefaultVO searchVO) throws Exception {
//		return sampleDAO.selectSampleList(searchVO);
//	}

	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
//	@Override
//	public int selectSampleListTotCnt(SampleDefaultVO searchVO) {
//		return sampleDAO.selectSampleListTotCnt(searchVO);
//	}

}