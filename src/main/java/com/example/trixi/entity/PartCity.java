package com.example.trixi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "part_city")
@EqualsAndHashCode(of = "id")
public class PartCity {

    public PartCity(String code, String name, String codeOfCity){
        this.code = code;
        this.name = name;
        this.codeOfCity = codeOfCity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "codeOfCity")
    private String codeOfCity;

}
