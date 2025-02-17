import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Menu from './components/Menu';
import TokenHandler from './components/TokenHandler';

function App() {
  return (
    <BrowserRouter>
      <Menu></Menu>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Login" element={<Login />} />
        <Route path="/oauth/callback" component={TokenHandler} />
      </Routes>
    </BrowserRouter>
  )
}

export default App;