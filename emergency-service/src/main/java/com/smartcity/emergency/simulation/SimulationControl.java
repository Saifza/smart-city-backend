package com.smartcity.emergency.simulation;

import org.springframework.stereotype.Component;

@Component
public class SimulationControl {
    private volatile boolean simulationEnabled = true; // Default ON

    public boolean isSimulationEnabled() {
        return simulationEnabled;
    }

    public void setSimulationEnabled(boolean simulationEnabled) {
        this.simulationEnabled = simulationEnabled;
    }
}
