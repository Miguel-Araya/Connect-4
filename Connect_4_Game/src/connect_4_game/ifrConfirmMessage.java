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
/**
 *
 * @author Maiguel
 */
public class ifrConfirmMessage extends JInternalFrame implements ActionListener,KeyListener{
    JButton btnYes,btnNo;
    JTextArea text;
    JDesktopPane desktopPrincipal;
    ifrInsertPlayer insert;
    pnlConnect_4 pnl;
    ifrGame viewGame;
   public ifrConfirmMessage(JDesktopPane desktop,ifrInsertPlayer player,pnlConnect_4 pnl,ifrGame viewGame){
       super("ConfirmMessage");
       this.viewGame=viewGame;
       this.desktopPrincipal=desktop;
       this.insert=player;
       this.pnl=pnl;
       initComponents();
       initAction();
       this.setLayout(null);
       this.setBounds(10,10,400,150);
      
       this.setVisible(true);
    }
   public void initComponents(){
       text=new JTextArea("Desea continuar?"+"\n"+"Esto reemplazará los jugadores actuales");
       text.setEditable(false);
       text.setBackground(this.getBackground());
       text.setForeground(Color.black);
        btnYes= new JButton("Sí");
        btnYes.setBounds(120,70, 60, 25);
        btnYes.setFocusable(false);
        btnNo= new JButton("No");
         btnNo.setBounds(180, 70, 60, 25);
         btnNo.setFocusable(false);
        text.setBounds(80, 20, 225,45);
        this.add(btnYes);
        this.add(btnNo);
        this.add(text);
   }
   
   public void initAction(){
       btnYes.addActionListener(this);
       btnNo.addActionListener(this);
       text.addKeyListener(this);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==btnYes){
      
               showInsertPlayer();
               viewGame.dispose();
       }else this.dispose();
    }

    public void showInsertPlayer(){
        if(!desktopPrincipal.isAncestorOf(insert))
         desktopPrincipal.add(insert);
        else desktopPrincipal.setSelectedFrame(insert);
        
           insert.setVisible(true);
           pnl.resetNames();
           pnl.resetGame(1);
           this.dispose();
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getKeyChar()=='\n'){
           showInsertPlayer();
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {}

}
