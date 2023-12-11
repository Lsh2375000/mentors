package kr.nomadlab.progrow_project.member.mapper;

import kr.nomadlab.progrow_project.member.domain.MenteeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenteeMapper {

    void insert(MenteeVO memberVO); // 멘티 회원등록

    List<MenteeVO> selectAll(); // 전체 멘티 회원조회

    MenteeVO selectOne(String mentee_id); // 특정 멘티 회원 정보 가져오기

    void update(MenteeVO menteeVO); // 멘티 회원 수정


    void delete(String mentee_id); // 멘티 회원 삭제

    void updatePw(String passwd ,String mentee_id); // 비밀번호 수정

}
