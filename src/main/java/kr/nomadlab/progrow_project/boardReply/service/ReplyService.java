package kr.nomadlab.progrow_project.boardReply.service;

import kr.nomadlab.progrow_project.common.PageRequestDTO;
import kr.nomadlab.progrow_project.common.PageResponseDTO;
import kr.nomadlab.progrow_project.boardReply.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO); //댓글 추가

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    //페이지 별 리플 목록
    PageResponseDTO<ReplyDTO> getList(int boardNo, PageRequestDTO pageRequestDTO);

    int getCount(int boardNo); //전체 리플 갯수




}
