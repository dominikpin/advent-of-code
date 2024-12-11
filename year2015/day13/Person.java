package year2015.day13;

import java.util.HashMap;

public class Person {

    private String name;
    private HashMap<Person, Integer> happiness = new HashMap<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addHappiness(Person person, int happiness) {
        this.happiness.put(person, happiness);
    }

    public int getHappiness(Person person) {
        return happiness.get(person);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        if (this.name.equals(((Person) obj).name)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder happinessValues = new StringBuilder();
        for (Person person : happiness.keySet()) {
            happinessValues.append("{" + person.name + ": " + happiness.get(person) + "},");
        }
        happinessValues.deleteCharAt(happinessValues.length() - 1);
        // return String.format("%s:[%s]", this.name, happinessValues);
        return this.name;
    }
}
