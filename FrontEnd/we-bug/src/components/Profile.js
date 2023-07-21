import { useNavigate } from "react-router-dom";
import { browserHistory } from "react-router";
import { useState, useEffect } from "react";

export default function Navbar() {
  const navigate = useNavigate();
  const [user, setUser] = useState([]);

  const url1 = "http://localhost:8081/user/getuserbyid/1";
  const handleFetchData = async () => {
    const response = await fetch(url1, {
      method: "GET",
    });
    const user = await response.json();
    setUser(user);
  };

  useEffect(() => {
    handleFetchData();
  }, []);
  return (
    <>
      <button onClick={() => navigate("/", { replace: true })}>Logout</button>
      <div>
        <h2>First Name : {user.first_name}</h2>
        <h2>Last Name : {user.last_name}</h2>
        <h2>Email : {user.email}</h2>
        <h2>Organization : {user.organization}</h2>
      </div>
    </>
  );
}
