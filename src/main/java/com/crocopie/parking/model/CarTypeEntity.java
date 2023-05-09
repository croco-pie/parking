package com.crocopie.parking.model;

import com.crocopie.parking.enumerated.Size;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "car_type")
@Getter
@Setter
@NoArgsConstructor
public class CarTypeEntity {

    @Id
    private Integer id;
    private String type;
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private Size size;

}
