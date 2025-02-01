/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_4_game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.border.*;
/**
 *
 * @author Maiguel
 */
public class pnlConnect_4 extends JPanel implements MouseListener{
    ImageIcon Nullimage=new ImageIcon(getClass().getResource("/imag/Null.png"));
    ImageIcon redIcon= new ImageIcon(getClass().getResource("/imag/RedIcon.png"));
    ImageIcon imageInsertIcon= new ImageIcon(getClass().getResource("/imag/InsertIcon.png"));
    ImageIcon yellowIcon= new ImageIcon(getClass().getResource("/imag/YellowIcon.png"));
    JLabel icon,insertIcon;
    JLabel[][] visualMatrix=new JLabel[6][7];
    JLabel[] vectorInsert=new JLabel[7];
    int[][] gameMatrix=new int[6][7];
    GameState game= new GameState();
    boolean open,player,resetState,chance;
    WinMoves winner= new WinMoves();
    Files file;
    Clip clip;
    private ifrGame viewGame;
    private ifrResetPoints reset;
    private Player  player1; //private
    private Player  player2; //private
    Border border = BorderFactory.createLineBorder(Color.black,2);
    
    public pnlConnect_4(ifrResetPoints reset){
      this.setLayout(null);
      this.reset=reset;
      initComponents();
      player1= new Player("","","0");
      player2=new Player("","","0");
      this.setBackground(Color.BLUE);
      this.setBounds(500,220,386,350);//331 ancho
      setEffect();
      this.setVisible(true);
     
    }
    
        
    public void initComponents(){
        player=true;
        resetState=false;
        chance=true;
        open=true;
        file= new Files("Players.txt");
        
        int X=3,Y=20,XInsert=7;
        
       for(int i=0;i<6;i++){
        for(int j=0;j<7;j++){
            if(i==5){
          insertIcon=new JLabel();
          insertIcon.setIcon(imageInsertIcon);
          insertIcon.setBounds(XInsert,2,40,13);
          insertIcon.addMouseListener(this);
          XInsert+=55;
          vectorInsert[j]=insertIcon;
          this.add(vectorInsert[j]);
            }
            
          icon=new JLabel();
          insertIcon=new JLabel();
          icon.setIcon(Nullimage);
          icon.setBounds(X,Y,50,50);
          visualMatrix[i][j]=icon;
          this.add(visualMatrix[i][j]);
         X+=55;
        }
        X=3;
        Y+=55;
       }
    }
    
   public void addIcon(int row,int column,ImageIcon icon){
   Thread thread=new Thread(()->{
        gameMatrix[row][column]=1;
        open=false;
         for(int i=0;i<=row;i++){
             if(i==row)
             playEffect();
             
              visualMatrix[i][column].setIcon(icon);
        try{
            Thread.sleep(150);
        }catch(InterruptedException error){}
        if(i!=row)
       visualMatrix[i][column].setIcon(Nullimage);
         }
         //Process
     open=true; 
     
            if(getLastIcon()==1){   
                 viewGame.addButton();
                 JOptionPane.showMessageDialog(null,"Empate");
               }
     
            if(winner.moves(gameMatrix, row, column, player,visualMatrix)==true){
                      JOptionPane.showMessageDialog(null,"El ganador es "+getPlayerWinner().getName());
                      file.addPoints(getPlayerWinner().getName(),getIntPoints());
                      String nameWinner=getPlayerWinner().getName();
                      getPlayerWinner().setPoints(file.getPoints(nameWinner));
                      viewGame.setPlayers(player1, player2);
                      viewGame.addButton();
                      resetState=true;
                      try{
                      reset.updatePointsPlayers();
                      }catch(NullPointerException error){}
                   }
            
             player=winner.changePlayer(player);
             viewGame.setChanceIcon(winner.setIcon(player, redIcon, yellowIcon));
             if (row==0)
             viewGame.animatedArrow1(gameMatrix);
   });
   thread.start();
   }
   
    @Override
    public void mouseClicked(MouseEvent me) {}
    
    public int obtainedColumn(){
        for(int i=0;i<vectorInsert.length;i++){
           if(vectorInsert[i].getBorder()!=null)
               return i;
        }
        return -1;
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        int X=me.getX(),Y=me.getY();
      if(obtainedColumn()!=-1&&open==true&&resetState==false){
          if(game.availableColumn(gameMatrix,obtainedColumn())!=-1){
              addIcon(game.availableColumn(gameMatrix,obtainedColumn()),obtainedColumn(),winner.setIcon(player, redIcon, yellowIcon));
          }
      }
    }

