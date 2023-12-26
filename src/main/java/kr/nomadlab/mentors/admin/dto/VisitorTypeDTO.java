package kr.nomadlab.mentors.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorTypeDTO {
    
    // 통계조건
    
    private String isLogin = "all"; // 로그인 사용자 여부
    private String period = "daily"; // 기간별 조건
    private String date; // 달력 선택 특정 날짜
}
