package com.company;

import java.util.ArrayList;

public class TrigrammeCommun implements Comparable {

    public String mot;
    public int compteur;

    public TrigrammeCommun(String mot, int compteur) {
        this.mot = mot;
        this.compteur = compteur;
    }

    @Override
    public int compareTo(Object o) {
        return this.compteur - ((TrigrammeCommun) o).compteur;
    }
}
