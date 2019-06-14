package baitap.lab8;

import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class JMailSender {
    
    private static final String email = "hungpx@is.viettel.com.vn";
    private static final String password = "xxx123";
    private static final Properties config = new Properties();

    static {
        //config.setProperty("mail.smtp.host", "smtp.is.viettel.com.vn");
        config.setProperty("mail.smtp.host", "smtp.is.viettel.com.vn");
        config.setProperty("mail.smtp.post", "465");
        config.setProperty("mail.smtp.starttls.enable", "true");
        config.setProperty("mail.smtp.auth", "true");
        config.setProperty("mail.smtp.socketFactory.class", "javax.net.SSLSocketFactory");
        config.setProperty("mail.smtp.socketFactory.fallback", "false");       
    }

    public static Session GetSession() {
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        };
        Session session = Session.getInstance(config, authenticator);
        return session;
    }

    public static void Send(String to, String subject, String body) {
        String from = String.format("EStore Web Master <%s>", email);
        JMailSender.Send(from, to, subject, body);
    }

    public static void Send(String from, String to, String subject, String body) {
        String cc = "", bcc = "", attach = "";
        JMailSender.Send(from, to, cc, bcc, subject, body, attach);;
    }

    public static void Send(String from, String to, String cc, String bcc, String subject, String body, String attachments) {
        try {
            MimeMessage mail = new MimeMessage(GetSession());
            String[] addresses = from.split("[<>]");
            String name = addresses[0].trim();
            String email = (addresses.length > 1 ? addresses[1] : addresses[0].trim());
            InternetAddress fromAddress = new InternetAddress(email, name, "utf8");

            mail.setFrom(fromAddress);
            mail.setReplyTo(new InternetAddress[]{fromAddress});

            final String toEmails = to.trim().replaceAll("[,;\\s]+", ",");
            mail.addRecipients(Message.RecipientType.TO, toEmails);

            if (cc != null && cc.trim().length() > 0) {
                final String ccEmail = cc.trim().replaceAll("[,;\\s]+", ",");
                mail.addRecipients(Message.RecipientType.CC, ccEmail);
            }

            if (bcc != null && bcc.trim().length() > 0) {
                final String bccEmail = bcc.trim().replaceAll("[,;\\s]+", ",");
                mail.addRecipients(Message.RecipientType.BCC, bccEmail);
            }
            mail.setSubject(subject, "utf-8");;
            mail.setContent(body, "text/html;charset=utf-8");
            mail.setSentDate(new Date());

            if (attachments != null && attachments.trim().length() > 0) {
                MimeMultipart multipart = new MimeMultipart();

                MimeBodyPart mailBodyPart = new MimeBodyPart();
                mailBodyPart.setContent(body, "text/html;charset=utf8");
                multipart.addBodyPart(mailBodyPart);

                String[] paths = attachments.split("[,;]+");

                for (String path : paths) {
                    File file = new File(path.trim());
                    MimeBodyPart attachBodyPart = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(file);
                    attachBodyPart.setDataHandler(new DataHandler(fds));
                    attachBodyPart.setFileName(file.getName());
                    multipart.addBodyPart(attachBodyPart);
                }
                mail.setContent(multipart);
            }

            JMailSenderService.queue(mail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        String from = "hungpx@is.viettel.com.vn";
        String to = "hungpx@is.viettel.com.vn";
        String subject = "test";
        String body = "Body test";
        JMailSender.Send(from, to, subject, body);
    }
}
