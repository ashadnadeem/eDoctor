/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aram;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;

/**
 *
 * @author Ashad Nadeem
 */
public class ARAM extends JApplet{
    public static int Arraylen;
    public static int MedArraylen;
    
    public static void main(String[] args) throws FileNotFoundException, Blank, IOException{
        adjustArrayLen(new FileReader("SymptomDisease.txt"),new FileReader("Medication.txt"));
        PatientGUI p = new PatientGUI();
        //Login lg = new Login();
        //RecordGUI r = new RecordGUI("Ashad");     
    }
    public static int getFilelen(FileReader file) throws IOException{
        BufferedReader reader = new BufferedReader(file);
        int lines = 0;
        while (reader.readLine() != null) 
            lines++;
        reader.close();
        return lines;
    }
    public static void adjustArrayLen(FileReader file1,FileReader file2){
        try {
            Arraylen = getFilelen(file1);
            MedArraylen = getFilelen(file2);
            
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
