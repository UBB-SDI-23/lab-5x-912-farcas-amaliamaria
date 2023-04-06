import {
    TableContainer,
    Paper,
    Table,
    TableHead,
    TableRow,
    TableCell,
    TableBody,
    CircularProgress,
    Container,
    IconButton,
    Tooltip,
    Button,
} from "@mui/material";

import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from '@mui/icons-material/Delete';
import { useEffect, useState } from "react";
import { Shelter } from "../../models/Shelter";
import { Link } from "react-router-dom";
import AddIcon from "@mui/icons-material/Add";
import SortIcon from '@mui/icons-material/Sort';
import VisibilityIcon from '@mui/icons-material/Visibility';
import { BACKEND_API_URL } from "../../constants";


export const SheltersShowAll = () => {

    const[loading, setLoading] = useState(true)
    const[shelters, setShelters] = useState([]);

    useEffect(() => {
        fetch(`${BACKEND_API_URL}/shelter`)
            .then(res => res.json())
            .then(data => {setShelters(data); setLoading(false);})
    }, []);

    console.log(shelters);

    const sortShelters = () => {
        const sortedShelters = [...shelters].sort((a: Shelter, b: Shelter) => {
            if (a.capacity < b.capacity) {
                return -1;
            }
            if (a.capacity > b.capacity) {
                return 1;
            }
            return 0;
        })
        console.log(sortedShelters);
        setShelters(sortedShelters);
    }

    return (

        <Container>
            <h1 style={{marginTop:"65px"}}>All Shelters</h1>

            {loading && <CircularProgress />}

            {!loading && shelters.length == 0 && <div>No shelters found</div>}

            {!loading && shelters.length > 0 && (

                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 800 }} aria-label="simple table" style={{backgroundColor:"whitesmoke"}}>
                        <TableHead>
                            <TableRow>
                                <TableCell align="center" style={{color:"#2471A3", fontWeight:'bold'}}>Crt.</TableCell>
                                <TableCell align="center" style={{color:"#2471A3", fontWeight:'bold'}}>Name</TableCell>
                                <TableCell align="center" style={{color:"#2471A3", fontWeight: 'bold'}}>City</TableCell>
                                <TableCell align="center" style={{color:"#2471A3", fontWeight: 'bold'}}>Postal Code</TableCell>
                                <TableCell align="center" style={{color:"#2471A3", fontWeight: 'bold'}}>Phone Number (+)</TableCell>
                                <TableCell align="center" style={{color:"#2471A3", fontWeight: 'bold'}}>
                                    Capacity
                                    <IconButton sx={{color:"black", paddingLeft:2, fontSize:"20px", width:"20px", '&:focus': {
                                            outline: "none"
                                        } }} onClick={sortShelters}>
                                        <Tooltip title="Sort" arrow>
                                            <SortIcon style={{color:"black", fontSize:"20px"}} />
                                        </Tooltip>
                                    </IconButton>

                                </TableCell>
                                <TableCell align="center" style={{color:"#2471A3", fontWeight: 'bold'}}>Operations
                                    <IconButton component={Link} sx={{ mr: 3 }} to={`/shelter/add`}>
                                        <Tooltip title="Add a new shelter" arrow>
                                            <AddIcon style={{color:"black", fontSize:"20px"}} />
                                        </Tooltip>
                                    </IconButton></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {shelters.map((shelter:Shelter, index) => (
                                <TableRow key={shelter.id}>
                                    <TableCell component="th" scope="row">
                                        {index + 1}
                                    </TableCell>
                                    <TableCell align="center">
                                        {shelter.name}
                                        </TableCell>
                                    <TableCell align="center">{shelter.city}</TableCell>
                                    <TableCell align="center">{shelter.postalCode}</TableCell>
                                    <TableCell align="center">{shelter.phoneNumber}</TableCell>
                                    <TableCell align="center">{shelter.capacity}</TableCell>
                                    <TableCell align="center">
                                        <IconButton component={Link} sx={{ mr: 3 }} to={`/shelter/${shelter.id}`}>
                                            <VisibilityIcon  style={{color:"black", fontSize:"20px"}}/>
                                        </IconButton>

                                        <IconButton component={Link} sx={{ mr: 3 }} to={`/shelter/${shelter.id}/edit`}>
                                            <EditIcon sx={{ color: "navy" }}/>
                                        </IconButton>

                                        <IconButton component={Link} sx={{ mr: 3 }} to={`/shelter/${shelter.id}/delete`}>
                                            <DeleteIcon sx={{ color: "darkred" }} />
                                        </IconButton>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            )
            }
        </Container>

    );
};