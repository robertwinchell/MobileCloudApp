package com.intellio.tesa.events;

/**
 * Pub/Sub event used to communicate between fragment and activity.
 * Subscription occurs in the {@link com.intellio.tesa.ui.MainActivity}
 */
public class NavItemSelectedEvent {
    private int itemPosition;

    public NavItemSelectedEvent(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public int getItemPosition() {
        return itemPosition;
    }
}
