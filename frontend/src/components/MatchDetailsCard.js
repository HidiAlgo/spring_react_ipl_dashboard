import { React } from 'react';
import { Link } from 'react-router-dom'

function MatchDetailsCard({match, teamName}){
    if (!match) return null;

    const otherTeam = match.firstInning === teamName ? match.secondInning : match.firstInning;
    return (
        <div className="matchDetailsCard">
            <h3>Latest Match</h3>
            <h1>vs <Link to={`/teams/${otherTeam}`}>{otherTeam}</Link></h1>
            <h3>on {match.date}</h3>
            <h3>at {match.venue}</h3>
            <h3>{match.winner} won by {match.resultMargin} {match.result}</h3>
        </div>
    )
}

export default MatchDetailsCard;

