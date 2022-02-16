package Model;

import java.util.Random;

public class RunnableModule implements Runnable{

    private int timeExpected;
    private int timeWorked;
    private int breakTime;
    private Proceeding proceeding;
    private Management management;

    public RunnableModule(Proceeding proceeding, Management management) {
        timeExpected = new Random().nextInt(15000+1-9000) + 9000;
        timeWorked = 0;
        breakTime = 0;
        this.proceeding = proceeding;
        this.management = management;
    }

    @Override
    public void run() {
        while (true) {
            try {
            	while(timeWorked<=timeExpected && proceeding.getPerson()!=null) {
                    try {
                        timeWorked += 100;
                        Thread.sleep(100);
                    } catch (Exception e) {}
                }
                management.nextPerson(proceeding);
                timeWorked = 0;
                while(breakTime<=2000 && proceeding.getPerson()==null) {
                    try {
                        breakTime  += 100;
                        Thread.sleep(100);
                    } catch (Exception e) {}
                }
                breakTime = 0;
			} catch (Exception e) {}
        }
    }

}
