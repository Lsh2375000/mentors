package kr.nomadlab.progrow_project.mentoring.mapper;


import kr.nomadlab.progrow_project.mentoring.MentorRoomNoticeVO;

import kr.nomadlab.progrow_project.member.domain.MenteeVO;
import kr.nomadlab.progrow_project.member.domain.MentorVO;
import kr.nomadlab.progrow_project.common.PageRequestDTO;
import kr.nomadlab.progrow_project.mentoring.domain.MentoringVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MentoringMapper {
    // 이 부분(매퍼) 작성 후 MentoringMapper.xml 에 쿼리 작성(마이바티스)
    String getTime();

    void insert(MentoringVO mentoringVO); // 멘토가 멘토링 생성

    void mentorIsMentoring(MentorVO mentorVO); // 멘토가 멘토링 참여했을 시 멘티테이블의 isMentoring(멘토링 참여유무) true 로

    boolean isMentorMentoring(@Param("mentor_nickname") String mentor_nickname); // 멘토의 멘토링 생성유무 조회

    List<MentoringVO> selectAll();

    // 상세보기 기능(조회) "/mentoring/read?mno=xx" 와 같이 MentoringController를 호출 하도록 개발
    MentoringVO selectOne(Long mNo);
    void update(MentoringVO mentoringVO); // 멘토의 멘토링 수정
    void delete(Long mNo); // 삭제

    List<MentoringVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

    void recruitInsert(MentoringVO mentoringVO); // 멘티의 멘토링 참여

    void menteeRecruit(MenteeVO menteeVO); // 멘티가 멘토링 참여했을 시 멘티테이블의 isMentoring(멘토링 참여유무)와 멘토링번호(mNo) 등록

    //    MenteeVO getMenteeIsMentoring(String mentee_id); // 멘티의 멘토링 참여유무 조회
    boolean isMenteeMentoring(@Param("mentee_id") String mentee_id); // 멘티의 멘토링 참여유무 조회

    MentoringVO mentorRoom(String mentor_nickname); // 멘티의 참여중 멘토링방 정보 조회

    MentoringVO menteeRoom(String mentee_id); // 멘티의 참여중 멘토링방 정보 조회(멘토링 정보)

//    MentorVO menteeRoomMentorInfo(String mentee_id); // 멘티의 참여중 멘토링방에서 멘토 정보 조회


    // 멘토링방 - 멘토
    void insertNotice(MentorRoomNoticeVO mentorRoomNoticeVO); // 멘토의 멘토링방의 공지사항 생성
    void updateNotice(MentorRoomNoticeVO mentorRoomNoticeVO); // 멘토의 멘토링방 공지사항 수정
    void deleteNotice(Long num); // 삭제

    List<MentorRoomNoticeVO> selectAllNotice();

    MentorRoomNoticeVO selectOneNotice(Long num);

//    private boolean isMentoring;



    // myPage 자신이 했던 멘토링 목록
    List<MentoringVO> mentorMentoring(String mentor_nickname);
    List<MentoringVO> menteeMentoring(String mentee_id);


}
