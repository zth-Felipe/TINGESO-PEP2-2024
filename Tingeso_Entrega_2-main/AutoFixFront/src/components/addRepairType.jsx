import RepairsService from "../services/Repairs.service";
import { Button, Paper } from '@mui/material';
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { TextField, Box } from "@mui/material";

const AddRepairType = () => {
    const navigate = useNavigate();
    const [repairData, setRepairData] = useState({
        type: "",
        description: "",
        cost_gasoline: 0,  // Inicializado como número
        cost_diesel: 0,     // Inicializado como número
        cost_hybrid: 0,     // Inicializado como número
        cost_electric: 0,   // Inicializado como número
    });

    // Manejar cambios en los campos del formulario
    const handleChange = (event) => {
        const { name, value } = event.target;
        const newValue = name.includes('cost') ? parseFloat(value) || '' : value;
        setRepairData({ ...repairData, [name]: newValue });
    };

    // Enviar los datos nuevos para crear una reparación
    const handleSubmit = (event) => {
        event.preventDefault();
        RepairsService.create(repairData)  // Asume que existe un método 'create' en RepairsService
            .then(() => {
                navigate("/repairsType/list");  // Redirigir a la lista de reparaciones tras la creación
            })
            .catch((error) => {
                console.error("Error al añadir la nueva reparación", error);
            });
    };

    return (
        <Paper elevation={3} style={{ padding: "20px", margin: "20px" }}>
            <form onSubmit={handleSubmit}>
                <Box display="flex" flexDirection="column" gap={2}>
                    <TextField
                        label="Tipo"
                        variant="outlined"
                        name="type"
                        value={repairData.type}
                        onChange={handleChange}
                    />
                    <TextField
                        label="Descripción"
                        variant="outlined"
                        name="description"
                        value={repairData.description}
                        onChange={handleChange}
                    />
                    <TextField
                        label="Costo Gasolina"
                        variant="outlined"
                        name="cost_gasoline"
                        type="number"
                        value={repairData.cost_gasoline}
                        onChange={handleChange}
                    />
                    <TextField
                        label="Costo Diesel"
                        variant="outlined"
                        name="cost_diesel"
                        type="number"
                        value={repairData.cost_diesel}
                        onChange={handleChange}
                    />
                    <TextField
                        label="Costo Híbrido"
                        variant="outlined"
                        name="cost_hybrid"
                        type="number"
                        value={repairData.cost_hybrid}
                        onChange={handleChange}
                    />
                    <TextField
                        label="Costo Eléctrico"
                        variant="outlined"
                        name="cost_electric"
                        type="number"
                        value={repairData.cost_electric}
                        onChange={handleChange}
                    />
                    <Button type="submit" variant="contained" color="primary">
                        Añadir Reparación
                    </Button>
                    <Button variant="contained" color="secondary" onClick={() => navigate("/repairsType/list")}>
                        Cancelar
                    </Button>
                </Box>
            </form>
        </Paper>
    );
}

export default AddRepairType;
