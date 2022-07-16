package u.doruk.calco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Ui implements ActionListener{
    JTextArea txt;
    JButton[] btn;
    boolean clickedForTheFirstTime = false;
    Calco calco;
    List<String> expressions = new ArrayList<String>();
    //variable to store the current position of cursor in the scrolling list using << and  >> buttons
    int currentPosition = 0;
    //store length of expressions list
    int expressionsLength = 0; // stores the index value of list
    public Ui(Calco clc){
        //initialize the calco variable to the instance of Calco received as clc
        calco = clc;

        //creating the main application window
        JFrame window = new JFrame("Doruk's Calco");
        //create main vertical layout
        var vertLay = new JPanel();
        vertLay.setLayout(new BoxLayout( vertLay, BoxLayout.Y_AXIS));
        
        //create textarea to hold all the expressions and answers
        txt = new JTextArea("A new way of calculation", 10 ,5);
        // txt.setPreferredSize(new Dimension(700, 200));
        txt.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ev){
            }
            @Override
            public void keyReleased(KeyEvent ev){
            }
            @Override
            public void keyPressed(KeyEvent ev){
                if(ev.getKeyCode() == 8){
                    String text = txt.getText();
                    if(text.equals(""))
                        return;
                    int len = text.length();
                    text = text.substring(0, len - 1);
                    txt.setText(text);
                }
            }

        });
        txt.setBackground(Color.gray);
        txt.setForeground(Color.BLUE);
        txt.setEditable(false);
        txt.setCursor(new Cursor(5));
        txt.setFont(new Font("Ariel", Font.ITALIC, 50));
        
        JScrollPane scroller = new JScrollPane(txt);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        vertLay.add(scroller);
        scroller.setAutoscrolls(true);
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
        String[] btnArray = {"^", "√", "<<", ">>", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
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
        //set focus to the text area
        txt.requestFocus();

        String key = e.getActionCommand();
        if(!clickedForTheFirstTime && !key.equals("=")){
            txt.setText("");
            clickedForTheFirstTime = true;
        }
        String prevText = txt.getText();

        if(key.equals("C")){
            txt.setText("");
            return;
        }   
        else if(key.equals("=")){
            //check if the expression already exists in the list and remove it from previous index
            if(prevText.indexOf("\n") > -1)
                return;
            else if(prevText.indexOf("calculation") > -1)
                return;
            else if(prevText.equals(""))
                return;

            //check if the same expression already exists in the expressions list
            int index = expressions.indexOf(prevText);
            if(index > -1)
                expressions.remove(index);
    
            //add the expression to the expressions list
            expressions.add(prevText);
            //update the expression list length and current position
            expressionsLength = expressions.size();
            currentPosition = expressionsLength - 1; // as it stores the index value

            //make it false so that when next number is entered it clears the previous text
            clickedForTheFirstTime = false;
            //call method to evaluate or whatever...
            calco.onEqualPressed();
            return;
        }
        else if(key.equals("+/-")){
            txt.setText(prevText + "-");
            return;
        }
        else if(key.equals("<<")){
            if(currentPosition > 0)
                txt.setText(expressions.get(--currentPosition));
            return;
        }
        else if(key.equals(">>")){
            if(currentPosition < (expressionsLength - 1))
            txt.setText(expressions.get(++currentPosition));
            return;
        }
        else if(key.equals("√")){
            txt.setText(prevText + "^0.5");
            return;
        }
        else
            txt.setText(prevText + key);
    }
    
}
