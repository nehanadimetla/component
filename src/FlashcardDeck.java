import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Proof-of-concept for FlashcardDeck Component.
 */
public class FlashcardDeck {
    /**
     * Represents a single flashcard with a front (term) and back (definition).
     */
    private static class Card {
        /**
         * The front (term) of this card.
         */
        private String front;

        /**
         * The back (end) of this card.
         */
        private String back;

        /**
         * Constucts a new Card with the given front and back text.
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
     * The index of the current card in this deck.
     */
    private int currentIndex;

    /**
     * Constructs an empty FlashcardDeck with no cards and current position
     * index 0.
     */
    public FlashcardDeck() {
        this.deck = new Sequence1L<>();
        this.currentIndex = 0;
    }

    /**
     * Adds a new card with thte given front and back text to the end of this
     * deck.
     *
     * @param front
     *            the front (term) text of new card
     * @param back
     *            the back (definition) text of new card
     */
    public void addCardToEnd(String front, String back) {
        this.deck.add(this.deck.length(), new Card(front, back));
    }

    /**
     * Removes the current card from this deck and adjusts the current position
     * if necessary.
     */
    public void removeCurrentCard() {
        if (this.deck.length() > 0) {
            this.deck.remove(this.currentIndex);
            if (this.currentIndex >= this.deck.length()
                    && this.currentIndex > 0) {
                this.currentIndex--;
            }
        }
    }

    /**
     * Advances the current position to the next in this deck, wraps around to
     * first card if a the end.
     */
    public void moveToNextCard() {
        if (this.deck.length() > 0) {
            this.currentIndex = (this.currentIndex + 1) % this.deck.length();
        }
    }

    /**
     * Moves the current position to the previous card in this deck, wraps
     * around to the last card if at the beginning.
     */
    public void moveToPreviousCard() {
        if (this.deck.length() > 0) {
            this.currentIndex = (this.currentIndex - 1 + this.deck.length())
                    % this.deck.length();
        }
    }

    /**
     * Resets the current position to the first card in this deck.
     */
    public void resetToFirstCard() {
        this.currentIndex = 0;
    }

    /**
     * Returns the front (term) text of the current card.
     *
     * @return the front text of the current card
     */
    public String currentFront() {
        return this.deck.entry(this.currentIndex).front;
    }

    /**
     * Returns the back (defintion) text of the current card.
     *
     * @return the back text of the current card
     */
    public String currentBack() {
        return this.deck.entry(this.currentIndex).back;
    }

    /**
     * Replaces the front (term) text of the current card with the given text.
     *
     * @param newFront
     *            the new front text for the current card
     */
    public void editCurrentFront(String newFront) {
        this.deck.entry(this.currentIndex).front = newFront;
    }

    /**
     * Returns the number of cards in this deck.
     *
     * @return this.deck.length();
     */
    public int size() {
        return this.deck.length();
    }

    /**
     * Main method demonstrates the FlashcardDeck proof-of-concept.
     *
     * @param args
     */
    public static void main(String[] args) {
        //Create deck
        SimpleWriter out = new SimpleWriter1L();
        FlashcardDeck deck = new FlashcardDeck();

        //Add flashcards
        deck.addCardToEnd("Photosynthesis",
                "The process by which plants convert sunlight to energy");
        deck.addCardToEnd("Mitosis",
                "Cell division resulting in two identical daughter cells");
        deck.addCardToEnd("Osmosis",
                "Movement of water across a semipermeable membrane");

        out.println("Decl size: " + deck.size());

        //Example study deck
        for (int i = 0; i < deck.size(); i++) {
            out.println("Card " + (i + 1) + " Front: " + deck.currentFront());
            out.println("Card " + (i + 1) + " Back: " + deck.currentBack());
            deck.moveToNextCard();
        }

        //Edit card
        deck.resetToFirstCard();
        deck.editCurrentFront("Photosynthesis 2");
        out.println("After editing first card");
        out.println("Front: " + deck.currentFront());

        //Remove current card
        deck.removeCurrentCard();
        out.println("After removal, deck size: " + deck.size());
        out.println("New first card: " + deck.currentFront());

        //Go backward
        deck.moveToNextCard();
        out.println("Moved forward to: " + deck.currentFront());
        deck.moveToPreviousCard();
        out.println("Moved back to: " + deck.currentFront());

        out.close();

    }
}
