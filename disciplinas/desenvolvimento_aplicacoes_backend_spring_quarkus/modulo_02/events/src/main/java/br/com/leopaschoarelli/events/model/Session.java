package br.com.leopaschoarelli.events.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Integer idSession;
    @Column(name = "title", length = 255, nullable = false)
    private String title;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @ManyToOne
    @JoinColumn(name = "tbl_conference_id_conference")
    private Conference conference;

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

}
