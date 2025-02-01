/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_4_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author Maiguel
 */
public class ifrInsertPlayer extends JInternalFrame implements ActionListener,KeyListener,MouseListener{
   private JTextField txtName;
   private JTextField txtInsert;
   private JButton btnInsert;
   private JComboBox icons;
   private Files file;
   private int count=0;
   private Player player1;
   private Player player2;
   private JLabel numberPlayer;
   private pnlConnect_4 pnl;
   private JDesktopPane desktop;
   private ifrContinuePlayer samePlayer;
   private ifrDeletePlayer delete;
    public ifrInsertPlayer(pnlConnect_4 pnl,JDesktopPane desktop,ifrDeletePlayer delete){
        super("InsertPlayer");
        this.pnl=pnl;
        this.desktop=desktop;
        this.delete=delete;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        initAction();
        this.setLayout(null);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setClosable(true);
        this.setBounds(450,100, 500, 500);
        this.setVisible(true);
        this.setFocusable(false);
        addMouseListener(this);
    }
public void setIfrPlayer(ifrContinuePlayer player){
    samePlayer=player;
}
    
    
    public void initComponents(){
        txtName= new JTextField(10);
        txtInsert=new JTextField(10);
        txtName.addMouseListener(this);
        txtName.addKeyListener(this);
        btnInsert= new JButton("Insert");
        icons=new JComboBox();
        player2= new Player("","","0");
        numberPlayer= new JLabel("Inserte el jugador "+(count+1));
        numberPlayer.setBounds(3, 0, 120, 30);
        this.add(numberPlayer);
        //Add color at screen
      
      resetScreen();
       //Final add Color
       
        file= new Files("Players.txt");
        txtName.setBounds(50,50,200,30);
        btnInsert.setBounds(260, 50, 80, 30);
        icons.setBounds(50,80,110,50);
        icons.setFocusable(false);
        btnInsert.setFocusable(false);
        this.add(icons);
        this.add(txtName);
        this.add(btnInsert);
    }
    
    public void initAction(){
        btnInsert.addActionListener(this);
        txtName.addKeyListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==btnInsert){
       insertPlayer();
    }
    }
    public void insertPlayer(){
       if(!txtName.getText().trim().equalsIgnoreCase("Inserte el nombre"))
         if (txtName.getText().trim().length()<=16){
           if (!txtName.getText().trim().equals("")){ 
            if(file.insertPlayer(txtName.getText().trim().toUpperCase())==true){
              if(file.getLimitNumberPlayers()==false){
                delete.updateNamesPlayers();//Update players
                
            count++;
             if (count==1){
          player1=new Player(txtName.getText().toString().toUpperCase().trim(),icons.getSelectedItem().toString(),"0");
          pnl.setPlayer1(player1);
          pnl.defaultChance();
             }
             
             if (count==2){
             player2=new Player(txtName.getText().toString().toUpperCase().trim(),icons.getSelectedItem().toString(),"0");
             pnl.setPlayer2(player2);
             }
            secondScreenPlayer();
             }else JOptionPane.showMessageDialog(null,"Se alcanzó la máxima cantidad de jugadores\nDebe eliminar alguno");
    
            }else{
             insertSamePlayer();
            }
              
           }else JOptionPane.showMessageDialog(null,"Debe ingresar al menos una letra");
               
       }else JOptionPane.showMessageDialog(null,"El tamaño del nombre no debe exceder los 16 caracteres");
        
     
}

    
    
    public void resetScreen(){
       this.setBackground(new Color(150,0,0));
       numberPlayer.setForeground(Color.white);
       txtName.setBackground(Color.black);
       txtName.setForeground(Color.white);
       icons.setBackground(Color.black);
       icons.setForeground(Color.white);
       btnInsert.setBackground(Color.black);
       btnInsert.setForeground(Color.white); 
       numberPlayer.setText("Inserte el jugador 1");
       txtName.setText("");
       txtName.setText("Inserte el nombre");
       icons.setModel(new DefaultComboBoxModel(new String[]{"Ficha roja","Ficha amarilla"}));
    }
    
   public String restrictEntry(){
       return player2.getName();
   }
   public void increaseCount(){
       this.count++;
       secondScreenPlayer();
   }
   public void insertSamePlayer(){
       if(!desktop.isAncestorOf(samePlayer))
         desktop.add(samePlayer);
       else desktop.setSelectedFrame(samePlayer);
               
         try {
           samePlayer.setSelected(true);
       } catch (PropertyVetoException ex) {}
      
             if (count==0){
                player1=new Player(txtName.getText().toString().toUpperCase().trim(),icons.getSelectedItem().toString(),"0");
                samePlayer.setPlayer(player1);
                samePlayer.setText();
             }else{
                player2=new Player(txtName.getText().toString().toUpperCase().trim(),icons.getSelectedItem().toString(),"0");
                samePlayer.setPlayer(player2);  
                samePlayer.setText();
             }
             samePlayer.setCount(count);
             samePlayer.setVisible(true);
   }
   
   public void secondScreenPlayer(){
       if(count==1){
           numberPlayer.setText("Inserte el jugador 2");
          //Add color player 2
           this.setBackground(new Color(0,0,120));
           numberPlayer.setForeground(Color.white);
           txtName.setBackground(Color.white);
           txtName.setForeground(Color.black);
           icons.setBackground(Color.white);
           icons.setForeground(Color.black);
           btnInsert.setBackground(Color.white);
           btnInsert.setForeground(Color.black);
          //Final add Color
            txtName.setText("");
            txtName.setText("Inserte el nombre");
            
         if (icons.getSelectedItem().toString().equalsIgnoreCase("Ficha amarilla")==true){
             icons.removeItem("Ficha amarilla");
             icons.addItem("Ficha roja");
         }
         if (icons.getSelectedItem().toString().equalsIgnoreCase("Ficha roja")==true){
             icons.removeItem("Ficha roja");
         }
         
       }
         if(count==2){
             count=0;
             resetScreen();
          this.dispose();
       }
         
   }

    @Override
    public void keyTyped(KeyEvent ke) {
        
      
    }

    @Override
    public void keyPressed(KeyEvent ke) {
       if(ke.getKeyChar()=='\n'&&!this.txtName.equals("")&&!this.txtName.equals("Inserte el nombre"))
           insertPlayer();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
      
    }

    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mousePressed(MouseEvent me) {
        
    if(txtName.getText().equals("Inserte el nombre")&&me.getComponent()==txtName)
        txtName.setText("");
    if (me.getComponent()==this&&txtName.getText().trim().equals(""))
        txtName.setText("Inserte el nombre");
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) { 
    
    if(txtName.getText().equals(""))
        txtName.setText("Inserte el nombre");
    
    }
}
