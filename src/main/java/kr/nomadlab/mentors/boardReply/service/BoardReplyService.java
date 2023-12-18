package kr.nomadlab.mentors.boardReply.service;

import kr.nomadlab.mentors.boardReply.dto.ReplyDTO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;

public interface BoardReplyService {

    Long registerReply(ReplyDTO replyDTO); // 댓글 등록

    ReplyDTO getReply(Long rno);

    void modifyReply(ReplyDTO replyDTO);

    void removeReply(Long rno);

    //페이지 별 리플 목록
    PageResponseDTO<ReplyDTO> getCommentWithReplyList(Long boardNo, PageRequestDTO pageRequestDTO);





}
