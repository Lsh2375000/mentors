package kr.nomadlab.mentors.question.mapper;

import kr.nomadlab.mentors.board.domain.BoardLikeVO;
import kr.nomadlab.mentors.board.domain.BoardVO;
import kr.nomadlab.mentors.board.domain.HashTagVO;
import kr.nomadlab.mentors.board.dto.HashTagDTO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.question.domain.QNAVoteVO;
import kr.nomadlab.mentors.question.domain.QuestionTagVO;
import kr.nomadlab.mentors.question.domain.QuestionVO;
import kr.nomadlab.mentors.question.dto.QuestionTagDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface QuestionMapper {

    void insertQuestion(QuestionVO questionVO); // 질문 생성

    void deleteQuestion(Long qno); // 질문 삭제

    QuestionVO selectQuestion(Long qno); // 질문 조회

    void updateQuestion(QuestionVO questionVO); // 질문 수정

    int getCount(PageRequestDTO pageRequestDTO); // 질문 총 갯수

    List<QuestionVO> selectQuestionList(PageRequestDTO pageRequestDTO); // 질문 전체 조회

    void updateHit(Long qno); // 조회수 업데이트

    void insertVote(QNAVoteVO qnaVoteVO); // 추천 추가

    Boolean deleteVote(Long vno); // 추천 삭제

    List<QNAVoteVO> selectVoteList(Long qno); // 해당 질문 추천 조회
    
    List<QuestionTagVO> selectTagList(Long qno); // 태그 목록 조회

    List<QuestionTagDTO> selectTopTagList(); // 상위 10개 태그 조회
    
    void insertTag(QuestionTagVO questionTagVO); // 태그 추가

    void deleteTag(Long qno); // 태그 삭제

    void updateComplete(Long qno); // 질문 해결 상태로 변경

    List<QuestionVO> selectMyQuestionList(@Param("mno") Long mno,
                                          @Param("skip") int skip,
                                          @Param("size") int size); // 질문 전체 조회

    int getMyCount(Long mno); // 본인 질문 총 갯수
}
