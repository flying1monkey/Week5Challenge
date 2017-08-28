package edu.berliner.week5challenge.repositories;

import edu.berliner.week5challenge.models.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepo extends CrudRepository<Skill,Long>{
    Iterable<Skill> findFirst20ByIdIsNotNull();

}
