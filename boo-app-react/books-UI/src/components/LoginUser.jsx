/* eslint-disable react-hooks/rules-of-hooks */
/* eslint-disable no-debugger */
import React, { useState, useContext } from "react";
import { MyContext } from "./useContext";

function LoginUser() {
  const [username, setUserName] = useState(),
    [password, setPassword] = useState(),
    // [auth, setAuth] = useContext(MyContext),

    url = `http://localhost:5173/api/login`,

  handleSubmit = (e) =>{
    e.preventDefault();
    const userPass = btoa(username + ':' + password);
 
    console.log("data", userPass)

    fetch(url,{
      method: "POST",
      headers: { 
        "Authorization": "Basic " + userPass
      },
    
    }).then(() => {
      console.log("new user")
      // setAuth(userPass)

      // localStorage.setItem('token-info', JSON.stringify(userPass));
      // window.location.assign("http://localhost:5173/books")
    })

  }

  return (
    <div className="container-lg" style={{ margin: 80 }}>
      <form style={{ maxWidth: 300 }} onSubmit={handleSubmit}>
        <div className="form-group" style={{ marginBottom: 20 }}>
          <label htmlFor="exampleInputUsername">Username</label>
          <pre>{JSON.stringify(username, null, 2)}</pre>
          <input
            type="username"
            className="form-control"
            id="exampleInputUsername"
            aria-describedby="usernameHelp"
            placeholder="Enter username"
            onChange={e => setUserName(e.target.value)}
          />
          <small id="usernameHelp" className="form-text text-muted">
            We'll never share your email with anyone else.
          </small>
        </div>
        <div className="form-group" style={{ marginBottom: 20 }}>
          <label htmlFor="exampleInputPassword1">Password</label>
          <pre>{JSON.stringify(password, null, 2)}</pre>
          <input
            type="password"
            className="form-control"
            id="exampleInputPassword1"
            placeholder="Password"
            onChange={e => setPassword(e.target.value)}
          />
        </div>

        <button type="submit" className="btn btn-primary" >
        Submit
        </button>
      </form>
    </div>
  );
}

export default LoginUser;