     public void setEffect(){
        try {
        URL url = this.getClass().getResource("/musicTest/DownIcon.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
       } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
         System.out.println("Error al reproducir el sonido.");
       }
     }
     
    public void playEffect(){
              setEffect();
              clip.start();
    }
    
     public void resetGame(int num){
        viewGame.resetGame();
        this.resetState=false;
        for(int i=0;i<visualMatrix.length;i++){
            for(int j=0;j<visualMatrix[i].length;j++){
               gameMatrix[i][j]=0; 
               visualMatrix[i][j].setIcon(Nullimage);
               visualMatrix[i][j].setBorder(null);
            }
        }
        if(num==0){
        if (chance==true)
            defaultChance();
        else randomChance();
        }else{}
    }
     
       public void setPlayer1(Player player1){
       this.player1=player1;
   }
   
   public void setPlayer2(Player player2){
       this.player2=player2;
   }
   
   public void resetNames(){
       player2.setName("");
       player1.setName("");
   }
   
   public Player getPlayer1(){
     return player1;  
   } 
   
   public Player getPlayer2(){
       return player2;
   }
  public boolean reviewInitGame(){
     for(int i=0;i<gameMatrix.length;i++){
         for(int j=0;j<gameMatrix[i].length;j++){
             if (gameMatrix[i][j]==1||gameMatrix[i][j]==2)
                 return true;
         }
     }
     return false;
  }
  
  public int[][] getMatrix(){
      return gameMatrix;
  } 
  
   public String getIntPoints(){
       int num=0;
       if(player==true){
         if(player1.getFigure().equals("Ficha roja")){
            num=Integer.parseInt(player1.getPoints());
            num++;
            return num+"";
         }
          if(player2.getFigure().equals("Ficha roja")){
            num=Integer.parseInt(file.getPoints(player2.getName()));
            num++;
            return num+"";
         } 
           
       }else{
           if(player1.getFigure().equals("Ficha amarilla")){
            num=Integer.parseInt(file.getPoints(player1.getName()));
            num++;
            return num+"";
         }
           if(player2.getFigure().equals("Ficha amarilla")){
            num=Integer.parseInt(file.getPoints(player2.getName()));
            num++;
            return num+"";
         }
       }
       return "";
   }//Final method
   public Player getPlayerWinner(){
      if(player==true){
         if(player1.getFigure().equals("Ficha roja")){
            return player1;
         }
          if(player2.getFigure().equals("Ficha roja")){
            return player2;
         } 
           
       }else{
           if(player1.getFigure().equals("Ficha amarilla")){
            return player1;
         }
           if(player2.getFigure().equals("Ficha amarilla")){
            return player2;
         }
       }
       return player1; 
   }
   
   public int getLastIcon(){
       int count=0;
       for(int i=0;i<gameMatrix.length;i++){
           for(int j=0;j<gameMatrix[i].length;j++){
               if (gameMatrix[i][j]==0)
                   count++;
           }
       }
       return count;
   }
   
  public void defaultChance(){
      chance=true;
     if(player1.getFigure().equals("Ficha roja")){
      player=true;
  }else player=false;
     
      viewGame.setChanceIcon(winner.setIcon(player, redIcon, yellowIcon));
  }
  
  public void randomChance(){
      chance=false;
      int num= (int) (Math.random()*2);
      switch(num){
          case 0:
              JOptionPane.showMessageDialog(null,"Empieza el jugador 1");
              
              if(player1.getFigure().equals("Ficha roja"))
              player=true;
              else player=false;
              break;
          case 1:
              JOptionPane.showMessageDialog(null,"Empieza el jugador 2");
              if(player2.getFigure().equals("Ficha roja"))
                  player=true;
              else player=false;
              break;
      }
    viewGame.setChanceIcon(winner.setIcon(player, redIcon, yellowIcon));
  }
  
  public void setIfrGame(ifrGame game){
      this.viewGame=game;
  }
     
    @Override
    public void mouseReleased(MouseEvent me) {
    }
    @Override
    public void mouseEntered(MouseEvent me) {  
      for(int i=0;i<vectorInsert.length;i++){
         if(me.getComponent()==vectorInsert[i])
             vectorInsert[i].setBorder(border);
      }
      
    }
    @Override
    public void mouseExited(MouseEvent me) {
         for(int i=0;i<vectorInsert.length;i++){
          if (vectorInsert[i].getBorder()!=null)
            vectorInsert[i].setBorder(null); 
      }
    }
 
}
