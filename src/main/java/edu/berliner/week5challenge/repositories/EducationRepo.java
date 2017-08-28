package edu.berliner.week5challenge.repositories;

import edu.berliner.week5challenge.models.Education;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EducationRepo extends CrudRepository<Education,Long>{
    Iterable<Education> findFirst10ByIdIsNotNull();

}
