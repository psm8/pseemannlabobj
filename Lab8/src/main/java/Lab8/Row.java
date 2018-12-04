package Lab8;

import javafx.beans.property.SimpleStringProperty;

public class Row {
    private SimpleStringProperty isbn;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty year;

    public Row(){
        isbn = new SimpleStringProperty();
        title = new SimpleStringProperty();
        author = new SimpleStringProperty();
        year = new SimpleStringProperty();
    }


    public String getIsbn() {
        return isbn.get();
    }

    public void setIsbn(String _isbn) {
        isbn.set(_isbn);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String _title) {
        title.set(_title);
    }

    public String getAuthor() {
        return author.get();
    }

    public String getAuthorName() {
        String authorFullName = author.get();
        int authorNameStarts = authorFullName.indexOf(" ");
        StringBuilder authorName = new StringBuilder();
        for (int i = authorNameStarts + 1; i < authorFullName.length(); i++) {
            authorName.append(authorFullName.charAt(i));
        }
        return authorName.toString();
    }

    public void setAuthor(String _author) {
        author.set(_author);
    }

    public String getYear() {
        return year.get();
    }

    public void setYear(String _author) {
        year.set(_author);
    }
}
