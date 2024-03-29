package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageInfo implements Serializable {

    private Long p_id;
    private Long usr_id;
    private String name;
    private String mime;
    private Long size;
}
