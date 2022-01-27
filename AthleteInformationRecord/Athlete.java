package AthleteInformationRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Athlete extends JFrame implements ActionListener{
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    private static DoublyLinkedList list;
    private static JTextArea text;

    public static void main(String[] args) {
        fileData();
        Athlete gui = new Athlete();
        gui.setVisible(true);
    }
    
    public Athlete(){
        super("Athlete information recording");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2,1));
        text = new JTextArea(5,20);
        textPanel.add(text);
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(Color.ORANGE);
        labelPanel.setLayout(new GridLayout(2,1));        
        JLabel label1 = new JLabel("To add new athlete, write the information of the athlete in format of day/month/year and click the \"add athlete\" button.");
        labelPanel.add(label1);
        JLabel label2 = new JLabel("To see athlete information, write the name and surname of the athlete and click the \"print athlete information\" button.");
        labelPanel.add(label2);
        textPanel.add(labelPanel);
        add(textPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.MAGENTA);
        buttonPanel.setLayout(new FlowLayout());
        JButton add = new JButton("Add athlete");
        add.addActionListener(this);
        buttonPanel.add(add);
        JButton delete = new JButton("Delete athlete");
        delete.addActionListener(this);
        buttonPanel.add(delete);
        JButton print = new JButton("Print athlete information");
        print.addActionListener(this);
        buttonPanel.add(print);
        JButton ascending = new JButton("All records (from A to Z)");
        ascending.addActionListener(this);
        buttonPanel.add(ascending);
        JButton descending = new JButton("All records (from Z to A)");
        descending.addActionListener(this);
        buttonPanel.add(descending);
        JButton quit = new JButton("Quit");
        quit.addActionListener(this);
        buttonPanel.add(quit);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    // This method is the abstract method of ActionListener class.
    // The purpose of this method is to determine which actions will be performed when the buttons are pressed.
    @Override
    public void actionPerformed(ActionEvent e){
        String action = e.getActionCommand();
        if(action.equals("Add athlete")){
            String info = text.getText();
            try{
                list.addNode(readData(info));
            } catch(Exception e3){
                text.setText("Error. Athlete information must be in format of  \" name surname, day/month/year, team\"");
            }
        } else if (action.equals("Delete athlete")){
            String bilgi = text.getText();
            list.deleteNode(bilgi);
        } else if (action.equals("Print athlete information")){
            try {
                String bilgi = text.getText();            
                text.setText(list.findNode(bilgi).toString());
            }catch (Exception e4){
                text.setText("Error. The name and the surname of the athlete must be written correctly.");
            }

        } else if (action.equals("All records (from A to Z)")){
            text.setText(list.printList());
        } else if (action.equals("All records (from Z to A)")){
            text.setText(list.reversedList());
        } else if (action.equals("Quit")) {
            try {
            FileOutputStream fos = new FileOutputStream("athlete.txt");
            OutputStreamWriter w = new OutputStreamWriter(fos);
            w.write(list.printList());
            w.flush();
            w.close();
            } catch (Exception e2){
                System.out.println("Athlete file cannot be found.");
                System.exit(0);
            }
            System.exit(0);
        }
    }
    
    //The purpose of this method is to read the data in the athlete.txt file
    //and add it to the DoublyLinkedList list.
    public static void fileData(){
        list = new DoublyLinkedList();
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(new FileInputStream("athlete.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Athlete file cannot be found.");
            System.exit(0);
        }
        while (fileIn.hasNext()) {
            String info = fileIn.nextLine();
            list.addNode(readData(info));
        }
    }
    
    //The purpose of this method is to seperate the athlete information with tokenizer
    //and return it in Info type.
    public static Info readData(String info){
        ArrayList<String> Team = new ArrayList<>();
        StringTokenizer newAthlete = new StringTokenizer(info, ",");
        String nameSurname = newAthlete.nextToken().trim();
        String sdate = newAthlete.nextToken().trim();
        StringTokenizer tDate = new StringTokenizer(sdate, "/");
        int day = Integer.parseInt(tDate.nextToken());
        int month = Integer.parseInt(tDate.nextToken());
        int year = Integer.parseInt(tDate.nextToken());
        Date date = new Date(month, day, year);
        while (newAthlete.hasMoreTokens()) {
            Team.add(newAthlete.nextToken().trim());
        }
        return new Info(nameSurname, Team, date);
    }
}
