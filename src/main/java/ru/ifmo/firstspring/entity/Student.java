package ru.ifmo.firstspring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private short studentAge;

    // students - имя свойства из класса Event
    @ManyToMany(mappedBy = "students")
    private List<Event> events = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private University university;

    // связи между entity классами
    // @OneToOne
    // @OneToMany - @ManyToOne
    // @ManyToMany

    // @JoinTable
    // @JoinColumn

    /*
    student
    id name age university_id
    1   rr   20     2



    university
    id name
    1    Q
    2    F
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public short getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(short studentAge) {
        this.studentAge = studentAge;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
