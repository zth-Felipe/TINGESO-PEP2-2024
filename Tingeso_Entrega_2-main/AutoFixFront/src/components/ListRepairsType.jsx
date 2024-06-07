import RepairsService from "../services/Repairs.service";
import { Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Box } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const ListRepairsType = () => {
    const [repairs, setRepairs] = useState([]);
    const navigate = useNavigate();

    const init = () => {
        RepairsService.getAll().then((response) => {
            const sortedRepairs = response.data.sort((a, b) => a.id - b.id);
            setRepairs(sortedRepairs);
        })
        .catch((error) => {
            console.error("Hubo un error al mostrar reparaciones", error);
        });
    };

    useEffect(() => {
        init();
    }, []);

    const updateRepair = (id) => {
        navigate(`/repairsType/edit/${id}`);
    };

    const createNewRepair = () => {
        navigate(`/repairsType/add`);
    };

    return (
        <Paper elevation={3} style={{ margin: "20px", padding: "20px" }}>
            
            <TableContainer>
                <Table sx={{ minWidth: 650 }} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell align="right">Tipo</TableCell>
                            <TableCell align="right">Descripción</TableCell>
                            <TableCell align="right">Costo Gasolina</TableCell>
                            <TableCell align="right">Costo Diesel</TableCell>
                            <TableCell align="right">Costo Hibrido</TableCell>
                            <TableCell align="right">Costo Electrico</TableCell>
                            <TableCell align="right">Acciones</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {repairs.map((repair) => (
                            <TableRow
                                key={repair.id}
                                sx={{
                                    '&:last-child td, &:last-child th': { border: 0 },
                                    '&:hover': {
                                        backgroundColor: 'rgba(0, 0, 0, 0.1)',
                                        cursor: 'pointer'
                                    }
                                }}
                                onClick={() => updateRepair(repair.id)}
                            >
                                <TableCell component="th" scope="row">{repair.id}</TableCell>
                                <TableCell align="right">{repair.type}</TableCell>
                                <TableCell align="right">{repair.description}</TableCell>
                                <TableCell align="right">{repair.cost_gasoline}</TableCell>
                                <TableCell align="right">{repair.cost_diesel}</TableCell>
                                <TableCell align="right">{repair.cost_hybrid}</TableCell>
                                <TableCell align="right">{repair.cost_electric}</TableCell>
                                <TableCell align="right">
                                    <Button
                                        variant="contained"
                                        color="primary"
                                        startIcon={<EditIcon />}
                                        onClick={(e) => {
                                            e.stopPropagation();
                                            updateRepair(repair.id);
                                        }}
                                    >
                                        Editar
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <Box display="flex" justifyContent="center" marginBottom="20px">
                <Button
                    variant="contained"
                    color="primary"
                    onClick={createNewRepair}
                >
                    Crear Nuevo Tipo de Reparación
                </Button>
            </Box>
        </Paper>
    );
}

export default ListRepairsType;
