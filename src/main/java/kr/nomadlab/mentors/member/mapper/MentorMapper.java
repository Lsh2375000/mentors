package kr.nomadlab.mentors.member.mapper;

import kr.nomadlab.mentors.member.domain.MentorVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MentorMapper {

    void insert(MentorVO mentorVO); // 멘토 회원등록

    List<MentorVO> selectAll(); // 전체 멘토 회원조회
    List<MentorVO> listByRanking(); // 랭킹순 멘토 목록
    MentorVO selectOne(String memberId); // 특정 멘토 회원 정보 가져오기

    void update(MentorVO mentorVO); // 멘토 회원 수정


    void delete(String memberId); // 멘토 회원 삭제

    void updatePw(String passwd ,String memberId); // 비밀번호 수정


}
