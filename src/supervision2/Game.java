package supervision1;

import java.util.Random;
import java.util.Scanner;

class Difficulty {
	private int level;
	private int maxGuesses;
	public Difficulty(int level) {
		setLevel(level);
	}
	public void setLevel(int level) {
		this.level = level;
		switch (level) {
		case 1:
			maxGuesses = 20;
			break;
		case 2:
			maxGuesses = 10;
			break;
		case 3:
			maxGuesses = 7;
			break;
		default:
			maxGuesses = 10;
		}
		
	}
	
	int getMaxGuesses() {
		return maxGuesses;
	}
}

class Scoreboard {
	private int totGames;
	private int gamesWon;
	private int fewestGuesses;
	
	public Scoreboard() {
		totGames = 0;
		gamesWon = 0;
		fewestGuesses = 0;
	}
	
	public void updateScore(boolean won) {
		if (won) {
			gamesWon++;
		}
		totGames++;
	}
	
	public void updateFewestGuesses(int guess) {
		fewestGuesses = Math.min(guess,  fewestGuesses);
	}
	
	public void printStats() {
		System.out.println("You have played " + totGames + " games and have won " + gamesWon 
				+ " games. The fewest number of guesses you have used is: " + fewestGuesses);
	}
}

class Player {
	private String name;
	private Scoreboard scoreboard;
	
	public Player(String name) {
		this.setName(name);
		this.setScoreboard(new Scoreboard());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}
	
	
}

class Game {
	private int secretNumber;
    private int maxGuesses;
    private int currentGuesses;
    private Difficulty difficulty;
    private Player player;
    
    public Game(Player player, int diff) {
        this.player = player;
        this.difficulty = new Difficulty(diff);
        this.maxGuesses = this.difficulty.getMaxGuesses();
        this.currentGuesses = this.maxGuesses;

        resetGame();
    }
    
    public void resetGame() {
        Random rand = new Random();
        secretNumber = rand.nextInt(100);
        currentGuesses = this.maxGuesses;
    }
    
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean won = false;
        
        
        System.out.println("You have " + currentGuesses + " guesses left. Guess the secret number between 1 and 100");
        while (currentGuesses > 0 && !won) {
        	System.out.print("Enter guess: ");
        	int guess = scanner.nextInt();
        	
        	currentGuesses--;
        	
        	if (guess == secretNumber) {
        		System.out.println("Correct! The secret number was " + secretNumber);
        		won = true;
        		player.getScoreboard().updateScore(won);
        		player.getScoreboard().updateFewestGuesses(maxGuesses - currentGuesses);
        		player.getScoreboard().printStats();
        		
        	}
        	else if (guess < secretNumber) {
        		System.out.println("Too low!");
        	}
        	else {
        		System.out.println("Too high");
        	}
        }
        
        if (!won) {
            System.out.println("Game over! The secret number was: " + secretNumber);
        }
        
    }
    
	
	
}







