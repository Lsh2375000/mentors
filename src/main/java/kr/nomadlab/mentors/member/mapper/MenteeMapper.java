package kr.nomadlab.mentors.member.mapper;

import kr.nomadlab.mentors.member.domain.MenteeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenteeMapper {

    void insert(MenteeVO memberVO); // 멘티 회원등록

    List<MenteeVO> selectAll(); // 전체 멘티 회원조회

    MenteeVO selectOne(String memberId); // 아이디로 특정 멘티 회원 정보 가져오기

    MenteeVO selectOneByMno(Long mno); // mno로 특정 멘티 회원 정보 가져오기
    void update(MenteeVO menteeVO); // 멘티 회원 수정

    void delete(String memberId); // 멘티 회원 삭제

    void updatePw(String passwd ,String memberId); // 비밀번호 수정

    void introWrite(String intro, Long mno);


}
