import {
  BrowserRouter as Router,
  Routes,
  Route,
  BrowserRouter,
} from "react-router-dom";
import BookList from "./components/BookList";
import NavBar from "./components/NavBar";
import UserList from "./components/UserList";
import LoginUser from "./components/LoginUser";
import {MyContext} from "./components/useContext";
import { useState } from "react";


function App() {

  const [auth, setAuth] = useState("");

  return (
 

    <BrowserRouter>
      <NavBar />
      <Routes>
        <Route path="/login" element={<LoginUser />} />
        <Route path="/books" element={<BookList />} />
        <Route path="/users" element={<UserList />} />
      </Routes>
    </BrowserRouter>
   
  );
}

export default App;
