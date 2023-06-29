import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isOnRepeat = true;
        Scanner input = new Scanner(System.in);
        while(isOnRepeat) {
            System.out.println("Playing the current song");
            System.out.println("Would you like to stop the loop? Yes or No");
            String userInput = input.next();
            if (userInput.equals("Yes")) {
                isOnRepeat = false;
            } else {
                System.out.println("Enter Yes or No");
            }
        }
        System.out.println("Playing the next song");
    }
}