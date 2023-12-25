package kr.nomadlab.mentors.projectReply.service;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.projectReply.dto.ProjectReplyDTO;

public interface ProjectReplyService {

    Long registerReply(ProjectReplyDTO projectReplyDTO); // 댓글 등록

    ProjectReplyDTO getReply(Long pjrNo);

    void modifyReply(ProjectReplyDTO projectReplyDTO);

    void removeReply(Long pjrNo);

    //페이지 별 리플 목록
    PageResponseDTO<ProjectReplyDTO> getCommentWithReplyList(Long projectNo, PageRequestDTO pageRequestDTO);





}
