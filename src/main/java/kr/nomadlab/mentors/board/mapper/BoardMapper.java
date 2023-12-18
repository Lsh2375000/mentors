package kr.nomadlab.mentors.board.mapper;

import kr.nomadlab.mentors.board.domain.BoardLikeVO;
import kr.nomadlab.mentors.board.domain.BoardVO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardMapper {
    String getTime();

    void insertBoard(BoardVO boardVO); //게시글 생성

    void deleteBoard(Long boardNo); // 게시글 삭제

    BoardVO selectBoard(Long boardNo); // 게시글 조회

    void updateBoard(BoardVO boardVO); // 게시글 수정

    int getCount(PageRequestDTO pageRequestDTO); // 게시글 총 갯수

    List<BoardVO> selectBoardList(PageRequestDTO pageRequestDTO); // 게시글 전체 조회

    void updateHit(Long boardNo); // 조회수 업데이트

    void insertLike(BoardLikeVO boardLikeVO); // 좋아요 추가

    List<BoardLikeVO> selectLikeList(Long boardNo); // 해당 게시글 좋아요 조회

}
