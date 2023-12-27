package kr.nomadlab.mentors.admin.mapper;

import kr.nomadlab.mentors.admin.domain.VisitorVO;
import kr.nomadlab.mentors.admin.dto.AdminTypeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisitorMapper {

    void insertVisitor(VisitorVO visitorVO); // 방문자 등록(방문할때마다)
    boolean isSameId(String sessionId); // 동일한 세션 Id 있는지 여부
    int visitorCnt(); // 총 방문자 수

//   일 별 방문자 수 구하기 daily
    int daily_5(AdminTypeDTO adminTypeDTO); // d-5
    int daily_4(AdminTypeDTO adminTypeDTO); // d-4
    int daily_3(AdminTypeDTO adminTypeDTO); // d-3
    int daily_2(AdminTypeDTO adminTypeDTO); // d-2
    int daily_1(AdminTypeDTO adminTypeDTO); // 어제 d-1
    int dailyD_day(AdminTypeDTO adminTypeDTO); // 오늘 d-day

    // 주간별 방문자 수 구하기 month
    int thisWeek(AdminTypeDTO adminTypeDTO); // 이번주
    int week_1(AdminTypeDTO adminTypeDTO); // 1주전
    int week_2(AdminTypeDTO adminTypeDTO); // 2주전
    int week_3(AdminTypeDTO adminTypeDTO); // 3주전
    int week_4(AdminTypeDTO adminTypeDTO); // 4주전

    // 월 별 방문자 수 구하기 month
    int thisMonth(AdminTypeDTO adminTypeDTO); // 이번달
    int month_1(AdminTypeDTO adminTypeDTO); // 1달전
    int month_2(AdminTypeDTO adminTypeDTO); // 2달전
    int month_3(AdminTypeDTO adminTypeDTO); // 3달전
    int month_4(AdminTypeDTO adminTypeDTO); // 4달전
    int month_5(AdminTypeDTO adminTypeDTO); // 5달전
    int month_6(AdminTypeDTO adminTypeDTO); // 6달전
    int month_7(AdminTypeDTO adminTypeDTO); // 7달전
    int month_8(AdminTypeDTO adminTypeDTO); // 8달전
    int month_9(AdminTypeDTO adminTypeDTO); // 9달전
    int month_10(AdminTypeDTO adminTypeDTO); // 10달전
    int month_11(AdminTypeDTO adminTypeDTO); // 11달전
    int month_12(AdminTypeDTO adminTypeDTO); // 12달전

    // 연도별 방문자 수 구하기 year
    int thisYear(AdminTypeDTO adminTypeDTO); // 올해
    int year_1(AdminTypeDTO adminTypeDTO); // 1년전
    int year_2(AdminTypeDTO adminTypeDTO); // 2년전
    int year_3(AdminTypeDTO adminTypeDTO); // 3년전
    int year_4(AdminTypeDTO adminTypeDTO); // 4년전
    int year_5(AdminTypeDTO adminTypeDTO); // 5년전

    // 특정 날짜기준 방문자 수 구하기
    int byDate_m3(AdminTypeDTO adminTypeDTO); // 특정 날짜 3일전
    int byDate_m2(AdminTypeDTO adminTypeDTO); // 특정 날짜 2일전
    int byDate_m1(AdminTypeDTO adminTypeDTO); // 특정 날짜 1일전
    int byDate(AdminTypeDTO adminTypeDTO); // 특정 날짜
    int byDate_p1(AdminTypeDTO adminTypeDTO); // 특정 날짜 1일후
    int byDate_p2(AdminTypeDTO adminTypeDTO); // 특정 날짜 2일후
    int byDate_p3(AdminTypeDTO adminTypeDTO); // 특정 날짜 3일후

}
