package manajero.manajerotdddynamicintro.Entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document(collection = "whatIf")
public class WhatIf {
    @Id
    private String id ;
    private String description ;
}
