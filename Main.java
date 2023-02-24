import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main
 {
    
    public static void main(String[] args) {
        
       // int cycles = 3; // number of cycles
        int rounds = 10; // number of rounds per cycle
        
        int score1 = 0; // keep track of player 1's total score
        int score2 = 0; // keep track of player 2's total score
        
        // play the game for 'cycles' cycles
        for (int i = 1; i <rounds; i++) {
            
            // play 'rounds' rounds per cycle
            int[] results1 = IntStream.range(0, rounds).map(j -> tossCoin()).toArray(); // player 1 tosses the coin
            int[] results2 = IntStream.range(0, rounds).map(j -> tossCoin()).toArray(); // player 2 tosses the coin
            
            // count the number of heads for each player
            int heads1 = (int) Arrays.stream(results1).filter(result -> result == 1).count();
            int heads2 = (int) Arrays.stream(results2).filter(result -> result == 1).count();
            
            // determine the winner of the cycle
            String winner = "";
            if (heads1 > heads2) {
                score1++; // player 1 wins the cycle
                winner = "Player 1";
            } else if (heads2 > heads1) {
                score2++; // player 2 wins the cycle
                winner = "Player 2";
            } else {
                winner = "Tie";
            }
            
            // write the results of the cycle to a CSV file
            String[] data = {Integer.toString(i), Integer.toString(heads1), Integer.toString(heads2), winner};
            writeDataToCsv("book.csv", data);
        }
        
        // determine the winner of the game based on the total scores
        String winner = "";
        if (score1 > score2) {
            winner = "Player 1";
        } else if (score2 > score1) {
            winner = "Player 2";
        } else {
            winner = "Tie";
        }
        
        // write the overall results to a CSV file
        for(int j=1;j<3;j++){
        String[] header = { "Player 1 Score", "Player 2 Score", "Winner"};
        String[] data = { Integer.toString(score1), Integer.toString(score2), winner};
        writeDataToCsv("book.csv", header);
        writeDataToCsv("book.csv", data);
        }
    }
    
    // toss a coin and return the result (0 for tails, 1 for heads)
    private static int tossCoin() {
        Random random = new Random();
        return random.nextInt(2);
    }
    
    // write an array of data to a CSV file
    private static void writeDataToCsv(String filename, String[] data) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            String line = Arrays.stream(data).collect(Collectors.joining(","));
            writer.append(line);
            writer.append("\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}


