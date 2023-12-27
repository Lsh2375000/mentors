package kr.nomadlab.mentors.admin.controller;

import kr.nomadlab.mentors.admin.dto.AdminTypeDTO;
import kr.nomadlab.mentors.admin.service.AdminService;
import kr.nomadlab.mentors.admin.service.CoinStatsService;
import kr.nomadlab.mentors.admin.service.VisitorService;
import kr.nomadlab.mentors.member.dto.MentorApplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Log4j2
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final VisitorService visitorService;
    private final CoinStatsService coinStatsService;

    @GetMapping("/stats")
    public void adminStats(Model model, AdminTypeDTO adminTypeDTO){// 통계 페이지
        log.info("period? "+ adminTypeDTO.getPeriod());
        // 방문자 통계
        // 일별정렬
        if(adminTypeDTO.getPeriod().equals("daily")){
            log.info("일별정렬");
            int d_5 = visitorService.daily_5(adminTypeDTO);
            int d_4 = visitorService.daily_4(adminTypeDTO);
            int d_3 = visitorService.daily_3(adminTypeDTO);
            int d_2 = visitorService.daily_2(adminTypeDTO);
            int d_1 = visitorService.daily_1(adminTypeDTO);
            int d_day = visitorService.dailyD_day(adminTypeDTO);
            model.addAttribute("d_5", d_5);
            model.addAttribute("d_4", d_4);
            model.addAttribute("d_3", d_3);
            model.addAttribute("d_2", d_2);
            model.addAttribute("d_1", d_1);
            model.addAttribute("d_day", d_day);
        } else if(adminTypeDTO.getPeriod().equals("weekly")){
            // 주간별 정렬
            log.info("주간별 정렬");
            int w_4 = visitorService.week_4(adminTypeDTO);
            int w_3 = visitorService.week_3(adminTypeDTO);
            int w_2 = visitorService.week_2(adminTypeDTO);
            int w_1 = visitorService.week_1(adminTypeDTO);
            int thisWeek = visitorService.thisWeek(adminTypeDTO);
            model.addAttribute("w_4", w_4);
            model.addAttribute("w_3", w_3);
            model.addAttribute("w_2", w_2);
            model.addAttribute("w_1", w_1);
            model.addAttribute("thisWeek", thisWeek);
        } else if(adminTypeDTO.getPeriod().equals("monthly")){
            // 월 별 정렬
            log.info("월별 정렬");
            int m_12 = visitorService.month_12(adminTypeDTO);
            int m_11 = visitorService.month_11(adminTypeDTO);
            int m_10 = visitorService.month_10(adminTypeDTO);
            int m_9 = visitorService.month_9(adminTypeDTO);
            int m_8 = visitorService.month_8(adminTypeDTO);
            int m_7 = visitorService.month_7(adminTypeDTO);
            int m_6 = visitorService.month_6(adminTypeDTO);
            int m_5 = visitorService.month_5(adminTypeDTO);
            int m_4 = visitorService.month_4(adminTypeDTO);
            int m_3 = visitorService.month_3(adminTypeDTO);
            int m_2 = visitorService.month_2(adminTypeDTO);
            int m_1 = visitorService.month_1(adminTypeDTO);
            int thisMonth = visitorService.thisMonth(adminTypeDTO);
            model.addAttribute("m_12", m_12);
            model.addAttribute("m_11", m_11);
            model.addAttribute("m_10", m_10);
            model.addAttribute("m_9", m_9);
            model.addAttribute("m_8", m_8);
            model.addAttribute("m_7", m_7);
            model.addAttribute("m_6", m_6);
            model.addAttribute("m_5", m_5);
            model.addAttribute("m_4", m_4);
            model.addAttribute("m_3", m_3);
            model.addAttribute("m_2", m_2);
            model.addAttribute("m_1", m_1);
            model.addAttribute("thisMonth", thisMonth);
        }else if(adminTypeDTO.getPeriod().equals("yearly")){
            // 연도별 정렬
            log.info("연도별 정렬");
            int y_5 = visitorService.year_5(adminTypeDTO);
            int y_4 = visitorService.year_4(adminTypeDTO);
            int y_3 = visitorService.year_3(adminTypeDTO);
            int y_2 = visitorService.year_2(adminTypeDTO);
            int y_1 = visitorService.year_1(adminTypeDTO);
            int thisYear = visitorService.thisYear(adminTypeDTO);
            model.addAttribute("y_5", y_5);
            model.addAttribute("y_4", y_4);
            model.addAttribute("y_3", y_3);
            model.addAttribute("y_2", y_2);
            model.addAttribute("y_1", y_1);
            model.addAttribute("thisYear", thisYear);
        }else if(adminTypeDTO.getDate() != null){
            log.info("특정 날짜 검색");
            int byDate_m3 = visitorService.byDate_m3(adminTypeDTO);
            int byDate_m2 = visitorService.byDate_m2(adminTypeDTO);
            int byDate_m1 = visitorService.byDate_m1(adminTypeDTO);
            int byDate = visitorService.byDate(adminTypeDTO);
            int byDate_p1 = visitorService.byDate_p1(adminTypeDTO);
            int byDate_p2 = visitorService.byDate_p2(adminTypeDTO);
            int byDate_p3 = visitorService.byDate_p3(adminTypeDTO);
            model.addAttribute("byDate_m3", byDate_m3);
            model.addAttribute("byDate_m2", byDate_m2);
            model.addAttribute("byDate_m1", byDate_m1);
            model.addAttribute("byDate", byDate);
            model.addAttribute("byDate_p1", byDate_p1);
            model.addAttribute("byDate_p2", byDate_p2);
            model.addAttribute("byDate_p3", byDate_p3);
        }

        // 코인 통계
        // 일별정렬
        if(adminTypeDTO.getCoinPeriod().equals("daily")){
            log.info("코인일별정렬");
            int cd_5 = coinStatsService.daily_5(adminTypeDTO);
            int cd_4 = coinStatsService.daily_4(adminTypeDTO);
            int cd_3 = coinStatsService.daily_3(adminTypeDTO);
            int cd_2 = coinStatsService.daily_2(adminTypeDTO);
            int cd_1 = coinStatsService.daily_1(adminTypeDTO);
            int cd_day = coinStatsService.dailyD_day(adminTypeDTO);
            model.addAttribute("cd_5", cd_5);
            model.addAttribute("cd_4", cd_4);
            model.addAttribute("cd_3", cd_3);
            model.addAttribute("cd_2", cd_2);
            model.addAttribute("cd_1", cd_1);
            model.addAttribute("cd_day", cd_day);
        } else if(adminTypeDTO.getCoinPeriod().equals("weekly")){
            // 주간별 정렬
            log.info("코인 주간별 정렬");
            int cw_4 = coinStatsService.week_4(adminTypeDTO);
            int cw_3 = coinStatsService.week_3(adminTypeDTO);
            int cw_2 = coinStatsService.week_2(adminTypeDTO);
            int cw_1 = coinStatsService.week_1(adminTypeDTO);
            int cthisWeek = coinStatsService.thisWeek(adminTypeDTO);
            model.addAttribute("cw_4", cw_4);
            model.addAttribute("cw_3", cw_3);
            model.addAttribute("cw_2", cw_2);
            model.addAttribute("cw_1", cw_1);
            model.addAttribute("cthisWeek", cthisWeek);
        } else if(adminTypeDTO.getCoinPeriod().equals("monthly")){
            // 월 별 정렬
            log.info("코인 월별 정렬");
            int cm_12 = coinStatsService.month_12(adminTypeDTO);
            int cm_11 = coinStatsService.month_11(adminTypeDTO);
            int cm_10 = coinStatsService.month_10(adminTypeDTO);
            int cm_9 = coinStatsService.month_9(adminTypeDTO);
            int cm_8 = coinStatsService.month_8(adminTypeDTO);
            int cm_7 = coinStatsService.month_7(adminTypeDTO);
            int cm_6 = coinStatsService.month_6(adminTypeDTO);
            int cm_5 = coinStatsService.month_5(adminTypeDTO);
            int cm_4 = coinStatsService.month_4(adminTypeDTO);
            int cm_3 = coinStatsService.month_3(adminTypeDTO);
            int cm_2 = coinStatsService.month_2(adminTypeDTO);
            int cm_1 = coinStatsService.month_1(adminTypeDTO);
            int cthisMonth = coinStatsService.thisMonth(adminTypeDTO);
            model.addAttribute("cm_12", cm_12);
            model.addAttribute("cm_11", cm_11);
            model.addAttribute("cm_10", cm_10);
            model.addAttribute("cm_9", cm_9);
            model.addAttribute("cm_8", cm_8);
            model.addAttribute("cm_7", cm_7);
            model.addAttribute("cm_6", cm_6);
            model.addAttribute("cm_5", cm_5);
            model.addAttribute("cm_4", cm_4);
            model.addAttribute("cm_3", cm_3);
            model.addAttribute("cm_2", cm_2);
            model.addAttribute("cm_1", cm_1);
            model.addAttribute("cthisMonth", cthisMonth);
        }else if(adminTypeDTO.getCoinPeriod().equals("yearly")){
            // 연도별 정렬
            log.info("코인 연도별 정렬");
            int cy_5 = coinStatsService.year_5(adminTypeDTO);
            int cy_4 = coinStatsService.year_4(adminTypeDTO);
            int cy_3 = coinStatsService.year_3(adminTypeDTO);
            int cy_2 = coinStatsService.year_2(adminTypeDTO);
            int cy_1 = coinStatsService.year_1(adminTypeDTO);
            int cthisYear = coinStatsService.thisYear(adminTypeDTO);
            model.addAttribute("cy_5", cy_5);
            model.addAttribute("cy_4", cy_4);
            model.addAttribute("cy_3", cy_3);
            model.addAttribute("cy_2", cy_2);
            model.addAttribute("cy_1", cy_1);
            model.addAttribute("cthisYear", cthisYear);
        }else if(adminTypeDTO.getCoinDate() != null){
            log.info("코인 특정 날짜 검색");
            int cbyDate_m3 = coinStatsService.byDate_m3(adminTypeDTO);
            int cbyDate_m2 = coinStatsService.byDate_m2(adminTypeDTO);
            int cbyDate_m1 = coinStatsService.byDate_m1(adminTypeDTO);
            int cbyDate = coinStatsService.byDate(adminTypeDTO);
            int cbyDate_p1 = coinStatsService.byDate_p1(adminTypeDTO);
            int cbyDate_p2 = coinStatsService.byDate_p2(adminTypeDTO);
            int cbyDate_p3 = coinStatsService.byDate_p3(adminTypeDTO);
            model.addAttribute("cbyDate_m3", cbyDate_m3);
            model.addAttribute("cbyDate_m2", cbyDate_m2);
            model.addAttribute("cbyDate_m1", cbyDate_m1);
            model.addAttribute("cbyDate", cbyDate);
            model.addAttribute("cbyDate_p1", cbyDate_p1);
            model.addAttribute("cbyDate_p2", cbyDate_p2);
            model.addAttribute("cbyDate_p3", cbyDate_p3);
        }

        // 방문자
        int total = visitorService.totalVisitorCnt();
        model.addAttribute("total", total); // 총 방문자 수
        model.addAttribute("date", adminTypeDTO.getDate()); // 특정 날짜
        model.addAttribute("isLogin", adminTypeDTO.getIsLogin()); // 로그인여부
        model.addAttribute("period", adminTypeDTO.getPeriod()); // 기간별 조건

        // 코인
        int coinTotal = coinStatsService.totalCoin();
        model.addAttribute("coinTotal", coinTotal); // 총 충전갯수
        model.addAttribute("coinDate", adminTypeDTO.getCoinDate()); // 코인 특정날짜
        model.addAttribute("coinPeriod", adminTypeDTO.getCoinPeriod()); //코인 기간별 조건

    }

    @GetMapping("/login")
    public void adminLoginGET() {
        log.info("adminLoginGET....");

    }

    @GetMapping("/mentorApply")
    public void mentorApplyGET(Model model) {
        List<MentorApplyDTO> mentorApplyDTO = adminService.getApplyList();
        model.addAttribute("dtoList", mentorApplyDTO);
    }

    @PostMapping("/mentorApply")
    public String mentorApplyPOST(Model model) {

        return "redirect:/admin/mentorApply";
    }


    @GetMapping("/exchangeList")
    public void exchangeList(){ // 환전 신청 처리 페이지
        
    }


}
