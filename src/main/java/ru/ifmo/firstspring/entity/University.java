package ru.ifmo.firstspring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class University {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(unique = true, nullable = false)
    private String universityName;

    // связь между entity классами.
    // university - имя поля в классе Student
    @OneToMany(mappedBy = "university",
            cascade = CascadeType.ALL, // .REMOVE
            orphanRemoval = true) // удаление из коллекции приведет к удалению из таблицы
    private List<Student> students = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
