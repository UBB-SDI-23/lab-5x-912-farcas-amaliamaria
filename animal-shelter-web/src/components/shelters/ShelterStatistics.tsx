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
} from "@mui/material";

import { useEffect, useState } from "react";
import { BACKEND_API_URL } from "../../constants";
import {ShelterReportAnimalsVaccinated} from "../../models/ShelterReportAnimalsVaccinated";


export const SheltersStatistics = () => {

    const[loading, setLoading] = useState(true)
    const[shelters, setShelters] = useState([]);

    useEffect(() => {
        fetch(`${BACKEND_API_URL}/shelter/reportAnimals`)
            .then(res => res.json())
            .then(data => {setShelters(data); setLoading(false);})
    }, []);

    console.log(shelters);

    return (

        <Container>
            <h1 style={{marginTop:"65px", color:'black'}}>Shelters Animals vaccination rate</h1>

            {loading && <CircularProgress />}

            {!loading && shelters.length == 0 && <div>No reports found</div>}

            {!loading && shelters.length > 0 && (

                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 800 }} aria-label="simple table" style={{backgroundColor:"whitesmoke"}}>
                        <TableHead>
                            <TableRow>
                                <TableCell align="center" style={{color:"black", fontWeight:'bold'}}>Crt.</TableCell>
                                <TableCell align="center" style={{color:"black", fontWeight:'bold'}}>Shelter Name</TableCell>
                                <TableCell align="center" style={{color:"black", fontWeight: 'bold'}}>Rate</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {shelters.map((report:ShelterReportAnimalsVaccinated, index) => (
                                <TableRow key={report.id}>
                                    <TableCell component="th" scope="row">
                                        {index + 1}
                                    </TableCell>
                                    <TableCell align="center">
                                        {report.shelter}
                                    </TableCell>
                                    <TableCell align="center">{report.ratioVaccinated}</TableCell>
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