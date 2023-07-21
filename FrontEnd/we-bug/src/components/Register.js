import React from "react";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";

export default function BugComponent() {
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [errorMessages, setErrorMessages] = useState({});

  const handleSubmit = (event) => {
    //Prevent page reload
    event.preventDefault();

    var { ufname, ulname, uemail, uorg, pass } = document.forms[0];
    var jso = {
      first_name: ufname.value,
      last_name: ulname.value,
      email: uemail.value,
      password: pass.value,
      organization: uorg.value,
    };

    fetch("http://localhost:8081/user/adduser", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(jso),
    })
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
  const navigate = useNavigate();

  const renderForm = (
    <div className="form">
      <form onSubmit={handleSubmit}>
        <div className="input-container">
          <label>first name </label>
          <input type="text" name="ufname" required />
        </div>
        <div className="input-container">
          <label>last name </label>
          <input type="text" name="ulname" required />
        </div>
        <div className="input-container">
          <label>email </label>
          <input type="text" name="uemail" required />
        </div>
        <div className="input-container">
          <label>organization </label>
          <input type="text" name="uorg" required />
        </div>
        <div className="input-container">
          <label>Password </label>
          <input type="password" name="pass" required />
          {errorMessages ? (
            <div>Successfully Registered</div>
          ) : (
            <div>Invalid Values</div>
          )}
        </div>
        <div className="button-container">
          <input type="submit" />
        </div>
        <div className="button-container">
          <button onClick={() => navigate("/")}>Login</button>
        </div>
      </form>
    </div>
  );

  return (
    <div className="app">
      <div className="login-form">
        <div className="title">Sign In</div>
        {isSubmitted ? renderForm : renderForm}
      </div>
    </div>
  );
}
