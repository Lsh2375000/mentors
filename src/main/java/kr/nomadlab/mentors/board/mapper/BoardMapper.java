package kr.nomadlab.mentors.board.mapper;

import kr.nomadlab.mentors.board.domain.BoardLikeVO;
import kr.nomadlab.mentors.board.domain.BoardVO;
import kr.nomadlab.mentors.board.domain.HashTagVO;
import kr.nomadlab.mentors.board.dto.HashTagDTO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BoardMapper {

    void insertBoard(BoardVO boardVO); //게시글 생성

    void deleteBoard(Long boardNo); // 게시글 삭제

    BoardVO selectBoard(Long boardNo); // 게시글 조회

    void updateBoard(BoardVO boardVO); // 게시글 수정

    int getCount(PageRequestDTO pageRequestDTO); // 게시글 총 갯수

    List<BoardVO> selectBoardList(PageRequestDTO pageRequestDTO); // 게시글 전체 조회

    void updateHit(Long boardNo); // 조회수 업데이트

    void insertLike(BoardLikeVO boardLikeVO); // 좋아요 추가

    Boolean deleteLike(Long blNo); // 좋아요 삭제

    List<BoardLikeVO> selectLikeList(Long boardNo); // 해당 게시글 좋아요 조회
    
    List<HashTagVO> selectTagList(Long boardNo); // 태그 목록 조회

    List<HashTagDTO> selectTopTagList(); // 상위 10개 태그 조회
    
    void insertTag(HashTagVO hashTagVO); // 태그 추가

    void deleteTag(Long boardNo); // 태그 삭제
    
    List<BoardVO> selectMyBoardList(@Param("mno") Long mno,
                                    @Param("skip") int skip,
                                    @Param("size") int size); // 본인 게시글 목록 조회

    int getMyCount(Long mno); // 내가쓴 게시글 총 갯수

}
