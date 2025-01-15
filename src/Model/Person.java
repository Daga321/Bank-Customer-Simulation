package Model;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private String personID;
    private Name personName;
    private Date date;
    private Module module;
    private Color color;
    private int x;
    private int y;
    private Thread tMovement;
    private boolean comingFromRightSide;
    private int turn;
    private int queuePosition;

    public Person(String personID, Name personName, Color color, Module module, Date date, boolean comingFromRightSide, Management management) {
        this.personID = personID;
        this.personName = personName;
        this.date = date;
        this.module = module;
        this.color = color;
        x=330;
        queuePosition = 12;
        this.comingFromRightSide = comingFromRightSide;
        tMovement = new Thread(new RunnablePersonMovement(this, management));
        tMovement.start();
    }

    public String getPersonID() {
        return personID;
    }

    public Name getPersonName() {
        return personName;
    }

    public Module getModule() {
        return module;
    }

    public boolean isComingFromRightSide() {
        return comingFromRightSide;
    }

    public String getDate() {
    	SimpleDateFormat sdfFormat = new SimpleDateFormat("dd/MM/yyyy");
        return sdfFormat.format(date);
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getChairPosition() {
        int chairPosition = 0;
        chairPosition = turn%6;
        if (chairPosition==0) {
            return 6;
        }
        return chairPosition;
    }


    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getQueuePosition() {
        return queuePosition;
    }

    public void setQueuePosition(int queuePosition) {
        this.queuePosition = queuePosition;
    }

}
