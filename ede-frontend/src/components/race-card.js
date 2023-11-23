import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';

function RaceCard({name, date, swimmer, event, besttime, onClick}) {
    
    return (
        <Card>
            <Card.Body>
                <Card.Title>{name}</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">{date}</Card.Subtitle>
                <Card.Text>
                    {swimmer} - {event} ({besttime})
                </Card.Text>
                <Button onClick={onClick}>Delete Race</Button>
            </Card.Body>
        </Card>
    )
}

export default RaceCard;