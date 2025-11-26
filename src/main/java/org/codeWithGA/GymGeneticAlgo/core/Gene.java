package org.codeWithGA.GymGeneticAlgo.core;

import java.util.Random;

public class Gene {
    /**
     * 0 = Rest
     * 1 = Strength
     * 2 = Cardio
     * 3 = Flexibility
     */
    private int type;

    public Gene(int type) {
        if (type < 0 || type > 3) {
            throw new IllegalArgumentException("Invalid gene type: " + type);
        }
        this.type = type;
    }

    public static Gene randomGene() {
        Random random = new Random();
        return new Gene(random.nextInt(4));
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        if (type < 0 || type > 3) {
            throw new IllegalArgumentException("Invalid gene type: " + type);
        }
        this.type = type;
    }

    @Override
    public String toString() {
        switch (type) {
            case 0: return "Rest";
            case 1: return "Strength";
            case 2: return "Cardio";
            case 3: return "Flexibility";
            default: return "Unknown";
        }
    }

    public Gene copy() {
        return new Gene(this.type);
    }
}