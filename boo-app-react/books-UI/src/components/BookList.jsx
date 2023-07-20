import  { useEffect, useState } from 'react'
import data from '../../mock-data.json'
import SearchNavBar from './SearchNavBar';
import { Link } from 'react-router-dom';

function BookList() {  
  const 
  url = `http://localhost:5173/api/books`,
  [bookList, setBookList] = useState(null),
  [loading, setLoading] = useState(true)

 const fetchData = async () => {
  setLoading(true);
  try{
  const response = await fetch(url,{
      method: "GET",
      headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
      }
  });
  const bookList =await response.json();
  setBookList(bookList)
  }catch(err){
      console.error(err)
    }
    setLoading(false);
  };
  useEffect(() => {
      fetchData();
  },[]);


    const handleClick = (id) => {
        const updateBook = bookList.map((book) => {
          if(book.id === id){
            return {...book, borrow: true}
          }
          return book;
          
        })
      setBookList(updateBook)
    }

  return (
    <div className="container-lg">
    <SearchNavBar
    data = {bookList}
    setBookList={setBookList}
    />
    <table className="table">
    <thead>
      <tr>
        <th scope="col">id</th>
        <th scope="col">Title</th>
        <th scope="col">Author</th>
        <th scope="col">Borrow</th>
        <th scope="col">Action</th>
      </tr>
    </thead>
    <tbody>
     {
     bookList?.map((book, index) => (
        <tr key={index}>
            <td>{book.id}</td>
            <td>{book.title}</td>
            <td>{book.author}</td>
            <td>
              {book.borrow === true ? <div >true</div> : <div  >not borrowed</div>}
            </td>
            <td>
            <button 
            className='btn btn-primary'
            type="button"
            disabled={book.borrow}
            onClick={() => handleClick(book.id)}
            >
             Button</button>
            </td>
            {/* <pre>{JSON.stringify(book, null,2)}</pre> */}
        </tr>
     ))}
    </tbody>
  </table>
  </div>
  )
}

BookList.propTypes = {}

export default BookList
