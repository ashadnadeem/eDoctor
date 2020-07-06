package aram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary {
    FileReader sympFile, medFile;
   
    String symptom;
    String disease;
    String Med = "";
    String[] Array , MedArray,Desease;
    int sympFileLen, medFileLen; 
    int a = 0;

    public Dictionary() throws FileNotFoundException {
        sympFile = new FileReader("SymptomDisease.txt");
        medFile = new FileReader("Medication.txt");
        Array = new String[ARAM.Arraylen];
        Desease = new String[ARAM.Arraylen]; 
        MedArray = new String[ARAM.MedArraylen];
        
        
        String line;
        int a = 0;
        Scanner inFile = new Scanner(sympFile);
        String[]temp;
        while (inFile.hasNextLine()) {
            line = inFile.nextLine();
            Array[a] = line;
            temp = Array[a].split("/");
            Desease[a] = temp[temp.length - 1];
            System.out.println(Desease[a]);
            a++;
        }
        String line1;
        int a1 = 0;

        Scanner inFile1 = new Scanner(medFile);
        while (inFile1.hasNextLine()) {
            line1 = inFile1.nextLine();
            MedArray[a1] = line1;
            a1++;
        }
    }

    public String[] SymptomWise(String symptom) {
        this.a++;
        this.symptom = symptom;

        for (int i = 0; i < Array.length; i++) {
            if ((Array[i].toUpperCase()).contains(this.symptom)) {
                String[] Array1 = Array[i].split("/");
                this.disease = this.disease + Array1[Array1.length - 1];
                Array1 = null;
            }
        }


        String[] FDisease = this.disease.split(",");
        this.disease = "";
        this.symptom = "";

        return FDisease;

    }

    public String[] DiseaseWise(String disease) {
        this.a++;
        this.disease = disease;


        for (int i = 0; i < Array.length; i++) {
            if ((Array[i].toUpperCase()).contains(this.disease)) {
                String[] Array1 = Array[i].split("/");
                for (int j = 0; j < Array1.length - 1; j++) {
                    this.symptom = this.symptom + Array1[j] + ",";
                }

            }
        }

        String[] FSymptoms = this.symptom.split(",");
        this.symptom = "";
        this.disease = "";

        return FSymptoms;

    }

    public String[] Medication(String disease) throws FileNotFoundException {
        this.disease = disease;



        for (int i = 0; i < MedArray.length; i++) {
            if ((MedArray[i].toUpperCase()).contains(this.disease)) {
                String[] Array1 = MedArray[i].split("/");
                for (int j = 1; j < Array1.length; j++)
                    this.Med = this.Med + Array1[j] + ",";
            }
        }

        String[] FMed = this.Med.split(",");
        this.Med = "";
        this.disease = "";

        return FMed;

    }

    public int getA(){
        return this.a;
    }
    public String[] getDeseaseArray(){
        return this.Desease;
    }
}


