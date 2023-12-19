import { useEffect, useState } from "react";
import ApiGateway from "../api/api";
import { Form, Dropdown, Button } from 'semantic-ui-react'
import { useAuth } from "./auth_context";
import { useNavigate, Link } from "react-router-dom";

function InputRace() {
    const [name, setName] = useState("");
    const [date, setDate] = useState(new Date());
    const [eventCode, setEventCode] = useState("");
    const [swimmerCode, setSwimmerCode] = useState("");

    const [swimmers, setSwimmers] = useState([]);
    const [events, setEvents] = useState([]);
    
    const {token} = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        fetchSwimmers();
        fetchEvents();
      }, [token]);

      // Fetch the list of swimmers when the component mounts
      const fetchSwimmers = async () => {
        try {
          const result = await ApiGateway.getSwimmers(token);
          setSwimmers(result.data);
        } catch (error) {
          console.error('Error fetching swimmers', error);
        }
      };

      const fetchEvents = async () => {
          try {
              const result = await ApiGateway.getEvents(token);
              setEvents(result.data);
          } catch (e) {
              console.log("error fetching events");
          }
      }


      const handleSwimmerChange = (event, data) => {
        setSwimmerCode(data.value);
      };

      const handleEventChange = (e, data) => {
        setEventCode(data.value);
      }

    const handleSubmit = async () => {
        try {
            const race = {
                name,
                date,
                eventCode,
                swimmerCode,
            };
            await ApiGateway.postRace(race, token);
            navigate(-1);
            
        } catch (error) {
            console.error("error creating race", error);
        }
    }

    const dropdownOptions = swimmers.map((swimmer) => ({
        key: swimmer.swimmerCode,
        text: `${swimmer.firstName} ${swimmer.lastName}`,
        value: swimmer.swimmerCode,
      }));

    const dropdownOptionsEvent = events.map((event) => ({
        key: event.eventCode,
        text: `${event.name}`,
        value: event.eventCode
    }))  ;

    return (
        <div>
            <Form>
                <Form.Field>
                    <label>Race Name</label>
                    <input placeholder="race name" value={name} onChange={(e) => setName(e.target.value)}></input>
                </Form.Field>
                <Form.Field>
                    <label>Date</label>
                    <input type="date" placeholder="date" value={date} onChange={(e) => setDate(e.target.value)}></input>
                </Form.Field>
                <Form.Field>
                    <label>Swimmer</label>
                    <Dropdown
                            placeholder="Select Swimmer"
                        
                            options={dropdownOptions}
                            onChange={handleSwimmerChange}
                            value={swimmerCode} // Set the value to the selected swimmer code
                            />

                </Form.Field>
                <Form.Field>
                    <label>Eventcode</label>
                    {/* <input placeholder="eventcode" value={eventCode} onChange={(e) => setEventCode(e.target.value)}></input> */}
                    <Dropdown
                            placeholder="Select event"
                        
                            options={dropdownOptionsEvent}
                            onChange={handleEventChange}
                            value={eventCode} // Set the value to the selected swimmer code
                            />
                </Form.Field>
                <Button type="button" onClick={handleSubmit}>submit</Button>
            </Form>
            <Link onClick={() => navigate(-1)}>Back</Link>
        </div>
    )
}

export default InputRace;