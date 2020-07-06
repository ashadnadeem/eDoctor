/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aram;

import java.util.Scanner;

/**
 *
 * @author Ashad Nadeem
 */
public class AI {
    public static String Simplify(String org, boolean ans){
        String[] word = org.split("/");
        String str = "";
        if(ans){
            str = retainGroup(org,word[0]);
            str = removeFirst(str, word[0]);
        }
        else{
            str = removeGroup(org,word[0]);
        }
        return str.trim();
    }
    public static String removeGroup(String org, String word){
        String[]arr = org.split(",");
        String removedGroup = "";
        for(int i=0;i<arr.length;i++){
            if(arr[i].contains(word)){}
            else
                removedGroup = removedGroup + arr[i] + ',';
        }
        return removedGroup;
    }
    public static String retainGroup(String org, String word){
        String[]arr = org.split(",");
        String retainGroup = "";
        for(int i=0;i<arr.length;i++){
            if(arr[i].contains(word)){
                retainGroup = retainGroup + arr[i] + ',';
            }
        }
        return retainGroup;
    }
    public static String removeFirst(String org, String word){
        String str = org.replaceAll(word+"/", "");
        
        return str;
    }
    public static String run(Boolean ans){
        String org = "Do you feel fatigue and weakness/Do you have regular sensations of nausea/Do you have excessive sweating/Do you face shortness of breath/Do you face reduced ability during to exercise/Coronary Artery Disease,Do you feel fatigue and weakness/Do you have swollen ankles or feet or abdomen/Do you face reduced ability during to exercise/Do you face shortness of breath/Do you often have an irregular heartbeat/Valvular Heart Disease,Do you feel fatigue and weakness/Do you have regular sensations of nausea/Do you have swollen ankles or feet or abdomen/Do you have swollen ankles or feet or abdomen/Heart Failure";
        String[] fQues = org.split("/");
        System.out.println(fQues.length);
        while(fQues.length > 1){
            System.out.println(fQues[0]+'?');
            Scanner inp = new Scanner(System.in);
            ans = inp.nextBoolean();
            org = Simplify(org,ans);
            org.trim();
            fQues = org.split("/");
        }
        if(fQues[0] != "")
            return ("Your Desease is " + fQues[0]);
        else 
            return("Couldnt figure your charachter");
    
    
    }
    
    
    
    
//    public static void main(String[]args){
//        
//        //String org = "m/19/student/Ashad,fe/15/student/Alina,m/52/office/Nadeem,fe/teacher/30/Sahar";
//        String org = "Do you feel fatigue and weakness/Do you have regular sensations of nausea/Do you have excessive sweating/Do you face shortness of breath/Do you face reduced ability during to exercise/Coronary Artery Disease,Do you feel fatigue and weakness/Do you have swollen ankles or feet or abdomen/Do you face reduced ability during to exercise/Do you face shortness of breath/Do you often have an irregular heartbeat/Valvular Heart Disease,Do you feel fatigue and weakness/Do you have regular sensations of nausea/Do you have swollen ankles or feet or abdomen/Do you have swollen ankles or feet or abdomen/Heart Failure";
//        String[] fQues = org.split("/");
//        System.out.println(fQues.length);
//        boolean ans;
//        while(fQues.length > 1){
//            System.out.println(fQues[0]+'?');
//            Scanner inp = new Scanner(System.in);
//            ans = inp.nextBoolean();
//            org = Simplify(org,ans);
//            org.trim();
//            fQues = org.split("/");
//        }
//        if(fQues[0] != "")
//            System.out.print("Your Desease is " + fQues[0]);
//        else 
//            System.out.print("Couldnt figure your charachter");
//    }
     
    
    
}
