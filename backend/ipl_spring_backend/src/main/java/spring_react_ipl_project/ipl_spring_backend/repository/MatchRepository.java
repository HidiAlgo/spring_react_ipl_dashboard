package spring_react_ipl_project.ipl_spring_backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import spring_react_ipl_project.ipl_spring_backend.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

    public List<Match> findByFirstInningOrSecondInningOrderByDateDesc(String firstInning, String secondInning, Pageable pageable);

    @Query("select m from Match m where (m.firstInning = :teamName or m.secondInning = :teamName) and m.date between :dateStart and :dateEnd order by date desc") 
    public List<Match> findByMatcheBetweenDates(
        @Param("teamName") String teamName, 
        @Param("dateStart") LocalDate dateStart, 
        @Param("dateEnd") LocalDate endDate);

    default List<Match> findRecentMatchesByTeamName(String teamName, int count){
        return findByFirstInningOrSecondInningOrderByDateDesc(teamName, teamName, PageRequest.of(0,count));
    }
    
}
