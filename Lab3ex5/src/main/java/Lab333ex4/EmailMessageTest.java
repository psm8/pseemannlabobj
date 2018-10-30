package Lab333ex4;

import java.util.*;

public class EmailMessageTest {
    public static void main(String[] args) {
        EmailMessage msg = new EmailMessage.Builder().setFrom("psm7agh@gmail.com").setTo(new LinkedList<String>(Arrays.asList("psm7agh@gmail.com")))
                .setSubject("Tytul").setContent("Tresc").build();
        msg.send();
    }
}
