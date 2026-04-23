import components.flashcarddeck.FlashcardDeck;
import components.flashcarddeck.FlashcardDeck1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A demonstration of basic FlashcardDeck operations including adding, studying,
 * editing, and removing cards.
 */
public final class BiologyDeck {

    private BiologyDeck() {

    }

    /**
     * Main method demonstrating basic FlashcardDeck usage with biology
     * flashcards.
     *
     * @param args
     *            the command line arguments (not used)
     */
    public static void main(String[] args) {
        //Create deck
        SimpleWriter out = new SimpleWriter1L();
        FlashcardDeck deck = new FlashcardDeck1L();

        //Add flashcards
        deck.addCardToEnd("Photosynthesis",
                "The process by which plants convert sunlight to energy");
        deck.addCardToEnd("Mitosis",
                "Cell division resulting in two identical daughter cells");
        deck.addCardToEnd("Osmosis",
                "Movement of water across a semipermeable membrane");

        out.println("Deck size: " + deck.size());

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