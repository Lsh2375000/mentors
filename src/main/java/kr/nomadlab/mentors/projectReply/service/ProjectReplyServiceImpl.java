package kr.nomadlab.mentors.projectReply.service;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.projectReply.domain.ProjectReplyVO;
import kr.nomadlab.mentors.projectReply.dto.ProjectReplyDTO;
import kr.nomadlab.mentors.projectReply.mapper.ProjectReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProjectReplyServiceImpl implements ProjectReplyService {

    private final ModelMapper modelMapper;
    private final ProjectReplyMapper projectReplyMapper;



    @Override
    public Long registerReply(ProjectReplyDTO projectReplyDTO) { // 댓글, 대댓글 등록
        log.info("registerReply...");
        log.info(projectReplyDTO);

        ProjectReplyVO projectReplyVO = modelMapper.map(projectReplyDTO, ProjectReplyVO.class);
        projectReplyMapper.insertReply(projectReplyVO);

        Long pjrNo = projectReplyVO.getPjrNo();

        if (projectReplyVO.getParentNo() == null) { // 댓글인 경우
            projectReplyMapper.updateParentNo(pjrNo);
        }
        
        return pjrNo;
    }

    @Override
    public ProjectReplyDTO getReply(Long pjrNo) { // 댓글, 대댓글 조회
        log.info("getReply...");
        log.info("pjrNo: " + pjrNo);

        ProjectReplyVO projectReplyVO = projectReplyMapper.selectReply(pjrNo);
        ProjectReplyDTO projectReplyDTO = modelMapper.map(projectReplyVO, ProjectReplyDTO.class);

        return projectReplyDTO;
    }

    @Override
    public void modifyReply(ProjectReplyDTO projectReplyDTO) { // 댓글 수정
      log.info("modifyReply...");

      ProjectReplyVO projectReplyVO = modelMapper.map(projectReplyDTO, ProjectReplyVO.class);

      projectReplyMapper.updateReply(projectReplyVO);
    }

    @Override
    public void removeReply(Long pjrNo) { // 댓글, 대댓글 삭제
        log.info("removeReply...");
        projectReplyMapper.deleteReply(pjrNo);
    }

    @Override
    public PageResponseDTO<ProjectReplyDTO> getCommentWithReplyList(Long projectNo, PageRequestDTO pageRequestDTO) { // 댓글, 대댓글 목록 조회

        List<ProjectReplyVO> voList = projectReplyMapper.selectCommentWithReplyList(projectNo, pageRequestDTO.getSkip(), pageRequestDTO.getSize());
        List<ProjectReplyDTO> dtoList = new ArrayList<>();

        voList.forEach(projectReplyVO -> dtoList.add(modelMapper.map(projectReplyVO, ProjectReplyDTO.class)));
        int total = projectReplyMapper.getCount(projectNo);

        return PageResponseDTO.<ProjectReplyDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
