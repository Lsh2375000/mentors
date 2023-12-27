package kr.nomadlab.mentors.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Arrays;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminExSearchDTO { //관리자 환전 관리 검색기능
    private String keyword; //검색내용
    private String[] types; //검색어

    private Integer up;
    private Integer down;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate from; //시작 날짜

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate to; //끝 날짜

    public boolean checkType(String type) {
        if (this.types == null || this.types.length == 0) {
            return false;
        }
        return Arrays.asList(this.types).contains(type);
    }
}
