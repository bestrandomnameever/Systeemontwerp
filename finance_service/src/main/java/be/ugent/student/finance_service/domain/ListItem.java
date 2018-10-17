package be.ugent.student.finance_service.domain;

public class ListItem {
    String name;
    double cost;

    public ListItem(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%10s - %.2f", this.name, this.cost);
    }
}
