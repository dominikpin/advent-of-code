package year2024.day05;

import java.util.ArrayList;

public class Page {

    private int number;
    private ArrayList<Page> dependantPages = new ArrayList<>();

    public Page(int number) {
        this.number = number;
    }

    public int getPageNumber() {
        return this.number;
    }

    public void addDependantPage(Page page) {
        dependantPages.add(page);
    }

    public ArrayList<Page> getDependantPages() {
        return this.dependantPages;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Page)) {
            return false;
        }
        if (this.number == ((Page) obj).number) {
            return true;
        }
        return false;
    }
}
