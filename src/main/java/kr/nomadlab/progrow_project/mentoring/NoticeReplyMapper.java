package kr.nomadlab.progrow_project.mentoring;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeReplyMapper {
    
    void register(NoticeReplyVO noticeReplyVO); // 공지에 댓글 등록

    NoticeReplyVO read(Long rno); // 리플 조회

    void modify(NoticeReplyVO replyVO); // 리플 수정

    void remove(Long rno); // 리플 삭제

//    List<ReplyVO> selectList(int , PageRequestDTO pageRequestDTO); // 페이지 별 리플 목록

    List<NoticeReplyVO> selectList(int num, int skip, int size); // 페이지 별 리플 목록

    int getCount(int num); // 전체 리플 갯수



    List<NoticeReplyVO> selectAll(Long num); // 전체 리스트(페이징없음) - 사용안함

//    bno 아니고 num  이다!!!!!!!!!!!
}
