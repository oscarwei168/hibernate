package tw.com.oscar.fx8.model;

import javafx.beans.property.*;
import tw.com.oscar.fx8.util.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class Person {
    
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IntegerProperty age;
    private final ObjectProperty<LocalDate> birthday;
    
    public Person() {
        this(null, null);
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleIntegerProperty(20);
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(2010, 10, 23));
    }
    
    public StringProperty firstNameProperty() {
        return firstName;
    }
    
    public String getFirstName() {
        return firstName.get();
    }
    
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    public StringProperty lastNameProperty() {
        return lastName;
    }
    
    public String getLastName() {
        return lastName.get();
    }
    
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    public IntegerProperty ageProperty() {
        return age;
    }
    
    public int getAge() {
        return age.get();
    }
    
    public void setAge(int age) {
        this.age.set(age);
    }
    
    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday.get();
    }
    
    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }
    
}
