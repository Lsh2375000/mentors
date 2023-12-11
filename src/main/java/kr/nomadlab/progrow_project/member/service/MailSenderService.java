package kr.nomadlab.progrow_project.member.service;

public interface MailSenderService {
    boolean sendMailByAddMember(String mailTo) throws Exception;

    String getConfirmKey();
}
