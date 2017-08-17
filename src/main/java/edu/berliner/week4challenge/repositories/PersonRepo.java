package edu.berliner.week4challenge.repositories;

import edu.berliner.week4challenge.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person,Long> {
}
