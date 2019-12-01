import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SymulatorLOTTO {
    
    public static void main(String[] args) {
        run();
    }

    
    static void run(){
        
        System.out.println("Podaj 6 liczb z zakresu od 1 do 49.");
        Scanner scanner = new Scanner(System.in);
        //String input = scanner.nextLine();
        int[] userNumbers = new int[6];
        for (int i = 1; i <= 6; i++) {
            
            int userNumber = readNumber(scanner, i);
            for (int j = 1; j < i; j++) {
                if (userNumber == userNumbers[j-1]){
                    System.out.println("Podano drugi raz tę samą liczbę. Podaj liczbę nr " + i);
                    userNumber = readNumber(scanner, i);
                }
            }
            userNumbers[i-1] = userNumber;
        }
        Arrays.sort(userNumbers);
        System.out.print("Podane liczby: ");
        for (int number : userNumbers){
            System.out.print(number + " ");
        }
        System.out.println();
        int[] winningNumbers = generateNumbers();
        Arrays.sort(winningNumbers);
        System.out.print("Zwycięskie numery to: ");
        for (int number : winningNumbers){
            System.out.print(number + " ");
        }
        int matched = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (userNumbers[i] == winningNumbers[j]){
                    matched++;
                }
            }
        }
        System.out.println();
        if (matched >=3){
            System.out.println("Gratulacje! Trałieś " + matched + "!");
        } else {
            System.out.println("Niestety tym razem nie udało się wygrać.");
        }
        
        
    }
    
    static boolean isCorrectNumber(String str){
        try{
            int n = Integer.parseInt(str);
            return (n>0 && n<50);
        } catch (IllegalArgumentException e){
            return false;
        }
    }
    
    static int readNumber (Scanner scanner, int no){
        String input = scanner.nextLine();
        while (!isCorrectNumber(input)){
            System.out.println("Wprowadzono nieprawidłowe dane. Podaj liczbę nr " + no);
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }
    
    static int[] generateNumbers (){
        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(arr));
        int[] result = new int[6];
        for (int i = 0; i < 6; i++) {
            result[i] = arr[i];
        }
        return result;
    }

}
