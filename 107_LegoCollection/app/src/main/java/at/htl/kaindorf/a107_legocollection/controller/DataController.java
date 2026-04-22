package at.htl.kaindorf.a107_legocollection.controller;

import java.util.ArrayList;
import java.util.Locale;

import at.htl.kaindorf.a107_legocollection.pojos.LegoSet;

public class DataController {
    private ArrayList<LegoSet> legoSets;

    public DataController() {
        this.legoSets = new ArrayList<>();
    }

    public void addLegoSet(String name) throws Exception {
        if (legoSets.contains(new LegoSet(name))) {
            throw new Exception(String.format(Locale.GERMAN, "The Legoset %s exists already!", name));
        }

        legoSets.add(new LegoSet(name));
    }

    public LegoSet getLegoSetAtPosition(int position) {
        return legoSets.get(position);
    }

    public LegoSet getLegoSetByName(String name) throws Exception {
        if (legoSets.contains(new LegoSet(name))) {
            return new LegoSet(name);
        }

        throw new Exception("There is no Legoset with that name");
    }

    public void deleteSet(int position) {
        legoSets.remove(position);
    }

    public ArrayList<LegoSet> getLegoSets() {
        return legoSets;
    }

    public void setLegoSets(ArrayList<LegoSet> legoSets) {
        this.legoSets = legoSets;
    }
}