/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_4_game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Maiguel
 */
public class ifrDeletePlayer extends JInternalFrame implements ActionListener,KeyListener{
    private JComboBox  cmbNamesPlayers;
    private JButton  btnDelete;
    private JTextArea txtNames;
    private Files file=new Files("Players.txt");
    private pnlConnect_4 pnl;
    public ifrDeletePlayer(pnlConnect_4 pnl){
        super("DeletePlayer");
        this.pnl=pnl;
        initComponents();
        initAction();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setVisible(true);
        this.setBounds(500,100, 500, 500);
    }
    public void initComponents(){
      txtNames=new JTextArea("Nombres de los jugadores");
      txtNames.setBounds(65,15,149,15);
      txtNames.setEditable(false);
      this.setBackground(new Color(0,130,0));
      txtNames.setBackground(this.getBackground());
      txtNames.setForeground(Color.WHITE);
      cmbNamesPlayers=new JComboBox();
      btnDelete=new JButton("Eliminar");
      cmbNamesPlayers.setBounds(65,50,130,50);
      btnDelete.setBounds(211,50,80,30);
      btnDelete.setFocusable(false);
      cmbNamesPlayers.setFocusable(false);
      updateNamesPlayers();
      this.add(btnDelete);
      this.add(cmbNamesPlayers);
      this.add(txtNames);
    }
    
    public void initAction(){
        btnDelete.addActionListener(this);
        txtNames.addKeyListener(this);
    }
    
    public void updateNamesPlayers(){
        cmbNamesPlayers.setModel(new DefaultComboBoxModel(file.showPlayers()));
    }
    
    public void deletePlayer(){
            if(!cmbNamesPlayers.getSelectedItem().toString().equals("")){
            if(!pnl.getPlayer1().getName().equals(cmbNamesPlayers.getSelectedItem().toString())&&!pnl.getPlayer2().getName().equals(cmbNamesPlayers.getSelectedItem().toString())){  
            file.deletePlayer(cmbNamesPlayers.getSelectedItem().toString());
            updateNamesPlayers();
            JOptionPane.showMessageDialog(null,"Eliminado");
            }else JOptionPane.showMessageDialog(null,"No puede eliminar un jugador que\nsea parte de la partida actual");
            }else JOptionPane.showMessageDialog(null,"No se encuentra ningún jugador disponible");
           
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnDelete){
            deletePlayer();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getKeyChar()=='\n'){
         deletePlayer();
        }
    }
    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {}
    
}
