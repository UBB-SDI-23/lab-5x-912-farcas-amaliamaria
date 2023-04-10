import React, {useState} from "react";
import "./App.css";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {AppMenu} from "./components/AppMenu";
import {AppHome} from "./components/AppHome";
import {SheltersShowAll} from "./components/shelters/AllShelters";
import {ShelterDetails} from "./components/shelters/ShelterDetails";
import {ShelterDelete} from "./components/shelters/ShelterDelete";
import {ShelterUpdate} from "./components/shelters/ShelterUpdate";
import {ShelterAdd} from "./components/shelters/ShelterAdd";
import {SheltersStatistics} from "./components/shelters/ShelterStatistics";

function App() {
    const [count, setCount] = useState(0);

    return (

        <React.Fragment>
            <Router>
                <AppMenu/>
                <Routes>
                    <Route path="/" element={<AppHome/>}/>
                    <Route path="/shelter" element={<SheltersShowAll/>}/>
                    <Route path="/shelter/:shelterId" element={<ShelterDetails/>}/>
                    <Route path="/shelter/:shelterId/delete" element={<ShelterDelete/>}/>
                    <Route path="/shelter/:shelterId/edit" element={<ShelterUpdate/>}/>
                    <Route path="/shelter/add" element={<ShelterAdd/>}/>
                    <Route path="/shelter/report" element={<SheltersStatistics/>}/>
                </Routes>
            </Router>
        </React.Fragment>
    );
}

export default App;