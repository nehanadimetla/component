package components.flashcarddeck;

import components.standard.Standard;

/**
 * Kernel interface for a flashcard deck component.
 */
public interface FlashcardDeckKernel extends Standard<FlashcardDeck> {

    /**
     * Adds a card with the given front and back to the end of this deck.
     *
     * @param front
     *            the front (term) text
     * @param back
     *            the back (definition) text
     * @updates this
     * @ensures a new card with the given front and back is at the end of this
     *          deck.
     */
    void addCardToEnd(String front, String back);

    /**
     * Removes the current card from this deck.
     *
     * @updates this
     * @requires this deck is not empty
     * @ensures the current card is removed from this deck, and the current
     *          position adjusts if the removed card was the last card.
     */
    void removeCurrentCard();

    /**
     * Moves the current position forward one card, wrapping to the first card
     * if at the end.
     *
     * @updates this
     * @requires this deck is not empty
     * @ensures the current position advances by one, or wraps to the first
     *          card.
     */
    void moveToNextCard();

    /**
     * Moves the current position backward one card, wrapping to the last card
     * if at the beginning.
     *
     * @updates this
     * @requires this deck is not empty
     * @ensures the current position moves back by one, or wraps to the last
     *          card.
     */
    void moveToPreviousCard();

    /**
     * Returns the front text of the current card in this deck.
     *
     * @return the front text of the current card
     * @requires this deck is not empty
     * @ensures the returned string is the front text of the current card in
     *          this deck.
     */
    String currentFront();

    /**
     * Returns the back text of the current card in this deck.
     *
     * @return the back text of the current card
     * @requires this deck is not empty
     * @ensures the returned string is the back text of the current card in this
     *          deck.
     */
    String currentBack();

    /**
     * Returns the back text of the current card in this deck.
     *
     * @return the number of cards in this deck.
     * @ensures the returned string is the back text of the current card in this
     *          deck.
     */
    int size();

    /**
     * Returns a new empty instance of this component.
     *
     * @return a new empty FlashcardDeckEnhanced
     */
    @Override
    FlashcardDeck newInstance();

    /**
     * Resets this component to an empty state.
     *
     * @clears this
     */
    @Override
    void clear();

    /**
     * Transfers the content of {@code source} into this, leaving source empty.
     *
     * @param source
     *            the component to transfer from
     * @updates this
     * @clears source
     */
    @Override
    void transferFrom(FlashcardDeck source);
}
