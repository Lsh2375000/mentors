package kr.nomadlab.mentors.main.service;

import kr.nomadlab.mentors.main.dto.MentorReviewDTO;

import java.util.List;

public interface MentorReviewService {
    
    void register(MentorReviewDTO mentorReviewDTO); // 수강평 작성
    List<MentorReviewDTO> mentorReviewList(Long mentorMno); // 수강평 목록
    
    int mentorReviewCount (Long mentorMno); // 멘토에게 달린 수강평 수

    int menteeReviewCount (Long menteeMno); // 멘티가 작성한 수강평 수
    
    boolean isReview(Long menteeMno); // 멘티가 특정 멘토링에 수강평을 달았는지 여부

}
