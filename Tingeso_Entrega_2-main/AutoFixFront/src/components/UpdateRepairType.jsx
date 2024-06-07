import RepairsService from "../services/Repairs.service";
import { Button, TextField, Box, Paper } from '@mui/material';
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const EditRepairType = () => {
    const { id } = useParams(); // Obtener el ID de la URL
    const navigate = useNavigate();
    const [repairData, setRepairData] = useState({
        id: "",
        type: "",
        description: "",
        cost_gasoline: "",
        cost_diesel: "",
        cost_hybrid: "",
        cost_electric: "",
    });

    // Función para inicializar y cargar los datos
    const init = () => {
        console.log("ID from URL:", id);
        if (id) {
            RepairsService.getById(id)
                .then((response) => {
                    const { type, description, cost_gasoline, cost_diesel, cost_hybrid, cost_electric } = response.data;
                    setRepairData({
                        id,
                        type: type || "",
                        description: description || "",
                        cost_gasoline: cost_gasoline || 0.0,
                        cost_diesel: cost_diesel || 0.0,
                        cost_hybrid: cost_hybrid || 0.0,
                        cost_electric: cost_electric || 0.0,
                    });
                })
                .catch((error) => {
                    console.error("Hubo un error al cargar la reparación", error);
                });
        } else {
            console.error("ID no proporcionado o inválido");
        }
    };

    useEffect(() => {
        init();
    }, [id]);

    // Manejar cambios en los campos de formulario
    const handleChange = (event) => {
        setRepairData({ ...repairData, [event.target.name]: event.target.value });
    };

    // Enviar datos actualizados
    const handleSubmit = (event) => {
        event.preventDefault();
        console.log("Data to submit:", repairData);
        RepairsService.update(repairData)
            .then(() => {
                navigate("/repairsType/list"); // Redirigir tras la actualización
            })
            .catch((error) => {
                console.error("Error actualizando la reparación", error);
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
                        label="Costo Hibrido"
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
                        Actualizar Reparación
                    </Button>
                    <Button variant="contained" color="secondary" onClick={() => navigate("/repairsType/list")}>
                        Cancelar
                    </Button>
                </Box>
            </form>
        </Paper>
    );
}

export default EditRepairType;
