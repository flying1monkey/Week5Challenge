package edu.berliner.week5challenge.repositories;

import edu.berliner.week5challenge.models.Education;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepo extends CrudRepository<Education,Long>{
    //Iterable<Education> findTop10ById();


}
