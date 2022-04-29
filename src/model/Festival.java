package model;

public class Festival extends Event{

    private int nrOfStages;
    private int ageLimit;

    public Festival(String name, Place place, String date, String details, int nrOfStages, int ageLimit){
        super(name, place, date, details);
        this.nrOfStages = nrOfStages;
        this.ageLimit = ageLimit;
    }

    public int getNrOfStages() {
        return nrOfStages;
    }

    public void setNrOfStages(int nrOfStages) {
        this.nrOfStages = nrOfStages;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public String toString() {
        return "Festival{" +
                "nrOfStages=" + nrOfStages +
                ", ageLimit=" + ageLimit +
                '}';
    }
}
