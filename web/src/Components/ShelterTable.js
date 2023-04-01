import * as React from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));

function createData(id, name, city, postalCode, phoneNumber, capacity) {
    return { id, name, city, postalCode, phoneNumber, capacity };
}

export default function ShelterTable({ filterValue }) {
    const paperStyle = {padding: '20px 20px', width: 1200, margin:'20px auto'}

    const [shelters, setShelters] = React.useState([]);


    React.useEffect(() => {
        let link = "http://54.167.157.177/api/shelter";
        if (filterValue !== "") {
            link = `http://54.167.157.177/api/shelter/greaterCapacity?capacity=${filterValue}`;
        }
        fetch(link)
            .then(response => response.json())
            .then(data => setShelters(data))
            .catch(error => console.log(error));
    }, [filterValue]);

    const rows = shelters.map(shelter => createData(shelter.id, shelter.name, shelter.city, shelter.postalCode, shelter.phoneNumber, shelter.capacity));


    return (
        <TableContainer component={Paper} style = {paperStyle}>
            <Table sx={{ minWidth: 1200 }} aria-label="customized table">
                <TableHead>
                    <TableRow>
                        {/*<StyledTableCell>Dessert (100g serving)</StyledTableCell>*/}
                        <StyledTableCell align="right">Id</StyledTableCell>
                        <StyledTableCell align="right">Name</StyledTableCell>
                        <StyledTableCell align="right"><b>City</b></StyledTableCell>
                        <StyledTableCell align="right"><b>Postal&nbsp;Code</b></StyledTableCell>
                        <StyledTableCell align="right"><b>Phone&nbsp;Number</b></StyledTableCell>
                        <StyledTableCell align="right"><b>Capacity</b></StyledTableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row) => (
                        <StyledTableRow key={row.name}>
                            <StyledTableCell align="right">{row.id}</StyledTableCell>
                            <StyledTableCell align="right">{row.name}</StyledTableCell>
                            <StyledTableCell align="right">{row.city}</StyledTableCell>
                            <StyledTableCell align="right">{row.postalCode}</StyledTableCell>
                            <StyledTableCell align="right">{row.phoneNumber}</StyledTableCell>
                            <StyledTableCell align="right">{row.capacity}</StyledTableCell>
                        </StyledTableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}
