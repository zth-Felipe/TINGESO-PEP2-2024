import GenerateRepairsService from "../services/GenerateRepairs.service";
import React, { useState } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import Box from '@mui/material/Box';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import CircularProgress from '@mui/material/CircularProgress';

const monthNames = [
    "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
];

const ViewRepairsByCombustible = () => {
    const [data, setData] = useState([]);
    const [selectedYear, setSelectedYear] = useState('');
    const [selectedMonth, setSelectedMonth] = useState('');
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    const handleYearChange = (event) => {
        setSelectedYear(event.target.value);
    };

    const handleMonthChange = (event) => {
        setSelectedMonth(event.target.value);
    };

    const handleSubmit = async () => {
        setIsLoading(true);
        setIsSubmitted(false);
        setError(null);

        try {
            const response = await GenerateRepairsService.getGenerateRepairsGroupByCombustible(selectedYear, selectedMonth);
            setData(response.data);
            setIsSubmitted(true);
        } catch (error) {
            setError(error);
            console.error("Error fetching data:", error);
        } finally {
            setIsLoading(false);
        }
    };

    const calculateVariation = (current, previous) => {
        if (previous === 0) return '0%';
        const variation = ((current - previous) / previous) * 100;
        return `${variation.toFixed(2)}%`;
    };

    const columns = [
        { field: 'name', headerName: 'Mes', width: 250 },
        { field: 'selectedMonthCount', headerName: `${monthNames[selectedMonth - 1]} - Cantidad`, width: 150 },
        { field: 'variationCountPrev', headerName: '% Variación', width: 150 },
        { field: 'prevMonth', headerName: `${monthNames[selectedMonth - 2]} - Cantidad`, width: 150 },
        { field: 'variationAmountPrev', headerName: '% Variación', width: 150 },
        { field: 'prev2Month', headerName: `${monthNames[selectedMonth - 3]} - Cantidad`, width: 150 }
    ];

    const rows = data.flatMap((item, index) => [
        {
            id: `${index}-count`,
            name: item[0],
            selectedMonthCount: item[1],
            variationCountPrev: calculateVariation(item[1], item[3]),
            prevMonth: item[3],
            variationAmountPrev: calculateVariation(item[3], item[5]),
            prev2Month: item[5]
        },
        {
            id: `${index}-total`,
            name: '',
            selectedMonthCount: `$${item[2].toLocaleString()}`,
            variationCountPrev: calculateVariation(item[2], item[4]),
            prevMonth: `$${item[4].toLocaleString()}`,
            variationAmountPrev: calculateVariation(item[4], item[6]),
            prev2Month: `$${item[6].toLocaleString()}`
        }
    ]);

    return (
        <Box display="flex" flexDirection="column" alignItems="center" justifyContent="center">
            <Box display="flex" justifyContent="space-between" sx={{ mb: 2 }}>
                <FormControl sx={{ m: 1, minWidth: 120 }}>
                    <InputLabel id="select-year-label">Año</InputLabel>
                    <Select
                        labelId="select-year-label"
                        id="select-year"
                        value={selectedYear}
                        label="Año"
                        onChange={handleYearChange}
                    >
                        {[2022, 2023, 2024].map(year => (
                            <MenuItem key={year} value={year}>{year}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl sx={{ m: 1, minWidth: 120 }}>
                    <InputLabel id="select-month-label">Mes</InputLabel>
                    <Select
                        labelId="select-month-label"
                        id="select-month"
                        value={selectedMonth}
                        label="Mes"
                        onChange={handleMonthChange}
                    >
                        {[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12].map(month => (
                            <MenuItem key={month} value={month}>{monthNames[month - 1]}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <Button variant="contained" color="primary" sx={{ m: 1 }} onClick={handleSubmit}>
                    Obtener Datos
                </Button>
            </Box>
            {isLoading ? (
                <Box display="flex" alignItems="center" justifyContent="center" height="100vh">
                    <CircularProgress size={50} />
                    <Box ml={2}>Cargando...</Box>
                </Box>
            ) : error ? (
                <Typography variant="h6" color="error" sx={{ mt: 2 }}>
                    Error: {error.message}
                </Typography>
            ) : !isSubmitted ? (
                <Typography variant="h6" sx={{ mt: 2 }}>
                    Seleccione el mes y el año para ver el reporte.
                </Typography>
            ) : (
                <Box sx={{ height: 700, width: '100%' }}>
                    <DataGrid
                        rows={rows}
                        columns={columns}
                        pageSize={5}
                        rowsPerPageOptions={[5]}
                        disableSelectionOnClick
                    />
                </Box>
            )}
        </Box>
    );
};

export default ViewRepairsByCombustible;
