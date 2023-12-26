package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.domain.VisitorVO;
import kr.nomadlab.mentors.admin.dto.VisitorDTO;
import kr.nomadlab.mentors.admin.dto.VisitorTypeDTO;
import kr.nomadlab.mentors.admin.mapper.VisitorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService{

    private final VisitorMapper visitorMapper;
    private final ModelMapper modelMapper;

    @Override
    public int totalVisitorCnt() { // 총 방문자 수
        return visitorMapper.visitorCnt();
    }

    @Override
    public void insertVisitor(VisitorDTO visitorDTO) { // 방문자 등록
        VisitorVO visitorVO = modelMapper.map(visitorDTO, VisitorVO.class);
        
        visitorMapper.insertVisitor(visitorVO);
    }

    @Override// 중복 세션 확인
    public boolean isSameId(String sessionId) {
        return visitorMapper.isSameId(sessionId);
    }

    @Override// 5일전
    public int daily_5(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.daily_5(visitorTypeDTO);
    }

    @Override// 4일전
    public int daily_4(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.daily_4(visitorTypeDTO);
    }

    @Override// 3일전
    public int daily_3(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.daily_3(visitorTypeDTO);
    }

    @Override// 2일전
    public int daily_2(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.daily_2(visitorTypeDTO);
    }

    @Override // 1일전
    public int daily_1(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.daily_1(visitorTypeDTO);
    }

    @Override// 오늘
    public int dailyD_day(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.dailyD_day(visitorTypeDTO);
    }

    @Override // 4주전
    public int week_4(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.week_4(visitorTypeDTO);
    }

    @Override // 3주전
    public int week_3(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.week_3(visitorTypeDTO);
    }

    @Override // 2주전
    public int week_2(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.week_2(visitorTypeDTO);
    }

    @Override // 1주전
    public int week_1(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.week_1(visitorTypeDTO);
    }

    @Override // 이번주
    public int thisWeek(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.thisWeek(visitorTypeDTO);
    }

    @Override // 12달 전
    public int month_12(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_12(visitorTypeDTO);
    }

    @Override // 11달 전
    public int month_11(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_11(visitorTypeDTO);
    }

    @Override // 10달 전
    public int month_10(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_10(visitorTypeDTO);
    }

    @Override // 9달 전
    public int month_9(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_9(visitorTypeDTO);
    }

    @Override // 8달 전
    public int month_8(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_8(visitorTypeDTO);
    }

    @Override // 7달 전
    public int month_7(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_7(visitorTypeDTO);
    }

    @Override // 6달 전
    public int month_6(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_6(visitorTypeDTO);
    }

    @Override // 5달 전
    public int month_5(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_5(visitorTypeDTO);
    }

    @Override // 4달 전
    public int month_4(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_4(visitorTypeDTO);
    }

    @Override // 3달 전
    public int month_3(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_3(visitorTypeDTO);
    }

    @Override // 2달 전
    public int month_2(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_2(visitorTypeDTO);
    }

    @Override // 1달 전
    public int month_1(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.month_1(visitorTypeDTO);
    }

    @Override // 이번 달
    public int thisMonth(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.thisMonth(visitorTypeDTO);
    }

    @Override // 5년 전
    public int year_5(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.year_5(visitorTypeDTO);
    }

    @Override // 4년 전
    public int year_4(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.year_4(visitorTypeDTO);
    }

    @Override // 3년 전
    public int year_3(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.year_3(visitorTypeDTO);
    }

    @Override // 2년 전
    public int year_2(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.year_2(visitorTypeDTO);
    }

    @Override // 1년 전
    public int year_1(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.year_1(visitorTypeDTO);
    }

    @Override // 올해
    public int thisYear(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.thisYear(visitorTypeDTO);
    }

    @Override // 특정 날짜 3일전
    public int byDate_m3(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.byDate_m3(visitorTypeDTO);
    }

    @Override // 특정 날짜 2일전
    public int byDate_m2(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.byDate_m2(visitorTypeDTO);
    }

    @Override // 특정 날짜 1일전
    public int byDate_m1(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.byDate_m1(visitorTypeDTO);
    }

    @Override // 특정 날짜
    public int byDate(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.byDate(visitorTypeDTO);
    }

    @Override // 특정 날짜 1일후
    public int byDate_p1(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.byDate_p1(visitorTypeDTO);
    }

    @Override // 특정 날짜 2일후
    public int byDate_p2(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.byDate_p2(visitorTypeDTO);
    }

    @Override // 특정 날짜 3일후
    public int byDate_p3(VisitorTypeDTO visitorTypeDTO) {
        return visitorMapper.byDate_p3(visitorTypeDTO);
    }
}
