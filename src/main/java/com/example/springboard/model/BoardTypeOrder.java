package com.example.springboard.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BoardTypeOrder implements Serializable {
    @Id
    // @Size(max = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Size(max = 50)
    String type;

    // @Size(max = 11)
    Long menuOrder;
}
