import * as React from "react";
import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import PaidIcon from "@mui/icons-material/Paid";
import CalculateIcon from "@mui/icons-material/Calculate";
import AnalyticsIcon from "@mui/icons-material/Analytics";
import DiscountIcon from "@mui/icons-material/Discount";
import HailIcon from "@mui/icons-material/Hail";
import MedicationLiquidIcon from "@mui/icons-material/MedicationLiquid";
import MoreTimeIcon from "@mui/icons-material/MoreTime";
import HomeIcon from "@mui/icons-material/Home";
import PostAddIcon from '@mui/icons-material/PostAdd';
import DirectionsCarIcon from '@mui/icons-material/DirectionsCar';
import CarRentalIcon from '@mui/icons-material/CarRental';
import ContentCopyIcon from '@mui/icons-material/ContentCopy';
import ContentPasteGoIcon from '@mui/icons-material/ContentPasteGo';
import ListIcon from '@mui/icons-material/List';
import ListAltIcon from '@mui/icons-material/ListAlt';
import DomainIcon from '@mui/icons-material/Domain';
import NoteAddIcon from '@mui/icons-material/NoteAdd';
import { useNavigate } from "react-router-dom";

export default function Sidemenu({ open, toggleDrawer }) {
  const navigate = useNavigate();

  const listOptions = () => (
    <Box
      role="presentation"
      onClick={toggleDrawer(false)}// Desde aqui puedes modificar
    >
        <List>
        <ListItemButton onClick={() => navigate("/home")}>
          <ListItemIcon>
            <HomeIcon />
          </ListItemIcon>
          <ListItemText primary="Home" />
        </ListItemButton>


        <ListItemButton onClick={() => navigate("/registro")}>
          <ListItemIcon>
            <DirectionsCarIcon />
          </ListItemIcon>
          <ListItemText primary="Registros" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/crear-registro")}>
          <ListItemIcon>
            <CarRentalIcon />
          </ListItemIcon>
          <ListItemText primary="Nuevo Registro" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/listareparaciones")}>
          <ListItemIcon>
            <ContentCopyIcon />
          </ListItemIcon>
          <ListItemText primary="Lista de Reparaciones" />
        </ListItemButton>
        
        <ListItemButton onClick={() => navigate("/addreparacion")}>
          <ListItemIcon>
            <NoteAddIcon />
          </ListItemIcon>
          <ListItemText primary="Añadir Tipo de Reparacion" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/updatereparacion")}>
          <ListItemIcon>
            <ContentPasteGoIcon />
          </ListItemIcon>
          <ListItemText primary="Modificar Reparación" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/historial-patente")}>
          <ListItemIcon>
            <ListIcon />
          </ListItemIcon>
          <ListItemText primary="Buscar Historial por Patente" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/historial-detallado-patente")}>
          <ListItemIcon>
            <ListIcon />
          </ListItemIcon>
          <ListItemText primary="Buscar Historial Detallado por Patente" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/nuevo-historial")}>
          <ListItemIcon>
            <ListAltIcon />
          </ListItemIcon>
          <ListItemText primary="Agregar Historial" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/nuevo-historial-d")}>
          <ListItemIcon>
            <ListAltIcon />
          </ListItemIcon>
          <ListItemText primary="Agregar Historial Detallado" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/reporte1")}>
          <ListItemIcon>
            <DomainIcon />
          </ListItemIcon>
          <ListItemText primary="Reporte Reparaciones / TipoVehiculo" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/reporte2")}>
          <ListItemIcon>
            <DomainIcon />
          </ListItemIcon>
          <ListItemText primary="Reporte Comparativo / Mes" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/AllHistorials")}>
          <ListItemIcon>
            <DomainIcon />
          </ListItemIcon>
          <ListItemText primary="Mostrar Todos los Historiales creados" />
        </ListItemButton>

        </List>
    </Box>
      );

  return (
    <div>
      <Drawer anchor={"left"} open={open} onClose={toggleDrawer(false)}>
        {listOptions()}
      </Drawer>
    </div>
  );
}