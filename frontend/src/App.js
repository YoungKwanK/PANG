import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import RegistUser from './pages/registUser';
import Menu from './components/Menu';
import OauthCallback from './components/OauthCallback';

function App() {
  return (
    <BrowserRouter>
      <Menu></Menu>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Login" element={<Login />} />
        <Route path="/auth/callback" element={<OauthCallback />} />
        <Route path="/registUser" element={<RegistUser />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App;
