import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Kernel implementation of FlashcardDeck using a Sequence as the underlying
 * representation.
 *
 * @convention <pre>
 * this.deck is not null and does not contain null elements;
 * this.currentIndex is always in the range [0, this.deck.length()-1]
 * when the deck is non-empty, and 0 when the deck is empty;
 * no Card entry or its fields are null
 * </pre>
 * @correspondence <pre>
 * this = [ (this.deck.entry(i).front, this.deck.entry(i).back)
 *           for i in 0 .. this.deck.length()-1 ]
 * and this.currentCard = this.deck.entry(this.currentIndex)
 * </pre>
 */
public class FlashcardDeck1L extends FlashcardDeckSecondary {

    /**
     * Represents a single flashcard with a front (term) and back (definition).
     */
    private static final class Card {

        /**
         * The front (term) of this card.
         */
        private String front;

        /**
         * The back (definition) of this card.
         */
        private String back;

        /**
         * Constructs a new Card with the given front and back text.
         *
         * @param front
         *            the front (term) text
         * @param back
         *            the back (definition) text
         */
        Card(String front, String back) {
            this.front = front;
            this.back = back;
        }
    }

    /**
     * The sequence of cards in this deck.
     */
    private Sequence<Card> deck;

    /**
     * The zero-based index of the current card in this deck.
     */
    private int currentIndex;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.deck = new Sequence1L<>();
        this.currentIndex = 0;
    }

    /**
     * Constructs an empty FlashcardDeck with no cards and current index 0.
     */
    public FlashcardDeck1L() {
        this.createNewRep();
    }

    /**
     * Constructs a FlashcardDeck that is a copy of the given deck.
     *
     * @param source
     *            the source of copied deck
     */
    public FlashcardDeck1L(FlashcardDeck1L source) {
        this.createNewRep();
        int n = source.size();
        if (n > 0) {
            int savedIndex = source.currentIndex();
            source.resetToFirstCard();
            for (int i = 0; i < n; i++) {
                this.addCardToEnd(source.currentFront(), source.currentBack());
                if (i < n - 1) {
                    source.moveToNextCard();
                }
            }
            source.resetToFirstCard();
            for (int i = 0; i < savedIndex; i++) {
                source.moveToNextCard();
            }
        }
    }

    @Override
    public void addCardToEnd(String front, String back) {
        this.deck.add(this.deck.length(), new Card(front, back));
    }

    @Override
    public void removeCurrentCard() {
        assert this.deck.length() > 0 : "Violation of: this deck is not empty";

        this.deck.remove(this.currentIndex);
        if (this.currentIndex >= this.deck.length() && this.currentIndex > 0) {
            this.currentIndex--;
        }
    }

    @Override
    public void moveToNextCard() {
        assert this.deck.length() > 0 : "Violation of: this deck is not empty";

        this.currentIndex = (this.currentIndex + 1) % this.deck.length();
    }

    @Override
    public void moveToPreviousCard() {
        assert this.deck.length() > 0 : "Violation of: this deck is not empty";

        this.currentIndex = (this.currentIndex - 1 + this.deck.length())
                % this.deck.length();
    }

    @Override
    public String currentFront() {
        assert this.deck.length() > 0 : "Violation of: this deck is not empty";

        return this.deck.entry(this.currentIndex).front;
    }

    @Override
    public String currentBack() {
        assert this.deck.length() > 0 : "Violation of: this deck is not empty";

        return this.deck.entry(this.currentIndex).back;
    }

    @Override
    public int size() {
        return this.deck.length();
    }

    @Override
    public int currentIndex() {
        assert this.deck.length() > 0 : "Violation of: this deck is not empty";

        return this.currentIndex;
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public FlashcardDeck newInstance() {
        return new FlashcardDeck1L();
    }

    @Override
    public void transferFrom(FlashcardDeck source) {
        assert source instanceof FlashcardDeck1L : "Violation of: source is of dynamic type FlashcardDeck1L";

        FlashcardDeck1L localSource = (FlashcardDeck1L) source;
        this.deck = localSource.deck;
        this.currentIndex = localSource.currentIndex;
        localSource.createNewRep();
    }
}