import { Box, AppBar, Toolbar, IconButton, Typography, Button } from "@mui/material";
import { Link, useLocation } from "react-router-dom";
import HomeIcon from '@mui/icons-material/Home';
import PetsIcon from '@mui/icons-material/Pets';

export const AppMenu = () => {
    const location = useLocation();
    const path = location.pathname;

    return (
        <Box>
            <AppBar style={{backgroundColor:"black"}}>
                <Toolbar>
                    <IconButton
                        component={Link}
                        to="/"
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="school"
                        sx={{ mr: 2 }}>
                        <HomeIcon />
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ mr: 5 }}>
                        Animal Shelters
                    </Typography>
                    <Button
                        variant={path.startsWith("/shelter") ? "outlined" : "text"}
                        to="/shelter"
                        component={Link}
                        color="inherit"
                        sx={{ mr: 5 }}
                        startIcon={<PetsIcon />}>
                        Shelters
                    </Button>

{/*                    <Button
                        variant={path.startsWith("/teams") ? "outlined" : "text"}
                        to="/teams/teamsOrd"
                        component={Link}
                        color="inherit"
                        sx={{ mr: 5 }}
                        startIcon={<Diversity3Icon />}>
                        Shelters Ordered By Capacity
                    </Button>*/}

                </Toolbar>
            </AppBar>
        </Box>
    );
};