package kr.nomadlab.mentors.payInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayInfoDto {
    private Long mbNo;
    private Long mentorMno;
    private Long menteeMno;
    private int price;
    private boolean isComplete;
    private LocalDate completeDate;
    private LocalDateTime addDate;
}
