import './App.css';
import * as React from 'react';
import Appbar from './Components/AppBar';
import ShelterTable from "./Components/ShelterTable";
import FilterField from "./Components/FilterField"

function App() {

    const [filterValue, setFilterValue] = React.useState('');

    const handleFilterChange = (newValue) => {
        setFilterValue(newValue);
    };

    return (
    <div className="App">
        <Appbar/>
        <ShelterTable filterValue={filterValue}/>
        <FilterField onFilterChange={handleFilterChange}/>

    </div>
  );
}

export default App;
