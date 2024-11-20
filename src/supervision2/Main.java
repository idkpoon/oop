package supervision1;

import java.util.Scanner;

public class Main {
	
	 public static void main(String[] args) {
	      Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter your name: ");
	        String playerName = scanner.nextLine();
	        Player player = new Player(playerName);

	        System.out.print("Choose difficulty 1,2,3 with 1 being the easiest: ");
	        int difficultyLevel = scanner.nextInt();

	        Game game = new Game(player, difficultyLevel);
	        game.startGame();

	        System.out.print("Play again? (y/n): ");
	        
	        String rematch = scanner.nextLine();
	        while (rematch.equalsIgnoreCase("y")) {
	            game.resetGame();
	            game.startGame();
		        System.out.print("Play again? (y/n): ");
	            rematch = scanner.nextLine();
	        }

	        System.out.println("Thanks for playing!");
	        scanner.close();
	        
	 }
	 

}


