package kr.nomadlab.progrow_project.boardReply.mapper;

import kr.nomadlab.progrow_project.boardReply.domain.ReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

   void register(ReplyVO replyVO); //댓글 작성

   ReplyVO read(Long rno);

   List<ReplyVO> selectList(int boardNo, int skip, int size); // 페이지 별 리플 목록

   int getCount(int boardNo);

   void modify(ReplyVO replyVO);

   void delete(Long rno);
}
