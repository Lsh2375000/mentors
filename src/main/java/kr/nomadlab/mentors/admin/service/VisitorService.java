package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.dto.VisitorDTO;
import kr.nomadlab.mentors.admin.dto.AdminTypeDTO;

public interface VisitorService {
    
    int totalVisitorCnt(); // 총 방문자 수
    void insertVisitor(VisitorDTO visitorDTO); // 방문자 등록
    boolean isSameId(String sessionId); // 동일한 sessionId 있는지 여부

    //   일 별 방문자 수 구하기 daily
    int daily_5(AdminTypeDTO adminTypeDTO); // d-5
    int daily_4(AdminTypeDTO adminTypeDTO); // d-4
    int daily_3(AdminTypeDTO adminTypeDTO); // d-3
    int daily_2(AdminTypeDTO adminTypeDTO); // d-2
    int daily_1(AdminTypeDTO adminTypeDTO); // 어제 d-1
    int dailyD_day(AdminTypeDTO adminTypeDTO); // 오늘 d-day

    // 주간 별 방문자 수 구하기 week
    int week_4(AdminTypeDTO adminTypeDTO); // w-4
    int week_3(AdminTypeDTO adminTypeDTO); // w-3
    int week_2(AdminTypeDTO adminTypeDTO); // w-2
    int week_1(AdminTypeDTO adminTypeDTO); // w-1
    int thisWeek(AdminTypeDTO adminTypeDTO); // 이번주

    // 월 별 방문자 수 구하기 month
    int month_12(AdminTypeDTO adminTypeDTO); // 12달 전
    int month_11(AdminTypeDTO adminTypeDTO); // 11달 전
    int month_10(AdminTypeDTO adminTypeDTO); // 10달 전
    int month_9(AdminTypeDTO adminTypeDTO); // 9달 전
    int month_8(AdminTypeDTO adminTypeDTO); // 8달 전
    int month_7(AdminTypeDTO adminTypeDTO); // 7달 전
    int month_6(AdminTypeDTO adminTypeDTO); // 6달 전
    int month_5(AdminTypeDTO adminTypeDTO); // 5달 전
    int month_4(AdminTypeDTO adminTypeDTO); // 4달 전
    int month_3(AdminTypeDTO adminTypeDTO); // 3달 전
    int month_2(AdminTypeDTO adminTypeDTO); // 2달 전
    int month_1(AdminTypeDTO adminTypeDTO); // 1달 전
    int thisMonth(AdminTypeDTO adminTypeDTO); // 이번 달

    // 연도별 방문자 수 구하기 Year
    int year_5(AdminTypeDTO adminTypeDTO); // 5년 전
    int year_4(AdminTypeDTO adminTypeDTO); // 4년 전
    int year_3(AdminTypeDTO adminTypeDTO); // 3년 전
    int year_2(AdminTypeDTO adminTypeDTO); // 2년 전
    int year_1(AdminTypeDTO adminTypeDTO); // 1년 전
    int thisYear(AdminTypeDTO adminTypeDTO); // 올해

    // 특정 날짜 기준 방문자 수 구하기 byDate
    int byDate_m3(AdminTypeDTO adminTypeDTO); // 특정 날짜 3일전
    int byDate_m2(AdminTypeDTO adminTypeDTO); // 특정 날짜 2일전
    int byDate_m1(AdminTypeDTO adminTypeDTO); // 특정 날짜 1일전
    int byDate(AdminTypeDTO adminTypeDTO); // 특정 날짜
    int byDate_p1(AdminTypeDTO adminTypeDTO); // 특정 날짜 1일후
    int byDate_p2(AdminTypeDTO adminTypeDTO); // 특정 날짜 2일후
    int byDate_p3(AdminTypeDTO adminTypeDTO); // 특정 날짜 3일후
}
