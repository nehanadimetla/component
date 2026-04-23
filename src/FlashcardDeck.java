/**
 * Enhanced interface for a flashcard deck component.
 */
public interface FlashcardDeck extends FlashcardDeckKernel {
    /**
     * Resets the current position to the first card in this deck.
     *
     * @updates this
     * @requires this deck is not empty
     * @ensures the current position is the first card in this deck
     */
    void resetToFirstCard();

    /**
     * Replaces the front text of the current card with {@code newFront}.
     *
     * @param newFront
     *            the new front text
     * @updates this
     * @requires this deck is not empty
     * @ensures the front text of the current card is {@code newFront}, and
     *          everything else in the deck is unchanged.
     *
     */
    void editCurrentFront(String newFront);

    /**
     * Replaces the back text of the current card with {@code newBack}.
     *
     * @param newBack
     *            the new back text
     * @updates this
     * @requires this deck is not empty
     * @ensures the back text of the current card is {@code newBack}, and
     *          everything else in the deck is unchanged.
     */
    void editCurrentBack(String newBack);

    /**
     * Returns true if this deck has no cards, false otherwise.
     *
     * @return true if and only if the size of the deck is zero
     */
    boolean isEmpty();

    /**
     * Returns the zero-based index of the current card in this deck.
     *
     * @return the current position index
     * @requires this deck is not empty
     * @ensures the returned value is the zero-based index of the current card
     */
    int currentIndex();
}
