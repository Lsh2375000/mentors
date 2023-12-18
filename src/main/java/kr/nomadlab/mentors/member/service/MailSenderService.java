package kr.nomadlab.mentors.member.service;

public interface MailSenderService {
    boolean sendMailByAddMember(String mailTo) throws Exception;

    String getConfirmKey();
}
