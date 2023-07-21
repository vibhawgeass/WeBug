import React from "react";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";

export default function AddBug() {
  const user_id = localStorage.getItem("id");
  const [errorMessages, setErrorMessages] = useState({});
  const [isSubmitted, setIsSubmitted] = useState(false);
  const handleSubmit = (event) => {
    //Prevent page reload
    event.preventDefault();

    var { message, to, cc } = document.forms[0];
    var jso = {
      message: message.value,
      toMessage: to.value,
      cc: "1",
      fromMessage: user_id,
      status: "open",
      organization: "newgen",
    };

    fetch("http://localhost:8080/dashboard/addbug", {
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

  const renderForm = (
    <div className="form">
      <form onSubmit={handleSubmit}>
        <div className="input-container">
          <label>Message </label>
          <input type="text" name="message" required />
        </div>
        <div className="input-container">
          <label>Assign To </label>
          <input type="text" name="to" required />
          {errorMessages ? <div></div> : <div>Something Went Wrong</div>}
        </div>
        <div className="button-container">
          <input type="submit" />
        </div>
      </form>
    </div>
  );

  const navigate = useNavigate();
  return (
    <>
      <div className="app">
        <div className="bug-form">
          <div className="title">Add New Bug</div>
          {isSubmitted ? navigate("/bug") : renderForm}
        </div>
      </div>
    </>
  );
}
