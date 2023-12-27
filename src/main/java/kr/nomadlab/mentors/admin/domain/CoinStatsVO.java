package kr.nomadlab.mentors.admin.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CoinStatsVO {
    private Long csNo; // 고유번호
    private Long amount; // 충전 갯수
    private LocalDate payDate; // 충전 일
}
