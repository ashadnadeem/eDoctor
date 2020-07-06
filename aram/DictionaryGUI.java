package aram;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class DictionaryGUI extends JFrame implements ActionListener {

    private JTextField InputSymptom, InputDisease;
    private JTextArea OutputSymptoms, OutputDisease, OutputMedications;

    private JButton SearchBySymptoms, SearchByDisease, exitJB;

    private Dictionary dictionary = new Dictionary();

    public DictionaryGUI() throws FileNotFoundException {
        JPanel J1 = new JPanel();
        J1.setLayout(new FlowLayout(FlowLayout.CENTER));
        J1.add(new JLabel("Welcome to the Medical Database"));


        JPanel J2 = new JPanel();
        J2.setLayout(new GridLayout(2, 3));
        J2.add(new JLabel("Symptom"));
        J2.add(InputSymptom = new JTextField(4));
        J2.add(SearchBySymptoms = new JButton("Search through symptoms"));
        J2.add(new JLabel("Disease"));
        J2.add(InputDisease = new JTextField(4));
        J2.add(SearchByDisease = new JButton("Search through disease"));
        J2.setBorder(new TitledBorder("You can search either through symptoms or diseases"));

        JPanel J3 = new JPanel();
        J3.setLayout(new GridLayout(2, 3));
        J3.add(new JLabel("Symptoms"));
        J3.add(new JLabel("Diseases"));
        J3.add(new JLabel("Medications"));
        J3.add(OutputSymptoms = new JTextArea());
        J3.add(OutputDisease = new JTextArea());
        J3.add(OutputMedications = new JTextArea());
        OutputSymptoms.setEditable(false);
        OutputSymptoms.setRows(5);
        OutputDisease.setEditable(false);
        OutputDisease.setRows(5);
        OutputMedications.setEditable(false);
        OutputMedications.setRows(5);
        J3.setBorder(new TitledBorder("Your Search Results are shown below"));

        Container container = getContentPane();
        container.add(J1, BorderLayout.NORTH);
        container.add(J2, BorderLayout.CENTER);
        container.add(J3, BorderLayout.SOUTH);

        InputSymptom.addActionListener(this);
        InputDisease.addActionListener(this);
        SearchBySymptoms.addActionListener(this);
        SearchByDisease.addActionListener(this);

        OutputDisease.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        OutputSymptoms.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        OutputMedications.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 9 / 20;
        int width = screenSize.width * 19/20;
        this.setPreferredSize(new Dimension(width, height));
        //this.setPreferredSize(new Dimension(945, 385));
        this.setVisible(true);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if((e.getSource() == SearchBySymptoms) || (e.getSource() == InputSymptom) ){
            if (InputSymptom.getText().isEmpty()){
                 JOptionPane.showMessageDialog(null,"Please enter the required field");
            }
            else{
                reset();
                String temp = "";
                String[] Arr = dictionary.SymptomWise(InputSymptom.getText().toUpperCase());
                int a = dictionary.getA();
                for (int i = 0; i < Arr.length;i++){
                    if (a == 1 && i == 0){
                        for (int j = 4; j < Arr[i].length();j++){
                            temp = temp + Arr[i].charAt(j);
                        }
                        OutputDisease.append(temp.replace("null", "") + "\n");
                    }
                    else
                        OutputDisease.append(Arr[i].replace("null", "") + "\n");
                }
            }
            

        }

        if ((e.getSource() == SearchByDisease) || (e.getSource() == InputDisease)){
            if (InputDisease.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Please enter the required field");
            }
            else{
                 reset();
                String temp1 = "";
                String[] Arr1 = dictionary.DiseaseWise(InputDisease.getText().toUpperCase());
                int a = dictionary.getA();
                for (int i = 0; i < Arr1.length;i++){
                    if (a == 1 && i == 0){
                        for (int j = 4; j < Arr1[i].length();j++){
                            temp1 = temp1 + Arr1[i].charAt(j);
                        }
                        OutputSymptoms.append(temp1 + "\n");
                    }
                    else
                        OutputSymptoms.append(Arr1[i] + "\n");
                }
                
                String temp2 = "";
                String[] Arr2 = new String[0];
                try {
                    Arr2 = dictionary.Medication(InputDisease.getText().toUpperCase());
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                for (int i = 0; i < Arr2.length;i++){
                    OutputMedications.append(Arr2[i] + "\n");
                }

            }
           
            


        }


    }
    public void reset(){
        OutputDisease.selectAll();
        OutputDisease.replaceSelection("");
        OutputSymptoms.selectAll();
        OutputSymptoms.replaceSelection("");
        OutputMedications.selectAll();
        OutputMedications.replaceSelection("");
    }

    public static void main(String[] args) throws FileNotFoundException {
        DictionaryGUI Dic = new DictionaryGUI();
        
    }


}


