package com.example.automata_proj;

public class AFD {
    public static final int PASS = 0;
    public static final int NOT_VALIDATED = -1;
    public static final int VALIDATED = 1;

    private String _char;
    private String[] _alphabet;
    private AFDNode _initialState;
    private AFDNode _currentState;
    private AFDNode[] _finalStates;
    private AFDTransition[] _transitions;
    private AFDNode[] _states;

    public AFD(String[] alphabet, AFDNode initialState, AFDNode finalState, AFDTransition[] transitions, AFDNode[] states) {
        _char = "";
        _alphabet = alphabet;
        _initialState = initialState;
        _currentState = initialState;
        _finalStates = new AFDNode[]{finalState};
        _transitions = transitions;
        _states = states;
    }

    public AFDNode getCurrentState() {
        return _currentState;
    }

    public AFDNode getInitialState() {
        return _initialState;
    }

    public AFDNode[] getFinalStates() {
        return _finalStates;
    }

    public AFDTransition[] getTransitions() {
        return _transitions;
    }

    public void setChar(String str) {
        _char = str;
    }

    public void setCurrentState(AFDNode state) {
        _currentState = state;
        _currentState.setActive(true);
    }

    public boolean validateString(String string) {
        setCurrentState(_initialState);

        for (int i = 0; i < string.length(); i++) {
            String character = String.valueOf(string.charAt(i));

            if (inAlphabet(character)) {
                boolean transitionFound = false;

                for (AFDTransition transition : _currentState.getTransitions()) {
                    if (transition.hasValue(character)) {
                        setCurrentState(transition.getToNode());
                        transitionFound = true;
                        break;
                    }
                }

                if (!transitionFound) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return _currentState.isFinal();
    }

    public int validateStep(String str) {
        setChar(str);
        if (inAlphabet(str)) {
            for (AFDTransition transition : _currentState.getTransitions()) {
                if (transition.hasValue(str)) {
                    setCurrentState(transition.getToNode());
                    return PASS;
                }
            }
        }

        if (_currentState.isFinal()) {
            return VALIDATED;
        }
        return NOT_VALIDATED;
    }

    public boolean inAlphabet(String str) {
        for (String character : _alphabet) {
            if (character.contains(str.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
