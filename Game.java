/**
 * input format
 * FlightSimple 10 RRRUULLLDL
 * FlightSimple: Lost
 * RandomWalk 11 RRRUURDLDDR
 * RandomWalk: Game Over
 * End
 */


// Interface for the Game
package game;
import java.util.Scanner;
interface Games {
    void start();                         // Start the game
    String processAction(char action);    // Process a player action ('L', 'R', 'U', 'D')
    boolean isGameOver();                 // Check if the game is over
}

// Game implementation for FlightSimple
class FlightSimple implements Games {
    private int speed;
    private int altitude;
    private int maxKeystrokes;
    private int keystrokes;
    private boolean started;

    public FlightSimple(int maxKeystrokes) {
        this.speed = 0;
        this.altitude = 0;
        this.maxKeystrokes = maxKeystrokes;
        this.keystrokes = 0;
        this.started = false;
    }

    @Override
    public void start() {
        started = true;
        //System.out.println("FlightSimple Game Started");
        //System.out.println("Initial Speed: " + speed + ", Altitude: " + altitude);
    }

    @Override
    public String processAction(char action) {
        if (!started) {
            return "None";
        }

        if (isGameOver()) {
            return "Game Over";
        }


        if (action == 'R') {
            // Increase speed by 1 unit
            speed++;
        } else if (action == 'L') {
            // Reduce speed by 1 unit (cannot go below 0)
            if (speed > 0) {
                speed--;
                // Plane will crash if speed decreases below zero in height
                if (altitude > 0 && speed == 0) {
                    return "Lost";
                }
            }
        } else if (action == 'U') {
            // Increase altitude by 1 unit (if speed > 2)
            if (speed > 2) {
                altitude++;
            }
        } else if (action == 'D') {
            // Reduce altitude by 1 unit
            altitude--;
            if (altitude < 0) {
                return "Lost";
            }
        }


        keystrokes++;

        if (altitude == 0 && speed == 0) {
            return "Won";
        } else if (keystrokes >= maxKeystrokes) {
            return "Game Over";
        } else {
            return "None";
        }
    }


    @Override
    public boolean isGameOver() {
        return altitude < 0 || keystrokes >= maxKeystrokes;
    }
}

// Game implementation for RandomWalk
class RandomWalk implements Games {
    private int row;
    private int col;
    private int maxKeystrokes;
    private int keystrokes;
    private boolean gameStarted;

    public RandomWalk(int maxKeystrokes) {
        this.row = 10;
        this.col = 10;
        this.maxKeystrokes = maxKeystrokes;
        this.keystrokes = 0;
        this.gameStarted = false;
    }

    @Override
    public void start() {
        // Initialize the game
        row = 10;
        col = 10;
        keystrokes = 0;
        gameStarted = true;
    }

    @Override
    public String processAction(char action) {
        if (!gameStarted) {
            return "None"; // Game hasn't started yet
        }

        if (isGameOver()) {
            return "Game Over";
        }

        if (action == 'R' && col < 19) {
            col++;
        } else if (action == 'L' && col > 0) {
            col--;
        } else if (action == 'U' && row > 0) {
            row--;
        } else if (action == 'D' && row <= 19) {
            row++;
        }

        keystrokes++;

        if (row > 19) {
            return "Won";
        } else if ( row<=0 || col > 19) {
            return "Lost";
        } else if (keystrokes >= maxKeystrokes) {
            //System.out.println(maxKeystrokes);
            return "Game Over";
        } else {
            //System.out.println(row+" "+col);
            return "None";
        }
    }

    @Override
    public boolean isGameOver() {
        return row <= 0 || col > 19 || keystrokes > maxKeystrokes;
    }
}



// Console class
class GameConsole {
    public String playGame(Games game, String keys) {
        game.start(); // Start the game
        for (char action : keys.toCharArray()) {
            String result = game.processAction(action);
            if (!result.equals("None")) {
                return result;
            }
        }
        return game.isGameOver() ? "Game Over" : "None";
    }
}

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameConsole console = new GameConsole();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty() || line.equals("End")) {
                break;
            }

            String[] parts = line.split(" ");
            if (parts.length >= 2) {
                String gameName = parts[0];
                int maxKeystrokes = Integer.parseInt(parts[1]);

                if (parts.length >= 3) {
                    String keys = parts[2];

                    if (gameName.equals("FlightSimple")) {
                        Games flightGame = new FlightSimple(maxKeystrokes);
                        String result = console.playGame(flightGame, keys);
                        System.out.println("FlightSimple: " + result);
                    } else if (gameName.equals("RandomWalk")) {
                        Games randomWalkGame = new RandomWalk(maxKeystrokes);
                        String result = console.playGame(randomWalkGame, keys);
                        System.out.println("RandomWalk: " + result);
                    }
                }
            }
        }

        scanner.close();
    }


}

