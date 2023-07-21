import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import {
  CCard,
  CCardText,
  CCardBody,
  CCardSubtitle,
  CCardTitle,
} from "@coreui/react";

export default function OpenBug() {
  const [user, setUser] = useState([]);

  const url1 = "http://localhost:8080/dashboard/getbugbystatus/open";
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

  const navigate = useNavigate();

  return (
    <>
      {user &&
        user.length > 0 &&
        user.map((userObj, index) => (
          <div
            style={{ backgroundColor: "#80ff80", width: "99%" }}
            className="bug-card"
            onClick={() => navigate("/detail", { state: userObj })}
          >
            <CCard>
              <CCardBody>
                <CCardTitle>Bug Id : {userObj.bug_id}</CCardTitle>
                <CCardSubtitle>Assigned To : {userObj.toMessage}</CCardSubtitle>
                <CCardText>Bug Description : {userObj.message}</CCardText>
              </CCardBody>
            </CCard>
          </div>
        ))}
    </>
  );
}
