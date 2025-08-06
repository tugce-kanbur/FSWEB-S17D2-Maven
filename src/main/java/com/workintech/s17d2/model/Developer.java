package com.workintech.s17d2.model;

import java.util.Objects;

public class Developer {
    private int id;
    private String name;
    private double salary;
    Experience experience;
    public Developer(int id, String name, double salary, Experience experience){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id == developer.id && Double.compare(salary, developer.salary) == 0 && Objects.equals(name, developer.name) && experience == developer.experience;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, experience);
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", experince=" + experience +
                '}';
    }
}
