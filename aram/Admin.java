/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aram;

import static aram.Patient.namelen;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Ashad Nadeem
 */
public class Admin extends Records implements Human {
    private String Name, userName, userPsw, cnic;
    private int age;
    private char gender;
    public static int namelen = 15;
    public static int userlen = 15;
    public static int userPswlen = 8;
    public static int cniclen = 13;
    static final int SearchTA = 2 *(namelen + cniclen + userlen + userPswlen + 1);
    
    public Admin(){}
    public Admin(String Name, String userName, String userPsw, String cnic, char gender,int age) {
        this.Name = Name;
        this.userName = userName;
        this.userPsw = userPsw;
        this.cnic = cnic;
        this.gender = gender;
    }
    
    public void setValues(String Name,String age, String userName, String userPsw, String cnic, char gender) throws Blank, FileNotFoundException, IOException{
        try{
            RandomAccessFile file = new RandomAccessFile("AdminData.dat","rw");
            if (Name.equals("Someone")) throw new Blank("Name Required");
            if (cnic.equals("4230123456789")) throw new Blank("Cnic Required");
            if (age.equals("")) throw new Blank("Age Required");
            int Tage = Integer.parseInt(age);
            if (Tage<=0 || Tage > 200) throw new Blank("Valid Age Required");
            if (gender == ' ') throw new Blank("Gender Required M/F");
            if (cnic.length() != 13) throw new Blank("Valid Cnic Required");
            //super.valid(file,cnic,'I',SearchTA);
            this.Name = Name;
            this.userName = userName;
            this.userPsw = userPsw;
            this.cnic = cnic;
            this.gender = gender;
        }
        catch(NumberFormatException inpm){
            throw new Blank("Invalid Inputs: Age:Integer,Name:Text");
        }
    }
    public void setuserpass(String userName, String userPsw) throws Blank, FileNotFoundException, IOException{
        this.userName = userName;
        this.userPsw = userPsw;
    }
    public void addAdmin(String Name,String userName, String userPsw) throws Blank, FileNotFoundException, IOException{
        try{
            RandomAccessFile file = new RandomAccessFile("AdminData.dat","rw");
            if (Name.equals("")) throw new Blank("Name Required");
            if (cnic.equals("")) throw new Blank("Cnic Required");
            //super.valid(file,cnic,'I',SearchTA);
            this.Name = Name;
            this.userName = userName;
            this.userPsw = userPsw;
            this.age = 0;
            this.cnic = "";
            this.gender = ' ';
            write(file);
        }
        catch(NumberFormatException inpm){
            throw new Blank("Invalid Inputs: Age:Integer,Name:Text");
        }
    }
    
    public void removeAdmin(RandomAccessFile file, String userName) throws IOException, Blank{
        long ptr = super.searchString(file,userName,SearchTA);
        file.seek(ptr);
        //writing blank field
        super.writeString("", SearchTA, file);
        super.writeInt(0, file);
    }
    public void write(RandomAccessFile file) throws IOException {
        file.seek(file.length());
        super.writeString(Name, namelen, file);
        super.writeString(cnic, cniclen, file);
        super.writeString(userName, userlen, file);
        super.writeString(userPsw, userPswlen, file);
        super.writeChar(gender, file);
        super.writeInt(age, file);
    }
        
    public void read(RandomAccessFile file) throws IOException {
        Name = super.readString(namelen, file);
        cnic = super.readString(cniclen, file);
        userName = super.readString(userlen, file);
        userPsw = super.readString(userPswlen , file);
        gender = super.readChar(file);
        age = super.readInt(file);
    }
    
    public static int size(){
        return 2 * (namelen + cniclen + userlen + userPswlen + 1) + 4;
        //2: beacuse 1 char is stored in 2 bytes
        //4: beacuse 1  int is stored in 4 bytes
    }
    public String getName() {
        return Name;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserPsw() {
        return userPsw;
    }
    public String getCnic() {
        return cnic;
    }
    public char getGender() {
        return gender;
    }
    
    
    public void updateCredentials(RandomAccessFile file) throws IOException, Blank
    {
        long ptr = super.searchString(file,userName,SearchTA);
        file.seek(ptr + 2 *(namelen + cniclen + userlen));
        super.writeString(userPsw, userPswlen, file);
    }
}
