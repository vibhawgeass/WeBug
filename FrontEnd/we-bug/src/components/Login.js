import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";

export default function Login() {
  const [errorMessages, setErrorMessages] = useState({});
  const [isSubmitted, setIsSubmitted] = useState(false);

  const [user, setUser] = useState([]);

  const handleSubmit = (event) => {
    //Prevent page reload
    event.preventDefault();

    var { uname, pass } = document.forms[0];
    var jso = {
      email: uname.value,
      password: pass.value,
    };

    fetch("http://localhost:8081/user/validatelogin", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(jso),
    })
      .then((response) => response.json())
      .then((user) => {
        console.log(user);
        if (user.status == 0) {
          localStorage.setItem("id", JSON.stringify(user.user_id));
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
          <label>email </label>
          <input type="text" name="uname" required />
          {/* {renderErrorMessage("uname")} */}
        </div>
        <div className="input-container">
          <label>Password </label>
          <input type="password" name="pass" required />
          {errorMessages ? <div></div> : <div>Invalid Credential</div>}
        </div>
        <div className="button-container">
          <input type="submit" />
        </div>
        <div className="button-container">
          <button onClick={() => navigate("/register")}>Register</button>
        </div>
      </form>
    </div>
  );

  return (
    <div className="app">
      <div className="login-form">
        <div className="title">Sign In</div>
        {isSubmitted ? navigate("/bug", { replace: true }) : renderForm}
      </div>
    </div>
  );
}
