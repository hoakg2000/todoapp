import React from 'react';
import logo from './logo.svg';
import './App.css';
import Login from './page/login';
import { BrowserRouter, HashRouter, Route } from "react-router-dom";
import { Switch } from 'react-router-dom';
import { AuthProvider } from './context/AuthProvider';
import Layout from './layout';

function App() {
  return (
    <>
      <AuthProvider>
        <BrowserRouter>
          <Layout />
        </BrowserRouter>
      </AuthProvider>
    </>
  );
}

export default App;
