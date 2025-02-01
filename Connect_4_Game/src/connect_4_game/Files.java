/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_4_game;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Maiguel
 */
public class Files {
    File f;
    BufferedWriter bw;
    BufferedReader br;
    
    public Files(String nameFile){
        
        f=new File(nameFile);
        try{
            f.createNewFile();
        }catch(IOException error){}
    }
    
    /**
     * Este método devuelve true o false
     * @param namePlayer require to write in the file
     * @return return true if the name was write or false if the name is in the file
     **/
    public boolean insertPlayer(String namePlayer){
        String stringFile="";
        String split[];
        Boolean value=true;
        try {
            br=new BufferedReader(new FileReader(f));
            bw=new BufferedWriter(new FileWriter(f,true));
            
            while((stringFile=br.readLine())!=null){
                split=stringFile.split("\\,");
                if(namePlayer.equalsIgnoreCase(split[0]))
                    value=false;
            }
            
            if(value==true){
                bw.write(namePlayer+","+0+"\n");
            }
           
            bw.close();
            br.close();
        } catch (FileNotFoundException ex) {}
          catch(IOException error){}
        
        return value;
    }
    /**
     * This method add a points to a name in the file
     * @param namePlayer Need to know the name to add points
     * @param points The quantity  of points to add for a player
     */
    public void addPoints(String namePlayer,String points){
        insertPlayer(namePlayer);
        String stringFile="";
        String allFile="";
        String split[];
        try {
            br=new BufferedReader(new FileReader(f));
           
            
            while((stringFile=br.readLine())!=null){
                split=stringFile.split("\\,");
                if(!namePlayer.equalsIgnoreCase(split[0]))
                    allFile+=stringFile+"\n";
                else{
                    allFile+=namePlayer+","+points+"\n";
                }
            }
            bw=new BufferedWriter(new FileWriter(f));
            bw.write(allFile);
            
            bw.close();
            br.close();
        } catch (FileNotFoundException ex) {}
          catch(IOException error){}
    }
    /**
     * This method obtain the points to a specific player
     * @param namePlayer To obtain the points for the player
     * @return Points that the player has in the file
     */
    public String getPoints(String namePlayer){
        String stringFile="";
        String split[];
        try {
            br=new BufferedReader(new FileReader(f));
            
            while((stringFile=br.readLine())!=null){
                split=stringFile.split("\\,");
                if(namePlayer.equalsIgnoreCase(split[0])){
        
                    return split[1];
                }
                 
            }
           
            br.close();
        } catch (FileNotFoundException ex) {}
          catch(IOException error){}
        return stringFile;
    }
    /**
     * This method read all the names in the file
     * @return a String[] with all the names of players in each row of the matrix 
     */
    public String[] showPlayers(){
        String stringFile="";
        String allFile="";
        String[] players;
        String name[]={""};
        try {
            br=new BufferedReader(new FileReader(f));
            while((stringFile=br.readLine())!=null){
               name=stringFile.split("\\,");
               allFile+=name[0]+",";
            }
            players=allFile.split("\\,");
            Arrays.sort(players);
             br.close();
            return players;
        } catch (FileNotFoundException ex) {}
         catch(IOException error){}
        String[] withoutName={""};
        return withoutName;
    }
    /**
     * This method read all the names of player who have zero (0) points
     * @return String[] with the names of players with zero (0) points in each row if the matrix
     */
    public String[] showPlayersZeroPoints(){
        String stringFile="";
        String allFile="";
        String[] players;
        String name[]={""};
        try {
            br=new BufferedReader(new FileReader(f));
            while((stringFile=br.readLine())!=null){
               name=stringFile.split("\\,");
               if(!name[1].equals("0"))
               allFile+=name[0]+",";
            }
            players=allFile.split("\\,");
            
            Arrays.sort(players);
             br.close();
            return players;
        } catch (FileNotFoundException ex) {}
         catch(IOException error){}
        String[] withoutName={""};
        return withoutName;
    }
    /**
     * This mathod delete a name of player in the file
     * @param namePlayer The specific name to delete in the file
     */
    public void deletePlayer(String namePlayer){
        String stringFile="";
        String allFile="";
        String split[];
        try {
            br=new BufferedReader(new FileReader(f));
           
            
            while((stringFile=br.readLine())!=null){
                split=stringFile.split("\\,");
                if(!namePlayer.equalsIgnoreCase(split[0]))
                    allFile+=stringFile+"\n";
            }
            bw=new BufferedWriter(new FileWriter(f));
            bw.write(allFile);
            
            bw.close();
            br.close();
        } catch (FileNotFoundException ex) {}
          catch(IOException error){} 
    }
    /**
     * This method count the  players in the file
     * @return true if the file have the limit players (30) or false if the file not have the limit players
     */
   public boolean getLimitNumberPlayers(){
         String stringFile="";
         int count=0;
        try {
            br=new BufferedReader(new FileReader(f));
            while((stringFile=br.readLine())!=null){
                count++;
            }
            
             br.close();
             if(count==30)
              return true;
            
        } catch (FileNotFoundException ex) {}
         catch(IOException error){}
        String[] withoutName={""};
        return false;
   }
   
    }
