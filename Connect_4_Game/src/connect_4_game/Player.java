/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_4_game;

/**
 *
 * @author Maiguel
 */
public class Player {
    private String points;
    private String name;
    private String figure;
    private int icon;
    
    public Player(String name,String figure, String points){
        this.name=name;
        this.figure=figure;
        this.points=points;
        if(figure.equals("Círculo"))
            this.icon=1;
        else this.icon=2;
    }
    
    @Override
    public String toString(){
       return this.name+","+figure;
    }
    
    //Access methods
    public String getFigure() {
        return figure;
    }
    public String getName(){
        return this.name;
    }
    public String getPoints(){
        return this.points;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(String points) {
        this.points = points;
    }
    
    public int getIcon(){
        return this.icon;
    }
    //End access methods
}
