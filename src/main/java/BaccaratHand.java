// TODO: Implement the BaccaratHand class in the file
public class BaccaratHand extends CardCollection {

    public BaccaratHand() {
        // Call the constructor of the CardCollection superclass
        super();
    }

    public void add(BaccaratCard card) {
        // Call the addCard() method of the CardCollection superclass
        super.add(card);
    }

    public int value() {
        int value = 0;
        int numAces = 0;
        for (Card card : this.cards) {
            BaccaratCard baccaratCard = (BaccaratCard) card;
            int cardValue = baccaratCard.value();
            if (cardValue == 1) {
                numAces++;
            }
            value += cardValue;
        }
        // If the total value is greater than 9, subtract 10
        // (this is equivalent to taking the ones digit)
        if (value > 9) {
            value -= 10;
        }
        // If there are any aces in the hand, and the total value
        // is less than or equal to 5, add 10 to the value
        if (numAces > 0 && value <= 5) {
            value += 10;
        }
        return value;
    }

    public boolean isNatural() {
        // A natural hand has two cards and a value of 8 or 9
        return (this.size() == 2 && this.value() >= 8);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : this.cards) {
            sb.append(card.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}
