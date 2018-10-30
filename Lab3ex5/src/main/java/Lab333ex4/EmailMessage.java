package Lab333ex4;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailMessage {

    private String from; //required (must be e-mail)
    private LinkedList<String> to; //required at least one (must be e-mail)
    private String subject; //optional
    private String content; //optional
    private String mimeType;  // optional
    private LinkedList<String> cc; //optional
    private LinkedList<String> bcc; // optional

    protected EmailMessage(String from, LinkedList<String> to, String subject, String content,
                           String mimeType, LinkedList<String> cc, LinkedList<String> bcc)throws IllegalArgumentException
    {

        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat1 = pattern.matcher(from);
        if(mat1.matches()){
            this.from = from;
        }
        else{
            throw new IllegalArgumentException("Wrong email From");
        }
        this.to = new LinkedList<String>();
        for(String tmp:to) {
            Matcher mat2 = pattern.matcher(tmp);
            if(mat2.matches()){
                this.to.add(tmp);
            }
            else{
                throw new IllegalArgumentException("Wrong email To");
            }
        }
        if(subject != null){
            this.subject = subject;
        }
        else {
            this.subject = null;
        }
        if(content != null){
            this.content = content;
        }
        else {
            this.content = null;
        }
        if(mimeType != null){
            this.mimeType = mimeType;
        }
        else {
            this.mimeType = null;
        }
        if(cc != null){
            this.cc = cc;
        }
        else {
            this.cc = null;
        }
        if(bcc != null){
            this.bcc = bcc;
        }
        else {
            this.bcc = null;
        }
    }
    public void send(){
        try {
            boolean sessionDebug = false;
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com" );
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            for(String tmp:to) {
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(tmp));
            }
            msg.setSubject(subject);
            msg.setText(content);
            Transport transport=mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", from, JIn.getString());
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
        } catch (MessagingException e) {}
    }
    public static class Builder {
        private String from; //required (must be e-mail)
        private LinkedList<String> to; //required at least one (must be e-mail)
        private String subject; //optional
        private String content; //optional
        private String mimeType;  // optional
        private LinkedList<String> cc; //optional
        private LinkedList<String> bcc; // optional

        public Builder setFrom(String from) {
            Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
            Matcher mat = pattern.matcher(from);
            if(mat.matches()){
                this.from = from;
            }
            else{
                throw new IllegalArgumentException("Wrong email From");
            }
            return this;
        }

        public Builder setTo(LinkedList<String> to1) {
            if(this.to == null ){
                this.to = new LinkedList<String>();
            }
            Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
            for(String tmp:to1) {
                Matcher mat = pattern.matcher(tmp);
                if(mat.matches()){
                    this.to.add(tmp);
                }
                else{
                    throw new IllegalArgumentException("Wrong email To");
                }
            }
            return this;
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setMimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public Builder setcc(LinkedList<String> cc) {
            this.cc = cc;
            return this;
        }

        public Builder setbcc(LinkedList<String> bcc) {
            this.bcc = bcc;
            return this;
        }
        public static Builder builder() {
            return new EmailMessage.Builder();
        }
        public EmailMessage build(){
            return new EmailMessage(from,to,subject,content,mimeType,cc,bcc);
        }
    }
}

