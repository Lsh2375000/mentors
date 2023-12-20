package kr.nomadlab.mentors.boardReply.service;

import kr.nomadlab.mentors.boardReply.dto.ReplyDTO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class ReplyServiceTest {
    @Autowired
    private BoardReplyService replyService;

    @Test
    void registerReplyTest() {
        Long boardNo = 17L;
        ReplyDTO replyDTO = ReplyDTO.builder()
                .content("댓글 작성 테스트")
                .writer("작성자 테스트")
                .boardNo(boardNo)
                .build();
        replyService.registerReply(replyDTO);
    }

    @Test
    void getReplyTest() {
        Long rno = 1L;
        ReplyDTO replyDTO = replyService.getReply(rno);
        log.info(replyDTO);
    }

    @Test
    void modifyReplyTest() {
        Long rno = 2L;
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(rno)
                .content("댓글 수정 테스트")
                .build();
        replyService.modifyReply(replyDTO);
    }

    @Test
    void removeReplyTest() {
        Long rno = 3L;
        replyService.removeReply(rno);
    }

    @Test
    void getReplyListTest() {
        Long boardNo = 17L;
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getCommentWithReplyList(boardNo, pageRequestDTO);
        log.info(responseDTO);
    }

    @Test
    void getCountTest() {

    }
}