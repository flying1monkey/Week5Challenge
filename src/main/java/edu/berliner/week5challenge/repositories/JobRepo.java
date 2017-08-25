package edu.berliner.week5challenge.repositories;

import edu.berliner.week5challenge.models.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepo extends CrudRepository<Job,Long> {
}
