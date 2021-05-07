package spring_react_ipl_project.ipl_spring_backend.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring_react_ipl_project.ipl_spring_backend.model.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Autowired
    private EntityManager entityManager;


    @Transactional
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
        }

        Map<String, Team> teamData = new HashMap<>();

        entityManager.createQuery("select m.firstInning, count(*) from Match m group by m.firstInning", Object[].class)
            .getResultList()
            .stream()
            .map( r -> new Team((String) r[0], (long) r[1]))
            .forEach( t -> teamData.put(t.getTeamName(), t));


        entityManager.createQuery("select distinct m.secondInning, count(*) from Match m group by m.secondInning", Object[].class)
            .getResultList()
            .stream()
            .forEach(r -> {
                Team team = teamData.get((String) r[0]);
                team.setTotalMatches(team.getTotalMatches() + (long) r[1]);
            });
            

        entityManager.createQuery("select m.winner, count(*) from Match m group by m.winner", Object[].class)
            .getResultList()
            .stream()
            .forEach(r -> {
                Team team = teamData.get((String) r[0]);
                if(team != null) team.setTotalWins((long) r[1]);
            });    
        
        teamData.values()
            .forEach(t -> entityManager.persist(t));
        
        teamData.values()
            .forEach(System.out::println);    

    }
}