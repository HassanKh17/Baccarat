
// TODO: Implement your Baccarat simulation program here
import java.util.Scanner;

public class Baccarat {
  private static final int MIN_CARDS_IN_SHOE = 6;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Shoe shoe = new Shoe(6);
    shoe.shuffle();
    // Initializing the game stats
    int roundsPlayed = 0;
    int roundNum = 1;
    int playerWins = 0;
    int bankerWins = 0;
    int ties = 0;
    // Implementing Interactive mode
    if (args.length > 0 && (args[0].equals("-i") || args[0].equals("--interactive"))) {
      // Play rounds until there are not enough cards in the shoe
      while (shoe.size() >= MIN_CARDS_IN_SHOE) {
        System.out.println("Another round? (y/n)");
        String userInput = scanner.nextLine();
        // Check if user inputs a y to continue the game or not
        if (!userInput.toLowerCase().startsWith("y")) {
          break;
        }

        System.out.println("Round " + roundNum);
        // Deal the initial cards for the player and banker
        BaccaratHand playerHand = new BaccaratHand();
        BaccaratHand bankerHand = new BaccaratHand();
        Card playerCard1 = shoe.deal();
        Card bankerCard1 = shoe.deal();
        Card playerCard2 = shoe.deal();
        Card bankerCard2 = shoe.deal();
        playerHand.add(playerCard1);
        playerHand.add(playerCard2);
        bankerHand.add(bankerCard1);
        bankerHand.add(bankerCard2);

        System.out.println("Player: " + playerHand + "=" + playerHand.value());
        System.out.println("Banker: " + bankerHand + "=" + bankerHand.value());
        // Draw third card for the player if necessary
        if (playerHand.value() <= 5) {
          Card playerCard3 = shoe.deal();
          playerHand.add(playerCard3);
          System.out.println("Dealing third card to Player...");
          System.out.println("Player: " + playerHand + "=" + playerHand.value());
          System.out.println("Banker: " + bankerHand + "=" + bankerHand.value());
        }
        // Draw third card for the banker if necessary
        if (playerHand.value() >= 6 && bankerHand.value() <= 5) {
          Card bankerCard3 = shoe.deal();
          bankerHand.add(bankerCard3);
          System.out.println("Dealing third card to Banker...");
          System.out.println("Banker: " + bankerHand + "=" + bankerHand.value());
          System.out.println("Player: " + playerHand + "=" + playerHand.value());
        }
        // Determine winner based on hand values
        int playerScore = playerHand.value();
        int bankerScore = bankerHand.value();

        if (playerScore > bankerScore) {
          System.out.println("Player wins!");
          playerWins++;
        } else if (bankerScore > playerScore) {
          System.out.println("Banker wins!");
          bankerWins++;
        } else {
          System.out.println("Tie!");
          ties++;
        }

        roundsPlayed++;
        roundNum++;
      }

      System.out.println(roundsPlayed + " Rounds played");
      System.out.println(playerWins + " Player wins");
      System.out.println(bankerWins + " Banker wins");
      System.out.println(ties + " Ties");

      // Non interactive mode
    } else {
      // Play rounds until there are not enough cards in the shoe
      while (shoe.size() >= MIN_CARDS_IN_SHOE) {
        // Deal the initial cards for the player and banker
        BaccaratHand playerHand = new BaccaratHand();
        BaccaratHand bankerHand = new BaccaratHand();
        Card playerCard1 = shoe.deal();
        Card bankerCard1 = shoe.deal();
        Card playerCard2 = shoe.deal();
        Card bankerCard2 = shoe.deal();
        playerHand.add(playerCard1);
        playerHand.add(playerCard2);
        bankerHand.add(bankerCard1);
        bankerHand.add(bankerCard2);

        int playerCardCount = 2;
        int bankerCardCount = 2;
        // Draw additional cards for the player if necessary
        while (playerHand.value() < 6 && playerCardCount < 3) {
          Card playerCard = shoe.deal();
          playerHand.add(playerCard);
          playerCardCount++;
        }
        // check if player has a natural
        if (playerHand.isNatural()) {
          bankerWins++;
          roundsPlayed++;
        } else {
          // Draw additional cards for banker if necessary
          while (bankerHand.value() < 7 && bankerCardCount < 3) {
            Card bankerCard = shoe.deal();
            bankerHand.add(bankerCard);
            bankerCardCount++;
          }
        }
        // check if banker has a natural
        if (bankerHand.isNatural()) {
          playerWins++;
          roundsPlayed++;
        } else {
          // Determine winner based on hand values
          int playerScore = playerHand.value();
          int bankerScore = bankerHand.value();
          if (playerScore > bankerScore) {
            playerWins++;
            roundsPlayed++;
          } else if (bankerScore > playerScore) {
            bankerWins++;
            roundsPlayed++;
          } else {
            ties++;
            roundsPlayed++;
          }
        }

      }
      System.out.println(roundsPlayed + " Rounds played");
      System.out.println(playerWins + " Player wins");
      System.out.println(bankerWins + " Banker wins");
      System.out.println(ties + " Ties");

    }

  }
}
