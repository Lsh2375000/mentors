package kr.nomadlab.mentors.member.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberVO {
    private Long mno; // 회원 고유번호
    private String memberId; // 세션에 저장된 아이디
    private String passwd; // 세션에 저장한 비밀번호
    private LocalDateTime regDate; // 회원 등록 날짜
    private LocalDateTime modDate; // 회원 수정 날짜
    private boolean del; // 회원 삭제여부
    private boolean social; // 회원 소셜 로그인 여부
    private String nickname; // 회원 닉네임
    private String memberName; // 회원 이름
    private int coin; // 회원 보유 코인
    private String region; // 회원 사는 곳

    //    @ElementCollection : Entity가 아닌 단순한 영태의 객체 집합을 정의하고 관리
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

//    public void changeMid(String memberId) {
//        this.memberId = memberId;
//    }
//
//    public void changeMpw(String passwd) {
//        this.passwd = passwd;
//    }
//
//    public void changeNickname(String nickname) {this.nickname = nickname;}
//
//    public void changeDel(boolean del) {
//        this.del = del;
//    }
//
//    public void changeSocial(boolean social) {
//        this.social = social;
//    }
//
    public void addRole(MemberRole memberRole) {
        this.roleSet.add(memberRole);
    }

    public void setRoleSet(Set<MemberRole> roleSet) {
        this.roleSet = roleSet;
    }

//    public void clearRole() {
//        this.roleSet.clear();
//    }
}
