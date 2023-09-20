package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionData implements Serializable {
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
