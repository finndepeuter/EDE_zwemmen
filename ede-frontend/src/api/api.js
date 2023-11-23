import axios from "axios";
//import { useAuth } from "../components/auth_context";

//const baseURL = "http://localhost:8083";
const baseURL = "https://api-gateway-finndepeuter.cloud.okteto.net";


class ApiGateway {
    
    static getRaces = async () => {
        return await axios.get(baseURL + "/races/all");
    }

    static postRace(race, token) {
        return axios.post(baseURL + "/races", race, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        })
    
    }

    static deleteRace = async (id, token) => {

        try {
            const response = await axios.delete(baseURL + "/races?raceId=" + id, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                }
            });
            return response.data;
        } catch (error) {
            console.error('Error deleting race', error);
            throw error; 
        }
        
    }

    static getRace(id) {
        return axios.get(baseURL + "/races?raceId=" + id);
    }

    static updateSwimmer(swimmerCode, swimmer, token) {
        return axios.put(baseURL + "/swimmers?swimmerCode=" + swimmerCode, swimmer, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        });
    }

    static getSwimmers(token) {
        return axios.get(baseURL + "/swimmers/all", {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
            }});
    }

    static getSwimmer(swimmerCode, token) {
        return axios.get(baseURL + "/swimmers?swimmerCode=" + swimmerCode,  {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
            }});
    }

    static getEvents(token) {
        return axios.get(baseURL + "/events", {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
            }});
    }
}

export default ApiGateway;