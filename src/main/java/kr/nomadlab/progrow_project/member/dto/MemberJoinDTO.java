package kr.nomadlab.progrow_project.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDTO {
    // 회원정보만 가져와서 서비스단에서 DB에 넣어주기만 하는 DTO / 여기서 회원가입 수정 정보를 가져옴

    private String mid; // 공통 회원 이메일
    private String mpw; // 공통 회원 비밀번호
    private boolean del; // 공통 회원 탈퇴여부(당장 사용하진 않음)
    private boolean social; // 회원 소셜 로그인 여부
    private String type; // 회원 타입
    private String nickname; // 회원 닉네임

}
