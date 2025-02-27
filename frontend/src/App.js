import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Profile from './pages/Profile';
import Menu from './components/Menu';
import OauthCallback from './components/OauthCallback';
import { AccessTokenProvider } from './components/AccessTokenContext.js';
import TokenRefresher from './components/TokenRefresher.js';
import axios from 'axios';
import SignUp from './pages/SignUp';

axios.defaults.withCredentials = true;

function App() {
  return (
    <AccessTokenProvider>
      <BrowserRouter>
        <TokenRefresher />
        <Menu></Menu>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/login" element={<Login />} />
          <Route path="/auth/callback" element={<OauthCallback />} />
          <Route path="/signup" element={<SignUp />} />
        </Routes>
      </BrowserRouter>
    </AccessTokenProvider>
  )
}

export default App;
