package kr.nomadlab.progrow_project.member.domain;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SMemberVO {
    private String mid;
    private String mpw;

    private boolean del;
    private boolean social;

    private String type;

    private String nickname;

    //    @ElementCollection : Entity가 아닌 단순한 영태의 객체 집합을 정의하고 관리
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changeMid(String mid) {
        this.mid = mid;
    }

    public void changeMpw(String mpw) {
        this.mpw = mpw;
    }

    public void changeNickname(String nickname) {this.nickname = nickname;}

    public void changeDel(boolean del) {
        this.del = del;
    }

    public void changeSocial(boolean social) {
        this.social = social;
    }

    public void addRole(MemberRole memberRole) {
        this.roleSet.add(memberRole);
    }

    public void setRoleSet(Set<MemberRole> roleSet) {
        this.roleSet = roleSet;
    }

    public void clearRole() {
        this.roleSet.clear();
    }
}
