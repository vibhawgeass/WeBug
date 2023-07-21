import React from "react";
import { useState, useEffect } from "react";
import BugDetail from "./BugDetail";
import { useNavigate } from "react-router-dom";
import DropDown from "./DropDown";
import AllBug from "./AllBug";
import OpenBug from "./OpenBug";
import ClosedBug from "./ClosedBug";
import MyBug from "./MyBug";
import Navbar from "./Navbar";

import {
  CCard,
  CCardText,
  CCardBody,
  CCardSubtitle,
  CCardTitle,
} from "@coreui/react";

export default function BugComponent() {
  const [user, setUser] = useState([]);

  const getInitialState = () => {
    const value = "1";
    return value;
  };

  const [value, setValue] = useState(getInitialState);

  const handleChange = (e) => {
    setValue(e.target.value);
  };
  console.log(value);
  const url1 = "http://localhost:8080/dashboard/getbugbyorganization/newgen";
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

  if (value == 1) {
    return (
      <>
        <Navbar />
        <div>
          <select value={value} onChange={handleChange}>
            <option value="1">All</option>
            <option value="2">Assigned To Me</option>
            <option value="3">Open</option>
            <option value="4">Closed</option>
          </select>
        </div>

        <button onClick={() => navigate("/add")}>Add Bug</button>
        <AllBug />
      </>
    );
  } else if (value == 2) {
    return (
      <>
        <Navbar />
        <div>
          <select value={value} onChange={handleChange}>
            <option value="1">All</option>
            <option value="2">Assigned To Me</option>
            <option value="3">Open</option>
            <option value="4">Closed</option>
          </select>
        </div>

        <button onClick={() => navigate("/add")}>Add Bug</button>
        <MyBug />
      </>
    );
  } else if (value == 3) {
    return (
      <>
        <Navbar />
        <div>
          <select value={value} onChange={handleChange}>
            <option value="1">All</option>
            <option value="2">Assigned To Me</option>
            <option value="3">Open</option>
            <option value="4">Closed</option>
          </select>
        </div>

        <button onClick={() => navigate("/add")}>Add Bug</button>
        <OpenBug />
      </>
    );
  } else {
    return (
      <>
        <Navbar />
        <div>
          <select value={value} onChange={handleChange}>
            <option value="1">All</option>
            <option value="2">Assigned To Me</option>
            <option value="3">Open</option>
            <option value="4">Closed</option>
          </select>
        </div>

        <button onClick={() => navigate("/add")}>Add Bug</button>
        <ClosedBug />
      </>
    );
  }

  // return (
  //   <>
  //     <div>
  //       <select value={value} onChange={handleChange}>
  //         <option value="1">All</option>
  //         <option value="2">Assigned To Me</option>
  //         <option value="3">Open</option>
  //         <option value="4">Closed</option>
  //       </select>
  //       <p>{`You selected ${value}`}</p>
  //     </div>

  //     <button onClick={() => navigate("/add")}>Add Bug</button>

  //     {user &&
  //       user.length > 0 &&
  //       user.map((userObj, index) => (
  //         <div
  //           style={{ backgroundColor: "#80ff80", width: "99%" }}
  //           className="bug-card"
  //           onClick={() => navigate("/detail", { state: userObj })}
  //         >
  //           <CCard>
  //             <CCardBody>
  //               <CCardTitle>{userObj.bug_id}</CCardTitle>
  //               <CCardSubtitle>{userObj.toMessage}</CCardSubtitle>
  //               <CCardText>{userObj.message}</CCardText>
  //             </CCardBody>
  //           </CCard>
  //         </div>
  //       ))}
  //   </>
  // );
}
