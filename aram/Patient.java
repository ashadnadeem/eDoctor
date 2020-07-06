/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aram;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Ashad Nadeem
 */
public class Patient extends Records implements Human{
    private String Name,Cnic,BloodGroup,Disease;
    private int  Age;
    private char Gender;
    
    static final int namelen = 15;
    static final int cniclen = 13;
    static final int Diseaselen = 30;
    static final int bloodGrouplen = 3;
    static final int genderlen = 1;
    static int SearchTP = 2 * (namelen + cniclen + bloodGrouplen + genderlen + Diseaselen);
    //static int SearchTP = 2 * (namelen + cniclen + bloodGrouplen + genderlen);
    
    public Patient(){}
    public Patient(String name, String cnic, String age, String gen,String BG)throws Blank{
        if (name.equals("")) throw new Blank("Name Required");
        if (age.equals("")) throw new Blank("Age Required");
        if (gen.equals("")) throw new Blank("Gender Required");
        int Tage = Integer.parseInt(age);
        char Tgen = gen.toUpperCase().charAt(0);
        if (Tgen != 'M' && Tgen != 'F') throw new Blank("Valid Gender Required");
        if (Tage<=0 || Tage > 200) throw new Blank("Valid Age Required");
        this.Name = name;
        this.Age = Tage;
        this.Cnic = cnic;
        this.Gender = Tgen;
        this.BloodGroup = BG.toUpperCase();
        this.Disease = "" ;
    }
    
    public void setValues(String name, String cnic, String age, char gen,String BG) throws Blank, FileNotFoundException{
        try{
            RandomAccessFile file = new RandomAccessFile("PersonalData.dat","rw");
            if (name.equals("Someone")) throw new Blank("Name Required");
            super.valid(file,name,'S',SearchTP);
            if (age.equals("")) throw new Blank("Age Required");
            if (cnic.equals("4230123456789")) throw new Blank("Cnic Required");
            int Tage = Integer.parseInt(age);
            if (Tage<=0 || Tage > 200) throw new Blank("Valid Age Required");
        
            if (gen == ' ') throw new Blank("Gender Required M/F");
            //char Tgen = gen.toUpperCase().charAt(0);
            //if (Tgen != 'M' && Tgen != 'F') throw new Blank("Valid Gender Required");
            if (BG.equals("")) throw new Blank("Blood Group Required");
            if (cnic.length() != 13) throw new Blank("Valid Cnic Required");
            super.valid(file,cnic,'I', SearchTP);
            this.Name = name;
            this.Age = Tage;
            this.Cnic = cnic;
            this.Gender = gen;
            this.BloodGroup = BG.toUpperCase();
            this.Disease = "" ;
        }
        catch(NumberFormatException inpm){
            throw new Blank("Invalid Inputs: Age:Integer,Name:Text");
        }
    }
    
    public String getName(){
        return this.Name;
    }
    public String getCnic(){
        return this.Cnic;
    }
    public int getAge(){
        return this.Age;
    }
    public char getGender(){
        return this.Gender;
    }
    public String getBG(){
        return this.BloodGroup;
    }
    public String getDisease(){
        return this.Disease;
    }
    public void setDisease(String newDisease) {
        this.Disease = newDisease;
    }
    public void writeDisease() throws IOException, Blank{
        super.addDiseaseseToRandomAccessFile(this.Cnic, this.Disease);
        
    }
    
    
    public void write() throws FileNotFoundException, IOException{
        //PrintWriter outFile = new PrintWriter("PersonalData.txt",true);
        //outFile.println(this.getName() + ',' + this.Cnic + ','+ this.getAge()+','+this.getGender()+','+this.getBG());
        
        BufferedWriter outFile = new BufferedWriter(new FileWriter("PersonalData.txt", true));
        outFile.append(this.getName() + ',' + this.Cnic + ','+ this.getAge()+','+this.getGender()+','+this.getBG());
        outFile.newLine();
        outFile.close();
    }
    
    public void write(RandomAccessFile file) throws IOException {
        file.seek(file.length());
        super.writeString(Name, namelen, file);
        super.writeString(Cnic, cniclen, file);
        super.writeChar(Gender, file);
        super.writeString(BloodGroup, bloodGrouplen , file);
        super.writeString(Disease, Diseaselen, file);
        super.writeInt(Age , file);
    }
        
    public void read(RandomAccessFile file) throws IOException {
        Name = super.readString(namelen, file);
        Cnic = super.readString(cniclen, file);
        Gender = super.readChar( file);
        BloodGroup = super.readString(bloodGrouplen , file);
        Disease = super.readString(Diseaselen, file);
        Age = super.readInt( file);
    }
    
    public static int size(){
        return 2 * (namelen + cniclen + bloodGrouplen + genderlen + Diseaselen) + 4;
        //2: beacuse 1 char is stored in 2 bytes
        //4: beacuse 1  int is stored in 4 bytes
    }
    
    public Patient alreadyRegistered(String text) throws IOException, Blank{
        RandomAccessFile file = new RandomAccessFile("PersonalData.dat","rw");
        long ptr = super.searchString(file,text,SearchTP);
        file.seek(ptr);
        Patient a = new Patient();
        a.read(file);
        return a;
    }
    
    public String toString(){
        return this.Name.trim()+'('+this.Gender+") of Age:" + this.Age+", BloodGroup:" + this.BloodGroup.trim()+", Bearing CNIC#:" + this.Cnic.trim()+", was Diagnosed with Desease: " + this.Disease;
    }
    
}