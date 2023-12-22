package kr.nomadlab.mentors.main.service;

import kr.nomadlab.mentors.main.dto.MentorReviewDTO;
import kr.nomadlab.mentors.main.mapper.MentorReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MentorReviewServiceImpl implements MentorReviewService{

    private final MentorReviewMapper mentorReviewMapper;

    @Override
    public int mentorReviewCount(Long mentorMno) {
        return mentorReviewMapper.mentorReviewCount(mentorMno);
    }

    @Override
    public int menteeReviewCount(Long menteeMno) {
        return mentorReviewMapper.menteeReviewCount(menteeMno);
    }
}
