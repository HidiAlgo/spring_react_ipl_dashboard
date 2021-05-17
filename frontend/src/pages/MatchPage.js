import {React, useState, useEffect} from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

import MatchDetailsCard from '../components/MatchDetailsCard';

export default function MatchPage() {

    const[matches, setMathces] = useState([]);
    const{teamName, year} = useParams();
    
    useEffect(() => {
        axios.get(`http://localhost:8080/teams/${teamName}/matches?year=${year}`)
            .then(responce => {
                setMathces(responce.data);
            })
            .catch(error => {
                console.log("Error");
            });

    }, [])

    return (
        <div className="MatchPage">
            {teamName}
            {
                matches.map(match => <MatchDetailsCard teamName={teamName} match={match} key={match.id}/>)
            }
        </div>
    );
}