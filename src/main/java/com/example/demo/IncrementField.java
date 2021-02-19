package com.example.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString(exclude = "dataModel")
@Table(name = "data_model_increment_field")
public class IncrementField {

    @Id
    @GeneratedValue
    @Column(name = "increment_field_id")
    private Integer id;

    @Column(name = "field_name")
    private String fieldName;

    @JsonBackReference(value = "incrementFields")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private DataModel dataModel;

}
