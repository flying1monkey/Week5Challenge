package edu.berliner.week4challenge.repositories;

import edu.berliner.week4challenge.models.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepo extends CrudRepository<Skill,Long>{
}
