
package aram;

import static aram.Patient.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

   
public class Records{
    static void add(Patient a) throws IOException{
        RandomAccessFile file = new RandomAccessFile("PersonalData.dat","rw");
        file.seek(file.length());
        a.write(file);
    }
    static Patient first(RandomAccessFile file) throws IOException{
        file.seek(0);
        
        Patient a = new Patient();
            a.read(file);
           // pointer = (int)file.getFilePointer();
          return a;
          
    }
    static Patient next(RandomAccessFile file) throws IOException{
        if(file.getFilePointer() == file.length())
        {
            file.seek(0);
        }
        Patient a = new Patient();
        a.read(file);
        return a;       
    }
    static Patient previous(RandomAccessFile file) throws IOException{
        
        Patient a = new Patient();
        int x = (int)file.getFilePointer() - 2*(size());
     
        if(x < 0)
        {
            file.seek(file.length() - size());
     
        }
        else
            file.seek(x);
        
        a.read(file);
        return a;     
    }
    static Patient last(RandomAccessFile file) throws IOException{
        Patient a = new Patient();
        file.seek(file.length() - size());
      
            a.read(file);

          return a;        
    }
    

    static boolean notunique(RandomAccessFile file, String searchUser, int size)throws IOException, Blank{
        char [] temp;
        String TempUser;
        file.seek(0);
        //Patient a = new Patient();
        long ptr;
        do{
            temp = new char[size/2];
            for (int i = 0; i < temp.length; i++)
                temp[i] = file.readChar();
            TempUser = new String(temp);
            System.out.println(TempUser.equals(searchUser));
            if(TempUser.contains(searchUser)) return true;
            file.seek(file.getFilePointer() + 4 );
        }while((!TempUser.equals(searchUser)) && (int)file.getFilePointer()<(int)file.length());
        System.out.print(TempUser);
        return false;
        //return a;
    }
    
    protected void writeString(String str,int strlen, RandomAccessFile file) throws IOException {
        StringBuffer sb;
        if (str != null)
            sb = new StringBuffer(str);
        else
            sb = new StringBuffer();
        sb.setLength(strlen);
        file.writeChars(sb.toString());
    }
    protected void writeInt(int number, RandomAccessFile file) throws IOException {
        file.writeInt(number);
    }
    protected void writeChar(char chr, RandomAccessFile file) throws IOException {
        file.writeChar(chr);
    }
    
//    protected void deleteRecord(String key, int size, RandomAccessFile file) throws IOException, Blank{
//        long ptr = searchString(file, key, size);
//        file.setLength(ptr);
//        while(file.getFilePointer() != file.length()){
//        
//        }
//    }
//    
    protected String readString(int strlen, RandomAccessFile file) throws IOException {
        char[] temp = new char[strlen];
        for (int i = 0; i < temp.length; i++)
            temp[i] = file.readChar();
        String str = new String(temp);
        return str;
    }
    protected int readInt(RandomAccessFile file) throws IOException {
       int Age = file.readInt();
       return Age;
    }
    protected char readChar(RandomAccessFile file) throws IOException {
       char chr = file.readChar();
       return chr;
    }
    
    
    public void valid(RandomAccessFile file, String name,char f, int size) throws Blank, FileNotFoundException,NullPointerException{
        //For Name  S:String(Text)
        if (f == 'S'){
            name = name.toUpperCase();
            for(int i=0;i<name.length();i++){
                if((name.charAt(i)>= 'A' && name.charAt(i)<='Z') || (name.charAt(i)==' ')){}
                else    throw new Blank("Valid Name Required");
            }
        }
        //For Cnic# I:Integer
        if (f == 'I'){
            for(int i=0;i<name.length();i++){
                if(name.charAt(i)>= '0' && name.charAt(i)<='9'){}
                else    throw new Blank("Valid Cnic Required");
            }
            try {
                boolean un = Records.notunique(file, name, size);
                if(un) throw new Blank("Cnic already in use, Please try different");
            } catch (IOException ex) {
                Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    static long searchString(RandomAccessFile file, String searchUser, int size)throws IOException, Blank{
        char [] temp;
        String TempUser;
        file.seek(0);
        //Patient a = new Patient();
        long ptr;
        do{
            temp = new char[size/2];
            for (int i = 0; i < temp.length; i++)
                temp[i] = file.readChar();
            TempUser = new String(temp);
            //System.out.println(TempUser.equals(searchUser));
            if(TempUser.contains(searchUser)) break;
            file.seek(file.getFilePointer() + 4 );
        }while((!TempUser.equals(searchUser)) && (int)file.getFilePointer()<(int)file.length());
        //System.out.print(TempUser);
        if(TempUser.contains(searchUser)){
            file.seek(file.getFilePointer() - size);
            //a.read(file);
            ptr = file.getFilePointer();
        }
        else throw new Blank("User Not Found");
        return ptr;
        //return a;
    }
    public void addDiseaseseToRandomAccessFile(String Name, String Disease) throws IOException, Blank{
        RandomAccessFile file = new RandomAccessFile("PersonalData.dat","rw");
        long ptr;
        ptr = searchString(file, Name, SearchTP);
        
        file.seek(ptr + 2 * (namelen + cniclen + bloodGrouplen + genderlen));
        writeString(Disease, Diseaselen, file);
        file.close();
    }
      
}