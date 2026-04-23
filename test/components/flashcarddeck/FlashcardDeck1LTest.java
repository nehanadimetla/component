package components.flashcarddeck;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for kernel methods of FlashcardDeck1L.
 */
public class FlashcardDeck1LTest {

    /*
     * Tests for addCardToEnd
     */

    /**
     * Tests that addCardToEnd increments the size of the deck by one.
     */
    @Test
    public void testAddCardToEndSizeIncrements() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        assertEquals(1, d.size());
    }

    /**
     * Tests that addCardToEnd correctly adds multiple cards and updates size.
     */
    @Test
    public void testAddCardToEndMultiple() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.addCardToEnd("C", "3");
        assertEquals(3, d.size());
    }

    /**
     * Tests that the last card added is accessible at the end of the deck.
     */
    @Test
    public void testAddCardToEndLastCard() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.moveToNextCard();
        assertEquals("B", d.currentFront());
        assertEquals("2", d.currentBack());
    }

    /*
     * Tests for removeCurrentCard
     */

    /**
     * Tests that removeCurrentCard decrements the size of the deck by one.
     */
    @Test
    public void testRemoveCurrentCardSizeDecrements() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.removeCurrentCard();
        assertEquals(1, d.size());
    }

    /**
     * Tests that removing the first card makes the next card the current card
     * at index 0.
     */
    @Test
    public void testRemoveCurrentCardFirst() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.removeCurrentCard();
        assertEquals("B", d.currentFront());
        assertEquals(0, d.currentIndex());
    }

    /**
     * Tests that removing the last card adjusts the current index to the new
     * last card.
     */
    @Test
    public void testRemoveCurrentCardLast() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.moveToNextCard();
        d.removeCurrentCard();
        assertEquals(1, d.size());
        assertEquals(0, d.currentIndex());
        assertEquals("A", d.currentFront());
    }

    /**
     * Tests that removing the only card results in an empty deck.
     */
    @Test
    public void testRemoveUntilEmpty() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.removeCurrentCard();
        assertEquals(0, d.size());
    }

    /*
     * Tests for moveToNextCard
     */

    /**
     * Tests that moveToNextCard advances the current index by one.
     *
     */
    @Test
    public void testMoveToNextCardBasic() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.moveToNextCard();
        assertEquals(1, d.currentIndex());
        assertEquals("B", d.currentFront());
    }

    /**
     * Tests that moveToNextCard wraps around to the first card when called from
     * the last card.
     */
    @Test
    public void testMoveToNextCardWraps() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.moveToNextCard();
        d.moveToNextCard();
        assertEquals(0, d.currentIndex());
        assertEquals("A", d.currentFront());
    }

    /*
     * Tests for moveToPreviousCard
     */

    /**
     * Tests that moveToPreviousCard moves the current index back by one.
     */
    @Test
    public void testMoveToPreviousCardBasic() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.moveToNextCard();
        d.moveToPreviousCard();
        assertEquals(0, d.currentIndex());
        assertEquals("A", d.currentFront());
    }

    /**
     * Tests that moveToPreviousCard wraps around to the last card when called
     * from the first card.
     */
    @Test
    public void testMoveToPreviousCardWraps() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.moveToPreviousCard();
        assertEquals(1, d.currentIndex());
        assertEquals("B", d.currentFront());
    }

    /*
     * Tests for currentFront and currentBack
     */

    /**
     * Tests that currentFront and currentBack return the correct values for the
     * current card.
     */
    @Test
    public void testCurrentFrontAndBack() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("Term", "Definition");
        assertEquals("Term", d.currentFront());
        assertEquals("Definition", d.currentBack());
    }

    /**
     * Tests that calling currentFront does not mutate the deck.
     */
    @Test
    public void testCurrentFrontDoesNotMutate() {
        FlashcardDeck d = new FlashcardDeck1L();
        FlashcardDeck dCopy = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        dCopy.addCardToEnd("A", "1");
        d.currentFront();
        assertEquals(dCopy, d);
    }

    /**
     * Tests that calling currentBack does not mutate the deck.
     */
    @Test
    public void testCurrentBackDoesNotMutate() {
        FlashcardDeck d = new FlashcardDeck1L();
        FlashcardDeck dCopy = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        dCopy.addCardToEnd("A", "1");
        d.currentBack();
        assertEquals(dCopy, d);
    }

    /*
     * Tests for size
     */

    /**
     * Tests that size returns 0 for a newly created empty deck.
     */
    @Test
    public void testSizeEmpty() {
        FlashcardDeck d = new FlashcardDeck1L();
        assertEquals(0, d.size());
    }

    /**
     * Tests that size returns the correct count after multiple cards are added.
     */
    @Test
    public void testSizeAfterAdds() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        assertEquals(2, d.size());
    }

    /**
     * Tests that calling size does not mutate the deck.
     */
    @Test
    public void testSizeDoesNotMutate() {
        FlashcardDeck d = new FlashcardDeck1L();
        FlashcardDeck dCopy = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        dCopy.addCardToEnd("A", "1");
        d.size();
        assertEquals(dCopy, d);
    }

    /*
     * Tests for currentIndex
     */

    /**
     * Tests that currentIndex starts at 0 for a newly created deck.
     */
    @Test
    public void testCurrentIndexStartsAtZero() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        assertEquals(0, d.currentIndex());
    }

    /**
     * Tests that currentIndex returns the correct value after moving forward.
     */
    @Test
    public void testCurrentIndexAfterMove() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.moveToNextCard();
        assertEquals(1, d.currentIndex());
    }

    /*
     * Tests for clear
     */

    /**
     * Tests that clear removes all cards from the deck.
     */
    @Test
    public void testClearEmptiesDeck() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.clear();
        assertEquals(0, d.size());
    }

    /**
     * Tests that clear works correctly on an already empty deck.
     */
    @Test
    public void testClearAlreadyEmpty() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.clear();
        assertEquals(0, d.size());
    }

    /*
     * Tests for newInstance
     */

    /**
     * Tests that newInstance returns a new empty deck.
     */
    @Test
    public void testNewInstanceIsEmpty() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        FlashcardDeck d2 = d.newInstance();
        assertEquals(0, d2.size());
    }

    /**
     * Tests that calling newInstance does not affect the original deck.
     */
    @Test
    public void testNewInstanceDoesNotAffectOriginal() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.newInstance();
        assertEquals(1, d.size());
    }

    /*
     * Tests for transferFrom
     */

    /**
     * Tests that transferFrom moves all cards from the source to the
     * destination deck.
     *
     */
    @Test
    public void testTransferFromMovesCards() {
        FlashcardDeck source = new FlashcardDeck1L();
        FlashcardDeck dest = new FlashcardDeck1L();
        source.addCardToEnd("A", "1");
        source.addCardToEnd("B", "2");
        dest.transferFrom(source);
        assertEquals(2, dest.size());
        assertEquals("A", dest.currentFront());
    }

    /**
     * Tests that transferFrom leaves the source deck empty after the transfer.
     */
    @Test
    public void testTransferFromClearsSource() {
        FlashcardDeck source = new FlashcardDeck1L();
        FlashcardDeck dest = new FlashcardDeck1L();
        source.addCardToEnd("A", "1");
        dest.transferFrom(source);
        assertEquals(0, source.size());
    }

}
