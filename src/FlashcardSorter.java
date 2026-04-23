import components.flashcarddeck.FlashcardDeck;
import components.flashcarddeck.FlashcardDeck1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Demonstrates organizing trivia flashcards across three decks by difficulty
 * level: unknown, learning, and mastered. Cards are moved between decks based
 * on whether the user knows them or not.
 */
public final class FlashcardSorter {

    /**
     * Private constructor to prevent instantiation.
     */
    private FlashcardSorter() {

    }

    /**
     * Moves the current card from one deck to the end of another deck.
     *
     * @param from
     *            the deck to remove the current card from
     * @param to
     *            the deck to add the card to
     * @updates from, to
     * @requires from is not empty
     * @ensures the current card of from is added to the end of to and removed
     *          from from
     *
     */
    private static void moveCard(FlashcardDeck from, FlashcardDeck to) {
        String front = from.currentFront();
        String back = from.currentBack();
        from.removeCurrentCard();
        to.addCardToEnd(front, back);
    }

    /**
     * Prints all cards in a deck with their index, front, and back text.
     *
     * @param deck
     *            the deck to print
     * @param label
     *            the label to print before the deck contents
     * @param out
     *            the output stream
     * @updates deck
     * @requires deck is not empty
     */
    private static void printDeck(FlashcardDeck deck, String label,
            SimpleWriter out) {
        out.println(label + " (" + deck.size() + " cards):");
        if (deck.isEmpty()) {
            out.println(" (empty)");
            return;
        }
        deck.resetToFirstCard();
        for (int i = 0; i < deck.size(); i++) {
            out.println(" " + (i + 1) + ". " + deck.currentFront() + " -> "
                    + deck.currentBack());
            if (i < deck.size() - 1) {
                deck.moveToNextCard();
            }
        }

    }

    /**
     * Main method demonstrating sorting flashcards across three decks by
     * difficulty: unknown, learning, and mastered.
     *
     * @param args
     *            the command line arguments (not used)
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        //Three decks by difficulty
        FlashcardDeck unknown = new FlashcardDeck1L();
        FlashcardDeck learning = new FlashcardDeck1L();
        FlashcardDeck mastered = new FlashcardDeck1L();

        //Fill unknown deck
        unknown.addCardToEnd("What is the largest ocean on Earth?",
                "The Pacific Ocean");
        unknown.addCardToEnd("How many bones are in the human body?",
                "206 bones");
        unknown.addCardToEnd("What year did the Berlin Wall fall?", "1989");
        unknown.addCardToEnd("What is the chemical symbol for gold?", "Au");
        unknown.addCardToEnd("What planet is known as the Red Planet?", "Mars");

        out.println("Initial State:");
        printDeck(unknown, "Unknown", out);
        printDeck(learning, "Learning", out);
        printDeck(mastered, "Mastered", out);

        //Move "largest ocean" to mastered
        unknown.resetToFirstCard();
        moveCard(unknown, mastered);

        //Move "bones in human body" to learning
        unknown.resetToFirstCard();
        moveCard(unknown, learning);

        //Move "Berlin Wall" to mastered
        unknown.resetToFirstCard();
        moveCard(unknown, mastered);

        out.println("After Sorting:");
        printDeck(unknown, "Unknown", out);
        printDeck(learning, "Learning", out);
        printDeck(mastered, "Mastered", out);

        //Move learning card back to review
        out.println("After Review: Moved Learning Card Back");
        learning.resetToFirstCard();
        moveCard(learning, unknown);
        printDeck(unknown, "Unknown", out);
        printDeck(learning, "Learning", out);
        printDeck(mastered, "Mastered", out);

        //Combine all decks into final deck
        FlashcardDeck review = new FlashcardDeck1L();
        while (!unknown.isEmpty()) {
            unknown.resetToFirstCard();
            moveCard(unknown, review);
        }

        while (!learning.isEmpty()) {
            learning.resetToFirstCard();
            moveCard(learning, review);

        }

        while (!mastered.isEmpty()) {
            mastered.resetToFirstCard();
            moveCard(mastered, review);
        }

        out.println("Final Combined Review Deck:");
        printDeck(review, "Review", out);
        out.close();
    }

}
