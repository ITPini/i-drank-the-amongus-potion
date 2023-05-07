package edu.aau.g404.core.trigger;

import java.time.LocalTime;

/**
 * TimeTrigger class represents a time-based trigger.
 * Implements the Trigger interface and checks if the current tiime is within a minute of the specified trigger time.
 */
public final class TimeTrigger implements Trigger {
    private LocalTime triggerTime;

    public TimeTrigger(LocalTime triggerTime) {
        this.triggerTime = triggerTime;
    }

    /**
     * Determines if the trigger conditions is met.
     * @return {@code true} if the current time is within a minute of the trigger time, {@code false} otherwise.
     */
    @Override
    public boolean isTriggered() {
        return LocalTime.now().isAfter(triggerTime.minusMinutes(1)) && LocalTime.now().isBefore(triggerTime.plusMinutes(1));
    }
}