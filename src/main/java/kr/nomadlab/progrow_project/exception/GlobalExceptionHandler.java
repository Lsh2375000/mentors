package kr.nomadlab.progrow_project.exception;//package kr.nomadlab.progrow_project.controller.mentoringController;
//
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.ui.Model;
//
//@ControllerAdvice
//@Log4j2
//public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception e, Model model) {
//        // 예외 발생 시 에러 메시지를 로깅하거나 필요한 처리를 수행할 수 있습니다.
//        // 로그 기록
//        log.error("에러 발생: ", e);
//
//        // 에러 페이지로 리다이렉트
//        return "errorPage"; // 에러 페이지로 이동하는 경로를 설정해주세요 .
//    }
//}
