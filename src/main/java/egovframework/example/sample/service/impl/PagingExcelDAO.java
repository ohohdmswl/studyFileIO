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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import egovframework.example.sample.service.FileVO;
import egovframework.example.sample.service.PagingExcelVO;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : SampleDAO.java
 * @Description : Sample DAO Class
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

@Repository("pagingExcelDAO")
public class PagingExcelDAO extends EgovAbstractMapper {

	public List<Map<Object, Object>> selectDataList() {
		return selectList("selectDataList");
	}

	public List<Map<Object, Object>> selectPDataList(PagingExcelVO pagingExcelVO) {
		return selectList("selectPDataList",pagingExcelVO);
	}

	public List<Map<Object, Object>> selectExcelDownload(PagingExcelVO pagingExcelVO) {
		return selectList("selectExcelDownload",pagingExcelVO);
	}

	public List<LinkedHashMap<Object, Object>> selectExcelDownloadLinked(PagingExcelVO pagingExcelVO) {
		return selectList("selectExcelDownloadLinked",pagingExcelVO);
	}

	public List<PagingExcelVO> selectExcelDownloadList(PagingExcelVO pagingExcelVO) {
		return selectList("selectExcelDownloadList",pagingExcelVO);
	}

	public void insertExcel(Map<String, Object> paramMap) {
		insert("insertExcel", paramMap);
	}

	public int insertFileUp(FileVO fileVO) {
		return insert("insertFileUp", fileVO);
	}

	public int selectFileGroupNo() {
		return selectOne("selectFileGroupNo");
	}

	//콤보박스 list 가져오기
	public List<Map<String, Object>> selectfileList() {
		return selectList("selectfileList");
	}

	//파일 상세 콤보박스 list 조회
	public List<Map<String, Object>> selectfileDetailList(FileVO fileVO) {
		return selectList("selectfileDetailList",fileVO);
	}

	//다운로드 받을 파일 정보 조회
	public FileVO selectFileDownload(FileVO fileVO) {
		return selectOne("selectFileDownload", fileVO);
	}

	
	
	
	
	
	
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
//	public String insertSample(SampleVO vo) throws Exception {
//		return (String) insert("sampleDAO.insertSample", vo);
//	}

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
//	public void updateSample(SampleVO vo) throws Exception {
//		update("sampleDAO.updateSample", vo);
//	}

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
//	public void deleteSample(SampleVO vo) throws Exception {
//		delete("sampleDAO.deleteSample", vo);
//	}

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
//	public SampleVO selectSample(SampleVO vo) throws Exception {
//		return (SampleVO) select("sampleDAO.selectSample", vo);
//	}

	/**
	 * 글 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 목록
	 * @exception Exception
	 */
//	public List<?> selectSampleList(SampleDefaultVO searchVO) throws Exception {
//		return list("sampleDAO.selectSampleList", searchVO);
//	}

	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 총 갯수
	 * @exception
	 */
//	public int selectSampleListTotCnt(SampleDefaultVO searchVO) {
//		return (Integer) select("sampleDAO.selectSampleListTotCnt", searchVO);
//	}

}
