package View;

import java.awt.Color;


public class PersonView{

    private Color color;
    private int x;
    private int y;
    private String name;
    private String id;
    private String date;
    private String turn;

    public PersonView(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public PersonView(String name, String id, String turn) {
        this.name = name;
        this.id = id;
        this.turn = turn;
    }
    
    public PersonView(String name, String id, String date, String turn) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.turn = turn;
    }

    public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getTurn() {
		return turn;
	}

	public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



}
