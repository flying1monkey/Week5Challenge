package edu.berliner.week4challenge.repositories;

import edu.berliner.week4challenge.models.Education;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepo extends CrudRepository<Education,Long>{
    //Iterable<Education> findTop10ById();


}
