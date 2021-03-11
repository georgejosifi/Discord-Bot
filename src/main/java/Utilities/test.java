package Utilities;

import java.util.ArrayList;
import java.util.List;

public class test {
    String [] testiii;
    public static void main(String[] args) {
        String test = " une jam karderr ";
        String test2 = test.trim();
        System.out.println(test2);
        String [] data = {"albi", "bora"};

        String [] answers = new String[3];
         for(int i=1; i<data.length; i++) {
              answers[i] = data[i];
    }
        data[0] = "nila";
        System.out.println(answers[1]);
//        List<String> answe;
//        List <String> bullshit = new ArrayList<>();
//        bullshit.add("bullshit");
//        answe = bullshit;
//        bullshit.remove(0);
//        //System.out.println(bullshit.get(0));
//        System.out.println(answe.get(0));

    }
    public void upload (String [] data) {
        this.testiii = data;
    }
}
