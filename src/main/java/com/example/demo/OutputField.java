package com.example.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = "dataModel")
@Entity(name = "DataModelOutputField")
@Table(name = "data_model_output_field")
public class OutputField {

    @Id
    @GeneratedValue
    @Column(name = "output_field_id")
    private Integer id;

    @Column(name = "field_name")
    private String fieldName;


    @JsonBackReference(value = "outputFields")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private DataModel dataModel;

}
