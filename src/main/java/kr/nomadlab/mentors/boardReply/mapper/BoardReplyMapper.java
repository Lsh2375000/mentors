package kr.nomadlab.mentors.boardReply.mapper;

import kr.nomadlab.mentors.boardReply.domain.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardReplyMapper {

   void insertReply(ReplyVO replyVO); // 댓글 등록
   
   void updateParentNo(Long rno); // 부모 댓글 고유번호 저장

   ReplyVO selectReply(Long rno); // 댓글 조회

   List<ReplyVO> selectCommentWithReplyList(@Param("boardNo") Long boardNo,
                                            @Param("skip") int skip,
                                            @Param("size") int size); // 댓글 목록 조회

   int getCount(Long boardNo); // 댓글 총 갯수

   void updateReply(ReplyVO replyVO); // 댓글 수정

   void deleteReply(Long rno); // 댓글 삭제
}
