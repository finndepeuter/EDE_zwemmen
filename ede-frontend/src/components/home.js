import { NavLink } from 'react-router-dom';
import { useAuth } from './auth_context';

function Home() {
  const { token } = useAuth();

  return (
    <div>
      {token && (
        <ul className="navbar">
          <li>
            <NavLink to="/" className={({ isActive }) => isActive ? "active" : undefined}>
              Home
            </NavLink>
          </li>
          <li>
            <NavLink to="/races" className={({ isActive }) => isActive ? "active" : undefined}>
              Races
            </NavLink>
          </li>
          <li>
            <NavLink to="/register" className={({ isActive }) => isActive ? "active" : undefined}>
              Register
            </NavLink>
          </li>
          <li>
            <NavLink to="/swimmers" className={({ isActive }) => isActive ? "active" : undefined}>
              Swimmers
            </NavLink>
          </li>
        </ul>
      )}

      <p>Welcome on the swimming competition web application.</p>
      <p>Here you can see all the upcoming races and even register for a race.</p>
      {token ? (
        <p>Now that you are logged in you can explore!</p>
      ) : (
        <p>Please log in to register for a race.</p>
      )}
    </div>
  );
}


export default Home;
