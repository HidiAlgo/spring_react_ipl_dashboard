package spring_react_ipl_project.ipl_spring_backend.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import spring_react_ipl_project.ipl_spring_backend.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

    public List<Match> findByFirstInningOrSecondInningOrderByDateDesc(String firstInning, String secondInning, Pageable pageable);

    default List<Match> findRecentMatchesByTeamName(String teamName, int count){
        return findByFirstInningOrSecondInningOrderByDateDesc(teamName, teamName, PageRequest.of(0,count));
    }
    
}
