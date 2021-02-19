package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DataModelRepository extends JpaRepository<DataModel, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT dm FROM DataModel dm " +
            "LEFT JOIN FETCH dm.outputFields " +
            "WHERE dm.id = :id")
    Optional<DataModel> findByIdIncludeOutputFields( Integer id);

}
