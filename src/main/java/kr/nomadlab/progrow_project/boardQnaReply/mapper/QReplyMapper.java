package kr.nomadlab.progrow_project.boardQnaReply.mapper;


import kr.nomadlab.progrow_project.boardQnaReply.domain.QReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Mapper
public interface QReplyMapper {

    void insertQR(QReplyVO qReplyVO); // Qna 댓글 등록
    QReplyVO selectOneQR(Long qnaRno);  // Qna 특정 댓글 조회
    void updateOneQR(QReplyVO qReplyVO); // Qna 특정 댓글 수정
    void deleteOneQR(Long qnaRno); // Qna 특정 댓글 삭제
    // Qna 댓글 리스트 조회
    List<QReplyVO> selectListOfBoardQR(@Param("qnaBoardNo") long qnaBoardNo, @RequestParam(defaultValue = "0") int skip, @RequestParam(defaultValue = "10") int size);

    int getCountQR(Long qnaBoardNo); // 댓글 수 카운팅


}
