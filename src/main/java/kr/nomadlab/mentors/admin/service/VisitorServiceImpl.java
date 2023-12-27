package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.domain.VisitorVO;
import kr.nomadlab.mentors.admin.dto.VisitorDTO;
import kr.nomadlab.mentors.admin.dto.AdminTypeDTO;
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
    public int daily_5(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.daily_5(adminTypeDTO);
    }

    @Override// 4일전
    public int daily_4(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.daily_4(adminTypeDTO);
    }

    @Override// 3일전
    public int daily_3(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.daily_3(adminTypeDTO);
    }

    @Override// 2일전
    public int daily_2(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.daily_2(adminTypeDTO);
    }

    @Override // 1일전
    public int daily_1(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.daily_1(adminTypeDTO);
    }

    @Override// 오늘
    public int dailyD_day(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.dailyD_day(adminTypeDTO);
    }

    @Override // 4주전
    public int week_4(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.week_4(adminTypeDTO);
    }

    @Override // 3주전
    public int week_3(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.week_3(adminTypeDTO);
    }

    @Override // 2주전
    public int week_2(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.week_2(adminTypeDTO);
    }

    @Override // 1주전
    public int week_1(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.week_1(adminTypeDTO);
    }

    @Override // 이번주
    public int thisWeek(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.thisWeek(adminTypeDTO);
    }

    @Override // 12달 전
    public int month_12(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_12(adminTypeDTO);
    }

    @Override // 11달 전
    public int month_11(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_11(adminTypeDTO);
    }

    @Override // 10달 전
    public int month_10(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_10(adminTypeDTO);
    }

    @Override // 9달 전
    public int month_9(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_9(adminTypeDTO);
    }

    @Override // 8달 전
    public int month_8(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_8(adminTypeDTO);
    }

    @Override // 7달 전
    public int month_7(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_7(adminTypeDTO);
    }

    @Override // 6달 전
    public int month_6(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_6(adminTypeDTO);
    }

    @Override // 5달 전
    public int month_5(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_5(adminTypeDTO);
    }

    @Override // 4달 전
    public int month_4(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_4(adminTypeDTO);
    }

    @Override // 3달 전
    public int month_3(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_3(adminTypeDTO);
    }

    @Override // 2달 전
    public int month_2(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_2(adminTypeDTO);
    }

    @Override // 1달 전
    public int month_1(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.month_1(adminTypeDTO);
    }

    @Override // 이번 달
    public int thisMonth(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.thisMonth(adminTypeDTO);
    }

    @Override // 5년 전
    public int year_5(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.year_5(adminTypeDTO);
    }

    @Override // 4년 전
    public int year_4(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.year_4(adminTypeDTO);
    }

    @Override // 3년 전
    public int year_3(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.year_3(adminTypeDTO);
    }

    @Override // 2년 전
    public int year_2(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.year_2(adminTypeDTO);
    }

    @Override // 1년 전
    public int year_1(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.year_1(adminTypeDTO);
    }

    @Override // 올해
    public int thisYear(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.thisYear(adminTypeDTO);
    }

    @Override // 특정 날짜 3일전
    public int byDate_m3(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.byDate_m3(adminTypeDTO);
    }

    @Override // 특정 날짜 2일전
    public int byDate_m2(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.byDate_m2(adminTypeDTO);
    }

    @Override // 특정 날짜 1일전
    public int byDate_m1(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.byDate_m1(adminTypeDTO);
    }

    @Override // 특정 날짜
    public int byDate(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.byDate(adminTypeDTO);
    }

    @Override // 특정 날짜 1일후
    public int byDate_p1(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.byDate_p1(adminTypeDTO);
    }

    @Override // 특정 날짜 2일후
    public int byDate_p2(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.byDate_p2(adminTypeDTO);
    }

    @Override // 특정 날짜 3일후
    public int byDate_p3(AdminTypeDTO adminTypeDTO) {
        return visitorMapper.byDate_p3(adminTypeDTO);
    }
}
