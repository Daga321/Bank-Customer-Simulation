package Model;

import java.awt.Color;

public class RunnableGeneratePasserby implements Runnable{

    private Management management;

    public RunnableGeneratePasserby(Management management) {
        this.management = management;
    }

    @Override
    public void run() {
        Passerby passerby;
        Color color;
        boolean comingFromRightSide;
        boolean crazy;
        int aux;
        while (true) {
            try {
                if (management.getPasserbyList().size()<8) {
                    color = new Color((int)(Math.random()*(256)),(int)(Math.random()*(256)),(int)(Math.random()*(256)));
                    aux = (int)(Math.random()*(2));// 50%
                    if (aux==1) {
                        comingFromRightSide=true;
                    }else {
                        comingFromRightSide=false;
                    }
                    aux = (int)(Math.random()*(15));// 0.06%
                    if (aux==0) {
                        crazy=true;
                    }else {
                        crazy=false;
                    }
                    passerby = new Passerby(color, comingFromRightSide, crazy, management);
                    management.addpasserby(passerby);
                }
                Thread.sleep((int)(Math.random()*(4000+1)+800));//0.8s a 4s
            } catch (Exception e) {}
        }
    }
}
