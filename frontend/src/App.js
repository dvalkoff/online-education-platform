import React from 'react';
import {BrowserRouter} from "react-router-dom";
import {AppRouter} from "./components/AppRouter";
import axios from 'axios';
axios.defaults.withCredentials = true

function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <AppRouter/>
        </BrowserRouter>
    </div>
  );
}

export default App;
