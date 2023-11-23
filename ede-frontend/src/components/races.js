import ApiGateway from "../api/api";
import { useEffect, useState} from "react";
import { useAuth } from "./auth_context";
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import { useNavigate, Link } from 'react-router-dom';
import RaceCard from "./race-card";
function Races() {
    const [items, setItems] = useState([]);
    const {token} = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
      fetchRaces();
    }, []);
 
  const fetchRaces = async () => {
    try {
      const result = await ApiGateway.getRaces();
      console.log('API Response:', result.data); // Log the response
      setItems(result.data);
    } catch (error) {
      console.log('Something went wrong with the races api.');
    }  
  }
  
  const output = items ? (
    <Row xs={1} md={2} className="g-4">
    {items.map((item, i) => (
      <Col key={i}>
      <RaceCard name={item.name} swimmer={item.swimmer} event={item.eventName} besttime={item.bestTime} date={formatDate(item.date)} raceId={item.raceId} onClick={() => handleDelete(item.raceId)}/>
      </Col>
    ))}
    </Row>
  ) : null;

  const handleDelete = async (raceId) => {
    try {
      
      await ApiGateway.deleteRace(raceId, token);

      // After successful deletion, update the state to re-render the component
      setItems((prevItems) => prevItems.filter((item) => item.raceId !== raceId));
      console.log("succesfully deleted race")
    } catch (error) {
      console.log('Error deleting race:', error);
    }
  };

  function formatDate(dateString) {
    const options = { day: 'numeric', month: 'numeric', year: 'numeric' };
    const formattedDate = new Date(dateString).toLocaleDateString(undefined, options);
    return formattedDate;
  }

  return (
    <div>
    <h2>Race Registrations</h2>
    {/* <ol> */}
        {output}
    {/* </ol> */}
    <Link onClick={() => navigate(-1)}>Back</Link>
    </div>
  )
}

export default Races;