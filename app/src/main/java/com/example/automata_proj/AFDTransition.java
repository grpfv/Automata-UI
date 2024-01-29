package com.example.automata_proj;

public class AFDTransition {
    private AFDNode fromNode;
    private AFDNode toNode;
    private String[] values;

    public AFDTransition(AFDNode fromNode, AFDNode toNode, String[] values) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.values = values;
    }

    public AFDNode getToNode() {
        return toNode;
    }

    public boolean hasValue(String charString) {
        for (String value : values) {
            if (value.equals(charString)) {
                return true;
            }
        }
        return false;
    }
}
