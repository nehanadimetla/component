package components.flashcarddeck;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for secondary methods of FlashcardDeck.
 */
public class FlashcardDeckTest {

    /*
     * Tests for resetToFirstCard
     */

    /**
     * Tests that resetToFirstCard moves the current position to index 0 when
     * called from the middle of the deck.
     */
    @Test
    public void testResetToFirstCardFromMiddle() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.addCardToEnd("C", "3");
        d.moveToNextCard();
        d.moveToNextCard();
        d.resetToFirstCard();
        assertEquals(0, d.currentIndex());
        assertEquals("A", d.currentFront());

    }

    /**
     * Tests that resetToFirstCard works correctly when already at the first
     * card.
     */
    @Test
    public void testResetToFirstCardAlreadyFirst() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.resetToFirstCard();
        assertEquals(0, d.currentIndex());
        assertEquals("A", d.currentFront());
    }

    /**
     * Tests that editCurrentFront correctly replaces the front text of the
     * current card while leaving the back text unchanged.
     */
    @Test
    public void testEditCurrentFrontBasic() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("OldFront", "Back");
        d.editCurrentFront("NewFront");
        assertEquals("NewFront", d.currentFront());
        assertEquals("Back", d.currentBack());
    }

    /**
     * Tests that editCurrentFront does not change the size of the deck.
     */
    @Test
    public void testEditCurrentFrontDoesNotChangeSize() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.editCurrentFront("Z");
        assertEquals(2, d.size());
        assertEquals("Z", d.currentFront());
    }

    /**
     * Tests that editCurrentFront correctly updates the front text of a card in
     * the middle of the deck and preserves the current index.
     */
    @Test
    public void testEditCurrentFrontMiddleCard() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.addCardToEnd("C", "3");
        d.moveToNextCard();
        d.editCurrentFront("Updated");
        assertEquals("Updated", d.currentFront());
        assertEquals("2", d.currentBack());
        assertEquals(1, d.currentIndex());
    }

    /*
     * Tests for editCurrentBack
     */

    /**
     * Tests that editCurrentBack correctly replaces the back text of the
     * current card while leaving the front text unchanged.
     */
    @Test
    public void testEditCurrentBackBasic() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("Front", "OldBack");
        d.editCurrentBack("NewBack");
        assertEquals("Front", d.currentFront());
        assertEquals("NewBack", d.currentBack());
    }

    /**
     * Tests that editCurrentBack does not change the size of the deck.
     */
    @Test
    public void testEditCurrentBackDoesNotChangeSize() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.editCurrentBack("Z");
        assertEquals(2, d.size());
        assertEquals("Z", d.currentBack());
    }

    /**
     * Tests that editCurrentBack correctly updates the back text of a card in
     * the middle of the deck and preserves the current index.
     */
    @Test
    public void testEditCurrentBackMiddleCard() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        d.addCardToEnd("C", "3");
        d.moveToNextCard();
        d.editCurrentBack("Updated");
        assertEquals("B", d.currentFront());
        assertEquals("Updated", d.currentBack());
        assertEquals(1, d.currentIndex());
    }

    /*
     * Tests for isEmpty
     */

    /**
     * Tests that isEmpty returns true for a newly created empty deck.
     */
    @Test
    public void testIsEmptyTrue() {
        FlashcardDeck d = new FlashcardDeck1L();
        assertEquals(true, d.isEmpty());
    }

    /**
     * Tests that isEmpty returns false after a card has been added.
     */
    @Test
    public void testIsEmptyFalse() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        assertEquals(false, d.isEmpty());
    }

    /**
     * Tests that isEmpty returns true after the only card in the deck is
     * removed.
     */
    @Test
    public void testIsEmptyAfterRemove() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.removeCurrentCard();
        assertEquals(true, d.isEmpty());
    }

    /*
     * Tests for equals
     */

    /**
     * Tests that two decks with the same cards in the same order are equal.
     */
    @Test
    public void testEqualsSameCards() {
        FlashcardDeck d1 = new FlashcardDeck1L();
        FlashcardDeck d2 = new FlashcardDeck1L();
        d1.addCardToEnd("A", "1");
        d2.addCardToEnd("A", "1");
        assertEquals(true, d1.equals(d2));
    }

    /**
     * Tests that two empty decks are equal.
     */
    @Test
    public void testEqualsBothEmpty() {
        FlashcardDeck d1 = new FlashcardDeck1L();
        FlashcardDeck d2 = new FlashcardDeck1L();
        assertEquals(true, d1.equals(d2));
    }

    /**
     * Tests that two decks with different cards are not equal.
     */
    @Test
    public void testNotEqualsDifferentCards() {
        FlashcardDeck d1 = new FlashcardDeck1L();
        FlashcardDeck d2 = new FlashcardDeck1L();
        d1.addCardToEnd("A", "1");
        d2.addCardToEnd("B", "2");
        assertEquals(false, d1.equals(d2));
    }

    /**
     * Tests that two decks with different sizes are not equal.
     */
    @Test
    public void testNotEqualsDifferentSizes() {
        FlashcardDeck d1 = new FlashcardDeck1L();
        FlashcardDeck d2 = new FlashcardDeck1L();
        d1.addCardToEnd("A", "1");
        d1.addCardToEnd("B", "2");
        d2.addCardToEnd("A", "1");
        assertEquals(false, d1.equals(d2));
    }

    /*
     * Tests for toString
     */

    /**
     * Tests that toString returns the correct string for an empty deck.
     */
    @Test
    public void testToStringEmpty() {
        FlashcardDeck d = new FlashcardDeck1L();
        assertEquals("FlashcardDeck[\n]", d.toString());
    }

    /**
     * Tests that toString returns the correct string for a deck with one card.
     */
    @Test
    public void testToStringOneCard() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("Hello", "World");
        assertEquals("FlashcardDeck[\n  0: front=\"Hello\" back=\"World\"\n]",
                d.toString());
    }

    /**
     * Tests that toString returns the correct string for a deck with multiple
     * cards.
     */
    @Test
    public void testToStringMultipleCards() {
        FlashcardDeck d = new FlashcardDeck1L();
        d.addCardToEnd("A", "1");
        d.addCardToEnd("B", "2");
        assertEquals("FlashcardDeck[\n  0: front=\"A\" back=\"1\"\n"
                + "  1: front=\"B\" back=\"2\"\n]", d.toString());
    }

}
