package kr.nomadlab.mentors.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    // 회원정보만 가져와서 서비스단에서 DB에 넣어주기만 하는 DTO / 여기서 회원가입 수정 정보를 가져옴
    private Long mno; // 회원 고유 번호
    private String memberId; // 공통 회원 이메일
    private String passwd; // 공통 회원 비밀번호
    private boolean del; // 공통 회원 탈퇴여부(당장 사용하진 않음)
    private boolean social; // 회원 소셜 로그인 여부
    private String nickname; // 회원 닉네임
    private String memberName; // 회원 이름
    private int coin; // 회원 보유 코인
    private String region; // 회원 사는 곳

}
