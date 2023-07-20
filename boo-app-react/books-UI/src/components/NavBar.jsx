import React, { useState } from "react";
import { Link } from "react-router-dom";

function NavBar() {

  const [loggedIn, setLoggedIn] = useState(
    () => localStorage.getItem('token-info') !== null
  ),
  url = `http://localhost:5173/api/logout`,

  handleLogout = () => {
    fetch(url, {
      method: "POST",
      headers:{
        "Clear-Site-Data": "*"
      }
    }).then(() => {
      localStorage.removeItem('token-info')
      
      window.location.assign("http://localhost:5173/login")
    })

  }

  console.log("logged",localStorage, loggedIn)
  return (
    <div className="container">
      <ul className="nav nav-underline">
        <li className="nav-item">
          <Link className="nav-link " to="/books">
            Books
          </Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/users">
            UserList
          </Link>
        </li>
        <div className="nav-item" style={{marginLeft:750}}>
          <li className="nav-item position-relative">
           
               <Link className="nav-link " to="/login" onClick={() => handleLogout()}>
              Logout
            </Link> 
          
            <Link className="nav-link " to="/login">
              Login
            </Link>
         

          </li>
        </div>
      </ul>
    </div>
  );
}

export default NavBar;
