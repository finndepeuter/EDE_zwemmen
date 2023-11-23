import {Routes, Route, BrowserRouter, useNavigate, Router } from 'react-router-dom';
import './App.css';
import './swim.css';
import Races from './components/races';
import InputRace from './components/input_race';
import Home from './components/home';
import { useEffect, useState } from 'react';
import { jwtDecode } from 'jwt-decode';
import { useAuth } from './components/auth_context';
import SwimmersComponent from './components/swimmers';
import UpdateSwimmerComponent from './components/updateSwimmer';
import { Button } from 'semantic-ui-react';

function Header() {
  return (
    <div>
      <h1>SwimCompetion service</h1>
    </div>
  );
}

function Main() {
  return (
    <div className="content">
      <Routes>
        <Route path={'/'} element={<Home/>}/>
        <Route path={'/races'} element={<Races/>}/>
        <Route path={'/register'} element={<InputRace/>}/>
        <Route path={'/swimmers'} element={<SwimmersComponent/>}/>
        <Route path={'/update/:swimmerCode'} element={<UpdateSwimmerComponent/>}/>
      </Routes>
    </div>
  );
}

function App() {
  const { login } = useAuth();
  const [ user, setUser] = useState(null);
  const navigate = useNavigate();

  function handleCallbackResponse(response) {
    login(response.credential);
    var userData = jwtDecode(response.credential);
    setUser(userData.name);
    document.getElementById("signIn").hidden = true;
  }

  function handleLogout() {
    // Perform logout logic, e.g., clear user data and reset state
    setUser(null);
    google.accounts.id.renderButton(
      document.getElementById("signIn"), {theme: "outline", size: "large"}
  )
  navigate('/')
  }
  
  useEffect(
    /* global google */
    () => {
        google.accounts.id.initialize({
            client_id: process.env.REACT_APP_GOOGLE_CLIENTID,
            callback: handleCallbackResponse
        });
        
        
          google.accounts.id.renderButton(
            document.getElementById("signIn"), {theme: "outline", size: "large"}
        )
        }, [user]);

  return (
    <div className='main'>
    <Header></Header>
    <div id='signIn' style={{ marginBottom: '20px', marginTop: '20px' }}></div>
    { user && 
      <div style={{display: 'flex', alignItems: 'center'}}>
        <p style={{ margin: '0', marginRight: '10px' }}>Welcome {user} </p>
        <Button onClick={handleLogout} style={{ marginLeft: '10px' }}>Logout</Button>
      </div>
    }
    <Main></Main>
    </div>
  );
}

export default App;
