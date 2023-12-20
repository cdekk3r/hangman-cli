package org.example;

import org.example.services.RandomWordService;

import java.util.Scanner;

public class App {
    private final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        printMainMenu();
        int mainMenuSelection = promptForMenuSelection("Please choose an option: ");

        if(mainMenuSelection == 1) {
            String word = generateWord();
            String spaces = createSpaces();
            System.out.println(spaces);

            int count = word.length();
            String wrongGuesses = "";

            while(true) {

                if(spaces.equals(word)) {
                    System.out.println("You've won!");
                    System.exit(0);
                }

                String letterSelection = promptForLetterSelection("Guess a letter: ");

                if(letterSelection.length() > 1 || letterSelection == "" || Character.isWhitespace(letterSelection.charAt(0)) || Character.isDigit(letterSelection.charAt(0))){
                    System.out.println("Please choose a letter.");
                    continue;
                } else if (spaces.contains(letterSelection) || wrongGuesses.contains(letterSelection)) {
                    System.out.println("You already guessed this letter. Guess again.");
                } else if(!word.contains(letterSelection)) {
                    count--;
                    wrongGuesses += letterSelection;
                    System.out.println("Wrong! " + count + " guesses left!");
                    if(count == 0) {
                        System.out.println("Game Over! The word was " + word);
                        break;
                    }
                } else {

                    System.out.println("Correct! Guess again.");
                    String updatedSpaces = "";

                    for(int i = 0; i<word.length(); i++) {
                        if(word.charAt(i) == letterSelection.charAt(0)) {
                            updatedSpaces += letterSelection;
                        } else {
                            updatedSpaces += spaces.charAt(i);
                        }
                    }
                    spaces = updatedSpaces;
                }
                System.out.println(spaces);
            }
        } else if (mainMenuSelection == 0){
            System.exit(0);
        }
    }

    private void printMainMenu() {
        System.out.println("1: New Game");
        System.out.println("0: Exit");
        System.out.println();
    }

    private int promptForMenuSelection(String prompt) {
        System.out.print(prompt);
        int menuSelection;
        try {
            menuSelection = Integer.parseInt(keyboard.nextLine());
        } catch (NumberFormatException e){
            menuSelection = 0;
        }
        return menuSelection;
    }

    private String promptForLetterSelection(String prompt) {
        System.out.print(prompt);
        String letterSelection ="";
        try {
            letterSelection = keyboard.nextLine();
        }
        catch (Exception e) {
            System.out.println("Please choose a letter.");
        }
        return letterSelection.toLowerCase();
    }

    private String generateWord() {
        RandomWordService service = new RandomWordService();
        String word = service.getWord();
        word = word.substring(2, word.length()-2);
        return word;
    }

    private String createSpaces() {
        String word = generateWord();
        int length = word.length();
        String spaces = "";
        while(length > 0) {
            spaces += "_";
            length--;
        }
        return spaces;
    }

}