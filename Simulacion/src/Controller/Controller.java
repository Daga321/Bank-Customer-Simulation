package Controller;

import Model.Management;
import Persistence.Persistence;
import View.Actioner;
import View.PersonView;
import View.TurnWithPerson;
import View.WindowStadistics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {

    private Management management;
    private Persistence persistence;
    private Actioner actioner;

    private WindowStadistics windowStadistics;

    public Controller(Actioner actioner) {
        management = new Management();
        persistence = new Persistence();

        windowStadistics = new WindowStadistics();
        
        this.actioner = actioner;
        persistence.createFiles();
    }

    public void setPersistenceTurno() {
        persistence.uploadTurno(management.getTurn(), management.getModules());
    }

    public ArrayList<TurnWithPerson> getPersistenceTurno(){
        return persistence.downloadTurn();
    }

    public void setPersistencePerson() {
        persistence.uploadPersons(management.getPersonList(), management.getPasserbyList());
    }

    public ArrayList<PersonView> getPersistenceView(){
        return persistence.downloadPerson();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        switch (event.getActionCommand()){
            case Actioner.CAPTURARDATOS:
                String[] data = actioner.captureData(Actioner.CAPTURARDATOS);
                if (data[0].equals("")){
                    actioner.mensaje("No digito el numero de cedula");
                }else {
                    try {
                        actioner.mensaje(management.addPersonManually(data[0], data[1]));
                    } catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                }

                break;

            case Actioner.ESTADISTICASVENTANA:

                windowStadistics.setVisible(true);
            	
                break;
        }

    }
    
    public void repaint() {
    	windowStadistics.getAreaModulOne().setText(management.personasModuloUno());
        windowStadistics.getAreaModulTwo().setText(management.personasModuloTwo());
        windowStadistics.getAreaModulThree().setText(management.personasModuloThree());
    	windowStadistics.repaint();
    }
}
