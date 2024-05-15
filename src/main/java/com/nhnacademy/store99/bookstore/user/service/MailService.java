package com.nhnacademy.store99.bookstore.user.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


/**
 * @author jinhyogyeom
 */

@Service
public class MailService {
    private final JavaMailSender emailsender;
    private String ePw;

    @Autowired
    public MailService(JavaMailSender emailsender) {
        this.emailsender = emailsender;
    }

    /**
     * 실제 발송하는 이메일 내용
     *
     * @param to
     * @return MimeMessage
     */
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = emailsender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);
        message.setSubject("Store99 회원가입 이메일 인증");

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<h1> Store99 입니다</h1>";
        msgg += "<br>";
        msgg += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += ePw + "</strong><div><br/> ";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");
        message.setFrom(new InternetAddress("jdkk1541@naver.com", "Store99_Admin"));

        return message;
    }


    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3);

            switch (index) {
                case 0:
                    key.append((char) (rnd.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) (rnd.nextInt(26) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }

        return key.toString();
    }

    public String sendSimpleMessage(String to) throws Exception {

        ePw = createKey();

        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        try {
            emailsender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }


        return ePw;
    }
}


