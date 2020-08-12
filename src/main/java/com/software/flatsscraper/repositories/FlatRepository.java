package com.software.flatsscraper.repositories;

import com.software.flatsscraper.models.Flat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepository extends CrudRepository<Flat, Long> {


}
