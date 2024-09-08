package ci.digitalacademy.monetab.models;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "address")
public class Address implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_addess;

    @Column(name = "country")
    private String country;

    @Column( name = "city")
    private String city;

    @Column( name = "street")
    private String street;

}
