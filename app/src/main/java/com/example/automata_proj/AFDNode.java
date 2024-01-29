package com.example.automata_proj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AFDNode {
    private String name;
    private boolean initialState;
    private boolean finalState;
    private boolean isActive;
    private List<AFDTransition> transitions;

    public AFDNode(String name, boolean initialState, boolean finalState) {
        this.name = name;
        this.initialState = initialState;
        this.finalState = finalState;
        this.isActive = false;
        this.transitions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean isInitialState() {
        return initialState;
    }

    public boolean isFinalState() {
        return finalState;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<AFDTransition> getTransitions() {
        return transitions;
    }

    public void setTransitions(AFDTransition[] transitions) {
        this.transitions = new ArrayList<>(Arrays.asList(transitions));
    }

    public boolean isFinal() {
        return finalState;
    }
}
