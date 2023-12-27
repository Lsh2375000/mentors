package kr.nomadlab.mentors.exChange.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExchangeVO {
    private Long exNo;
    private Long mno;
    private String memberName;
    private String bank;
    private String accountNum;
    private int coin;
    private int amount;
    private boolean isComplete;
    private LocalDateTime addDate;
    private LocalDateTime completeDate;
}
