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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Maiguel
 */
public class ifrGame extends JInternalFrame implements ActionListener{
   
    private JMenuBar menu;
    private JButton btnExit,btnResetGame;
    private pnlConnect_4 pnl;
    private JTextArea text,txtChance;
    private JLabel chanceIcon;
    private boolean resetState;
    private JLabel[] lblMatrix;
    private JLabel lbl;
    private boolean animation=false;
    ImageIcon imageLbl=new ImageIcon(getClass().getResource("/imag/InstructionInsert1.png"));
    public ifrGame(pnlConnect_4 pnl){
        super("Game");
        this.pnl=pnl;
        initComponents();
        initAction();
       this.setLayout(null);
       this.setMaximizable(true);
       this.setBounds(0,0,1365, 673);
       this.setVisible(true);
    }
   public void initComponents(){
       resetState=false;
       chanceIcon=new JLabel();
       chanceIcon.setBounds(440, 38, 50, 50);
       
       text= new JTextArea();
       text.setBounds(345, 0, 800, 40);
       text.setFont(new Font("Tahoma",1,18));
       text.setBackground(this.getBackground());
       text.setEditable(false);
       txtChance=new JTextArea("TURNO :");
       txtChance.setEditable(false);
       txtChance.setBounds(345, 47, 75, 40);
       txtChance.setFont(new Font("Tahoma",1,18));
       txtChance.setBackground(this.getBackground());
       lblMatrix=new JLabel[7];
       menu= new JMenuBar();
       btnExit=new JButton("Volver al menú");
       //
       btnResetGame= new JButton("Siguiente Partida");
       btnResetGame.setBounds(617,45,140,30);
       //TEST
       menu.add(btnExit);
       //pnl= new TicTacToepnl();
       this.add(pnl);
       this.setJMenuBar(menu);
       this.add(text);
       this.add(txtChance);
       this.add(chanceIcon);
      
       //Label Matrix
       int X=501;
       for(int i=0;i<lblMatrix.length;i++){
           lbl=new JLabel();
           lbl.setBounds(X,120,90,100);
           lblMatrix[i]=lbl;
           this.add(lblMatrix[i]);
           X+=55;
       }
       //End Label Matrix
       pnl.setVisible(true);
   }
   
   public void setChanceIcon(ImageIcon icon){
       chanceIcon.setIcon(icon);
   }
   
   public void initAction(){
       btnExit.addActionListener(this);
       btnResetGame.addActionListener(this);
   }
   
    @Override
    public void actionPerformed(ActionEvent e) { 
        if (e.getSource()==btnExit){
        this.dispose();
        }
       if(e.getSource()==btnResetGame){
           pnl.resetGame(0);
           resetGame();
       }
    }
    public void addButton(){
        this.add(btnResetGame);
        this.repaint();
    }
    
    public void resetGame(){
           this.remove(btnResetGame);
           this.repaint();
    }
    
   public void setPlayers(Player player1,Player player2){
       text.setText(player1.getName().toUpperCase()+" (" +player1.getFigure().toUpperCase()+" ) "+player1.getPoints()+" VS "+player2.getName().toUpperCase()+" (" +player2.getFigure().toUpperCase()+" ) "+player2.getPoints());
   } 
 
   public void animatedArrow1(int matrix[][]){
       if(animation==false){
       addArrows(matrix);
       Thread thread =new Thread(()->{
              //up
              for(int k=0;k<2;k++){
               for(int up=120;up>90;up--){
                  int X=501;
                   try {
                       Thread.sleep(17);
                   }catch (InterruptedException ex) {}
                   
                  for(int i=0;i<lblMatrix.length;i++){
                    if(matrix[0][i]==0)
                    lblMatrix[i].setBounds(X,up,90,100);
                     X+=55;
                  }
               }
                //down
                for(int down=91;down<121;down++){
                   int X=501;
                   try {
                       Thread.sleep(17);
                   }catch (InterruptedException ex) {}
                   
                  for(int i=0;i<lblMatrix.length;i++){
                    if(matrix[0][i]==0)
                    lblMatrix[i].setBounds(X,down,90,100);
                     X+=55;
                  }
                }
              }
            removeArrows(); 
       });
       thread.start();
       }
   }
   
   public void animatedArrow2(){
       animation=true;
       removeArrows();
       Thread thread =new Thread(()->{
              //up
               int X=501;
              for(int i=0;i<lblMatrix.length;i++){
                  lblMatrix[i].setIcon(imageLbl);
                  
              for(int k=0;k<2;k++){
               for(int up=120;up>90;up--){
                   try {
                       Thread.sleep(17);
                   }catch (InterruptedException ex) {}
                
                    lblMatrix[i].setBounds(X,up,90,100);
               }
                //down
                for(int down=91;down<121;down++){
                   try {
                       Thread.sleep(17);
                   }catch (InterruptedException ex) {}
                   
                    lblMatrix[i].setBounds(X,down,90,100);
                     
                }
                
              }
              X+=55;
              lblMatrix[i].setIcon(null);
              }
              animation=false;
       });
       thread.start();
   }
   
   public void addArrows(int matrix[][]){
       for(int i=0;i<lblMatrix.length;i++){
           if (matrix[0][i]==0)
           lblMatrix[i].setIcon(imageLbl);
       }
   }
   
   public void removeArrows(){
        for(int i=0;i<lblMatrix.length;i++){
           lblMatrix[i].setIcon(null);
       }
   }
   
}
