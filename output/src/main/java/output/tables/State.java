package output.tables;

public class State {
    
    public int id;
    public String name;
    public String abbreviation;
    public String region;
    public double min;
    public double max;
    public double average;
    public double standardDeviation;
    public int rank;

    public State(String name) {
        this.name = name;
    }
    
    public State(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

}