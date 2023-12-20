package kr.nomadlab.mentors.board.mapper;

import kr.nomadlab.mentors.board.domain.BoardLikeVO;
import kr.nomadlab.mentors.board.domain.BoardVO;
import kr.nomadlab.mentors.board.domain.HashTagVO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class BoardMapperTest {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    void selectBoardListTest() {
        String sort = "reply";
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("twc")
                .keyword("등록")
                .sort(sort)
                .hashTag("Tag,등록")
                .build();
        List<BoardVO> boardVOList = boardMapper.selectBoardList(pageRequestDTO);
        boardVOList.forEach(log::info);

    }

    @Test
    void insertLikeTest() {
        Long boardNo = 18L;
        Long mNo = 2L;
        BoardLikeVO boardLikeVO = BoardLikeVO.builder()
                .boardNo(boardNo)
                .mno(mNo)
                .build();

        boardMapper.insertLike(boardLikeVO);
    }

    @Test
    void selectLikeListTest() {
        Long boardNo = 18L;
        List<BoardLikeVO> boardLikeVOList = boardMapper.selectLikeList(boardNo);
        boardLikeVOList.forEach(log::info);
    }

    @Test
    void insertTagTest() {
        Long boardNo = 17L;
        String tagName = "Tag";
        HashTagVO hashTagVO = HashTagVO.builder()
                .boardNo(boardNo)
                .tagName(tagName)
                .build();
        boardMapper.insertTag(hashTagVO);
    }

    @Test
    void removeTagTest() {
        Long htNo = 2L;
        boardMapper.deleteTag(htNo);
    }

    @Test
    void getCountTest() {
        int count = boardMapper.getCount(PageRequestDTO.builder().build());
        log.info(count);
    }
}