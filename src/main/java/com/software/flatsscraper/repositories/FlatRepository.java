package com.software.flatsscraper.repositories;

import com.software.flatsscraper.models.Flat;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlatRepository extends CrudRepository<Flat, Long> {

    @Query("select f.site_id from flats f")
    List<String> findAllSiteIds();
}
