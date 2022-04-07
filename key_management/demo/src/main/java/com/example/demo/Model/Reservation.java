package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private Date start;
    private Date end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Key_id", nullable = false)
    private Key key;

    public Reservation() {
    }

    public Reservation(Date start, Date end, User user, Key key) {
        this.start = start;
        this.end = end;
        this.user = user;
        this.key = key;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getKey() {
        return key.getId();
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
