import java.util.Scanner;

public class Main {
    // Create a question with three answer choices (one should be correct)
    //Ask the question to the user
    //Output answer choices to the user
    //Collect the user's input(that is the answer they provide)
    // If the user is correct print out a congratulation message
    //If the user is incorrect print out an incorrect message as well the correct answer

    public static void main(String[] args) {
        String question = "What is the capital of France ?";
        String choiceOne = "Kinshasa";
        String choiceTwo = "Paris";
        String choiceThree = "Pretoria";

        String correctAnswer = choiceTwo;

        // Write a print statement asking the question
        System.out.println(question);
        // Write a print statement giving the answer choices
        System.out.println("1. " + choiceOne + " " + "2. " + choiceTwo + " "
                + "3. " + choiceThree + " ");
        System.out.println("Choose between Kinshasa, Paris, and Pretoria");
        // Have the user input an answer
        Scanner input = new Scanner(System.in);
        // Retrieve the user's input
        String userInput =  input.next();
        // If the user's input matches the correctAnswer...
        if (correctAnswer.equals(userInput.toLowerCase())) {
            // then the user is correct and we want to print out a congrats message to the user.
            System.out.println("Congratulation that is the correct answer");
        } else {
            // If the user's input does not match the correctAnswer...
            // then the user is incorrect and we want to print out a message saying that the user is incorrect as well as what the correct choice was.
            System.out.println("Sorry, that is not the correct answer, The correct answer is " + correctAnswer);
        }
    }
}