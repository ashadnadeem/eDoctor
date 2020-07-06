package aram;
import javax.swing.text.MaskFormatter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Symptoms extends AI {

    private String MasterSymptoms, currentQues;
    private boolean Answer;
    private int count;

    public Symptoms() throws FileNotFoundException {

        String line;
        Scanner inFile = new Scanner(new FileReader("SymptomDisease.txt"));
        //System.out.println(inFile.next());
        while(inFile.hasNextLine()) {
            line = inFile.nextLine();
            this.MasterSymptoms = this.MasterSymptoms + line + "";
        }
        
        inFile.close();
        this.adjustcurrentQues();
        this.adjustcount();

    }
    
    public void adjustcount(){
        this.count = 0;
        for (int i = 0; i < this.MasterSymptoms.length(); i++){
            if (this.MasterSymptoms.charAt(i) == '/')
                this.count++;
        }
    }
    public void adjustcurrentQues(){
        String[] fQues = this.MasterSymptoms.split("/");
        this.currentQues = fQues[0];
    }
    
     public void setMasterSymptoms(String str){
        this.MasterSymptoms = str;
    }

    public String getMasterSymptoms(){
        return this.MasterSymptoms;
    }
    public String getcurrentQues(){
        return this.currentQues;
    }
    public int getCount(){
        return this.count;
    }

    public void setAnswer(boolean Ans){
        this.Answer = Ans;
    }

    public boolean getAnswer(){
        return this.Answer;
    }
    public void MAYBE(){
        String[] Array = this.MasterSymptoms.split("/");
        this.MasterSymptoms = super.removeFirst(this.MasterSymptoms,Array[0]);
        this.adjustcount();
        System.out.println(this.count);
        this.adjustcurrentQues();
    } 

    public void Run(){
        this.MasterSymptoms = super.Simplify(this.MasterSymptoms,this.Answer);
        //this.MasterSymptoms = super.Simplify(this.MasterSymptoms,ans);
        this.adjustcount();
        System.out.println(this.count);
        this.adjustcurrentQues();
    }
}