import { useEffect, useState } from 'react';
import { useNavigate, useParams, Link } from 'react-router-dom';
import ApiGateway from '../api/api';
import { useAuth } from './auth_context';
import { Form, FormField , Button} from 'semantic-ui-react';

function UpdateSwimmerComponent() {
    const {token } = useAuth();
  const { swimmerCode } = useParams();
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [club, setClub ] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
        console.log(swimmerCode);
        const result = await ApiGateway.getSwimmer(swimmerCode, token);
        const swimmer = result.data;
        setFirstName(swimmer.firstName);
        setLastName(swimmer.lastName);
        setClub(swimmer.setClub);
    }
    fetchData()
  }, [swimmerCode])


  const handleSubmit = async () => {
    try {
        const swimmer = {
            firstName,
            lastName,
            club,
            swimmerCode
        };

        await ApiGateway.updateSwimmer(swimmerCode, swimmer, token);

        navigate(-1);
    } catch (error) {
        console.log("error updating swimmer", error);
    }
  };

  return (
    <div>
      <h2>Update Swimmer</h2>
      <Form onSubmit={handleSubmit}>
        <FormField>
        <label>
          First Name:
          <input
            type="text"
            name="firstName"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
          />
        </label>
        </FormField>
        <FormField>
        <label>
          Last Name:
          <input
            type="text"
            name="lastName"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />
        </label>
        </FormField>
        <FormField>
        <label>
          Club:
          <input
            type="text"
            name="club"
            value={club}
            onChange={(e) => setClub(e.target.value)}
          />
        </label>
        </FormField>

        <Button type="submit" onClick={handleSubmit}>Update Swimmer</Button>
      </Form>
      <Link onClick={() => navigate(-1)}>Back</Link>
    </div>
  );
}

export default UpdateSwimmerComponent;
