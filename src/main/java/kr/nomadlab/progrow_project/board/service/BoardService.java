package kr.nomadlab.progrow_project.board.service;

import kr.nomadlab.progrow_project.board.dto.BoardDTO;
import kr.nomadlab.progrow_project.board.dto.BoardListReplyCountDTO;
import kr.nomadlab.progrow_project.common.PageRequestDTO;
import kr.nomadlab.progrow_project.common.PageResponseDTO;

import java.util.List;


public interface BoardService {

//    void add(BoardDTO boardDTO, List<MultipartFile> files); // 게시글 업로드
    void add(BoardDTO boardDTO); // 게시글 업로드

    void removeOne(int boardNo); //게시글 하나 선택해서 삭제

    BoardDTO selectOne(int boardNo, String mode); //게시물 하나 선택해서 읽음

    void modify(BoardDTO boardDTO); // 게시글 수정

    int getCount(PageRequestDTO pageRequestDTO); //페이징

    PageResponseDTO<BoardListReplyCountDTO> getList(PageRequestDTO pageRequestDTO);

    List<BoardDTO> myPage(String mid);

}
