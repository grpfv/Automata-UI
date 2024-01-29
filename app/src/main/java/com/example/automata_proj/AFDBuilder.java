package com.example.automata_proj;

public class AFDBuilder {
    private static final String[] LOWER_CASE_CHARACTERS = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"
    };

    private static final String[] DIGITS = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    private static final String[] SPECIAL_CHARACTERS = {
            "-", "_", "."
    };

    private static final String[] AT = {
            "@"
    };

    private static final String[] DOT = {
            "."
    };

    private String[] alphabet;
    private String[] stateNames;

    public AFDBuilder(String[] alphabet, String[] stateNames) {
        this.alphabet = alphabet;
        this.stateNames = stateNames;
    }

    public AFD build() {
        // Define states
        AFDNode q1 = new AFDNode("q1", true, false);
        AFDNode q2 = new AFDNode("q2", false, false);
        AFDNode q3 = new AFDNode("q3", false, false);
        AFDNode q4 = new AFDNode("q4", false, false);
        AFDNode q5 = new AFDNode("q5", false, false);
        AFDNode q6 = new AFDNode("q6", false, true);

        // Define transitions
        AFDTransition t12 = new AFDTransition(q1, q2, alphabet);
        AFDTransition t22 = new AFDTransition(q2, q2, concatenateArrays(alphabet, new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "-", "_", "."}));
        AFDTransition t23 = new AFDTransition(q2, q3, new String[]{"@"});
        AFDTransition t34 = new AFDTransition(q3, q4, alphabet);
        AFDTransition t44 = new AFDTransition(q4, q4, concatenateArrays(alphabet, new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "-", "_", "."}));
        AFDTransition t45 = new AFDTransition(q4, q5, new String[]{"."});
        AFDTransition t55 = new AFDTransition(q5, q5, alphabet);
        AFDTransition t56 = new AFDTransition(q5, q6, new String[]{"."});
        AFDTransition t66 = new AFDTransition(q6, q6, alphabet);

        // Set transitions for each state
        q1.setTransitions(new AFDTransition[]{t12});
        q2.setTransitions(new AFDTransition[]{t22, t23});
        q3.setTransitions(new AFDTransition[]{t34});
        q4.setTransitions(new AFDTransition[]{t44, t45});
        q5.setTransitions(new AFDTransition[]{t56});
        q6.setTransitions(new AFDTransition[]{t66});

        // Define the transitions array, initial and final states, and build the AFD
        AFDTransition[] transitions = {t12, t22, t23, t34, t44, t45, t56, t66};
        AFDNode[] states = {q1, q2, q3, q4, q5, q6};
        AFDNode initialState = q1;
        AFDNode finalState = q6;
        return new AFD(alphabet, initialState, finalState, transitions, states);
    }

    // Helper method to concatenate arrays
    private String[] concatenateArrays(String[]... arrays) {
        int totalLength = 0;
        for (String[] array : arrays) {
            totalLength += array.length;
        }

        String[] result = new String[totalLength];
        int currentIndex = 0;

        for (String[] array : arrays) {
            System.arraycopy(array, 0, result, currentIndex, array.length);
            currentIndex += array.length;
        }

        return result;
    }
}