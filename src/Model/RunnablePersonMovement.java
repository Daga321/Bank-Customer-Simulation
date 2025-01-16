package Model;

public class RunnablePersonMovement implements Runnable{

    private Person person;
    private Management management;

    public RunnablePersonMovement(Person person, Management management) {
        this.person = person;
        this.management = management;
    }

    @Override
    public void run() {
        try {
            person.setY((int)(Math.random()*(575+1-475)+475));
            if (person.isComingFromRightSide()) {
                person.setX(730);
                left(330);
            }else {
                person.setX(-30);
                right(330);
            }
            up(395);
            management.setTurn(person);
            if (person.getModule()!=Module.Retiros) {
                Thread.sleep(2000);
                left(190);
                up(300);
                if (person.getModule()==Module.Prestamos) {
                    up(147);
                }
                if (management.waiting(person)) {
                    chairs();
                }
            }else {
                right(637);
                up(360);
                queue();
            }
            goToModule();
            management.goToModule(person);
            waitBeingServed();
            goodBay();
            management.deletePerson(person);
        } catch (Exception e) {
        	management.deleteFromQueue(person);
        	goodBay();
        	management.deletePerson(person);
        }
    }

    public void crazyMode() {
        boolean right = true;
        boolean down = true;
        person.setX((int)(Math.random()*(460+1)));
        person.setY((int)(Math.random()*(440+1)));

        while(true) {
            try {
                if (person.getX()==0) {
                    right = true;
                }else if (person.getX()==460) {
                    right = false;
                }
                if (person.getY()==0) {
                    down = true;
                }else if (person.getY()==440) {
                    down = false;
                }

                if (right) {
                    person.setX(person.getX()+1);
                }else {
                    person.setX(person.getX()-1);
                }

                if (down) {
                    person.setY(person.getY()+1);
                }else {
                    person.setY(person.getY()-1);
                }
                Thread.sleep(75);
            } catch (Exception e) {}
        }
    }

    private void left(int limit) {
        while (person.getX()>limit) {
            try {
                person.setX(person.getX()-5);
                Thread.sleep(75);
            } catch (Exception e) {}
        }
    }

    private void right(int limit) {
        while (person.getX()<limit) {
            try {
                person.setX(person.getX()+5);
                Thread.sleep(75);
            } catch (Exception e) {}
        }
    }

    private void up(int limit) {
        while (person.getY()>limit) {
            try {
                person.setY(person.getY()-5);
                Thread.sleep(75);
            } catch (Exception e) {}
        }
    }

    private void down(int limit) {
        while (person.getY()<limit) {
            try {
                person.setY(person.getY()+5);
                Thread.sleep(75);
            } catch (Exception e) {}
        }
    }

    private void waiting() {
        while(management.waiting(person)) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {}
        }
    }

    private void goToModule() {
        if (person.getModule()!=Module.Retiros) {
            left(47);
            if (person.getModule()==Module.Prestamos) {
                up(90);
            }
        }else {
            up(170);
            right(590);
        }
    }

    private void chairs() {
        if (person.getChairPosition()<4) {
            up(person.getY()+5-(person.getChairPosition()*40));
            right(237);
        }else{
            right(317);
            up(person.getY()+5-((person.getChairPosition()-3)*40));
            left(277);
        }
        waiting();
        if (person.getChairPosition()<4) {
            left(190);
            down(person.getY()-5+(person.getChairPosition()*40));
        }else{
            right(317);
            down(person.getY()-5+((person.getChairPosition()-3)*40));
        }
    }
    
    private void queue() {
        while (person.getQueuePosition()!=0) {//person.getY()!=200 && (person.getX()!=637 || person.getX()!=517)
            try {
            	if (management.getQueuePosition(person)<person.getQueuePosition() || person.getQueuePosition()==1) {
                    if (person.getQueuePosition()>=1 && person.getQueuePosition()<=4) {
                        up(person.getY()-35);
                    }else if (person.getQueuePosition()>=5 && person.getQueuePosition()<=8) {
                        down(person.getY()+28);
                    }else if (person.getQueuePosition()>=9 && person.getQueuePosition()<=12) {
                        up(person.getY()-35);
                    }
                    if (person.getQueuePosition()==5 || person.getQueuePosition()==9) {
                        left(person.getX()-40);
                    }
                    person.setQueuePosition(person.getQueuePosition()-1);
                }else {
                	Thread.sleep(100);
                }
            	
			} catch (Exception e) {}
        }
        waiting();
    }

    private void waitBeingServed() {
        if (person.getModule()!=Module.Retiros) {
            while(!management.isAModuleFree(person)) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {}
            }
        }else {
            while(!management.waitBeingServed(person)) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {}
            }
        }
    }
    
    private void goodBay() {
    	
    	if (person.getModule()!=Module.Retiros) {
            right(190);
            down(395);
            right(260);
        }else {
            left(480);
            down(395);
            left(260);
        }

        down((int)(Math.random()*(575+1-475)+475));
        if (person.isComingFromRightSide()) {
            left(-30);
        }else {
            right(780);
        }
    }
}
