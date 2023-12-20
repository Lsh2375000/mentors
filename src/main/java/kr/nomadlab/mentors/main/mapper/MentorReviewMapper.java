package kr.nomadlab.mentors.main.mapper;

import kr.nomadlab.mentors.main.domain.MentorReviewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MentorReviewMapper {

    void insert(MentorReviewVO mentorReviewVO); // 리뷰 작성

    List<MentorReviewVO> menteeReviewList(String nickname); // 멘티 기준 내가쓴 리뷰 리스트 가져오기

    List<MentorReviewVO> mentorReviewList(int mno); // 멘토 기준 작성된 리뷰 리스트 가져오기



}
