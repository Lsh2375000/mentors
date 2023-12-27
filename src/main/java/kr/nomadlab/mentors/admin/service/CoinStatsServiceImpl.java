package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.domain.CoinStatsVO;
import kr.nomadlab.mentors.admin.dto.AdminTypeDTO;
import kr.nomadlab.mentors.admin.dto.CoinStatsDTO;
import kr.nomadlab.mentors.admin.mapper.CoinStatsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CoinStatsServiceImpl implements CoinStatsService{

    private final ModelMapper modelMapper;
    private final CoinStatsMapper coinStatsMapper;

    @Override // 코인 충전량 통계 추가
    public void insertCoinStats(CoinStatsDTO coinStatsDTO) {
        CoinStatsVO coinStatsVO = modelMapper.map(coinStatsDTO, CoinStatsVO.class);
        coinStatsMapper.insertCoinStats(coinStatsVO);
    }

    @Override // 충전 총 갯수
    public int totalCoin() {
        return coinStatsMapper.totalCoin();
    }

    @Override // 5일전
    public int daily_5(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.daily_5(adminTypeDTO);
    }

    @Override // 4일전
    public int daily_4(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.daily_4(adminTypeDTO);
    }

    @Override // 3일전
    public int daily_3(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.daily_3(adminTypeDTO);
    }

    @Override // 2일전
    public int daily_2(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.daily_2(adminTypeDTO);
    }

    @Override // 1일전
    public int daily_1(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.daily_1(adminTypeDTO);
    }

    @Override // 오늘
    public int dailyD_day(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.dailyD_day(adminTypeDTO);
    }

    @Override // 이번주
    public int thisWeek(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.thisWeek(adminTypeDTO);
    }

    @Override // 1주전
    public int week_1(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.week_1(adminTypeDTO);
    }

    @Override // 2주전
    public int week_2(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.week_2(adminTypeDTO);
    }

    @Override // 3주전
    public int week_3(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.week_3(adminTypeDTO);
    }

    @Override // 4주전
    public int week_4(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.week_4(adminTypeDTO);
    }

    @Override // 이번달
    public int thisMonth(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.thisMonth(adminTypeDTO);
    }

    @Override // 1달전
    public int month_1(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_1(adminTypeDTO);
    }

    @Override // 2달전
    public int month_2(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_2(adminTypeDTO);
    }

    @Override // 3달전
    public int month_3(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_3(adminTypeDTO);
    }

    @Override // 4달전
    public int month_4(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_4(adminTypeDTO);
    }

    @Override // 5달전
    public int month_5(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_5(adminTypeDTO);
    }

    @Override //6달전
    public int month_6(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_6(adminTypeDTO);
    }

    @Override // 7달전
    public int month_7(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_7(adminTypeDTO);
    }

    @Override // 8달전 
    public int month_8(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_8(adminTypeDTO);
    }

    @Override //9달전
    public int month_9(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_9(adminTypeDTO);
    }

    @Override // 10달전
    public int month_10(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_10(adminTypeDTO);
    }

    @Override // 11달전
    public int month_11(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_11(adminTypeDTO);
    }

    @Override // 12달전
    public int month_12(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.month_12(adminTypeDTO);
    }

    @Override // 올해
    public int thisYear(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.thisYear(adminTypeDTO);
    }

    @Override // 1년전
    public int year_1(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.year_1(adminTypeDTO);
    }

    @Override // 2년전
    public int year_2(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.year_2(adminTypeDTO);
    }

    @Override // 3년전
    public int year_3(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.year_3(adminTypeDTO);
    }

    @Override // 4년전
    public int year_4(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.year_4(adminTypeDTO);
    }

    @Override // 5년 전
    public int year_5(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.year_5(adminTypeDTO);
    }

    @Override // 특정 날짜 3일전
    public int byDate_m3(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.byDate_m3(adminTypeDTO);
    }

    @Override // 특정 날짜 2일전
    public int byDate_m2(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.byDate_m2(adminTypeDTO);
    }

    @Override // 특정 날짜 1일전
    public int byDate_m1(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.byDate_m1(adminTypeDTO);
    }

    @Override // 특정 날짜
    public int byDate(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.byDate(adminTypeDTO);
    }

    @Override // 특정날짜 1일 후
    public int byDate_p1(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.byDate_p1(adminTypeDTO);
    }

    @Override // 특정날짜 2일 후
    public int byDate_p2(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.byDate_p2(adminTypeDTO);
    }

    @Override // 특정 날짜 3일 후
    public int byDate_p3(AdminTypeDTO adminTypeDTO) {
        return coinStatsMapper.byDate_p3(adminTypeDTO);
    }
}
