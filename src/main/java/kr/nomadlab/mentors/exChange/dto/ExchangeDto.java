package kr.nomadlab.mentors.exChange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDto {
    private Long exNo;
    private Long mno;
    private String memberName;
    private String bank;
    private String accountNum;
    private int coin;
    private int amount;
    private boolean isComplete;
}
