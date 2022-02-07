package Encapsulation.SalaryIncrease;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private String name;
    private List<Person> firstTeam;
    private List<Person> reserveTeam;

    public Team(String name) {
        this.name = name;
        this.firstTeam = new ArrayList<>();
        this.reserveTeam = new ArrayList<>();
    }

    public  void addPlayer(Person person){
        if (person.getAge() < 40){
            firstTeam.add(person);
        }else {
            reserveTeam.add(person);
        }
    }

    public String getName() {
        return name;
    }

    public List<Person> getFirstTeam() {
        return Collections.unmodifiableList(this.firstTeam);
    }

    public List<Person> getReserveTeam() {
        return Collections.unmodifiableList(this.reserveTeam);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("First team have %d players", firstTeam.size()))
                .append(System.lineSeparator())
                .append(String.format("Reserve team have %d players", reserveTeam.size()));
        return sb.toString();
    }
}
