import React from 'react'
import ReactDOM from 'react-dom/client'
import MiFieldSet from './MiFieldSet.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    {/* <App /> */}
    <MiFieldSet titulo = "Datos personales" txt1="nombre" txt2="password"/>
    <MiFieldSet titulo = "Datos personales 2" txt1="nombre" txt2="password"/>
    <MiFieldSet titulo = "Datos personales 3" txt1="nombre" txt2="password"/>
  </React.StrictMode>,
)
