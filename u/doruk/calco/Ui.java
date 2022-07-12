package u.doruk.calco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Ui implements ActionListener{
    JTextArea txt;
    JButton[] btn;
    boolean clickedForTheFirstTime = false;
    public Ui(){
        //creating the main application window
        JFrame window = new JFrame("Doruk's Calco");
        //create main vertical layout
        var vertLay = new JPanel();
        vertLay.setLayout(new BoxLayout( vertLay, BoxLayout.Y_AXIS));
        
        //create textarea to hold all the expressions and answers
        txt = new JTextArea("A new way of calculation", 10 ,5);
        txt.setPreferredSize(new Dimension(700, 200));
        txt.setBackground(Color.gray);
        txt.setForeground(Color.BLUE);
        txt.setEditable(false);
        txt.setFont(new Font("Ariel", Font.ITALIC, 50));
        
        vertLay.add(txt);
        //create row1 for holding first row of buttons
        var row1 = new JPanel();
        row1.setSize(new Dimension(100,20));
        row1.setLayout(new GridLayout(0,5));
        vertLay.add(row1);

        //create first row of buttons
        var bC = new JButton("C");
        bC.addActionListener(this);

        var bbo = new JButton("(");
        bbo.addActionListener(this);

        var bbc = new JButton(")");
        bbc.addActionListener(this);

        var bper = new JButton("%");
        bper.addActionListener(this);

        var div = new JButton("/");
        div.addActionListener(this);
        
        //setting button sizes
        bC.setPreferredSize(new Dimension(150, 55));
        bbo.setPreferredSize(new Dimension(150, 55));
        bbc.setPreferredSize(new Dimension(150, 55));
        bper.setPreferredSize(new Dimension(150, 55));
        div.setPreferredSize(new Dimension(150, 55));
        
        //setting buttons fonts, styles and size of font
        bC.setFont(new Font("Ariel", Font.PLAIN, 40));
        bbo.setFont(new Font("Ariel", Font.PLAIN, 40));
        bbc.setFont(new Font("Ariel", Font.PLAIN, 40));
        bper.setFont(new Font("Ariel", Font.PLAIN, 40));
        div.setFont(new Font("Ariel", Font.PLAIN, 40));

        //add all the buttons to row1
        row1.add(bC);
        row1.add(bbo);
        row1.add(bbc);
        row1.add(bper);
        row1.add(div);
        
        //create row2 for holding second row of buttons
        var grid = new JPanel();
        grid.setLayout(new GridLayout(0, 4));
        
        vertLay.add(grid);

        //create list of all the button texts
        String[] btnArray = {"^", "n√x", "<<", ">>", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
        int len = btnArray.length;
        //initialize the jButton array to the length of btnArray
        btn = new JButton[len];
        //create all the buttons in a loop and set the corresponding button text from btnArray
        for( int i = 0; i < len; i++){
            btn[i] = new JButton();
            btn[i].setPreferredSize(new Dimension(150, 55));
            btn[i].setFont(new Font("Ariel", Font.PLAIN, 40));
            btn[i].setText(btnArray[i]);
            btn[i].addActionListener(this);
            grid.add(btn[i]);
        }

        //add verticalLay to the main frame
        window.add(vertLay);
        //set the main window size
        window.setSize(700, 600);
        window.setVisible(true);
        window.setResizable(false); // disable window resizing

    }

    //getter method to get the text of the txt 
    public String getTxt(){
        return txt.getText();
    }

    //setter method to set the text of txt
    // this method is required to set the answer to the txt
    public void setTxt(String txt){
        this.txt.setText(txt);
    }

    //define what happens when each buttons is pressed
    public void actionPerformed(ActionEvent e){
        if(!clickedForTheFirstTime){
            txt.setText("");
            clickedForTheFirstTime = true;
        }
        
        String prevText = txt.getText();
        if(e.getActionCommand().equals("=")){

        }

        txt.setText(prevText + e.getActionCommand());
    }
    
}
