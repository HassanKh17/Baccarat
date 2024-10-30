
// TODO: Implement the Shoe class in this file
import java.util.Collections;

public class Shoe extends CardCollection {
    private final int numDecks;

    public Shoe(int numDecks) {
        if (numDecks != 6 && numDecks != 8) {
            throw new CardException("Number of decks must be 6 or 8");
        }

        this.numDecks = numDecks;
        initializeShoe();

    }

    // Intialzing the shoe
    private void initializeShoe() {
        for (int i = 0; i < numDecks; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    add(new BaccaratCard(rank, suit));
                }
            }
        }
    }

    public int size() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        if (isEmpty()) {
            throw new CardException("No cards left in shoe");
        }
        return cards.remove(0);
    }
}
