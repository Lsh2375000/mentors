package kr.nomadlab.mentors.admin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class AdminSecurityDTO extends User {
    private Long ano;
    private String adminId;
    private String passwd;

    public AdminSecurityDTO(Long ano, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        log.info(authorities);
        this.ano = ano;
        this.adminId = username;
        this.passwd = password;

    }


}
