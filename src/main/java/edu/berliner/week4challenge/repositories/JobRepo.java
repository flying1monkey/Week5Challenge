package edu.berliner.week4challenge.repositories;

import edu.berliner.week4challenge.models.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepo extends CrudRepository<Job,Long> {
}
