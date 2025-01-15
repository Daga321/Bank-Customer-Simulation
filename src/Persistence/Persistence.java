package Persistence;


import Model.Passerby;
import Model.Person;
import Model.Proceeding;
import View.PersonView;
import View.TurnWithPerson;

import java.awt.Color;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * The Class Persistence.
 */
public class Persistence {

    private Path path;

    public void createFiles() {
        try {
            File file = new File(".");
            file = new File(file.getCanonicalPath()+"/SimulationFiles");
            path = file.toPath();
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {}
    }

    //-----------------------------------------------------------------------------------Turn-------------------------------------------
    public void uploadTurno(ArrayList<LinkedList<Person>> queueList, ArrayList<Proceeding> proceedingsList) {
        FileWriter fwFileWriter = null;
        try {
            for(int i = 0; i<proceedingsList.size(); i++) {
                fwFileWriter = new FileWriter(path+"/turnList"+i+".txt");
                BufferedWriter bwWrite = new BufferedWriter(fwFileWriter);

                if (proceedingsList.get(i).getPerson()!=null) {
                	Person person = proceedingsList.get(i).getPerson();
                    bwWrite.write(person.getPersonName()+";"+person.getPersonID()+";"+person.getDate()+";"+person.getTurn()+"\n");
                    if (queueList.get(i).size()>0) {
                        for (int j = 0; j<queueList.get(i).size(); j++) {
                            bwWrite.write(queueList.get(i).get(j).getPersonName()+";");
                            bwWrite.write(queueList.get(i).get(j).getPersonID()+";");
                            bwWrite.write(queueList.get(i).get(j).getDate()+";");
                            bwWrite.write(queueList.get(i).get(j).getTurn()+"\n");
                        }
                    }
                }
                bwWrite.close();
            }
        } catch (Exception e) {
            System.out.println("ERROR EN uploadTurn  "+e);
        } finally {
            if (fwFileWriter != null) {
                try {
                    fwFileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<TurnWithPerson> downloadTurn() {
        ArrayList<TurnWithPerson> turnWithPersonList = new ArrayList<TurnWithPerson>();
        ArrayList<PersonView> personList;
        try {
            for (int i=0; i<3; i++) {
                personList = new ArrayList<PersonView>();
                File file = new File(path+"/turnList"+i+".txt");
                Scanner sc = new Scanner(file);

                PersonView personInTurn = null;
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    Scanner delimiting = new Scanner(line);
                    delimiting.useDelimiter("\\s*;\\s*");

                    if (personInTurn==null) {
                    	personInTurn = new PersonView(delimiting.next(), delimiting.next(), delimiting.next(), delimiting.next());
                    }else {
                        PersonView person = new PersonView(delimiting.next(), delimiting.next(), delimiting.next(), delimiting.next());
                        personList.add(person);
                    }
                }
                turnWithPersonList.add(new TurnWithPerson(personInTurn, personList));
            }
        } catch (Exception e) {
            System.out.println("ERROR EN downloadTurn " + e );
        }

        return turnWithPersonList;
    }

//-----------------------------------------------------------------------------------PERSON-------------------------------------------

    public void uploadPersons(ArrayList<Person> personList, ArrayList<Passerby> passerbyList) {
        FileWriter fwFileWriter = null;
        try {
            fwFileWriter = new FileWriter(path+"/personList.txt");
            BufferedWriter bwWrite = new BufferedWriter(fwFileWriter);

            if (personList.size()>0) {
                for (int i = 0; i < personList.size(); i++) {
                    bwWrite.write(personList.get(i).getColor().getRed()+";P;"+personList.get(i).getColor().getGreen()+";P;"+
                            personList.get(i).getColor().getBlue()+";P;"+personList.get(i).getX()+";P;"+personList.get(i).getY()+"\n");
                }
            }
            if (passerbyList.size()>0) {
                for (int i = 0; i < passerbyList.size(); i++) {
                    bwWrite.write(passerbyList.get(i).getColor().getRed()+";P;"+passerbyList.get(i).getColor().getGreen()+";P;"+
                            passerbyList.get(i).getColor().getBlue()+";P;"+passerbyList.get(i).getX()+";P;"+passerbyList.get(i).getY()+"\n");
                }
            }
            bwWrite.close();
//			System.out.println("EL ARCHIVO \"personList.txt\" FUE CREADO CORRECTAMENTE");
        } catch (IOException e) {
            System.out.println("ARCHIVO \"personList.txt\" NO EXISTE");
        } finally {
            if (fwFileWriter != null) {
                try {
                    fwFileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<PersonView> downloadPerson() {
        ArrayList<PersonView> personList = new ArrayList<PersonView>();
        try {
            File file = new File(path+"/personList.txt");
            Scanner sc = new Scanner(file);
            Color color;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Scanner delimiting = new Scanner(line);
                delimiting.useDelimiter("\\s*;P;\\s*");

                color = new Color(Integer.parseInt(delimiting.next()), Integer.parseInt(delimiting.next()), Integer.parseInt(delimiting.next()));
                PersonView Person = new PersonView(color, Integer.parseInt(delimiting.next()), Integer.parseInt(delimiting.next()));
                personList.add(Person);
            }
        } catch (FileNotFoundException e) {
            System.out.println("EL ARCHIVO \"personList.txt\" NO EXISTE ");
        }

        return personList;
    }

}