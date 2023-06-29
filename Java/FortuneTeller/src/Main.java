import com.sun.source.tree.WhileLoopTree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("Pick a number between 1 to 10");
            Scanner input = new Scanner(System.in);
            int num = input.nextInt();
            if (num >= 0 && num <= 5) {
                System.out.println("Enjoy the good luck of money and health coming to you");
            } else if (num >= 5 && num <= 10) {
                System.out.println("Enjoy the good luck of happiness and satisfaction coming to you");
            } else {
                System.out.println("Invalid Input, Choose a number between 1 and 10 ");
            }
            System.out.println("Would you like to stop the loop? Yes or No");
            String userInput = input.next();
            String posInput = "Yes";
            String negInput = "No";
            if(userInput.equals(posInput.toLowerCase())) {
                run = false;
            } else if (userInput.equals(negInput.toLowerCase())) {
                System.out.println("The loop is still on");
            } else {
                System.out.println("Enter Yes or No");
            }
        }
    }
    }
