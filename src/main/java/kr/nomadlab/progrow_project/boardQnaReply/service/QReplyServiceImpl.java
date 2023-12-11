package kr.nomadlab.progrow_project.boardQnaReply.service;


import kr.nomadlab.progrow_project.boardQnaReply.domain.QReplyVO;
import kr.nomadlab.progrow_project.boardQnaReply.dto.QReplyDTO;
import kr.nomadlab.progrow_project.boardQnaReply.mapper.QReplyMapper;
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
public class QReplyServiceImpl implements QReplyService {
    private final ModelMapper modelMapper; // modelMapper 의존성 주입
    private final QReplyMapper qReplyMapper; // qReplyMapper 의존성 주입

    @Override
    public Long addReplyQ(QReplyDTO qReplyDTO) { // 특정 Qna 댓글 추가
        QReplyVO qReplyVO = modelMapper.map(qReplyDTO, QReplyVO.class);
        qReplyMapper.insertQR(qReplyVO);
        Long qnaRno = qReplyVO.getQnaRno();
        log.info("qnaRno : " + qnaRno);
        return qnaRno;
    }

//    @Override
//    public Long registerReQ(QReplyDTO qReplyDTO) { // 특정 Qna 답글(대댓글) 추가
//        QReplyVO qReplyVO = modelMapper.map(qReplyDTO, QReplyVO.class);
//        qReplyMapper.insert(qReplyVO);
//        return qReplyVO.getQRno();
//    }
    @Override
    public QReplyDTO read(Long qnaRno) { // 특정 Qna 댓글 조회
        QReplyVO qReplyVO = qReplyMapper.selectOneQR(qnaRno);
        return modelMapper.map(qReplyVO, QReplyDTO.class);
    }

    @Override
    public void modifyOne(QReplyDTO qReplyDTO) { // // 특정 Qna 댓글 수정
        QReplyVO qReplyVO = modelMapper.map(qReplyDTO, QReplyVO.class);
        qReplyMapper.selectOneQR(qReplyDTO.getQnaRno());
        qReplyVO.changeQnaText(qReplyVO.getContent());
        qReplyMapper.updateOneQR(qReplyVO);


    }
    // 특정 Qna 댓글 삭제
    @Override
    public void removeOne(Long qnaRno) {
        qReplyMapper.deleteOneQR(qnaRno);
    }

    // Qna 댓글 리스트 조회
    @Override
    public PageResponseDTO<QReplyDTO> getListOfBoardQR(long qnaBoardNo, PageRequestDTO pageRequestDTO) {
        List<QReplyVO> voList = qReplyMapper.selectListOfBoardQR(qnaBoardNo, pageRequestDTO.getSkip(), pageRequestDTO.getSize());
        List<QReplyDTO> dtoList = new ArrayList<>();

        voList.forEach(qReplyVO -> dtoList.add(modelMapper.map(qReplyVO, QReplyDTO.class)));

        return PageResponseDTO.<QReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(qReplyMapper.getCountQR(qnaBoardNo))
                .build();

    }



}
