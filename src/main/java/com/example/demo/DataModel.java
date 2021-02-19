package com.example.demo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "data_model")
public class DataModel {

    @Id
    @GeneratedValue
    @Column(name = "model_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(orphanRemoval = true, mappedBy = "dataModel", cascade = CascadeType.ALL)
    private List<OutputField> outputFields;

    @Column(name = "json_source_description")
    private String jsonSourceDescription;

    @OneToMany(orphanRemoval = true, mappedBy = "dataModel", cascade = CascadeType.ALL)
    private List<IncrementField> incrementFields;

}
