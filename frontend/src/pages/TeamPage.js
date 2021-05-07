import { React } from 'react';
import MatchDetailsCard from '../components/MatchDetailsCard';
import MatchSmallCard from '../components/MatchSmallCard';

function TeamPage(){
    return (
        <div className={"teamPage"}>
            <h1>Team Name</h1>
            <MatchDetailsCard />
            <MatchSmallCard />
            <MatchSmallCard />
            <MatchSmallCard />
        </div>
    );
}

export default TeamPage;