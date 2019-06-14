package baitap.lab8;

import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class JMailSenderService extends Thread {

    static {
        JMailSenderService sender = new JMailSenderService();
        sender.start();
    }

    static final List<MimeMessage> queue = new ArrayList<>();

    public static void queue(MimeMessage mail) {
        synchronized (queue) {
            queue.add(mail);
            queue.notify();
        }

    }
    
    public void run() {
        while(true) {
            try {
                synchronized(queue) {
                    if(queue.size() > 0) {
                        try {
                            MimeMessage mail = queue.remove(0);
                            Transport.send(mail);
                            System.out.println("The mail was sent");
                        } catch (MessagingException me) {
                            System.out.println("Unable to send mail");
                        }
                    }
                    else {
                        queue.wait();
                    }
                }
            } catch (InterruptedException ie) {
                break;
            }
        }
    }
}
