package Model;

import java.awt.Color;

public class Passerby {
    private Color color;
    private int x;
    private int y;
    private Thread threadMovement;
    private boolean comingFromRightSide;
    private boolean crazy;


    public Passerby(Color color, boolean comingFromRightSide, boolean crazy, Management management) {
        this.color = color;
        this.comingFromRightSide = comingFromRightSide;
        this.crazy = crazy;
        threadMovement = new Thread(new RunnablePasserbyMovement(this, management));
        threadMovement.start();
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


    public boolean isComingFromRightSide() {
        return comingFromRightSide;
    }


    public boolean isCrazy() {
        return crazy;
    }


}
