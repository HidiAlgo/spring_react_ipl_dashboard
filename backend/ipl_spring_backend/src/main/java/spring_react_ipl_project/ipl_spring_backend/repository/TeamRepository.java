package spring_react_ipl_project.ipl_spring_backend.repository;

import org.springframework.data.repository.CrudRepository;

import spring_react_ipl_project.ipl_spring_backend.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByTeamName(String teamName);
}
