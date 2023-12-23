package kr.nomadlab.mentors.main.service;

import kr.nomadlab.mentors.main.domain.MentorReviewVO;
import kr.nomadlab.mentors.main.dto.MentorReviewDTO;
import kr.nomadlab.mentors.main.mapper.MainMapper;
import kr.nomadlab.mentors.main.mapper.MentorReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MentorReviewServiceImpl implements MentorReviewService{

    private final ModelMapper modelMapper;
    private final MentorReviewMapper mentorReviewMapper;
    private final MainMapper mainMapper;

    @Override
    public void register(MentorReviewDTO mentorReviewDTO) { // 수강평 작성
        MentorReviewVO mentorReviewVO = modelMapper.map(mentorReviewDTO, MentorReviewVO.class);
        Long mno = mentorReviewDTO.getMentorMno();
        mentorReviewMapper.insert(mentorReviewVO); // 수강평 등록
        mentorReviewMapper.updateScore(mno); // 해당 멘토의 score 갱신
        mainMapper.updateScore(mno); // 해당 멘토가 작성한 main글의 score 갱신

    }

    @Override
    public int mentorReviewCount(Long mentorMno) { // 멘토에게 달린 수강평 수
        return mentorReviewMapper.mentorReviewCount(mentorMno);
    }

    @Override
    public int menteeReviewCount(Long menteeMno) { // 멘티가 작성한 수강평 수
        return mentorReviewMapper.menteeReviewCount(menteeMno);
    }
}
