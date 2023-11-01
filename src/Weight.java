import java.time.LocalDate;

public class Weight {
    private double weight;
    private LocalDate date;

    public Weight(double weight, LocalDate date) {
        this.weight = weight;
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return weight+" "+date;
    }
}
