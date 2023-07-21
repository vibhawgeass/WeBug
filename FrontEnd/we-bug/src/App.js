import "./App.css";
import Login from "./components/Login";
import BugComponent from "./components/BugComponent";
import Navbar from "./components/Navbar";
import BugDetail from "./components/BugDetail";
import AddBug from "./components/AddBug";
import Register from "./components/Register";
import Profile from "./components/Profile";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

function App() {
  return (
    <Router>
      <Routes>
        <Route
          exact
          path="/"
          element={
            <div>
              <Login />
            </div>
          }
        />
        <Route
          path="/detail"
          element={
            <div className="app">
              <BugDetail />
            </div>
          }
        />
        <Route
          path="/add"
          element={
            <div className="app">
              <AddBug />
            </div>
          }
        />
        <Route
          path="/profile"
          element={
            <div className="app">
              <Profile />
            </div>
          }
        />
        <Route
          path="/register"
          element={
            <div className="app">
              <Register />
            </div>
          }
        />
        <Route
          path="/bug"
          element={
            <div className="app">
              <BugComponent />
            </div>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
