package com.nulpointerexception.recommendation.db;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PreferenceRepository extends CrudRepository<Preference, Long>, JpaSpecificationExecutor {
    @Override
    List findAll(Specification specification);
}
