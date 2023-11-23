import { useEffect, useState } from "react";
import ApiGateway from "../api/api";
import { useAuth } from "./auth_context";
import { Link , useNavigate} from "react-router-dom";
import SwimCard from "./swim-card";
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

function SwimmersComponent() {
    const [items, setItems] = useState([]);
    const {token} = useAuth();
    const navigate = useNavigate();
    
    useEffect(() => {
        fetchSwimmers();
      }, [token]);

      const fetchSwimmers = async () => {
        try {
          const result = await ApiGateway.getSwimmers(token);
          console.log('API Response:', result.data); // Log the response
          setItems(result.data);
        } catch (error) {
          console.log('Something went wrong with the swimmers api.');
        }  
      }

      var output = items ? (
        <Row xs={1} md={2} className="g-4">
        {items.map((item, i) => (
        <Col key={i}> 
          <SwimCard firstName={item.firstName} lastName={item.lastName} club={item.club} bestTimes={item.bestTimes} swimmerCode={item.swimmerCode}/>
        </Col>
        ))}
        </Row>
      ) : null;

    return (
        <div>
          <h2>Swimmers</h2>
            <ul>{output}</ul>
            <Link onClick={() => navigate(-1)}>Back</Link>
        </div>
    )
}

export default SwimmersComponent;