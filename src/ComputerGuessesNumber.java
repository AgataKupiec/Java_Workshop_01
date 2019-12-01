import java.util.Scanner;

public class ComputerGuessesNumber {
    
    public static void main(String[] args) {
        run();
    }
    
    
    static void run(){
        System.out.println("Pomyśl liczbę od 1 do 1000.");
        int min = 1;
        int max = 1000;
    
        Scanner scanner = new Scanner(System.in);
        int guess = (max - min)/2;
        
        int response = getResponse(scanner, guess);
    
        for (int i = 0; i < 9; i++) {
            if (response < 0){
                max = guess;
            } else if (response > 0){
                min = guess;
            } else {
                System.out.println("Dzięki za grę!");
                return;
            }
            guess = (max - min)/2 + min;
            response = getResponse(scanner, guess);

        }
        System.out.println("Chyba coś oszukano mnie...");
    }
    
    static int getResponse(Scanner scanner, int guess){
        System.out.println("Mój strzał to: " + guess + ". Zgadłem? mniej/wiecej/trafiles");
        String answer = scanner.nextLine();
        while (true){
            if ("mniej".equals(answer)){
                return -1;
            } else if ("wiecej".equals(answer)){
                return 1;
            } else if ("trafiles".equals(answer)){
                return 0;
            }
            System.out.println("Nie rozumiem. Mniej, wiecej, czy trafiłem?");
            answer = scanner.nextLine();
        }
    }
}
