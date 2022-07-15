package u.doruk.calco;
import org.python.util.PythonInterpreter;

public class Evaluate {
    public Evaluate(){
    }
    
    public String evaluate(String exp){
        PythonInterpreter python = new PythonInterpreter();
        String ans = "";
        try{
            ans = python.eval(exp).toString();
        } catch(Exception e){
            ans = "Error: invalid expression";
        }
        return ans;
    }
}
