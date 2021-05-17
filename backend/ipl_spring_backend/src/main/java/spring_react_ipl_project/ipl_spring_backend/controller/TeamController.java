package spring_react_ipl_project.ipl_spring_backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring_react_ipl_project.ipl_spring_backend.model.Match;
import spring_react_ipl_project.ipl_spring_backend.model.Team;
import spring_react_ipl_project.ipl_spring_backend.repository.MatchRepository;
import spring_react_ipl_project.ipl_spring_backend.repository.TeamRepository;

@RestController
@CrossOrigin     
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team = teamRepository.findByTeamName(teamName);
        Pageable pageable = PageRequest.of(0, 4);
        team.setMatches(matchRepository.findRecentMatchesByTeamName(teamName, 4));

        return team;
    }

    @GetMapping("/teams/{teamName}/matches")
    public List<Match> getMatchesByDate(@PathVariable String teamName, @RequestParam int year){
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year+1, 1, 1);

        return matchRepository.findByMatcheBetweenDates(teamName, startDate, endDate);
    }
    
}
