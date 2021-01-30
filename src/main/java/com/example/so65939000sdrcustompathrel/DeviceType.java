package com.example.so65939000sdrcustompathrel;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Devicetype")
@Data
@NoArgsConstructor
public class DeviceType {

    /** Unique Entity Id */
    @Id
    @GeneratedValue
    private Long uid;

    /** DeviceType name */
    private String name;

    /** DeviceType description */
    private String description;

}
