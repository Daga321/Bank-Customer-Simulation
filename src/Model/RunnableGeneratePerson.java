package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RunnableGeneratePerson implements Runnable{

    private Management management;

    public RunnableGeneratePerson(Management management) {
        this.management = management;
    }

    @Override
    public void run() {
        Person person;
        int id;
        Color color;
        int nameIndex;
        int moduleIndex;
        Calendar calendar = new GregorianCalendar();
        Date date;
        long begin = System.currentTimeMillis();
        
        while (System.currentTimeMillis()<(begin+60000)) {
            try {
                if (management.getPersonList().size()<18) {
                    id = (int)(Math.random()*(999999999+1)+1000000000);
                    color = new Color((int)(Math.random()*(256)),(int)(Math.random()*(256)),(int)(Math.random()*(256)));
                    nameIndex = (int)(Math.random()*(Name.values().length));
                    do {
                        moduleIndex = (int)(Math.random()*(Module.values().length));
                    }while(!checkModule(Module.values()[moduleIndex]));
                    calendar.set(Calendar.YEAR, (int)(Math.random()*((2021-19)-(2021-80))+(2021-80)));
                    calendar.set(Calendar.DAY_OF_YEAR, (int)(Math.random()*(calendar.getActualMaximum(Calendar.DAY_OF_YEAR)))+1);
                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
                    date= new Date(calendar.get(Calendar.YEAR)-1900, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_YEAR));
                    person = new Person(""+id, Name.values()[nameIndex], color, Module.values()[moduleIndex], date, true, management);
                    management.addPerson(person);
                }
                Thread.sleep((int)(Math.random()*(6000+1)+2300));//2.3s a 6s
            } catch (Exception e) {}
        }
    }

    private boolean checkModule(Module module) {
        ArrayList<Person> personList = management.getPersonList();
        int countModule1= 0;
        int countModule2= 0;
        int countModule3 = 0;
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getModule()==Module.Prestamos) {
                countModule1++;
            }else if(personList.get(i).getModule()==Module.Asesorias) {
                countModule2++;
            }else {
                countModule3++;
            }
        }

        if(module==Module.Prestamos && countModule1<6) {
            return true;
        }else if(module==Module.Asesorias && countModule2<6) {
            return true;
        }else if(module==Module.Retiros && countModule3<6) {
            return true;
        }

        return false;
    }

}
