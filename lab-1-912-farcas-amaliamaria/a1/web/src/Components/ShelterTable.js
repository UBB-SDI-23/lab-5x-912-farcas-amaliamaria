import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';


function createData(id, name, city, postalCode, phoneNumber, capacity) {
    return { id, name, city, postalCode, phoneNumber, capacity };
}

export default function ShelterTable() {

    const [shelters, setShelters] = React.useState([]);

    React.useEffect(() => {
        fetch("http://localhost:8080/api/shelter")
            .then(response => response.json())
            .then(data => setShelters(data))
            .catch(error => console.log(error));
    }, []);

    const rows = shelters.map(shelter => createData(shelter.id, shelter.name, shelter.city, shelter.postalCode, shelter.phoneNumber, shelter.capacity));

    return (
        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        {/*<TableCell>Shelters</TableCell>*/}
                        <TableCell align="right"><b>Id</b></TableCell>
                        <TableCell align="right"><b>Name</b></TableCell>
                        <TableCell align="right"><b>City</b></TableCell>
                        <TableCell align="right"><b>Postal&nbsp;Code</b></TableCell>
                        <TableCell align="right"><b>Phone&nbsp;Number</b></TableCell>
                        <TableCell align="right"><b>Capacity</b></TableCell>
                        <TableCell align="right"></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row) => (
                        <TableRow
                            key={row.name}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            {/*<TableCell component="th" scope="row">
                                {row.name}
                            </TableCell>*/}
                            <TableCell align="right">{row.id}</TableCell>
                            <TableCell align="right">{row.name}</TableCell>
                            <TableCell align="right">{row.city}</TableCell>
                            <TableCell align="right">{row.postalCode}</TableCell>
                            <TableCell align="right">{row.phoneNumber}</TableCell>
                            <TableCell align="right">{row.capacity}</TableCell>
                            <TableCell align="right"></TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}
