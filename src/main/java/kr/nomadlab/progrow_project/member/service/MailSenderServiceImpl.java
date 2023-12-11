package kr.nomadlab.progrow_project.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;

@Service
@Log4j2
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService{


    private final JavaMailSender mailSender; // 메일을 보내는 역할

    private final TemplateEngine templateEngine;


    private String confirmKey; // 인증문자

    @Value("${myapp.custom.mail.sender.mailFrom}")
    private String mailFrom; // 보내는 메일

    @Value("${myapp.custom.mail.sender.mailFromName}")
    private String mailFromName; // 보내는 사람


    @Override
    public boolean sendMailByAddMember(String mailTo) throws Exception { // 회원 가입시 인증 메일 발송
        this.confirmKey = createConfirmKey();
        MimeMessage message = createMessageByAddMember(mailTo);
        try {
            mailSender.send(message);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getConfirmKey() {
        return confirmKey;
    }

    private MimeMessage createMessageByAddMember(String mailTo) throws Exception {
        // 회원 가입시 인증 메일 관련 내용 작성
        log.info("보내는 대상 : " + mailTo);
        log.info("인증 번호 : " + confirmKey);

        Context context = new Context();
        context.setVariable("confirmKey", confirmKey);
        String messageText = templateEngine.process("/mail/add_member", context);

        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, mailTo);
        message.setSubject(mailFromName + "이메일 인증");
        message.setText(messageText, "utf-8", "html");
        message.setFrom(new InternetAddress(mailFrom, mailFromName));
        return message;

    }




    private static String createConfirmKey() { // 랜덤 인증문자 생성 메소드
        // 인증문자 작성
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 8자리 인증코드
            int index = rnd.nextInt(3); // 0~2까지 랜덤 숫자

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a~z
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }

        }
        return key.toString();
    }
}
