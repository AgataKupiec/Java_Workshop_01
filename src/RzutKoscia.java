import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.Scanner;

public class RzutKoscia {
    
    public static void main(String[] args) {
        run();
    }
    
    static void run() {
        System.out.println("Podaj rodzaj kostki i liczbę rzutów w formacie xDy+z, gdzie:");
        System.out.println("Format: xDy+z, gdzie:");
        System.out.println("x - liczba rzutów");
        System.out.println("y - rodzaj kostki (liczba ścian)");
        System.out.println("z - (opcjonalnie) liczba, która ma być dodana/odjęta od wyniku");
        Scanner scanner = new Scanner(System.in);
        read(scanner);
        
    }
    
    static void read(Scanner scanner) {
        String scanned = scanner.nextLine();
        String trimmed = StringUtils.deleteWhitespace(scanned);
        StringBuilder line = new StringBuilder(trimmed);
        
        int numberOfThrows = 1;
        int diceType = 0;
        int addedNumber = 0;
        
        // Sprawdzam, czy wprowadzono 'D'
        if (!StringUtils.contains(line, 'D')) {
            System.out.println("Niepoprawna wartość (brak D).");
            return;
        }
        int indexD = line.indexOf("D");
        int indexPlus;
        
        //Sprawdzenie, czy wprowadzono +/-. Jeśli tak, zapisuję indeks znaku w zmiennej indeksPlus.
        //Sprawdzana jest reszta napisu występująca po znaku. Jeśli jest liczbą, jest zapisywana do zmiennej addedNumber.
        //Jeśli nie ma znaku +/-, do indeksPlus przypisywana jest wartość line.length + 1
        if (StringUtils.contains(line, '+')) {
            indexPlus = line.indexOf("+");
            if (indexPlus != line.length() - 1) {
                String number = line.substring(indexPlus + 1, line.length());
                if (isNumber(number)) {
                    addedNumber = Integer.parseInt(number);
                } else {
                    System.out.println("Niepoprawny numer po znaku +.");
                    return;
                }
            } else {
                System.out.println("Niepoprawna wartość (+ na końcu).");
                return;
            }
        } else if (StringUtils.contains(line, '-')) {
            indexPlus = line.indexOf("-");
            if (indexPlus != line.length() - 1) {
                String number = line.substring(indexPlus + 1, line.length());
                if (isNumber(number)) {
                    addedNumber = Integer.parseInt(number);
                    addedNumber *= -1;
                } else {
                    System.out.println("Niepoprawny numer po znaku -.");
                    return;
                }
            } else {
                System.out.println("Niepoprawna wartość (- na końcu).");
                return;
            }
        } else {
            indexPlus = line.length();
        }
        
        //Sprawdzenie, czy są znaki po 'D'
        if (indexD == line.length() - 1) {
            System.out.println("Niepoprawna wartość (D jest ostatnim znakiem).");
            return;
        }
        
        //Sprawdzanie, czy są znaki przed 'D' i czy są liczbą. Liczba zapisywana jest do zmiennej numberOfThrows
        if (indexD > 0) {
            String howManyThrows = line.substring(0, indexD);
            if (isNumberGreaterThanZero(howManyThrows)) {
                numberOfThrows = Integer.parseInt(howManyThrows);
            } else {
                System.out.println("Niepoprawna wartość (przed D).");
                return;
            }
        }
        
        //Sprawdzenie, czy znaki między 'D' a '+/-' są liczbą ze zbioru dozwolonych rodzajów kostek.
        //Rodzaj kostki przypisywany jest do zmiennej diceType.
        if (isNumberGreaterThanZero(line.substring(indexD + 1, indexPlus))) {
            int number = Integer.parseInt(line.substring(indexD + 1, indexPlus));
            int[] dices = {3, 4, 6, 8, 10, 12, 20, 100};
            for (int dice : dices) {
                if (number == dice) {
                    diceType = dice;
                }
            }
        } else {
            System.out.println("Niepoprawny typ kości");
            return;
        }
        
        //Sprawdzenie, czy pierwotnie przypisana wartość zmiennej diceType (0) została zmieniona.
        if (diceType == 0) {
            System.out.println("Niepoprawna typ kości");
            return;
        }
        
        int myRoll = rollTheDice(diceType, numberOfThrows, addedNumber);
        System.out.println();
        System.out.println("Wyrzuciłeś: " + myRoll);
    }
    
    static int rollTheDice(int diceType, int numberOfThrows, int addedNumber) {
        Random rand = new Random();
        int sum = 0;
        for (int i = 1; i <= numberOfThrows; i++) {
            int roll = rand.nextInt(diceType) + 1;
            sum += roll;
            System.out.println("Wyrzucono: " + roll);
        }
        sum += addedNumber;
        return sum;
    }
    
    private static boolean isNumber(String substring) {
        try {
            int n = Integer.parseInt(substring);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    private static boolean isNumberGreaterThanZero(String howManyThrows) {
        try {
            int n = Integer.parseInt(howManyThrows);
            return n > 0;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
