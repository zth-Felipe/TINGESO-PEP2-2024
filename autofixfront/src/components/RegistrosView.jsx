import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import registroService from "../services/registro.service";


export default function RegistersView() {
    const {patente} = useParams();
    const [registro, setRegistro] = useState(null);
    async function fetchRegistro(){
        try{
            const response = await registroService.getRegistro(patente)
            setRegistro(response.data);
        }catch(error){
            alert("Error al obtener el registro.")
        }
    }

    useEffect(() => {
        fetchRegistro();
    }, [])

    return(
        <div>
            <h1>Patente registro: {patente}</h1>
            <div>
                {registro ? (
                    <ul>
                        <li>Id registro: {registro.id}</li>
                        <li>Marca: {registro.marca}</li>
                        <li>Modelo: {registro.modelo}</li>
                        <li>Tipo: {registro.tipo}</li>
                        <li>Año fabricación: {registro.anoFabr}</li>
                        <li>Tipo Motor: {registro.tipoMotor}</li>
                        <li>Numero de asientos: {registro.numAsientos}</li>
                    </ul>
                )
                :
                (
                    <p>Cargando ...</p>
                )
            }
            </div>
        </div>
    )
}