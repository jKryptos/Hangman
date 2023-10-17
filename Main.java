import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;


public class Main {

static String storedPuzzle = " "; //This is where the randomly selected puzzle is saved for future use.
static List<Character> userGuessList = new ArrayList<>(); //List of characters guessed by user.
static int correctGuessCount = 0;
static int spaceCount = 0;

    public static void main(String[] args) {
        //User is presented with 3 difficulties. Uses puzzle() for puzzle selection and playGame() to start the game loop.
        Scanner scanner = new Scanner(System.in);
        boolean puzzleChosen = false;
        int userInput = 0;

        while (!puzzleChosen) {
            System.out.println("Choose a difficulty:\n1. Easy\n2. Intermediate\n3. Hard");
            userInput = Integer.parseInt(scanner.nextLine());

            if (userInput == 1) {
                puzzleChosen = true;
                puzzle(1);
            }
            if (userInput == 2) {
                puzzleChosen = true;
                puzzle(2);
            }
            if (userInput == 3) {
                puzzleChosen = true;
                puzzle(3);
            }
            if (puzzleChosen) {
                System.out.println(" ");
                playGame();
            } else {
                System.out.println("Incorrect choice!");
            }
        }
    }
    public static void playGame(){
    //User asked to guess a letter. Uses flipper() to manipulate puzzle.
    //Check if the guess is in the puzzle and game state is checked here.
    //If the guess was incorrect, it uses drawHangman() to draw the 5 steps of the man.
        flipper(userGuessList);

        Scanner scanner = new Scanner(System.in);
        int hangmanCounter = 0;
        String userInput = " ";

        //This keeps the game running for 26 guesses, the entire alphabet.
        for (int i = 0;i < 26; i++) {
            //Checks for complete puzzle.
            if (correctGuessCount == (storedPuzzle.length() - spaceCount)) {
                System.out.println(" ");
                System.out.println("|-------------------------------------------------------------------------------------------------------|");
                System.out.println("|                                                                                                       |");
                System.out.println("|                                          YOU WIN!                                                     |");
                System.out.println("|                                                                                                       |");
                System.out.println("|-------------------------------------------------------------------------------------------------------|");
                break;
            }
            //This section is the start of every loop that the user sees, It resets the values for checking a win each pass.
            System.out.println(" ");
            System.out.println("Letters guessed: " + userGuessList);
            System.out.println("Guess a letter: ");
            userInput = scanner.nextLine();
            userGuessList.add(userInput.charAt(0));
            //Win Condition checks are reset to 0 here.
            correctGuessCount = 0;
            spaceCount = 0;

            //Adds whitespace between each loop.
            for(int whiteSpace = 0; whiteSpace <= 3;whiteSpace++){
                System.out.println(" ");
            }
            if (storedPuzzle.contains(userInput)) {
                flipper(userGuessList);
            } else {
                //Add hangman piece here.
                hangmanCounter++;
                System.out.println("Letter does not exist!");
                drawHangman(hangmanCounter);
                flipper(userGuessList);
                //Check for complete Hangman.
                if (hangmanCounter == 5){
                    System.out.println(" ");
                    System.out.println("Letters guessed: " + userGuessList);
                    System.out.println("The puzzle was: " + storedPuzzle);
                    System.out.println("|-------------------------------------------------------------------------------------------------------|");
                    System.out.println("|                                                                                                       |");
                    System.out.println("|                                         GAME OVER                                                     |");
                    System.out.println("|                                                                                                       |");
                    System.out.println("|-------------------------------------------------------------------------------------------------------|");
                    break;
                }
            }
        }
    }
    public static void flipper(List<Character> userGuessList) {
    //Counts spaces(spaceCount) and correct guesses(correctGuessCount). Manipulates the puzzle.
        for (int i = 0;i < storedPuzzle.length();i++){
            if (storedPuzzle.charAt(i) == ' '){
                System.out.print(" ");
                spaceCount++;
            } else if (userGuessList.contains(storedPuzzle.charAt(i))) {
                System.out.print(storedPuzzle.charAt(i));
                correctGuessCount++;
            } else {
                System.out.print("_");
            }
        }
    }
    public static void puzzle(int difficulty){
        //Contains the puzzles, sorted by their difficulty types.

        ArrayList<String> easyPuzzle = new ArrayList<>();
        easyPuzzle.add("hello kitty");
        easyPuzzle.add("my little pony");
        easyPuzzle.add("treehouse");
        easyPuzzle.add("chocolate");
        easyPuzzle.add("hop and a skip");
        easyPuzzle.add("cellphones");
        easyPuzzle.add("video games");
        easyPuzzle.add("big dinosaurs");
        easyPuzzle.add("butterfly");
        easyPuzzle.add("nova scotia");

        ArrayList<String> mediumPuzzle = new ArrayList<>();
        mediumPuzzle.add("this game was fun to make");
        mediumPuzzle.add("do not run with scissors");
        mediumPuzzle.add("stay away from angry chickens");
        mediumPuzzle.add("potatoes bananas gravy and mushrooms");
        mediumPuzzle.add("spongebob square pants");
        mediumPuzzle.add("whatever happened to crazy frog");
        mediumPuzzle.add("pumpkin slices on hard biscuits");
        mediumPuzzle.add("a ride on the magic school bus");
        mediumPuzzle.add("pumpernickel bread");
        mediumPuzzle.add("to infinity and beyond");

        ArrayList<String> hardPuzzle = new ArrayList<>();
        hardPuzzle.add("when running with zebras on the african savannah be sure to bring a hat");
        hardPuzzle.add("rhythm games are super fun drums are cool but guitar hero is the best");
        hardPuzzle.add("roses are red the sky is blue throw flowers in the air for something to do");
        hardPuzzle.add("oh my god the chickens are coming like a flock of locusts");
        hardPuzzle.add("these chickens are trying to get in through my windows");
        hardPuzzle.add("i wish i had a basement so i could hide from all of these darn chickens");
        hardPuzzle.add("blueberry pancakes are delicious so are chocolate chip waffles");
        hardPuzzle.add("unicorns run through the fields of narnia and battle with keebler elves");
        hardPuzzle.add("godzilla just wanted a hug and maybe some sour twizzlers");
        hardPuzzle.add("ninja backflip into the attic that trick was quick now hes back with an elastic");

        Random rand = new Random();

        if (difficulty == 1){
            //select random puzzle from easy list.
            int easyIndex = rand.nextInt(0,10);
            storedPuzzle = easyPuzzle.get(easyIndex);
        }
        if (difficulty == 2){
            //select random puzzle from medium list.
            int mediumIndex = rand.nextInt(0,10);
            storedPuzzle = mediumPuzzle.get(mediumIndex);
        }
        if (difficulty == 3){
            //select random puzzle from hard list.
            int hardIndex = rand.nextInt(0,10);
            storedPuzzle = hardPuzzle.get(hardIndex);
        }
    }
    public static void drawHangman(int hangmanCounter){
    //Draw a person as the user makes more incorrect guesses.
        if (hangmanCounter == 1){
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("                                             0                                                         ");
            System.out.println("                                                                                                       ");
            System.out.println("                                                                                                       ");
            System.out.println("                                                                                                       ");
            System.out.println("-------------------------------------------------------------------------------------------------------");
        }
        if (hangmanCounter == 2){
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("                                             0                                                         ");
            System.out.println("                                             |                                                         ");
            System.out.println("                                                                                                       ");
            System.out.println("                                                                                                       ");
            System.out.println("-------------------------------------------------------------------------------------------------------");
        }
        if (hangmanCounter == 3){
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("                                             0                                                         ");
            System.out.println("                                          ---|---                                                      ");
            System.out.println("                                                                                                       ");
            System.out.println("                                                                                                       ");
            System.out.println("-------------------------------------------------------------------------------------------------------");
        }
        if (hangmanCounter == 4){
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("                                             0                                                         ");
            System.out.println("                                          ---|---                                                      ");
            System.out.println("                                             |                                                         ");
            System.out.println("                                                                                                       ");
            System.out.println("-------------------------------------------------------------------------------------------------------");
        }
        if (hangmanCounter == 5){
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("                                             0                                                         ");
            System.out.println("                                          ---|---                                                      ");
            System.out.println("                                             |                                                         ");
            System.out.println("                                            / \\                                                       ");
            System.out.println("-------------------------------------------------------------------------------------------------------");
        }
    }
}