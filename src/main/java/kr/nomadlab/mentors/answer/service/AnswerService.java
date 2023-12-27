package kr.nomadlab.mentors.answer.service;

import kr.nomadlab.mentors.answer.dto.AnswerDTO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;

public interface AnswerService {

    Long registerAnswer(AnswerDTO answerDTO); // 답변 등록

    AnswerDTO getAnswer(Long ano); // 답변 조회

    void modifyAnswer(AnswerDTO answerDTO); // 답변 수정

    void removeAnswer(Long ano); // 답변 삭제

    //페이지 별 답변 목록
    PageResponseDTO<AnswerDTO> getAnswerList(Long qno, PageRequestDTO pageRequestDTO);

    void modifySelect(Long qno, Long ano); // 답변 채택 상태로 변경
}
