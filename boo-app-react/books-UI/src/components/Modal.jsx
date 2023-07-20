/* eslint-disable no-debugger */
import React, { useCallback, useState } from "react";
import PropTypes from "prop-types";
import "../style/modal.css";

const Modal = ({ books }) => {
  const [book, setBook] = useState(books),
    [show, setShow] = useState(false),
    showModal = useCallback(() => {
      setShow(true);
    }, []),
    handleClose = useCallback(() => {
      setShow(false);
    }, []),

    updateBorrow = async(id, book) =>{
      debugger;
      const response = await fetch(`http://localhost:8080/${id}`, {
        method: "PUT",
        headers:{
          'Content-Type': 'application/json'
        },
        // eslint-disable-next-line no-undef
        body: JSON.stringify({ ...book, borrow:false})
      });
      const data =await response.json();
      console.log(data)
      setBook(data)
      // history.back();
      location.reload();
      
    }

  return (
    <>
      <button className="btn btn-primary" type="button" onClick={showModal}>
        Show Borrow Books
      </button>
      {show ? (
        <div>
          <section className="modal-main">
            <table className="table table-striped">
              <thead>
                <tr>
                  <th scope="col">Title</th>
                  <th scope="col">Author</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              {books.map((book, index) => (
                <tr key={index}>
                  <td>{book?.title}</td>
                  <td>{book?.author}</td>
                  <button
                    className="btn btn-outline-success"
                    type="button"
                    onClick={()=> updateBorrow(book.id, book)}
                  >
                    Return Book
                  </button>
                </tr>
              ))}
            </table>
            <button
              className="btn btn-dark"
              type="button"
              onClick={handleClose}
            >
              Close
            </button>
          </section>
        </div>
      ) : null}
    </>
  );
};
Modal.propTypes = {
  books: PropTypes.any,
};

export default Modal;
