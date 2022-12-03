package ru.kpfu.itis.models;

import java.util.Objects;

public class Flat {
    private Long id;
    private String flatName;
    private String status;
    private String location;
    private int cost;

    public Flat(Long id, String flatName, String status, String location, int cost) {
        this.id = id;
        this.flatName = flatName;
        this.status = status;
        this.location = location;
        this.cost = cost;
    }

    public Flat(String flatName, String status, String location, int cost) {
        this.flatName = flatName;
        this.status = status;
        this.location = location;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", flatName='" + flatName + '\'' +
                ", status='" + status + '\'' +
                ", location='" + location + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return cost == flat.cost && id.equals(flat.id) && Objects.equals(flatName, flat.flatName) && Objects.equals(status, flat.status) && Objects.equals(location, flat.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flatName, status, location, cost);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlatName() {
        return flatName;
    }

    public void setFlatName(String flatName) {
        this.flatName = flatName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
