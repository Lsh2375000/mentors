package kr.nomadlab.mentors.question.service;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.question.dto.QNAVoteDTO;
import kr.nomadlab.mentors.question.dto.QuestionDTO;
import kr.nomadlab.mentors.question.dto.QuestionTagDTO;

import java.util.List;


public interface QuestionService {
    
    void registerQuestion(QuestionDTO questionDTO); // 질문 등록

    void removeQuestion(Long qno); //질문 삭제

    QuestionDTO getQuestion(Long qno, String mode); //질문 조회

    void modifyQuestion(QuestionDTO questionDTO); // 질문 수정

    PageResponseDTO<QuestionDTO> getQuestionList(PageRequestDTO pageRequestDTO); // 질문 목록 조회
    
    Long addVote(QNAVoteDTO qnaVoteDTO); // 질문 추천 추가
    
    Boolean removeVote(Long vno); // 질문 추천 삭제
    
    List<QuestionTagDTO> getTopTagList(); // 상위 10개 태그 조회
    
    void addQuestionTag(QuestionTagDTO questionTagDTO); // 태그 추가

    void modifyQuestionTag(Long qno, List<QuestionTagDTO> tagList); // 태그 수정

    PageResponseDTO<QuestionDTO> getMyQuestionList(Long mno, PageRequestDTO pageRequestDTO); // 질문 목록 조회
}
