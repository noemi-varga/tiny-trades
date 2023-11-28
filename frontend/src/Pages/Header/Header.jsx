import React from 'react';
import { Link, Outlet } from 'react-router-dom';

const Header = () => {
  return (
    <>
    <header>
      <nav>
        <ul>
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
