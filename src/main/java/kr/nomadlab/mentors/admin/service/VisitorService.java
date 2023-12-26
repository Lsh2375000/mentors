package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.dto.VisitorDTO;
import kr.nomadlab.mentors.admin.dto.VisitorTypeDTO;

public interface VisitorService {
    
    int totalVisitorCnt(); // 총 방문자 수
    void insertVisitor(VisitorDTO visitorDTO); // 방문자 등록
    boolean isSameId(String sessionId); // 동일한 sessionId 있는지 여부

    //   일 별 방문자 수 구하기 daily
    int daily_5(VisitorTypeDTO visitorTypeDTO); // d-5
    int daily_4(VisitorTypeDTO visitorTypeDTO); // d-4
    int daily_3(VisitorTypeDTO visitorTypeDTO); // d-3
    int daily_2(VisitorTypeDTO visitorTypeDTO); // d-2
    int daily_1(VisitorTypeDTO visitorTypeDTO); // 어제 d-1
    int dailyD_day(VisitorTypeDTO visitorTypeDTO); // 오늘 d-day

    // 주간 별 방문자 수 구하기 week
    int week_4(VisitorTypeDTO visitorTypeDTO); // w-4
    int week_3(VisitorTypeDTO visitorTypeDTO); // w-3
    int week_2(VisitorTypeDTO visitorTypeDTO); // w-2
    int week_1(VisitorTypeDTO visitorTypeDTO); // w-1
    int thisWeek(VisitorTypeDTO visitorTypeDTO); // 이번주

    // 월 별 방문자 수 구하기 month
    int month_12(VisitorTypeDTO visitorTypeDTO); // 12달 전
    int month_11(VisitorTypeDTO visitorTypeDTO); // 11달 전
    int month_10(VisitorTypeDTO visitorTypeDTO); // 10달 전
    int month_9(VisitorTypeDTO visitorTypeDTO); // 9달 전
    int month_8(VisitorTypeDTO visitorTypeDTO); // 8달 전
    int month_7(VisitorTypeDTO visitorTypeDTO); // 7달 전
    int month_6(VisitorTypeDTO visitorTypeDTO); // 6달 전
    int month_5(VisitorTypeDTO visitorTypeDTO); // 5달 전
    int month_4(VisitorTypeDTO visitorTypeDTO); // 4달 전
    int month_3(VisitorTypeDTO visitorTypeDTO); // 3달 전
    int month_2(VisitorTypeDTO visitorTypeDTO); // 2달 전
    int month_1(VisitorTypeDTO visitorTypeDTO); // 1달 전
    int thisMonth(VisitorTypeDTO visitorTypeDTO); // 이번 달

    // 연도별 방문자 수 구하기 Year
    int year_5(VisitorTypeDTO visitorTypeDTO); // 5년 전
    int year_4(VisitorTypeDTO visitorTypeDTO); // 4년 전
    int year_3(VisitorTypeDTO visitorTypeDTO); // 3년 전
    int year_2(VisitorTypeDTO visitorTypeDTO); // 2년 전
    int year_1(VisitorTypeDTO visitorTypeDTO); // 1년 전
    int thisYear(VisitorTypeDTO visitorTypeDTO); // 올해

    // 특정 날짜 기준 방문자 수 구하기 byDate
    int byDate_m3(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 3일전
    int byDate_m2(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 2일전
    int byDate_m1(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 1일전
    int byDate(VisitorTypeDTO visitorTypeDTO); // 특정 날짜
    int byDate_p1(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 1일후
    int byDate_p2(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 2일후
    int byDate_p3(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 3일후
}
