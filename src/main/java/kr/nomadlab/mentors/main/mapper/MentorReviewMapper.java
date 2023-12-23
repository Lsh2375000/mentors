package kr.nomadlab.mentors.main.mapper;

import kr.nomadlab.mentors.main.domain.MentorReviewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MentorReviewMapper {

    void insert(MentorReviewVO mentorReviewVO); // 리뷰 작성
    void updateScore(Long mno); // 멘토 평점 업데이트

    List<MentorReviewVO> menteeReviewList(String nickname); // 멘티 기준 내가쓴 리뷰 리스트 가져오기

    List<MentorReviewVO> mentorReviewList(Long mno); // 멘토 기준 작성된 리뷰 리스트 가져오기

    int mentorReviewCount(Long mentorMno); // 멘토에게 달린 수강평 수 

    int menteeReviewCount(Long menteeMno); // 멘티가 작성한 수강평 수

}
