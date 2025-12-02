/**
 * Represents a subject with a name. Also provides methods to get and set the name of the Subject.
 * @author Aditi Sharma
 * @version 1.0
 */
public class Subject {
    private String name; 

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
