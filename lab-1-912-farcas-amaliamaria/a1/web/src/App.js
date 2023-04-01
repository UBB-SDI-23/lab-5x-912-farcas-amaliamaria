import logo from './logo.svg';
import './App.css';
import * as React from 'react';
import Button from '@mui/material/Button';
import Appbar from './Components/AppBar';
import ShelterTable2 from "./Components/ShelterTable2";
import FilterField from "./Components/FilterField"
function App() {

    const [filterValue, setFilterValue] = React.useState('');

    const handleFilterChange = (newValue) => {
        setFilterValue(newValue);
    };

    return (
    <div className="App">
        <Appbar/>
        <ShelterTable2 filterValue={filterValue}/>
        <FilterField onFilterChange={handleFilterChange}/>

    </div>
  );
}

export default App;
