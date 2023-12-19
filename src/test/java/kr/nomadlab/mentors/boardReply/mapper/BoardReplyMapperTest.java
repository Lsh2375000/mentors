package kr.nomadlab.mentors.boardReply.mapper;

import kr.nomadlab.mentors.boardReply.domain.ReplyVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class BoardReplyMapperTest {
    @Autowired
    private BoardReplyMapper boardReplyMapper;

    @Test
    void selectReplyWithChildrenTest() {
        Long boardNo = 17L;
        List<ReplyVO> voList = boardReplyMapper.selectCommentWithReplyList(boardNo, 0, 10);
        voList.forEach(log::info);
    }
}