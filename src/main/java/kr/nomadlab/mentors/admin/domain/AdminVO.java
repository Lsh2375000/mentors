package kr.nomadlab.mentors.admin.domain;

import kr.nomadlab.mentors.member.domain.MemberRole;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminVO {
    private Long ano;
    private String adminId;
    private String passwd;


    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addRole(MemberRole adminRole) {
        this.roleSet.add(adminRole);
    }

    public void setRoleSet(Set<MemberRole> roleSet) {
        this.roleSet = roleSet;
    }
}
