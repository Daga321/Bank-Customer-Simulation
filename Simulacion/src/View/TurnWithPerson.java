package View;

import java.util.ArrayList;

public class TurnWithPerson {

    private PersonView person;
    private ArrayList<PersonView> personViews;

    public TurnWithPerson(PersonView person, ArrayList<PersonView> personViews) {
        this.person = person;
        this.personViews = personViews;
    }

    public PersonView getPerson() {
        return person;
    }
    
    public boolean existPerson() {
    	return person!=null ? true: false;
    }

    public ArrayList<PersonView> getPersonViews() {
        return personViews;
    }
    
}
