import React from 'react'
import ReactDOM from 'react-dom/client'
import Formulario from './Formulario.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    {/* <App /> */}
    {/* <form action=''>
      <MiFieldSet titulo = "Datos personales" txt1="nombre" txt2="password"/>
      <MiFieldSet titulo = "Datos generales" txt1="direccion" txt2="correo"/>
      <input type='submit' value="enviar datos" ></input>
    </form> */}
    <Formulario />
  </React.StrictMode>,
)
