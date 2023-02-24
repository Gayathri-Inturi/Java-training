import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter op = new PrintWriter(new File("Book1.csv"));
        StringBuilder sb = new StringBuilder();
        int count=0;
        int count2=0;
        ArrayList<ArrayList<String>> hm = new ArrayList<>();
        Random r = new Random();
        for (int j = 0; j < 3; j++) {
            sb.append("\r\n");
            sb.append("Player1");
            sb.append(",");
            sb.append("Player2");
            sb.append("\r\n");
    
            ArrayList<String> t1 = new ArrayList<>();
            ArrayList<String> t2 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                int p = r.nextInt(2);
                if (p == 0) {
                    t1.add("H");
                    t2.add("T");
                } else {
                    t1.add("T");
                    t2.add("H");
                }
            }
            int l1 = t1.stream().filter(x -> x.equals("head")).collect(Collectors.toList()).size();
            int l2 = t2.stream().filter(x -> x.equals("head")).collect(Collectors.toList()).size();
            for (int i = 0; i <= 9; i++) {
                sb.append("Round " + (i + 1));
                sb.append(",");
                sb.append(t1.get(i));
                sb.append(",");
                sb.append(t2.get(i));
                sb.append("\r\n");

            }
            String winner="";
            if (l1 != l2) {
                winner = (l1 > l2) ? "Player1" : "Player2";
                sb.append(winner);
            } else {
                sb.append("Match tie");
            }
            if(winner.equals("Player1")){
                count++;
            }
            else{
                count2++;
            }
            
            hm.add(t1);
            hm.add(t2);
        }
        if(count>count2){
            op.append("Player1 winner");
        }
        else{
            op.append("Player2 winner");
        }
        op.write(sb.toString());
        op.close();
    }
}