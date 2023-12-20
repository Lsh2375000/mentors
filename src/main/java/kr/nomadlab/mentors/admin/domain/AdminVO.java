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
    private Set<AdminRole> roleSet = new HashSet<>();

    public void addRole(AdminRole adminRole) {
        this.roleSet.add(adminRole);
    }

    public void setRoleSet(Set<AdminRole> roleSet) {
        this.roleSet = roleSet;
    }
}
