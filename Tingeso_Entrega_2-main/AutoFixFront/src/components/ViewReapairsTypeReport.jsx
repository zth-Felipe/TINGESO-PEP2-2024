import GenerateRepairsService from "../services/GenerateRepairs.service";
import React, { useState, useEffect } from 'react';
import { BarChart } from '@mui/x-charts/BarChart';
import { DataGrid } from '@mui/x-data-grid';
import Box from '@mui/material/Box';
import CircularProgress from '@mui/material/CircularProgress';

const ViewRepairsTypeReport = () => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await GenerateRepairsService.getGenerateRepairsGroupByTipe();
                setData(response.data);
            } catch (error) {
                setError(error);
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, []);

    const loadingStyle = {
        fontSize: '2em', 
        fontWeight: 'bold', 
        textAlign: 'center', 
        marginTop: '20%' 
    };

    if (loading) return (
        <Box display="flex" alignItems="center" justifyContent="center" height="100vh">
            <CircularProgress size={50} />
            <Box ml={2}>Cargando...</Box>
        </Box>
    );
    if (error) return <div>Error: {error.message}</div>;

    const rows = data.flatMap((item, index) => [
        {
            id: `${index}-count`,
            name: item[0],
            sedan: item[1],
            hatchback: item[3],
            suv: item[5],
            pickUp: item[7],
            furgoneta: item[9],
            total: item[12],
        },
        {
            id: `${index}-total`,
            name: '',
            sedan: `$${item[2].toLocaleString()}`,
            hatchback: `$${item[4].toLocaleString()}`,
            suv: `$${item[6].toLocaleString()}`,
            pickUp: `$${item[8].toLocaleString()}`,
            furgoneta: `$${item[10].toLocaleString()}`,
            total: `$${item[11].toLocaleString()}`,
            
        }
    ]);

    const columns = [
        { field: 'name', headerName: 'Tipo de reparación', width: 250 },
        { field: 'sedan', headerName: 'Sedan', width: 150 },
        { field: 'hatchback', headerName: 'Hatchback', width: 150 },
        { field: 'suv', headerName: 'SUV', width: 150 },
        { field: 'pickUp', headerName: 'Pick Up', width: 150 },
        { field: 'furgoneta', headerName: 'Furgoneta', width: 150 },
        { field: 'total', headerName: 'Total', width: 150 }
    ];

    return (
        <Box display="flex" flexDirection="column" alignItems="center" justifyContent="center">
            <BarChart
                width={1300}
                height={400}
                margin={{ top: 45, right: 10, left: 10, bottom: 30 }}
                xAxis={[{ scaleType: 'band', dataKey: 'name', align: 'center' }]}
                dataset={data.map(item => ({
                    name: item[0], 
                    sedan: item[1], 
                    montoSedan: item[2], 
                    hatchback: item[3], 
                    montoHatchback: item[4], 
                    suv: item[5], 
                    montoSuv: item[6], 
                    pickUp: item[7], 
                    montoPickUp: item[8], 
                    furgoneta: item[9], 
                    montoFurgoneta: item[10], 
                    cost: item[11]
                    
                }))}
                series={[
                    {
                        dataKey: 'cost',
                        label: 'Total',
                        valueFormatter: (value) => `$${value}`
                    }
                ]}
            />
            <Box sx={{ height: 400, width: '100%', marginTop: 2 }}>
                <DataGrid
                    rows={rows}
                    columns={columns}
                    pageSize={5}
                    rowsPerPageOptions={[5]}                
                />
            </Box>
        </Box>
    );
};

export default ViewRepairsTypeReport;
