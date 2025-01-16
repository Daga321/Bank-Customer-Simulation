package Model;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;


public class Management {


    private ArrayList<String> personListModulOne;
    private ArrayList<String> personListModulTwo;
    private ArrayList<String> personListModulThree;

    private ArrayList<Proceeding> proceedingsList;
    private ArrayList<LinkedList<Person>> queueList;
    private ArrayList<Person> personList;
    private ArrayList<Passerby> passerbyList;
    private Thread threadGeneratePerson;
    private Thread threadGeneratePasserby;
    private int gTurn;
    private int aTurn;
    private int cTurn;

    public Management(){
        proceedingsList = new ArrayList<Proceeding>();
        proceedingsList.add(new Proceeding(Module.Prestamos, this));
        proceedingsList.add(new Proceeding(Module.Asesorias, this));	//[0]  Module 1 //[1]  Module 2 //[2]  Module 3
        proceedingsList.add(new Proceeding(Module.Retiros, this));

        queueList= new ArrayList<LinkedList<Person>>();
        queueList.add(new LinkedList<Person>());
        queueList.add(new LinkedList<Person>());	//[0]  Module 1 //[1]  Module 2 //[2]  Module 3
        queueList.add(new LinkedList<Person>());

        passerbyList = new ArrayList<Passerby>();
        threadGeneratePasserby = new Thread(new RunnableGeneratePasserby(this));
        threadGeneratePasserby.start();
        personList = new ArrayList<Person>();
        threadGeneratePerson = new Thread(new RunnableGeneratePerson(this));
        threadGeneratePerson.start();

        personListModulOne = new ArrayList<>();
        personListModulTwo = new ArrayList<>();
        personListModulThree = new ArrayList<>();

        gTurn = 0;
        aTurn = 0;
        cTurn = 0;
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void deletePerson(Person person) {
        personList.remove(person);
    }
    public void deleteFromQueue(Person person) {
        queueList.get(2).remove(person);
    }

    public void addpasserby(Passerby passerby) {
        passerbyList.add(passerby);
    }

    public void deletepasserby(Passerby passerby) {
        passerbyList.remove(passerby);
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public ArrayList<Passerby> getPasserbyList() {
        return passerbyList;
    }

    public void setTurn(Person person) {
        if (person.getModule()==Module.Prestamos) {
            gTurn++;
            person.setTurn(gTurn);
            queueList.get(0).add(person);

        }else if (person.getModule()==Module.Asesorias) {
            aTurn++;
            person.setTurn(aTurn);
            queueList.get(1).add(person);

        }else if (person.getModule()==Module.Retiros){
            cTurn++;
            person.setTurn(cTurn);
            queueList.get(2).add(person);
        }
    }

    public int getQueuePosition(Person person) {
        if (person.getModule()==Module.Prestamos) {
            for (int i=0; i<queueList.get(0).size(); i++) {
                if (queueList.get(0).get(i)==person) {
                    return i+1;
                }
            }
        }else if(person.getModule()==Module.Asesorias) {
            for (int i=0; i<queueList.get(1).size(); i++) {
                if (queueList.get(1).get(i)==person) {
                    return i+1;
                }
            }

        }else if (person.getModule() == Module.Retiros){
            for (int i=0; i<queueList.get(2).size(); i++ ){
                if (queueList.get(2).get(i)==person) {
                    return i+1;
                }
            }
        }
        return -1;
    }

    public boolean isAModuleFree(Person person) {
        if (person.getModule()==Module.Prestamos && proceedingsList.get(0).getPerson()==null) {
            return true;
        }else if(person.getModule()==Module.Asesorias && proceedingsList.get(1).getPerson()==null) {
            return true;
        }else if(person.getModule()==Module.Retiros && proceedingsList.get(2).getPerson()==null) {
            return true;
        }
        return false;
    }

    public boolean waiting(Person person) {
        if(getQueuePosition(person) == 1 && isAModuleFree(person)) {
            return false;
        }
        return true;
    }

    public void nextPerson(Proceeding proceeding) {
        if (proceeding.getModule()==Module.Prestamos) {
            proceedingsList.get(0).setPerson(null);
        }else if (proceeding.getModule()==Module.Asesorias) {
            proceedingsList.get(1).setPerson(null);
        }else{
            proceedingsList.get(2).setPerson(null);
        }
    }

    public void goToModule(Person person) {
        if (person.getModule()==Module.Prestamos) {
        	personListModulOne.add("Nombre: " + person.getPersonName()+"  Cedula: " + person.getPersonID()+ "\n");
            proceedingsList.get(0).setPerson(queueList.get(0).poll());
            proceedingsList.get(0).setCount();
        }else if (person.getModule()==Module.Asesorias) {
        	personListModulTwo.add("Nombre: " + person.getPersonName()+"  Cedula: " + person.getPersonID()+ "\n");
            proceedingsList.get(1).setPerson(queueList.get(1).poll());
            proceedingsList.get(1).setCount();
        }else if (person.getModule()==Module.Retiros){
        	personListModulThree.add("Nombre: " + person.getPersonName()+"  Cedula: " + person.getPersonID()+ "\n");
            proceedingsList.get(2).setPerson(queueList.get(2).poll());
            proceedingsList.get(2).setCount();
        }
    }

    public boolean waitBeingServed(Person person) {
        if(person.getModule()==Module.Retiros && proceedingsList.get(2).getPerson()==null) {
            return true;
        }
        return false;
    }

    public String addPersonManually(String id, String module){

        int pick = new Random().nextInt(Name.values().length);
        Date dateA = new Date();
        int yearF = (int)(Math.random()*(2000-1940+1)+1940);
        String yearS = Integer.toString(yearF);
        String fecha = "10/05/" + yearS;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dateA = simpleDateFormat.parse(fecha);

            Person person = null;
            if (module.equals("Prestamos")){
                person = new Person(id, Name.values()[pick], new Color(255,255,255), Module.valueOf(module), dateA ,false, this);
            }else if (module.equals("Asesorias")){
                person = new Person(id, Name.values()[pick], new Color(255,0,0), Module.valueOf(module), dateA ,false, this);
            }else if (module.equals("Retiros")){
                person = new Person(id, Name.values()[pick], new Color(255,128,0), Module.valueOf(module), dateA ,false, this);
            }
            personList.add(person);
            return "Se�or(a): " + Name.values()[pick]+ " C.C: "+id + "    Turno Asignado Correctamente" + "\n" + "Verifique su turno en el modulo " + module + "\n";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Ha ocurrido un error inesperado";
    }

    public ArrayList<LinkedList<Person>> getTurn(){
        return queueList;
    }

    public ArrayList<Proceeding> getModules(){
        return proceedingsList;
    }

    public String personasModuloUno(){
        String msj = "";
        msj += "Personas Atentidas en el modulo 1 --- " + proceedingsList.get(0).getCount() + "\n";
        
        if (personListModulOne.size()>0) {
        	for (int i = 0; i < personListModulOne.size(); i++){
                msj += personListModulOne.get(i);

            }
		}
        return msj;

    }

    public String personasModuloTwo(){
        String msj = "";
        msj += "Personas Atentidas en el modulo 2 --- " + proceedingsList.get(1).getCount() + "\n";
        
        if (personListModulTwo.size()>0) {
        	for (int i = 0; i < personListModulTwo.size(); i++){
                msj += personListModulTwo.get(i);

            }
		}
        return msj;

    }

    public String personasModuloThree(){
        String msj = "";
        msj += "Personas Atentidas en el modulo 3 --- " + proceedingsList.get(2).getCount() + "\n";
        if (personListModulThree.size()>0) {
        	for (int i = 0; i < personListModulThree.size(); i++){
                msj += personListModulThree.get(i);

            }
		}
        return msj;

    }

    public ArrayList<Proceeding> getProceedingsList() {
        return proceedingsList;
    }
}
