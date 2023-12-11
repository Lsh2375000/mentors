package kr.nomadlab.progrow_project.mentoring;


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
public class NoticeReplyServiceImpl implements NoticeReplyService {
    private final ModelMapper modelMapper;
    private final NoticeReplyMapper noticeReplyMapper;
    // 제발..

    @Override
    public Long register(NoticeReplyDTO noticeReplyDTO) {
        NoticeReplyVO noticeReplyVO = modelMapper.map(noticeReplyDTO, NoticeReplyVO.class);
        noticeReplyMapper.register(noticeReplyVO);
        return noticeReplyVO.getRno();
    }

    @Override
    public NoticeReplyDTO read(Long rno) {
        NoticeReplyVO noticeReplyVO = noticeReplyMapper.read(rno);
        log.info("ReplyServiceImpl..read / replyVO = " + noticeReplyVO);
        NoticeReplyDTO noticeReplyDTO = modelMapper.map(noticeReplyVO, NoticeReplyDTO.class);
        log.info("ReplyServiceImpl..read / replyDTO = " + noticeReplyDTO);
        return noticeReplyDTO;
    }

    @Override
    public void modify(NoticeReplyDTO noticeReplyDTO) {
        log.info("ReplyServiceImpl..modify");
        NoticeReplyVO noticeReplyVO = modelMapper.map(noticeReplyDTO, NoticeReplyVO.class);
        noticeReplyMapper.modify(noticeReplyVO);
    }

    @Override
    public void remove(Long rno) {
        noticeReplyMapper.remove(rno);
    }

    @Override
    public PageResponseDTO<NoticeReplyDTO> getList(int num, PageRequestDTO pageRequestDTO) {
        List<NoticeReplyVO> voList = noticeReplyMapper.selectList(num, pageRequestDTO.getSkip(), pageRequestDTO.getSize());
        List<NoticeReplyDTO> dtoList = new ArrayList<>();
        for(NoticeReplyVO replyVO : voList) {
            dtoList.add(modelMapper.map(replyVO, NoticeReplyDTO.class));
        }
        int total= noticeReplyMapper.getCount(num);

        return PageResponseDTO.<NoticeReplyDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public int getCount(int num) {
        return noticeReplyMapper.getCount(num);
    }


    // ////////////////// 안씀
    @Override
    public List<NoticeReplyDTO> getAllList(Long num) {
        List<NoticeReplyVO> voList = noticeReplyMapper.selectAll(num);
        List<NoticeReplyDTO> dtoList = new ArrayList<>();

        for (NoticeReplyVO noticeReplyVO : voList) {
            dtoList.add(modelMapper.map(noticeReplyVO, NoticeReplyDTO.class));
        }

        return dtoList;
    }


}
