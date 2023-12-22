package kr.nomadlab.mentors.main.service;

import kr.nomadlab.mentors.main.dto.MentorReviewDTO;

public interface MentorReviewService {
    int mentorReviewCount (Long mentorMno);

    int menteeReviewCount (Long menteeMno);

}
