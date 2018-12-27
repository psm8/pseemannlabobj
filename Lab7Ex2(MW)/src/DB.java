import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

public class DB implements Iterable<Person>{
    private ObservableList<Person> data;

    public DB(){
        this.data = FXCollections.observableArrayList();
    }

    public void add(Person p) {
        data.add(p);
    }

    public void remove(Person p) {
        data.remove(p);
    }

    @Override
    public Iterator<Person> iterator() {
        return data.iterator();
    }

}
