package kr.nomadlab.mentors.notify.service;

import kr.nomadlab.mentors.notify.dto.NotifyDto;

import java.util.List;

public interface NotifyService {
    void addNotify(Long mno, String types);

    int countNotify(Long mno);

    void readNotify(Long mno);

    List<NotifyDto> getNotReadNotify(Long mno);

    void passNotify(String types, Long receiverMno, String nickName);
}
