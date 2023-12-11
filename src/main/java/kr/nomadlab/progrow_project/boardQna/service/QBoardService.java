package kr.nomadlab.progrow_project.boardQna.service;


import kr.nomadlab.progrow_project.common.PageRequestDTO;
import kr.nomadlab.progrow_project.common.PageResponseDTO;
import kr.nomadlab.progrow_project.boardQna.dto.QBoardDTO;
import kr.nomadlab.progrow_project.boardQna.dto.QBoardListAllDTO;

public interface QBoardService {

    void getAll(QBoardDTO qBoardDTO); //게시판 데이터 불러오기

    QBoardDTO getOne(Long qnaBoardNo, String read); // 특정 게시글 불러오기

    void add(QBoardDTO qBoardDTO); //게시글 추가

    void deleteOne(Long qnaBoardNo); //게시글 하나 선택해서 삭제

    QBoardDTO selectOne(Long qnaBoardNo); //게시물 하나 선택해서 읽음

    void modifyOne(QBoardDTO qBoardDTO); // 게시글 수정

    PageResponseDTO<QBoardListAllDTO> getList(PageRequestDTO pageRequestDTO); // 게시글 리스트 조회




}
