
import MiFieldSet from './MiFieldSet.jsx'

function Formulario() {
    return (
        <>
        <form action=''>
            <MiFieldSet titulo = "Datos personales" txt1="nombre" txt2="password"/>
            <MiFieldSet titulo = "Datos generales" txt1="direccion" txt2="correo"/>
            <input type='submit' value="enviar datos" ></input>
        </form>
        </>
    )
}

export default Formulario;
