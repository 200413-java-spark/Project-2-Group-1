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

    public State(int id, String name, String abbreviation, String region, double min, double max, double average, double standardDeviation, int rank) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.region = region;
        this.min = min;
        this.max = max;
        this.average = average;
        this.standardDeviation = standardDeviation;
        this.rank = rank;
    }

    public int getInt(String field) {
        if (field.equals("id")) return this.id;
        if (field.equals("rank")) return this.rank;
        return -1;
    }

    public double getDouble(String field) {
        if (field.equals("min")) return this.min;
        if (field.equals("max")) return this.max;
        if (field.equals("average")) return this.average;
        if (field.equals("standardDeviation")) return this.standardDeviation;
        return -1;
    }

    public String getString(String field) {
        if (field.equals("name")) return this.name;
        if (field.equals("abbreviation")) return this.abbreviation;
        if (field.equals("region")) return this.region;
        return null;
    }

}