/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_4_game;
import java.awt.Color;
import javax.swing.*;
import javax.swing.border.*;
/**
 *
 * @author Maiguel
 */
public class GameState {
   Border borde = BorderFactory.createLineBorder(Color.white);
    public int availableColumn(int[][] matrix,int column){
        boolean value=false;
        for(int i=matrix.length-1;i>-1;i--){
          if(matrix[i][column]==0){
              return (i);
          }
        }
       return -1;
    }
    
    public boolean diagonalRight(int[][] matrix,int row,int column,int icon,JLabel visualMatrix[][]){
     
      while(column>0&&row<5){
          row++;
          column--;
      }
     int count=0;
     int[] rowPositions=new int[4];
     int[] columnPositions=new int[4];
     
     while(column<7&&row>-1){
         if(matrix[row][column]==icon){
          rowPositions[count]=row;
          columnPositions[count]=column;
          count++;
         }
         else count=0;
       
         if(count==4){
             for(int k=0;k<4;k++)
             visualMatrix[rowPositions[k]][columnPositions[k]].setBorder(borde);
             
             return true;
         }  
         column++;
         row--;
     }
     return false;
}
    public boolean diagonalLeft(int[][] matrix,int row,int column,int icon,JLabel visualMatrix[][]){
        while(row<5&&column<6){
            row++;
            column++;
        }
        int count=0;
        int[] rowPositions=new int[4];
        int[] columnPositions=new int[4];
        
        while(row>-1&&column>-1){
            if(matrix[row][column]==icon){
                rowPositions[count]=row;
                columnPositions[count]=column;
                count++;
            }
            else count=0;
        
            if(count==4){
                for(int k=0;k<4;k++)
                 visualMatrix[rowPositions[k]][columnPositions[k]].setBorder(borde);
                
                return true;
            }
            row--;
            column--;
        }
        return false;
    }
    
    public boolean horizontal(int[][] matrix,int row,int icon,JLabel visualMatrix[][]){
        int count=0;
        int positions[] = new int[4];
      for(int i=0;i<matrix[0].length;i++){
          if (matrix[row][i]==icon){
              positions[count]=i;
              count++;
          }
          else count=0;
          
          if (count==4){
               for(int k=0;k<4;k++)
               visualMatrix[row][positions[k]].setBorder(borde);
               
             return true;
          }
      }  
      return false;
    }
    
    public boolean vertical(int[][] matrix,int column,int icon,JLabel visualMatrix[][]){
        int count=0;
        int[] positions=new int[4];
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][column]==icon){
                positions[count]=i;
                count++;
            }
            else count=0;
            
            if(count==4){
                for(int k=0;k<4;k++){
                  visualMatrix[positions[k]][column].setBorder(borde);
                }
                return true;
            }
        }
        return false;
    }
    
}
