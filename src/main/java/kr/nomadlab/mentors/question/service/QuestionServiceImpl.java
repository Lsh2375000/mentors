package kr.nomadlab.mentors.question.service;

import kr.nomadlab.mentors.answer.mapper.AnswerMapper;
import kr.nomadlab.mentors.board.dto.BoardDTO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.question.domain.QNAVoteVO;
import kr.nomadlab.mentors.question.domain.QuestionTagVO;
import kr.nomadlab.mentors.question.domain.QuestionVO;
import kr.nomadlab.mentors.question.dto.QNAVoteDTO;
import kr.nomadlab.mentors.question.dto.QuestionDTO;
import kr.nomadlab.mentors.question.dto.QuestionTagDTO;
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
public class QuestionServiceImpl implements QuestionService {

    private final ModelMapper modelMapper;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @Override
    public void registerQuestion(QuestionDTO questionDTO) { // 게시글 등록
        log.info("registerQuestion...");
        log.info(questionDTO);

        QuestionVO questionVO = modelMapper.map(questionDTO, QuestionVO.class);

        questionMapper.insertQuestion(questionVO);

        if (questionDTO.getTagList().size() > 0) { // 태그 목록이 존재하는 경우
            List<QuestionTagDTO> tagList = questionDTO.getTagList();
            tagList.forEach(questionTagDTO -> {
                questionTagDTO.setQno(questionVO.getQno()); // 게시글 고유번호 추가
                addQuestionTag(questionTagDTO); // DB에 태그 저장
            });
        }
    }

    @Override
    public void removeQuestion(Long qno) { // 게시글 삭제
        questionMapper.deleteQuestion(qno);
    }

    @Override
    public QuestionDTO getQuestion(Long qno, String mode) { // 게시글 조회

        QuestionVO questionVO = questionMapper.selectQuestion(qno);
        List<QNAVoteDTO> voteList = new ArrayList<>();
        List<QuestionTagDTO> tagList = new ArrayList<>();

        if (mode.equals("view")) { // mode가 view일때만
            questionMapper.updateHit(qno); // 조회수 업데이트
            List<QNAVoteVO> voteVOList = questionMapper.selectVoteList(qno); // 게시글 좋아요 조회
            voteVOList.forEach(qnaVoteVO -> voteList.add(modelMapper.map(qnaVoteVO, QNAVoteDTO.class)));
        }
        questionVO.getTagVOList().forEach(questionTagVO -> tagList.add(
                modelMapper.map(questionTagVO, QuestionTagDTO.class)));

        QuestionDTO questionDTO = modelMapper.map(questionVO, QuestionDTO.class);
        questionDTO.setVoteList(voteList);
        questionDTO.setTagList(tagList);

        return questionDTO;
    }

    @Override
    public void modifyQuestion(QuestionDTO questionDTO) { // 게시글 수정
        QuestionVO questionVO = modelMapper.map(questionDTO, QuestionVO.class);
        questionMapper.updateQuestion(questionVO);
    }

    @Override
    public PageResponseDTO<QuestionDTO> getQuestionList(PageRequestDTO pageRequestDTO) { // 전체 게시물 조회
        List<QuestionVO> voList = questionMapper.selectQuestionList(pageRequestDTO);
        List<QuestionDTO> dtoList = new ArrayList<>();

        voList.forEach(questionVO -> {
            QuestionDTO questionDTO = modelMapper.map(questionVO, QuestionDTO.class);

            if (questionVO.getTagVOList().size() > 0) { // 태그 목록이 존재하는 경우
                List<QuestionTagDTO> tagList = new ArrayList<>(); // 해시 태그 목록을 담을 리스트 객체
                questionVO.getTagVOList().forEach(questionTagVO -> tagList.add(
                        modelMapper.map(questionTagVO, QuestionTagDTO.class)));
                questionDTO.setTagList(tagList);
            }

            dtoList.add(questionDTO);
        });

        int total = questionMapper.getCount(pageRequestDTO);

        return PageResponseDTO.<QuestionDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO).build();
    }



    @Override
    public Long addVote(QNAVoteDTO qnaVoteDTO) { // 게시글 좋아요 추가
        QNAVoteVO qnaVoteVO = modelMapper.map(qnaVoteDTO, QNAVoteVO.class);
        questionMapper.insertVote(qnaVoteVO);

        return qnaVoteVO.getVno();
    }

    @Override
    public Boolean removeVote(Long vno) { // 게시글 좋아요 삭제
        Boolean result = questionMapper.deleteVote(vno);
        return result;
    }

    @Override
    public List<QuestionTagDTO> getTopTagList() {
        List<QuestionTagDTO> topTagList = questionMapper.selectTopTagList();
        return topTagList;
    }

    @Override
    public void addQuestionTag(QuestionTagDTO questionTagDTO) { // 해쉬태그 추가
        QuestionTagVO questionTagVO = modelMapper.map(questionTagDTO, QuestionTagVO.class);
        questionMapper.insertTag(questionTagVO);
    }

    @Override
    public void modifyQuestionTag(Long qno, List<QuestionTagDTO> tagList) { // 해쉬 태그 추가 및 삭제

        questionMapper.deleteTag(qno); // 해당 게시글의 태그 모두 삭제
        
        if (tagList.size() > 0) { // 태그 목록이 존재하는 경우
            tagList.forEach(this::addQuestionTag); // 수정한 태그 목록 DB에 추가
        }
    }
}
