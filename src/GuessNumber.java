import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    
    public static void main(String[] args) {
        run();
    }
    
    static void run(){
        Random rand = new Random();
        int numberToGuess = rand.nextInt(100)+1;
        System.out.println(numberToGuess);
        Scanner scanner = new Scanner(System.in);

        int guessedNumber = guess(scanner);
        while (guessedNumber!=numberToGuess){
            if (guessedNumber<numberToGuess){
                System.out.println("Za mało!");
            } else {
                System.out.println("Za dużo!");
            }
            guessedNumber = guess(scanner);
        }
        System.out.println("Zgadłeś!");
        
    }
    
    static boolean isNumber(String str){
        try{
            int n = Integer.parseInt(str);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
    
    static int guess (Scanner scanner){
        System.out.println("Zgadnij liczbę.");
        String guess = scanner.nextLine();
        while (!isNumber(guess)){
            System.out.println("To nie jest liczba.");
            System.out.println("Zgadnij liczbę.");
            guess = scanner.nextLine();
        }
        return Integer.parseInt(guess);
    }

}
