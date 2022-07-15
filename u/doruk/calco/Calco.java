package u.doruk.calco;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

public class Calco{
    Ui ui;
    static Evaluate eval;
    public Calco(){
       ui =  new Ui(this);
    }

    //separate and store separately
    //each symbols are single element of list and each numbers are single elements of list
    //e.g. ["-", "435", "(", "345345", "+", "-"]
    private List<String> prepareList(String exp){
        char[] charArray = exp.toCharArray();
        List<String> charList = new ArrayList<String>();
        String buffer = "";
        for (char c : charArray){
            if((c >= '0' && c <= '9') || c == '.'){
                buffer += c;
                continue;
            }
            else if( buffer.length() > 0){
                charList.add(buffer);
                buffer = "";
            }
            buffer += c;
            charList.add(buffer);
            buffer = "";
        }
        if(buffer.length() > -1 )
            charList.add(buffer);
        return charList;
    }
    private String toDouble(String x){
        try{
            return Double.toString(Double.parseDouble(x));
        }catch (Exception e){
            return x;
        }
    }
    //convert the string expression into valid expression
    private String validate(String exp){
        List<String> expList = new ArrayList<String>();
        expList = this.prepareList(exp);
        //now replace % , underroot and other symbols with valid expression
        while(expList.indexOf("%") > -1){
            int index = expList.indexOf("%");
            String toReplace = expList.get(index - 1);
            //now replace
            //add / to next to the number
            // replace % with 100 and it will make, 'x','%' -> 'x', '/', '100'
            expList.add(index, "/"); //add / in between number and % sign
            expList.set(index + 1, "100"); // this will replace % with 100
        }
        //replace ^ with **
        if(expList.indexOf("^") > -1)
            expList.replaceAll(e -> (e.equals("^"))? "**" : e);
        expList.replaceAll(e -> this.toDouble(e));
        exp = String.join("", expList);
        return exp;
    }

    // define what happens when user clicks the equal '='
    public void onEqualPressed(){
        String exps = ui.getTxt();
        String expression = "";

        expression = validate(exps);
        String ans = eval.evaluate(expression);
        
        //set answer in the output
        ui.setTxt(exps + "\n" + ans);
    }

    public static void main(String[] args) {
        //since for the first time evaluate method takes some time we invoke it here so it takes less time next time it is called via UI
        //instantiate eval object
        eval = new Evaluate();
        eval.evaluate("5*5");
        new Calco();
    }
    
}