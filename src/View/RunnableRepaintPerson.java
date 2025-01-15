package View;

import Controller.Controller;

public class RunnableRepaintPerson implements Runnable{

    private Controller controller;
    private PanelPerson panelPerson;

    public RunnableRepaintPerson(PanelPerson panelPerson) {
        this.panelPerson = panelPerson;
    }

    @Override
    public void run() {
        while (true) {
            try {
                controller.setPersistencePerson();
                panelPerson.setPersonList(controller.getPersistenceView());
                panelPerson.repaint();
                Thread.sleep(15);
            } catch (Exception e) {}
        }

    }

    public void assignController(Controller controller) {
        this.controller = controller;
    }

}

