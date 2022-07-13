package u.doruk.calco;

public class Calco{

    public Calco(){
        new Ui(this);
    }

    // define what happens when user clicks the equal '='
    public void onEqualPressed(){
        System.out.println("equal is pressed...");
    }

    public static void main(String[] args) {
        new Calco();
    }
    
}