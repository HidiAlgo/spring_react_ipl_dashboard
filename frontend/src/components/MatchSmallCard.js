import { React } from 'react';
import { Link } from 'react-router-dom';

function MatchSmallCard({match, teamName}){
    const otherTeam = match.firstInning === teamName ? match.secondInning : match.firstInning;

    return (
        <div className={"matchSmallCard"}>
            <h3>vs <Link to={`/teams/${otherTeam}`}>{otherTeam}</Link></h3>
            <p>{match.winner} won by {match.resultMargin} {match.result}</p>
        </div>
    )
}

export default MatchSmallCard;