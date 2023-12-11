package kr.nomadlab.progrow_project.boardReply.service;

import kr.nomadlab.progrow_project.boardReply.mapper.ReplyMapper;
import kr.nomadlab.progrow_project.boardReply.domain.ReplyVO;
import kr.nomadlab.progrow_project.boardReply.dto.ReplyDTO;
import kr.nomadlab.progrow_project.common.PageRequestDTO;
import kr.nomadlab.progrow_project.common.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ModelMapper modelMapper;
    private final ReplyMapper replyMapper;



    @Override
    public Long register(ReplyDTO replyDTO) { //댓글 작성
        log.info("Reply...regisert...");
        log.info(replyDTO);
        ReplyVO replyVO = modelMapper.map(replyDTO, ReplyVO.class);
        log.info(replyVO);
        replyMapper.register(replyVO);
        return replyVO.getRno();
    }

    @Override
    public ReplyDTO read(Long rno) {
        log.info("board..rno :" + rno);
        ReplyVO replyVO = replyMapper.read(rno);
        log.info(replyVO);
        ReplyDTO replyDTO = modelMapper.map(replyVO, ReplyDTO.class);
        log.info(replyDTO);
        return replyDTO;
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
      log.info("Reply..modify");
      ReplyVO replyVO = modelMapper.map(replyDTO, ReplyVO.class);
       replyMapper.modify(replyVO);
    }

    @Override
    public void remove(Long rno) {
        log.info("Reply..delete");
        replyMapper.delete(rno);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getList(int boardNo, PageRequestDTO pageRequestDTO) {

        List<ReplyVO> replyVOList = replyMapper.selectList(boardNo, pageRequestDTO.getSkip(), pageRequestDTO.getSize());
        List<ReplyDTO> dtoList = new ArrayList<>();
        for (ReplyVO replyVO : replyVOList){
            dtoList.add(modelMapper.map(replyVO, ReplyDTO.class));
        }
        int total = replyMapper.getCount(boardNo);

        return PageResponseDTO.<ReplyDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public int getCount(int boardNo) {
        return replyMapper.getCount(boardNo);
    }


}
