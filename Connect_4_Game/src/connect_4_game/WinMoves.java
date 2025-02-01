/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_4_game;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Maiguel
 */
public class WinMoves {
    GameState state= new GameState();
    
    /**
     * This method read all the matrix and make the diagonal right,diagonal lefth, horizontal and vertical moves
     * @param matrix to read all the cahcnges make for the player
     * @param row the row that make the change
     * @param column the column that make the change
     * @param player the player that make the move
     * @return true if the player win in a direction of the matrix
     */
    public boolean moves(int matrix[][],int row, int column,boolean player,JLabel visualMatrix[][]){
       
        int icon=0;
        //setIcon in base of player
        if(player==true)
          icon=1;
        else icon=2;
        //Final process setIcon
      
        
         //Update matrixGa,e
        matrix[row][column]=icon;
        //Final updaae matrixGame
        
        
        //Diagonal moves
          if(state.diagonalLeft(matrix,row,column, icon,visualMatrix)==true)
               return true;
        //Final diagonal moves
        if(state.diagonalRight(matrix,row,column, icon, visualMatrix)==true)
        return true;
        
        //Horizontal moves
        if(state.horizontal(matrix,row,icon,visualMatrix)==true)
            return true;
        //Final horizontal moves
        
        //Vertical moves
        if(state.vertical(matrix, column, icon,visualMatrix)==true)
            return true;
        //Final vertical moves
        
        return false;
    }
    /**
     * This method change the player to make the option to other player to move 
     * @param player The player that make the move to pass to other 
     * @return The player that make the next move
     */
    public boolean changePlayer(boolean player){
        if(player==true)
            return false;
        else return true;
    }
    /**
     * This method add the specific icon to the interface
     * @param buttons The visual matrix to make the changes
     * @param row The specific row to make the change in the matrix
     * @param column The specific column the specific row to make the change in the matri
     * @param player The player that make the move
     * @param circle The icon to change the visual matrix
     * @param ex The icon to change the visual matrix
     */
    public ImageIcon setIcon(boolean player,ImageIcon redIcon,ImageIcon yellowIcon){
        if(player==true)  
            return redIcon;
        else return yellowIcon;
    }
    
}
