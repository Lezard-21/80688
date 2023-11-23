import axios from 'axios';
import { useState } from 'react';
import { Box, Button, TextField } from '@mui/material';

function Formulario() {
    const [cargando, setCargando] = useState(false)
    const [datosFormulario, setDatosFormulario] = useState({
        nombre: '',
        password: '',
        email: ''
    });
    const procesarFormulario = async (e)=>{
        e.preventDefault();
        setCargando(true)
        try {
            console.log(datosFormulario)
            const res = await hacerPeticion();
            setCargando(false)
            if (datosFormulario.nombre == res.nombre) {
                console.log("El usuario es correcto");
            }
            // console.log(res.data);
        } catch (error) {
            setCargando(false)
            console.log(error)
        }
    }
    const hacerPeticion = async ()=>{
        try {
            const res = await axios.post('http://localhost:4567/usuario',datosFormulario);
            // const res = await axios.post('http://localhost:4567/tipo-usuario',{params: datosFormulario});
            // const res = await axios.get('http://localhost:4567/tipo-usuario',datosFormulario);
            console.log(res.data)
            return res.data;
        } catch (error) {
            throw error;
        }
    }
    const cambiosFormulario = (e) => {
        const {name,value} = e.target
        setDatosFormulario({
            ...datosFormulario,
            [name]: value
        })
    }
    return (
        <>
        <h1>Inicio de sesión</h1>
        <form onSubmit={procesarFormulario}>
            <Box m={5}>
                <TextField label="Nombre:" variant='outlined' type='text' id='nombre' name='nombre' placeholder='Nombre' onChange={cambiosFormulario} value={datosFormulario.nombre} fullWidth ></TextField>
            </Box>
            <Box m={5}>
                <TextField label="email:" variant='outlined' type='email' id='email' name='email' placeholder='email@gmail.com' onChange={cambiosFormulario} value={datosFormulario.email} fullWidth ></TextField>
            </Box>
            <Box m={5}>
                <TextField label="Contraseña:" variant='outlined' type='password' disabled={cargando} id='password' name='password' placeholder='contraseña' onChange={cambiosFormulario} value={datosFormulario.password} fullWidth></TextField>
            </Box>
            <Box m={5}>
                <Button variant='contained' type="submit" color='primary' disabled={cargando} fullWidth>Iniciar sesion</Button>
            </Box>
        </form>
        </>
    )
}

export default Formulario;