package me.mykim.javatest.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDY_ID")
    private Long id;

    private StudyStatus studyStatus;
    private int limit;

    private String name;

    private LocalDateTime openedDateTime;


    @ManyToOne
    @JoinColumn(name = "STUDY_ID")
    private Member owner;

    public Study() {

    }

    public Study(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야한다.");
        }
        this.limit = limit;
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }

    public int getLimit() {
        return limit;
    }

    public void setStudyStatus(StudyStatus studyStatus) {
        this.studyStatus = studyStatus;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getOpenedDateTime() {
        return openedDateTime;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public void open() {
        this.studyStatus = StudyStatus.OPEN;
        this.openedDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Study{" +
                "studyStatus=" + studyStatus +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                '}';
    }
}
