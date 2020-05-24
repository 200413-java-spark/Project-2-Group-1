package output.tables;

public class County {
    
    public int id;
    public String name;
    public String state;
    public double min;
    public double max;
    public double average;
    public double standardDeviation;
    public int rank;

    public County(String name) {
        this.name = name;
    }

    public County(int id, String name, String state, double min, double max, double average, double standardDeviation, int rank) {
        this.id = id;
        this.name = name;
        this.state = state;
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
        if (field.equals("state")) return this.state;
        return null;
    }

}