package Model;

public class Proceeding {
    private Module module;
    private Person person;
    private Thread tWork;
    private int count;

    public Proceeding(Module module, Management management) {
        this.module = module;
        person = null;
        count = 0;
        tWork = new Thread(new RunnableModule(this, management));
        tWork.start();
    }

    public Module getModule() {
        return module;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

	public int getCount() {
		return count;
	}

	public void setCount() {
		count++;
	}
    
	
    

}
