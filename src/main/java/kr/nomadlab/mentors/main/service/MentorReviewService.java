package kr.nomadlab.mentors.main.service;

import kr.nomadlab.mentors.main.dto.MentorReviewDTO;

public interface MentorReviewService {
    
    void register(MentorReviewDTO mentorReviewDTO);
    
    int mentorReviewCount (Long mentorMno); // 멘토에게 달린 수강평 수

    int menteeReviewCount (Long menteeMno); // 멘티가 작성한 수강평 수

}
