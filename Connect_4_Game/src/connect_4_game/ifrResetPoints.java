/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_4_game;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author Maiguel
 */
public class ifrResetPoints extends JInternalFrame implements ActionListener,KeyListener{
 private JComboBox  cmbNamesPlayers;
    private JButton  btnReset;
    private JTextArea txtNames;
    private Files file;
    private JPanel pnlTest;
    private pnlConnect_4 pnl;
    public ifrResetPoints(){
        super("ResetPoints");
        initComponents();
        initAction();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setVisible(true);
        this.setBounds(400,100, 500, 500);
    }
    public void initComponents(){
      file=new Files("Players.txt");
      txtNames=new JTextArea("Nombres de los jugadores");
      txtNames.setBounds(65,15,149,15);
      txtNames.setEditable(false);
      this.setBackground(new Color(50,80,100));
      txtNames.setBackground(this.getBackground());
      txtNames.setForeground(Color.WHITE);
      cmbNamesPlayers=new JComboBox();
      btnReset=new JButton("Reiniciar");
      cmbNamesPlayers.setBounds(65,50,130,50);
      btnReset.setBounds(211,50,100,30);
      btnReset.setFocusable(false);
      cmbNamesPlayers.setFocusable(false);
      updatePointsPlayers();
      this.add(btnReset);
      this.add(cmbNamesPlayers);
      this.add(txtNames);
    }
    
    public void initAction(){
        btnReset.addActionListener(this);
        txtNames.addKeyListener(this);
    }
    public void setPanel(pnlConnect_4 pnl){
        this.pnl=pnl;
    }
    public void updatePointsPlayers(){
        cmbNamesPlayers.setModel(new DefaultComboBoxModel(file.showPlayersZeroPoints()));
    }
public void resetPoints(){
            if(!cmbNamesPlayers.getSelectedItem().toString().equals("")){
            if(!pnl.getPlayer1().getName().equals(cmbNamesPlayers.getSelectedItem().toString())&&!pnl.getPlayer2().getName().equals(cmbNamesPlayers.getSelectedItem().toString())){  
            file.addPoints(cmbNamesPlayers.getSelectedItem().toString(),"0");
            updatePointsPlayers();
            JOptionPane.showMessageDialog(null,"Reiniciado");
            }else JOptionPane.showMessageDialog(null,"No puede reiniciar los puntos de un jugador\nque sea parte de la partida actual");
            }else JOptionPane.showMessageDialog(null,"Todos los jugadores se encuentran con 0 puntos");
          
}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnReset){
         resetPoints();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
      if(ke.getKeyChar()=='\n'){
          resetPoints();
      }
    }

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {}

}