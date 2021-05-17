import { React } from 'react';
import { Link } from 'react-router-dom'

import './match-details.css'
function MatchDetailsCard({match, teamName}){
    if (!match) return null;

    const isMatchWon = teamName === match.winner;

    const otherTeam = match.firstInning === teamName ? match.secondInning : match.firstInning;
    return (
        <div className={isMatchWon ? "match-details-card match-won" : "match-details-card match-lose"}>
            <div className="main-detail">
                <span className="vs">vs</span>
                <h1><Link to={`/teams/${otherTeam}`}>{otherTeam}</Link></h1>
                <h3 className="match-date">on {match.date}</h3>
                <h3 className="match-venue">at {match.venue}</h3>
                <h3 className="match-winner">{match.winner} won by {match.resultMargin} {match.result}</h3>
            </div>
            <div className="additional-detail">
                <h3>First Inning</h3>
                <p>{match.firstInning}</p>
                <h3>Second Inning</h3>
                <p>{match.secondInning}</p>
                <h3>Man of the Match</h3>
                <p>{match.playerOfMatch}</p>
                <h3>Umpires</h3>
                <p>{match.umpire1} , {match.umpire2}</p>
            </div>
        </div>
    )
}

export default MatchDetailsCard;

