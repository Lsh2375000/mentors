package kr.nomadlab.mentors.admin.mapper;

import kr.nomadlab.mentors.admin.domain.VisitorVO;
import kr.nomadlab.mentors.admin.dto.VisitorTypeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisitorMapper {

    void insertVisitor(VisitorVO visitorVO); // 방문자 등록(방문할때마다)
    boolean isSameId(String sessionId); // 동일한 세션 Id 있는지 여부
    int visitorCnt(); // 총 방문자 수

//   일 별 방문자 수 구하기 daily
    int daily_5(VisitorTypeDTO visitorTypeDTO); // d-5
    int daily_4(VisitorTypeDTO visitorTypeDTO); // d-4
    int daily_3(VisitorTypeDTO visitorTypeDTO); // d-3
    int daily_2(VisitorTypeDTO visitorTypeDTO); // d-2
    int daily_1(VisitorTypeDTO visitorTypeDTO); // 어제 d-1
    int dailyD_day(VisitorTypeDTO visitorTypeDTO); // 오늘 d-day

    // 주간별 방문자 수 구하기 month
    int thisWeek(VisitorTypeDTO visitorTypeDTO); // 이번주
    int week_1(VisitorTypeDTO visitorTypeDTO); // 1주전
    int week_2(VisitorTypeDTO visitorTypeDTO); // 2주전
    int week_3(VisitorTypeDTO visitorTypeDTO); // 3주전
    int week_4(VisitorTypeDTO visitorTypeDTO); // 4주전

    // 월 별 방문자 수 구하기 month
    int thisMonth(VisitorTypeDTO visitorTypeDTO); // 이번달
    int month_1(VisitorTypeDTO visitorTypeDTO); // 1달전
    int month_2(VisitorTypeDTO visitorTypeDTO); // 2달전
    int month_3(VisitorTypeDTO visitorTypeDTO); // 3달전
    int month_4(VisitorTypeDTO visitorTypeDTO); // 4달전
    int month_5(VisitorTypeDTO visitorTypeDTO); // 5달전
    int month_6(VisitorTypeDTO visitorTypeDTO); // 6달전
    int month_7(VisitorTypeDTO visitorTypeDTO); // 7달전
    int month_8(VisitorTypeDTO visitorTypeDTO); // 8달전
    int month_9(VisitorTypeDTO visitorTypeDTO); // 9달전
    int month_10(VisitorTypeDTO visitorTypeDTO); // 10달전
    int month_11(VisitorTypeDTO visitorTypeDTO); // 11달전
    int month_12(VisitorTypeDTO visitorTypeDTO); // 12달전

    // 연도별 방문자 수 구하기 year
    int thisYear(VisitorTypeDTO visitorTypeDTO); // 올해
    int year_1(VisitorTypeDTO visitorTypeDTO); // 1년전
    int year_2(VisitorTypeDTO visitorTypeDTO); // 2년전
    int year_3(VisitorTypeDTO visitorTypeDTO); // 3년전
    int year_4(VisitorTypeDTO visitorTypeDTO); // 4년전
    int year_5(VisitorTypeDTO visitorTypeDTO); // 5년전

    // 특정 날짜기준 방문자 수 구하기
    int byDate_m3(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 3일전
    int byDate_m2(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 2일전
    int byDate_m1(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 1일전
    int byDate(VisitorTypeDTO visitorTypeDTO); // 특정 날짜
    int byDate_p1(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 1일후
    int byDate_p2(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 2일후
    int byDate_p3(VisitorTypeDTO visitorTypeDTO); // 특정 날짜 3일후

}
