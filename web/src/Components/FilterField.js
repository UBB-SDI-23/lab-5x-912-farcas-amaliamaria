import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

export default function FilterField({ onFilterChange }) {
    const[value, setValue] = React.useState('');
    const handleClick=(e)=>{e.preventDefault()
        onFilterChange(value);
    }

    return (
        <Box
            component="form"
            sx={{
                '& > :not(style)': { m: 1, width: '25ch' },
                paddingBottom: '30px'
            }}
            noValidate
            autoComplete="off"
        >
            <TextField id="standard-basic" label="Filter shelters on capacity" variant="standard" value={value} onChange={(e)=>setValue(e.target.value) }/>
            <Button  variant="contained" onClick={handleClick} sx={{ backgroundColor: 'black', padding: '5px', width:60, height:45 }} >Filter</Button>
        </Box>
    );
}
