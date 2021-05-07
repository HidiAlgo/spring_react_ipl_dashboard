package spring_react_ipl_project.ipl_spring_backend.data;


import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import spring_react_ipl_project.ipl_spring_backend.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  @Override
  public Match process(final MatchInput matchInput) throws Exception {

    Match match = new Match();

    match.setId(Long.parseLong(matchInput.getId()));
    match.setCity(matchInput.getCity());
    match.setDate(LocalDate.parse(matchInput.getDate()));
    match.setVenue(matchInput.getVenue());
    match.setPlayerOfMatch(matchInput.getPlayer_of_match());

    String firstInning, secondInning;

    if("bat".equals(matchInput.getToss_decision())){
        firstInning = matchInput.getToss_winner();

        secondInning = matchInput.getTeam1().equals(matchInput.getToss_winner()) 
            ? matchInput.getTeam2() : matchInput.getTeam1();
    }else{
        firstInning = matchInput.getTeam1().equals(matchInput.getToss_winner()) 
            ? matchInput.getTeam2() : matchInput.getTeam1();

        secondInning = matchInput.getToss_winner();
    }

    match.setFirstInning(firstInning);
    match.setSecondInning(secondInning);
    match.setTossDecision(matchInput.getToss_decision());
    match.setWinner(matchInput.getWinner());
    match.setResult(matchInput.getResult());
    match.setUmpire1(matchInput.getUmpire1());
    match.setUmpire2(matchInput.getUmpire2());
    match.setResultMargin(matchInput.getResult_margin());
    match.setTossWinner(matchInput.getToss_winner());
    
    return match;
  }

}