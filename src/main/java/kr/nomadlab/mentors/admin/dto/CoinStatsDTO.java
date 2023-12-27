package kr.nomadlab.mentors.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinStatsDTO {

    private Long csNo; // 고유번호
    private Long amount; // 충전 갯수
    private LocalDate payDate; // 충전 일
}
