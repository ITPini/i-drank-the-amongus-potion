package edu.aau.g404.api;

import edu.aau.g404.device.SmartLight;

/**
 * LightController interface for controlling and interacting with SmartLight devices.
 * Provides methods to update the light state and get the light class.
 */
public interface LightController {
    /**
     * Update the state of the SmartLight with the specified identifier.
     * @param identifier    The unique identifier of the SmartLight to be updated.
     * @param newLightState The new state of the SmartLight.
     */
    void updateLightState(String identifier, SmartLight newLightState);

    /**
     * Returns the SmartLight class associated with the LightCotnroller implementation.
     * @return The SmartLight class.
     */
    SmartLight getLightClass();
}