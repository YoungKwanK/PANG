package org.PANG.common.domain;

public class PositiveIntegerCounter {

    private int count;

    public PositiveIntegerCounter(int count) {
        this.count = count;
    }

    public PositiveIntegerCounter() {
        this.count = 0;
    }

    public void increaseCount() {
        this.count++;
    }

    public void decreaseCount() {
        if (count <= 0) {
            return;
        }
        this.count--;
    }

    public int getCount() {
        return count;
    }
}
