package model;

public enum PairAndNonPair {
    NONPAIR("1"), PAIR("0");

    private String text;

    PairAndNonPair(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
