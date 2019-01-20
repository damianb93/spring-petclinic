package com.github.damianb93.springpetclinic.repositories;

import com.github.damianb93.springpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
