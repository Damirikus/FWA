package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class SessionData {
    private Long s_id;
    private Long usr_id;
    private LocalDateTime date;
    private String ip;

    public SessionData(Long usr_id, LocalDateTime date, String ip) {
        this.usr_id = usr_id;
        this.date = date;
        this.ip = ip;
    }
}
