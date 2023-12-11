package kr.nomadlab.progrow_project.board.mapper;

import kr.nomadlab.progrow_project.board.domain.BoardVO;
import kr.nomadlab.progrow_project.board.dto.BoardListReplyCountDTO;
import kr.nomadlab.progrow_project.common.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardMapper {
    String getTime();

    void insert(BoardVO boardVO); //게시글 생성 메서드

    void removeOne(int boardVO);

    BoardVO selectOne(int boarNo);

    void modify(BoardVO boardVO);


    void count(BoardVO boardVO); //게시물 총 갯수

     int getCount(PageRequestDTO pageRequestDTO);

    List<BoardListReplyCountDTO> selectList(PageRequestDTO pageRequestDTO);

    int hit(int boardNo);


    // myPage 자신이 적성했던 게시글
    List<BoardVO> myPage(String id);

}
