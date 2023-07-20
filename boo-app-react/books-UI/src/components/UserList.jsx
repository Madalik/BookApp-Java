import React, { useEffect, useState } from "react";
import User from "./User";

function UserList() {
  const url = `http://localhost:5173/api/users`,
    [users, setUsers] = useState(null),
    [loading, setLoading] = useState(true);

  const fetchData = async () => {
    setLoading(true);
    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
      });
      const users = await response.json();
      setUsers(users);
    } catch (err) {
      console.error(err);
    }
    setLoading(false);
  };
  useEffect(() => {
    fetchData();
  }, []);


  return (
    <div className="container-md">
      <table className="table table-sm">
        <thead>
          <tr>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Borrow Books</th>
          </tr>
        </thead>
        {!loading && (
          <tbody>
            {users?.map((user, index) => (
              <User user={user} key={index} />
            ))}
          </tbody>
        )}
      </table>
    </div>
  );
}

export default UserList;
