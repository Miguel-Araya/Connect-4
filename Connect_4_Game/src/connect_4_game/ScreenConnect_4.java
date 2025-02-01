/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_4_game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Maiguel
 */
public class ScreenConnect_4 extends JFrame implements ActionListener{
   private JDesktopPane desktop;
   private ifrInsertPlayer insertPlayer;
   private ifrDeletePlayer delete;
   private ifrResetPoints reset;
   private  ifrGame viewGame;
   private JButton btnDefault,btnRandom;
   private ifrConfirmMessage confirm;
   private ifrContinuePlayer samePlayer;
   private JMenuBar menuBar;
   private JMenu player,game,chance;
   private JMenuItem insert,initGame,deletePlayer,resetPoints;
   private pnlConnect_4 pnl;
   private Files file;
   //private Player player1;
   ///private Player player2;
    public ScreenConnect_4(){
        super("Connect_4");
        initComponents();
        initAction();
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       this.setExtendedState(MAXIMIZED_BOTH);
       this.setLayout(new GridLayout());
       this.add(desktop);
       this.setJMenuBar(menuBar);
       this.setVisible(true);
    }
    
  public void initComponents(){
       file = new Files("Players.txt");
       reset=new ifrResetPoints();
       pnl=new pnlConnect_4(reset);
       reset.setPanel(pnl);
       viewGame= new ifrGame(pnl);
       delete=new ifrDeletePlayer(pnl);
       pnl.setIfrGame(viewGame);
       desktop= new JDesktopPane();
       insertPlayer= new ifrInsertPlayer(pnl,desktop,delete);
       samePlayer= new ifrContinuePlayer(pnl,insertPlayer);
       insertPlayer.setIfrPlayer(samePlayer);
       confirm= new ifrConfirmMessage(desktop,insertPlayer,pnl,viewGame);
       menuBar= new JMenuBar();
       btnDefault= new JButton("Default   ");
       btnRandom= new JButton("Aleatorio");
       player=new JMenu(" "+" " +" "+"Jugador");
       game= new JMenu(" "+" " +" "+"Juego");
       chance= new JMenu(" "+" "+" "+"Turno");
       chance.add(btnDefault);
       chance.add(btnRandom);
       player.setPreferredSize(new Dimension(114,30));
       game.setPreferredSize(new Dimension(93,25));
       menuBar.add(player);
       menuBar.add(game);
       insert= new JMenuItem("Insertar jugador");
       initGame= new JMenuItem("Iniciar juego");
       deletePlayer= new JMenuItem("Eliminar jugador");
       resetPoints=new JMenuItem("Reiniciar puntos");
       player.add(insert);
       player.add(resetPoints);
       player.add(deletePlayer);
       player.add(chance);
       game.add(initGame);
   }

  public void initAction(){
      insert.addActionListener(this);
      initGame.addActionListener(this);
      deletePlayer.addActionListener(this);
      resetPoints.addActionListener(this);
      btnDefault.addActionListener(this);
      btnRandom.addActionListener(this);
  }
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==insert){
          viewGame.dispose();
          if(insertPlayer.restrictEntry().equals("")){
          insertPlayer.setVisible(true);    
          if(!desktop.isAncestorOf(insertPlayer)){
          desktop.add(insertPlayer);
         
          }else{
              desktop.setSelectedFrame(insertPlayer);
          }
          
          }else{
              
                confirm.setVisible(true);
                if(!desktop.isAncestorOf(confirm)){
                  desktop.add(confirm);
                } else{
                  desktop.setSelectedFrame(confirm);
                    
                }
                   try {
                        confirm.setSelected(true);
                    } catch (PropertyVetoException ex) {} 
           }
      }
      
      if(e.getSource()==initGame){
          if(!pnl.getPlayer2().getName().equals("")){
              viewGame.setVisible(true);
               insertPlayer.dispose();
               viewGame.setPlayers(pnl.getPlayer1(), pnl.getPlayer2());
          if(!desktop.isAncestorOf(viewGame)){
              desktop.add(viewGame);
              viewGame.animatedArrow2();
          }else{
           desktop.setSelectedFrame(viewGame);
          }
         
      }else JOptionPane.showMessageDialog(null,"Debe ingresar 2 usuarios para jugar");
      }
      
     if(e.getSource()==btnDefault){
         if(pnl.reviewInitGame()==false){
         if(!pnl.getPlayer1().getName().equals(""))
         pnl.defaultChance();
         else JOptionPane.showMessageDialog(null,"Debe haber ingresado al menos el jugador 1");
         }else JOptionPane.showMessageDialog(null,"Solo puede cambiar de turno\nSi no ha empezado una partida");
     }
     
       if(e.getSource()==btnRandom){
           if(pnl.reviewInitGame()==false){
          if(!pnl.getPlayer2().getName().equals(""))
          pnl.randomChance();
          else  JOptionPane.showMessageDialog(null,"Debe haber ingresado los 2 jugadores");
          }else JOptionPane.showMessageDialog(null,"Solo puede cambiar de turno\nSi no ha empezado una partida");
       }  
       
       if(e.getSource()==deletePlayer){
            delete.setVisible(true);
           try{
           desktop.add(delete);
           }catch(IllegalArgumentException error){
               
           desktop.setSelectedFrame(delete);
           }
           try {
                 delete.setSelected(true);
               } catch (PropertyVetoException ex) {}
       }
       
       if(e.getSource()==resetPoints){
            reset.setVisible(true);
           
           if(!desktop.isAncestorOf(reset)){
           desktop.add(reset);
           
           }else{
           desktop.setSelectedFrame(reset);
           }
           try {
                   reset.setSelected(true);
               } catch (PropertyVetoException ex) {}
       }
       
    }
    
}
  
