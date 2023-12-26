package kr.nomadlab.mentors.projectReply.mapper;

import kr.nomadlab.mentors.projectReply.domain.ProjectReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectReplyMapper {

   void insertReply(ProjectReplyVO projectReplyVO); // 댓글 등록
   
   void updateParentNo(Long pjrNo); // 부모 댓글 고유번호 저장

   ProjectReplyVO selectReply(Long pjrNo); // 댓글 조회

   List<ProjectReplyVO> selectCommentWithReplyList(@Param("projectNo") Long projectNo,
                                            @Param("skip") int skip,
                                            @Param("size") int size); // 댓글 목록 조회

   int getCount(Long projectNo); // 댓글 총 갯수

   void updateReply(ProjectReplyVO projectReplyVO); // 댓글 수정

   void deleteReply(Long pjrNo); // 댓글 삭제
}
