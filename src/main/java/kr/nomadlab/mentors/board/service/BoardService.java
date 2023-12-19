package kr.nomadlab.mentors.board.service;

import kr.nomadlab.mentors.board.dto.BoardDTO;
import kr.nomadlab.mentors.board.dto.BoardLikeDTO;
import kr.nomadlab.mentors.board.dto.HashTagDTO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;


public interface BoardService {
    
    void registerBoard(BoardDTO boardDTO); // 게시글 등록

    void removeBoard(Long boardNo); //게시글 삭제

    BoardDTO getBoard(Long boardNo, String mode); //게시글 조회

    void modifyBoard(BoardDTO boardDTO); // 게시글 수정

    PageResponseDTO<BoardDTO> getBoardList(PageRequestDTO pageRequestDTO); // 게시글 목록 조회
    
    Long addLike(BoardLikeDTO boardLikeDTO); // 게시글 좋아요 추가
    
    Boolean removeLike(Long blNo); // 게시글 좋아요 삭제
    
    void addHashTag(HashTagDTO hashTagDTO); // 해쉬태그 추가
    
    void removeHashTag(Long htNo); // 해쉬태그 삭제

}
