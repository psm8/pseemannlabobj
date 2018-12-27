import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Person {
    SimpleStringProperty fullName;
    SimpleStringProperty phone;
    SimpleStringProperty pesel;

    Person(String _fullName, String _phone, String _pesel){
        this.fullName = new SimpleStringProperty(_fullName);
        this.phone = new SimpleStringProperty(_phone);
        this.pesel = new SimpleStringProperty(_pesel);
    }

    public String getFirstName() {
        String authorFullName = fullName.get();
        int authorNameStarts = authorFullName.indexOf(" ");
        StringBuilder authorName = new StringBuilder();
        for (int i = 0; i < authorNameStarts; i++) {
            authorName.append(authorFullName.charAt(i));
        }
        return authorName.toString();
    }

    public String getLastName() {
        String authorFullName = fullName.get();
        int authorNameStarts = authorFullName.indexOf(" ");
        StringBuilder authorName = new StringBuilder();
        for (int i = authorNameStarts + 1; i < authorFullName.length(); i++) {
            authorName.append(authorFullName.charAt(i));
        }
        return authorName.toString();
    }

    public SimpleStringProperty fullNameProperty() {
        return fullName;
    }


    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public String getAge() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        StringBuilder peselSB = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            peselSB.append(pesel.get().charAt(i));
        }
        /*100+year%100 zeby age byl dodatni*/
        int age = (10000*(100+year%100) + 100*month + day - Integer.parseInt(peselSB.toString()))/10000;
        return String.valueOf((age)%100);
    }

    public String getPesel() {
        return pesel.get();
    }

    public SimpleStringProperty peselProperty() {
        return pesel;
    }
}
