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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Maiguel
 */
public class ifrContinuePlayer extends JInternalFrame implements ActionListener,KeyListener{
    private JButton btnYes,btnNo;
    private JTextArea text;
    private Files file;
    private pnlConnect_4 pnl;
    private ifrInsertPlayer insertPlayer;
    private  Player player;
    private  int count;
   public ifrContinuePlayer(pnlConnect_4 pnl,ifrInsertPlayer insertPlayer){
       super("ContinuePlayer");
        this.pnl=pnl;
        this.insertPlayer=insertPlayer;
        initComponents();
        initAction();
        this.setLayout(null);
       this.setBounds(10,10,400,200);
       this.setVisible(true);
   }
   
   public void initComponents(){
       file=new Files("Players.txt");
       //Corregir
       text=new JTextArea("Se encontró un jugador con este nombre"+"\n"+"Con la cantidad de "+0+" puntos"+"\n"+"\n"+"Desea insertar este jugador?");
       text.setForeground(Color.black);
        text.setEditable(false);
        btnYes= new JButton("Sí");
        btnYes.setBounds(120,90, 60, 25);
        btnYes.setFocusable(false);
        btnNo= new JButton("No");
        btnNo.setBounds(180, 90, 60, 25);
        btnNo.setFocusable(false);
        text.setBounds(80, 20, 225,67);
        text.setBackground(this.getBackground());
        this.add(btnYes);
        this.add(btnNo);
        this.add(text);
   } 
   public void initAction(){
       btnYes.addActionListener(this);
       btnNo.addActionListener(this);
       text.addKeyListener(this);
   }
  
public void setText(){
    text.setText("Se encontró un jugador con este nombre"+"\n"+"Con la cantidad de "+player.getPoints()+" puntos"+"\n"+"\n"+"Desea insertar este jugador?");
}
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnYes){
           insertPlayer();
        }
        
       if(e.getSource()==btnNo){
                this.dispose();
               try { 
                insertPlayer.setSelected(true);
            } catch (PropertyVetoException ex) {
            }
           
       }
       
    }
    public void insertPlayer(){
         if(count==0){
              insertPlayer.increaseCount();
               pnl.setPlayer1(player);
               pnl.defaultChance();
            }else{ 
                pnl.setPlayer2(player);
                if(!pnl.getPlayer1().getName().equals(pnl.getPlayer2().getName())){
                     insertPlayer.increaseCount();
                }else JOptionPane.showMessageDialog(null,"Los jugadores no pueden ser el mismo");
            }
            
            this.dispose();
        }
    
   public void setPlayer(Player player){
       this.player=player;
       this.player.setPoints(file.getPoints(player.getName()));
   }
   public void setCount(int count){
       this.count=count;
   }

    @Override
    public void keyTyped(KeyEvent ke) {
    if (ke.getKeyChar()=='\n')
        insertPlayer();
    }

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {}
 
   
}
