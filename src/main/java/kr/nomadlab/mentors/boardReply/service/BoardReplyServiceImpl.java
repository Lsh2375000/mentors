package kr.nomadlab.mentors.boardReply.service;

import kr.nomadlab.mentors.boardReply.domain.ReplyVO;
import kr.nomadlab.mentors.boardReply.dto.ReplyDTO;
import kr.nomadlab.mentors.boardReply.mapper.BoardReplyMapper;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardReplyServiceImpl implements BoardReplyService {

    private final ModelMapper modelMapper;
    private final BoardReplyMapper boardReplyMapper;



    @Override
    public Long registerReply(ReplyDTO replyDTO) { // 댓글, 대댓글 등록
        log.info("registerReply...");
        log.info(replyDTO);

        ReplyVO replyVO = modelMapper.map(replyDTO, ReplyVO.class);
        boardReplyMapper.insertReply(replyVO);

        Long rno = replyVO.getRno();

        if (replyVO.getParentNo() == null) { // 댓글인 경우
            boardReplyMapper.updateParentNo(rno);
        }
        
        return rno;
    }

    @Override
    public ReplyDTO getReply(Long rno) { // 댓글, 대댓글 조회
        log.info("getReply...");
        log.info("rno: " + rno);

        ReplyVO replyVO = boardReplyMapper.selectReply(rno);
        ReplyDTO replyDTO = modelMapper.map(replyVO, ReplyDTO.class);

        return replyDTO;
    }

    @Override
    public void modifyReply(ReplyDTO replyDTO) { // 댓글 수정
      log.info("modifyReply...");

      ReplyVO replyVO = modelMapper.map(replyDTO, ReplyVO.class);

      boardReplyMapper.updateReply(replyVO);
    }

    @Override
    public void removeReply(Long rno) { // 댓글, 대댓글 삭제
        log.info("removeReply...");
        boardReplyMapper.deleteReply(rno);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getCommentWithReplyList(Long boardNo, PageRequestDTO pageRequestDTO) { // 댓글, 대댓글 목록 조회

        List<ReplyVO> voList = boardReplyMapper.selectCommentWithReplyList(boardNo, pageRequestDTO.getSkip(), pageRequestDTO.getSize());
        List<ReplyDTO> dtoList = new ArrayList<>();

        voList.forEach(replyVO -> dtoList.add(modelMapper.map(replyVO, ReplyDTO.class)));
        int total = boardReplyMapper.getCount(boardNo);

        return PageResponseDTO.<ReplyDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
