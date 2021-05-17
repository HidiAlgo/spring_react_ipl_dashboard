import { React, useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { PieChart } from 'react-minimal-pie-chart';

import MatchDetailsCard from '../components/MatchDetailsCard';
import MatchSmallCard from '../components/MatchSmallCard';

import './team-page.css';

function TeamPage(){

    const [match, setMatch] = useState({matches:[]});
    const [teamFound, setTeamFound] = useState(false)
    const {teamName} = useParams();

    useEffect( () => {
        axios.get(`http://localhost:8080/teams/${teamName}`)
            .then(response => {
                setMatch(response.data);
                setTeamFound(true)
            })
            .catch(error => {
                console.log(error);
            })
    }, [teamName]);       

    return (
        <>
            {teamFound && 
                <div className="team-page">
                    <div className="team-name-section">
                        <h1 className="team-name">{match.teamName}</h1>
                    </div>
                    <div className="pie-chart">
                        <h4>Win / Loses</h4>

                        <PieChart
                            data={[
                                { title: 'Wins', value: match.totalWins, color: '#4da375' },
                                { title: 'Loses', value: match.totalMatches - match.totalWins, color: '#a34d5d' },
                            ]}
                        />
                    </div>
                    <div className="match-details-card">
                        <h3>Latest Match</h3>
                        <MatchDetailsCard match={match.matches[0]} teamName={match.teamName}/>
                    </div>
                    {match.matches.slice(1).map(m => <MatchSmallCard teamName={match.teamName} match={m} key={m.id}/>)}
                    <div className="more-link">
                        <a href="#" >More ></a>
                    </div>
                </div>
            }

            {!teamFound && 
                <div>
                    <h1>Team not found</h1>
                </div>
            }

        </>
    );
}

export default TeamPage; 