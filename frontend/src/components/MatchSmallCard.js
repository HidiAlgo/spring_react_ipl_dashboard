import { React } from 'react';
import { Link } from 'react-router-dom';

import './match-small.css'

function MatchSmallCard({match, teamName}){
    const otherTeam = match.firstInning === teamName ? match.secondInning : match.firstInning;
    const isMatchWon = teamName === match.winner;

    return (
        <div className={isMatchWon ? "match-small-card match-won" : "match-small-card match-lose"}>
            <span className="vs">vs</span>
            <h1 className="small-card-title"><Link to={`/teams/${otherTeam}`}>{otherTeam}</Link></h1>
            <p>{match.winner} won by {match.resultMargin} {match.result}</p>
        </div>
    )
}

export default MatchSmallCard;