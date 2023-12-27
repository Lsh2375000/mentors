package kr.nomadlab.mentors.answer.service;

import kr.nomadlab.mentors.answer.domain.AnswerVO;
import kr.nomadlab.mentors.answer.dto.AnswerDTO;
import kr.nomadlab.mentors.answer.mapper.AnswerMapper;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.question.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AnswerServiceImpl implements AnswerService {

    private final ModelMapper modelMapper;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;



    @Override
    public Long registerAnswer(AnswerDTO answerDTO) { // 댓글, 대댓글 등록
        log.info("registerAnswer...");
        log.info(answerDTO);

        AnswerVO answerVO = modelMapper.map(answerDTO, AnswerVO.class);
        answerMapper.insertAnswer(answerVO);

        Long ano = answerVO.getAno();

        if (answerVO.getParentNo() == null) { // 댓글인 경우
            answerMapper.updateParentNo(ano);
        }
        
        return ano;
    }

    @Override
    public AnswerDTO getAnswer(Long ano) { // 댓글, 대댓글 조회
        log.info("getAnswer...");
        log.info("ano: " + ano);

        AnswerVO answerVO = answerMapper.selectAnswer(ano);
        AnswerDTO answerDTO = modelMapper.map(answerVO, AnswerDTO.class);

        return answerDTO;
    }

    @Override
    public void modifyAnswer(AnswerDTO answerDTO) { // 댓글 수정
      log.info("modifyAnswer...");

      AnswerVO answerVO = modelMapper.map(answerDTO, AnswerVO.class);

        answerMapper.updateAnswer(answerVO);
    }

    @Override
    public void removeAnswer(Long ano) { // 댓글, 대댓글 삭제
        log.info("removeAnswer...");
        answerMapper.deleteAnswer(ano);
    }

    @Override
    public PageResponseDTO<AnswerDTO> getAnswerList(Long qno, PageRequestDTO pageRequestDTO) { // 댓글, 대댓글 목록 조회

        List<AnswerVO> voList = answerMapper.selectAnswerList(qno, pageRequestDTO.getSkip(), pageRequestDTO.getSize());
        List<AnswerDTO> dtoList = new ArrayList<>();

        voList.forEach(answerVO -> dtoList.add(modelMapper.map(answerVO, AnswerDTO.class)));
        int total = answerMapper.getCount(qno);

        return PageResponseDTO.<AnswerDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public void modifySelect(Long qno, Long ano) { // 답변 채택 상태로 변경
        answerMapper.updateSelect(ano); // 답변 채택 상태로 변경
        questionMapper.updateComplete(qno);
    }

    @Override
    public PageResponseDTO<AnswerDTO> getMyAnswerList(Long mno, PageRequestDTO pageRequestDTO) {
        List<AnswerVO> voList = answerMapper.selectMyAnswerList(mno, pageRequestDTO.getSkip(), pageRequestDTO.getSize());
        List<AnswerDTO> dtoList = new ArrayList<>();

        voList.forEach(answerVO -> dtoList.add(modelMapper.map(answerVO, AnswerDTO.class)));
        int total = answerMapper.getMyCount(mno);

        return PageResponseDTO.<AnswerDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
