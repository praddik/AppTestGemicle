package model;

public enum Colors {
    RED("red"),
    GREEN("green"),
    BLUE("blue");

    private String text;

    Colors(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
