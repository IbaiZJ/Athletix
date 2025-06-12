package com.athletix.model.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter 
@ToString
public class RankingDTO {
    private String profileImage;
    private String name;
    private String surname;
    private Float distance;
}
