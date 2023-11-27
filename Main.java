import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;


public class Main {

static String storedPuzzle = " "; //This is where the randomly selected puzzle is saved for future use.
static List<Character> userGuessList = new ArrayList<>(); //List of characters guessed by user.
static int correctGuessCount = 0;
static int spaceCount = 0;
static int winCounter = 0;
static int lossCounter = 0;
static ArrayList<String> easyPuzzle = new ArrayList<>();
static ArrayList<String> mediumPuzzle = new ArrayList<>();
static ArrayList<String> hardPuzzle = new ArrayList<>();

    public static void main(String[] args) {

        easyPuzzle.add("hello kitty island adventure");
        easyPuzzle.add("yogi bear and boo boo");
        easyPuzzle.add("treehouse or teletoon");
        easyPuzzle.add("chocolate bar");
        easyPuzzle.add("hop and a skip");
        easyPuzzle.add("cellphones listen to you");
        easyPuzzle.add("play video games");
        easyPuzzle.add("big dinosaurs");
        easyPuzzle.add("butterfly dust");
        easyPuzzle.add("nova scotia");
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

        gameStartSetUp();
    }
    public static void gameStartSetUp() {
        Scanner scanner = new Scanner(System.in);
        boolean puzzleChosen = false;
        int userInput = 0;

        while (!puzzleChosen) {
            System.out.println("Choose a difficulty:\n1. Easy\n2. Intermediate\n3. Hard");
            userInput = Integer.parseInt(scanner.nextLine());

            if (userInput == 1 || userInput == 2 || userInput == 3) {
                puzzleSelector(userInput);
                puzzleChosen = true;
            } else {
                System.out.println("Incorrect choice!");
            }
        }
        playGame();
    }
    public static void playGame(){
        puzzleManipulator(userGuessList);

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
                winCounter++;
                restartOrQuit();
            }
            //This section is the start of every loop that the user sees, It resets the values for checking a win each pass.
            System.out.println(" ");
            System.out.println("Wins: " + winCounter + " Losses: " + lossCounter);
            System.out.println("Letters guessed: " + userGuessList);
            System.out.println("Guess a letter: ");
            userInput = scanner.nextLine();
            userGuessList.add(userInput.charAt(0));
            //Win Condition checks are reset to 0 here.
            correctGuessCount = 0;
            spaceCount = 0;

            //Adds whitespace between each loop.
            for (int whiteSpace = 0; whiteSpace <= 3;whiteSpace++){
                System.out.println(" ");
            }
            if (storedPuzzle.contains(userInput)) {
                System.out.println("Letter was found!");
                puzzleManipulator(userGuessList);
            } else {
                hangmanCounter++;
                System.out.println("Letter was not found!");
                drawHangman(hangmanCounter);
                puzzleManipulator(userGuessList);
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
                    lossCounter++;
                    restartOrQuit();
                }
            }
        }
    }
    public static void puzzleManipulator(List<Character> userGuessList) {
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
    public static void puzzleSelector(int difficulty){
        Random rand = new Random();

        if (difficulty == 1){
            int easyIndex = rand.nextInt(0,10);
            storedPuzzle = easyPuzzle.get(easyIndex);
        }
        if (difficulty == 2){
            int mediumIndex = rand.nextInt(0,10);
            storedPuzzle = mediumPuzzle.get(mediumIndex);
        }
        if (difficulty == 3){
            int hardIndex = rand.nextInt(0,10);
            storedPuzzle = hardPuzzle.get(hardIndex);
        }
    }
    public static void restartOrQuit(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to play again? Yes or No?\n1. Yes\n2. No");
        int userInput = Integer.parseInt(scanner.nextLine());
        if (userInput == 1){
            userGuessList.clear();
            gameStartSetUp();
        } else {
            System.out.println("Thank you for playing!");
            System.exit(0);
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