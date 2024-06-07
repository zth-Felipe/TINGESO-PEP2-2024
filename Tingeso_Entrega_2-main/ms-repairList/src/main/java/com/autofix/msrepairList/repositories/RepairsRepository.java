package com.autofix.msrepairList.repositories;

import com.autofix.msrepairList.entities.RepairsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface RepairsRepository extends JpaRepository<RepairsEntity, Long> {

    @Query(value = "SELECT r.id, r.type FROM RepairsEntity r")
    public String[] getTypes();

    public Optional<RepairsEntity> findById(Long id);

    public ArrayList<RepairsEntity> findAll();


}