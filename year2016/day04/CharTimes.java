package year2016.day04;

public class CharTimes {

    private char char1;
    private int numberOfTimes;

    public CharTimes(char char1) {
        this.char1 = char1;
        this.numberOfTimes = 1;
    }

    public void increaseByOne() {
        this.numberOfTimes++;
    }

    public char getChar() {
        return this.char1;
    }

    public int getNumberOfTimes() {
        return this.numberOfTimes;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CharTimes)) {
            return false;
        }
        if (((CharTimes) obj).char1 == this.char1) {
            return true;
        }
        return false;
    }
}
