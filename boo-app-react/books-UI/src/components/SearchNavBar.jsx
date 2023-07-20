import { useState } from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

function SearchNavBar({ data, setBookList }) {
  const [searchTitle, setSearchTitle] = useState("");
  const [searchAuthor, setSearchAuthor] = useState("");

  const handleSearch = () => {
    if (searchAuthor.length === 0 && searchTitle.length === 0) {
      setBookList(data);
    } else {
      if (searchAuthor.length > 0 && searchTitle.length > 0) {
        const updateBook = data.filter(
          (item) =>
            item.author.includes(searchAuthor) &&
            item.title.includes(searchTitle)
        );
        setBookList(updateBook);
      } else {
        const updateBook = data.filter(
          (item) =>
            item.author.includes(
              searchAuthor.length > 0 ? searchAuthor : null
            ) ||
            item.title.includes(searchTitle.length > 0 ? searchTitle : null)
        );
        setBookList(updateBook);
      }
    }
}

    return (
      <nav className="navbar bg-body-tertiary" >
        <div className="container-fluid">
          <a className="navbar-brand">SearchNavbar</a>
         
          <form className="d-flex" role="search">
            <input
              className="form-control me-2"
              type="search"
              placeholder="Search title"
              aria-label="Search"
              value={searchTitle}
              onChange={(e) => setSearchTitle(e.target.value)}
            />
            <input
              className="form-control me-2"
              type="search"
              placeholder="Search author"
              aria-label="Search"
              value={searchAuthor}
              onChange={(e) => setSearchAuthor(e.target.value)}
            />
            <button
              className="btn btn-secondary"
              type="button"
              onClick={handleSearch}
            >
              Search
            </button>
          </form>
        </div>
      </nav>
    );
  
}

SearchNavBar.propTypes = {
    data: PropTypes.array.isRequired,
    setBookList:PropTypes.any.isRequired 
}

export default SearchNavBar;
