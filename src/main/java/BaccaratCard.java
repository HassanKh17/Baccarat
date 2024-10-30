// TODO: Implement the BaccaratCard class in this file
public class BaccaratCard extends Card {
    public BaccaratCard(Rank r, Suit s) {
        super(r, s);
    }

    @Override
    public int value() {
        return super.value() == 10 ? 0 : super.value();
    }

    BaccaratCard(String name) {
        super(name);
    }

}