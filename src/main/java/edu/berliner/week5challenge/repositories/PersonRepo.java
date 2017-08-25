package edu.berliner.week5challenge.repositories;

import edu.berliner.week5challenge.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person,Long> {
}
