package View;

import Controller.Controller;

public class RunnableUpdateTurnos implements Runnable{

    private Controller controller;
    private FramePrincipal framePrincipal;

    public RunnableUpdateTurnos(FramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
    }

    public void assignController(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void run() {
        while (true){
            try {
                controller.setPersistenceTurno();
                framePrincipal.setTurnWithPeople(controller.getPersistenceTurno());
                controller.repaint();
                if (framePrincipal.getTurnWithPeople().size() > 0){
                    framePrincipal.updateFrame();
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
