package kr.nomadlab.mentors.answer.mapper;

import kr.nomadlab.mentors.answer.domain.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnswerMapper {

   void insertAnswer(AnswerVO replyVO); // 답변 등록
   
   void updateParentNo(Long ano); // 부모 답변 고유번호 저장

   AnswerVO selectAnswer(Long ano); // 답변 조회

   List<AnswerVO> selectAnswerList(@Param("qno") Long qno,
                                             @Param("skip") int skip,
                                             @Param("size") int size); // 답변 목록 조회

   int getCount(Long qno); // 답변 총 갯수

   void updateAnswer(AnswerVO answerVO); // 답변 수정

   void deleteAnswer(Long ano); // 답변 삭제
   
   void updateSelect(Long ano); // 답변 채택 상태로 변경

   List<AnswerVO> selectMyAnswerList(@Param("mno") Long mno,
                           @Param("skip") int skip,
                           @Param("size") int size);

   int getMyCount(Long mno); // 나의 답변 총 갯수
}
