/**
 * Layered implementation of the FlashcardDeck component.
 */

public abstract class FlashcardDeckSecondary implements FlashcardDeck {

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder("FlashcardDeck[\n");
        int n = this.size();
        if (n > 0) {
            int startIndex = this.currentIndex();
            this.resetToFirstCard();
            for (int i = 0; i < n; i++) {
                sb.append("  ").append(i).append(": front=\"")
                        .append(this.currentFront()).append("\" back=\"")
                        .append(this.currentBack()).append("\"\n");
                if (i < n - 1) {
                    this.moveToNextCard();
                }
            }
            this.resetToFirstCard();
            for (int i = 0; i < startIndex; i++) {
                this.moveToNextCard();
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlashcardDeck)) {
            return false;
        }
        FlashcardDeck other = (FlashcardDeck) obj;
        int n = this.size();
        if (n != other.size()) {
            return false;
        }
        if (n == 0) {
            return true;
        }

        int thisStart = this.currentIndex();
        int otherStart = other.currentIndex();

        this.resetToFirstCard();
        other.resetToFirstCard();
        boolean equal = true;
        for (int i = 0; i < n && equal; i++) {
            if (!this.currentFront().equals(other.currentFront())
                    || !this.currentBack().equals(other.currentBack())) {
                equal = false;
            }
            if (i < n - 1) {
                this.moveToNextCard();
                other.moveToNextCard();
            }
        }

        this.resetToFirstCard();
        for (int i = 0; i < thisStart; i++) {
            this.moveToNextCard();
        }
        other.resetToFirstCard();
        for (int i = 0; i < otherStart; i++) {
            other.moveToNextCard();
        }

        return equal;
    }

    @Override
    public final int hashCode() {
        return this.size();
    }

    @Override
    public final void resetToFirstCard() {
        while (this.currentIndex() != 0) {
            this.moveToPreviousCard();
        }
    }

    @Override
    public final void editCurrentFront(String newFront) {
        String oldBack = this.currentBack();
        int index = this.currentIndex();
        this.rebuildWithReplacement(index, newFront, oldBack);
    }

    @Override
    public final void editCurrentBack(String newBack) {
        String oldFront = this.currentFront();
        int index = this.currentIndex();
        this.rebuildWithReplacement(index, oldFront, newBack);
    }

    @Override
    public final boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Rebuilds the deck replacing the card at {@code index} with
     * {@code newFront} and {@code newBack}, restoring position to
     * {@code index}.
     *
     * @param index
     *            the position of the card to replace
     * @param newFront
     *            the replacement front text
     * @param newBack
     *            the replacement back text
     * @updates this
     * @requires 0 <= index < this.size()
     * @ensures this.size() = #this.size() and this.currentIndex() = index and
     *          [all cards unchanged except the card at index, which has
     *          newFront/newBack]
     */
    private void rebuildWithReplacement(int index, String newFront,
            String newBack) {
        int n = this.size();
        String[] fronts = new String[n];
        String[] backs = new String[n];

        this.resetToFirstCard();
        for (int i = 0; i < n; i++) {
            fronts[i] = this.currentFront();
            backs[i] = this.currentBack();
            if (i < n - 1) {
                this.moveToNextCard();
            }
        }

        fronts[index] = newFront;
        backs[index] = newBack;

        this.resetToFirstCard();
        for (int i = 0; i < n; i++) {
            this.removeCurrentCard();
        }

        for (int i = 0; i < n; i++) {
            this.addCardToEnd(fronts[i], backs[i]);
        }

        this.resetToFirstCard();
        for (int i = 0; i < index; i++) {
            this.moveToNextCard();
        }

    }
}
