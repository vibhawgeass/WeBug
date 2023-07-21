import React from "react";
import { useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";

export default function BugDetail() {
  const location = useLocation();

  const [isSubmitted, setIsSubmitted] = useState(false);
  const [errorMessages, setErrorMessages] = useState({});
  const [isSubmitted1, setIsSubmitted1] = useState(false);
  const [errorMessages1, setErrorMessages1] = useState({});
  const navigate = useNavigate();
  const getInitialState = () => {
    const value = "1";
    return value;
  };

  const [value, setValue] = useState(getInitialState);

  const handleChange = (e) => {
    setValue(e.target.value);
  };
  console.log(value);
  if (value == 1) {
    var jso = {
      status: "open",
    };
  } else {
    var jso = {
      status: "closed",
    };
  }
  console.log(jso);
  const handleSubmit = (event) => {
    //Prevent page reload
    event.preventDefault();

    fetch(
      "http://localhost:8080/dashboard/deletebug/" + location.state.bug_id,
      {
        method: "DELETE",
      }
    )
      .then((response) => response.json())
      .then((user) => {
        console.log(user);
        if (user.status == 0) {
          setIsSubmitted(true);
          setErrorMessages(true);
        } else {
          setIsSubmitted(false);
          setErrorMessages(false);
        }
      });
  };

  const handleSubmit1 = (event) => {
    //Prevent page reload
    event.preventDefault();

    fetch(
      "http://localhost:8080/dashboard/updatestatus/" + location.state.bug_id,
      {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(jso),
      }
    )
      .then((response) => response.json())
      .then((user) => {
        var j = {
          bug_id: location.state.bug_id,
          cc: location.state.cc,
          fromMessage: location.state.fromMessage,
          message: location.state.message,
          organization: location.state.organization,
          status: jso.status,
          toMessage: location.state.toMessage,
        };

        if (user.status == 0) {
          navigate("/bug");
          setIsSubmitted1(true);
          setErrorMessages1(true);
        } else {
          setIsSubmitted1(false);
          setErrorMessages1(false);
        }
      });
  };

  const detailComponent = (
    <div>
      <h1>Bug Id : {location.state.bug_id}</h1>
      <h2>Bug Description : {location.state.message}</h2>
      <h2>Bug Status : {location.state.status}</h2>
      <h2>Assigned To : {location.state.toMessage}</h2>
      <h2>Assigned By : {location.state.fromMessage}</h2>
      <div className="button-container">
        <button onClick={handleSubmit}>Delete</button>
      </div>
      <div>
        <select value={value} onChange={handleChange}>
          <option value="1">Open</option>
          <option value="2">Closed</option>
        </select>
        <div className="button-container">
          <button onClick={handleSubmit1}>Change Status</button>
        </div>
      </div>
    </div>
  );

  return <>{isSubmitted ? navigate("/bug") : detailComponent}</>;
}
