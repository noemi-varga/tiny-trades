import React from 'react';
import { Link, Outlet } from 'react-router-dom';
import "./Header.css";

const Header = () => {
  return (
    <>
    <header>
    <nav>
          <div className="logo-container">
            <Link to="/">
              <img src="/img/tinytrades_logo.png" alt="Logo" className="logo" />
            </Link>
          </div>
          <ul className="nav-links">
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/products">My products</Link>
            </li>
          </ul>
        </nav>
    </header>
      <Outlet />
      </>
  );
}

export default Header;
