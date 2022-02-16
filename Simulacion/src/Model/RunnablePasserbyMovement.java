package Model;

public class RunnablePasserbyMovement implements Runnable{

    private Passerby passerby;
    private Management management;

    public RunnablePasserbyMovement(Passerby passerby, Management management) {
        this.passerby = passerby;
        this.management = management;
    }

    @Override
    public void run() {
        try {
            passerby.setY((int)(Math.random()*(570+1-470)+470));
            if (passerby.isComingFromRightSide()) {
                passerby.setX(780);
            }else {
                passerby.setX(-30);
            }
            if (passerby.isCrazy()) {
                crazyMode();
            }else {
                if (passerby.isComingFromRightSide()) {
                    left(-30);
                }else {
                    right(780);
                }
            }
            management.deletepasserby(passerby);
        } catch (Exception e) {}
    }

    public void crazyMode() {
        boolean down = true;
        while(passerby.getX()>=-30 && passerby.getX()<=730) {
            try {

                if (passerby.getY()<470) {
                    down = true;
                }else if (passerby.getY()>570) {
                    down = false;
                }

                if (!passerby.isComingFromRightSide()) {
                    passerby.setX(passerby.getX()+5);
                }else {
                    passerby.setX(passerby.getX()-5);
                }

                if (down) {
                    passerby.setY(passerby.getY()+5);
                }else {
                    passerby.setY(passerby.getY()-5);
                }
                Thread.sleep(75);
            } catch (Exception e) {}
        }
    }

    private void left(int limit) {
        while (passerby.getX()>limit) {
            try {
                passerby.setX(passerby.getX()-5);
                Thread.sleep(75);
            } catch (Exception e) {}
        }
    }

    private void right(int limit) {

        while (passerby.getX()<limit) {
            try {
                passerby.setX(passerby.getX()+5);
                Thread.sleep(75);
            } catch (Exception e) {}
        }
    }

}
