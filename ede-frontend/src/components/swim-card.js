import Card from 'react-bootstrap/Card';
import { Link } from "react-router-dom";
import { Button } from "semantic-ui-react";

function SwimCard({firstName, lastName, club, bestTimes, swimmerCode}) {
    return (
        <Card>
            <Card.Body>
                <Card.Title>{firstName} {lastName}</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">{club}</Card.Subtitle>
                <Card.Text>
                    <ul>
                    {bestTimes.map((bestTime, index) => (
                        <li key={index}>{bestTime.eventCode} - {bestTime.time}</li>))}
                    </ul>
                </Card.Text>
                <Link to={`/update/${swimmerCode}`}>
           <Button>Update swimmer</Button>
         </Link>
            </Card.Body>
        </Card>
    )

}

export default SwimCard;