package Execution;

import Controller.Controller;
import View.FramePrincipal;

public class Runner {

    public static void main(String[] args) {
        FramePrincipal runner = new FramePrincipal();
        Controller controller = new Controller(runner);
        runner.start();
        runner.assignController(controller);
        runner.setVisible(true);
    }
}
