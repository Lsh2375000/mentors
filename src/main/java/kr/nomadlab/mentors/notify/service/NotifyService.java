package kr.nomadlab.mentors.notify.service;

import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.notify.dto.NotifyDto;

import java.util.List;

public interface NotifyService {
    void addNotify(Long mno, String types);

    int countNotify(Long mno);

    void readNotify(Long mno);

    List<NotifyDto> getNotReadNotify(Long mno);

    void passNotify(NotifyDto notifyDto, MemberSecurityDTO member);
}
