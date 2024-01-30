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
package egovframework.example.sample.service;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @Class Name : EgovSampleService.java
 * @Description : EgovSampleService Class
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
public interface PagingExcelService {

	//표 데이터 가져오기
	List<Map<Object, Object>> selectDataList();

	//표 데이터 가져오기 - pagehelper 사용
	List<Map<Object, Object>> selectPDataList(PagingExcelVO pagingExcelVO);

	
	//엑셀 다운로드할 표 데이터 가져오기
	List<Map<Object, Object>> selectExcelDownload(PagingExcelVO pagingExcelVO);

	//엑셀 다운로드할 표 데이터 가져오기(LinkedHashMap)
	List<LinkedHashMap<Object, Object>> selectExcelDownloadLinked(PagingExcelVO pagingExcelVO);

	
	//엑셀 다운로드할 표 데이터 가져오기(List로 가져오기)
	List<PagingExcelVO> selectExcelDownloadList(PagingExcelVO pagingExcelVO);

	
	//엑셀파일 업로드
	void excelUpload(File destFile);

	//csv파일 업로드
	int insertFile1(MultipartFile upDownFile, MultipartHttpServletRequest request);
	
	int insertFile2(Map<String, Object> paramMap, HttpServletRequest request, int groupNo);

	Map<String, Object> insertCsvFile(Map<String, Object> paramMap, HttpServletRequest request, int groupNo);

	//파일 그룹넘버 조회
	int selectFileGroupNo();

	//콤보박스 list 뽑기
	List<Map<String, Object>> selectfileList();

	//파일 상세 콤보박스 list 조회
	List<Map<String, Object>> selectfileDetailList(FileVO fileVO);

	//다운로드 받을 파일 정보 조회
	FileVO selectFileDownload(FileVO fileVO);

	//게시글 조회
	PagingExcelVO selectBoardDetail(PagingExcelVO pagingExcelVO);

	//게시글 파일 등록
	Map<String, Object> insertFiles(MultipartHttpServletRequest request, int boardNo);

	//게시글 번호 조회
	int selectBoardNo();
	
	

	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
//	String insertSample(SampleVO vo) throws Exception;

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
//	void updateSample(SampleVO vo) throws Exception;

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
//	void deleteSample(SampleVO vo) throws Exception;

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
//	SampleVO selectSample(SampleVO vo) throws Exception;

	/**
	 * 글 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
//	List<?> selectSampleList(SampleDefaultVO searchVO) throws Exception;

	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
//	int selectSampleListTotCnt(SampleDefaultVO searchVO);

}
