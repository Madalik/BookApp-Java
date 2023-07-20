/* eslint-disable react/prop-types */
import React, { useState } from "react";
import Modal from "./Modal";

function User({ user }) {
  const books = user.books,
    [show, setShow] = useState(false)
 
  return (
    <tr>
      <td>
        <div>{user.name}</div>
      </td>
      <td>
        <div>{user.email}</div>
      </td>
      <td>
        <Modal  books = {books}/>
      </td>
    </tr>
  );
}
User.propTypes = {};

export default User;
