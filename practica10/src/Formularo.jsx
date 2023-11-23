
import { Box, Button, TextField } from '@mui/material';
import axios from 'axios';
import { useState } from 'react';

function Formulario() {
    const [canrgando, setCargando] = useState(false)
    const [datosFormulario, setDatosFormulario] = useState({
        nombre: '',
        password: ''
    });
    const procesarFormulario = async (e)=>{
        e.preventDefault()
        console.log("datos recuperados del formulario" , datosFormulario)
        setCargando(true)
        try {
            const response = await hacerPeticion();
            setCargando(false)
            if (datosFormulario.nombre === response.Nombre) {
                console.log("El usuario es Correcto");
            }else{
                console.log("El usuario es incorrecto");
            }
        } catch (error) {
            console.log(error)
            setCargando(false)
        }
    } 
    const hacerPeticion = async ()=>{
            try {
                const res = await axios.post('http://localhost:4567/tipo-usuario',datosFormulario);
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
        <h1>Inicio de sesion</h1>
        <form onSubmit={ procesarFormulario}>
            <Box m={5}>
                <TextField label="Nombre:" variant='outlined' name="nombre" onChange={cambiosFormulario} value={datosFormulario.nombre} fullWidth ></TextField>
            </Box>
            <Box m={5}>
                <TextField label="Contraseña:" variant='outlined' name='password' onChange={cambiosFormulario} value={datosFormulario.password} type="password" fullWidth></TextField>
            </Box>
            <Box m={5}>
                <Button variant='contained' type="submit" color='primary' disabled={canrgando} fullWidth>Iniciar sesion</Button>
            </Box>
        </form>
        </>
    )
}

export default Formulario;