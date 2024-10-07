# API - Tickets Leones

### ![Última versión Docker:](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white) dacolcha/leones-tickets:changes-2.1

## INDICE
- [Compra](#endpoints-para-compra)
- [General](#endpoints-para-general)
- [Vendidos](#endpoints-para-vendidos)
- [Eliminar Individualmente](#endpoints-para-eliminar-individualmente)
- [Reportes](#endpoints-para-reportes)
- [Sitio Venta](#endpoints-para-sitio-venta)
- [Asiento](#endpoints-para-asiento)
- [Cliente](#endpoints-para-cliente)

## ENDPOINTS para Compra
<img src="https://img.shields.io/badge/POST-blue?style=plastic" alt="POST" height = 20px/> **/compra**   Recibe un objeto de tipo <img src="https://img.shields.io/badge/COMPRA-fcd703?style=for-the-badge" alt="COMPRA" height = 20px /> para finalizar la misma. 

![OBJETO COMPRA ABONADO ASIENTOS NUMERADOS](https://img.shields.io/badge/OBJETO%20COMPRA-fcd703?style=for-the-badge)

      {
        "comprador": {
                "correoComprador": "dacol@gmail.com",
                "nombreComprador": "Daniela Colcha",
                "telefonoComprador": "0979349623",
            }, 
        "asientosSeleccionados": [4,5],
        "tipoCompra": "A",
        "localidad":"T",
        "zona":"A2",
        "tipo":"SILLAS",
        "sitioVenta":[nombre sitio venta],
        "pago":1,
        "plazo": null
      }
      


## ENDPOINTS para General

<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/general/*{zona}***  Obtienes un número que indica la cantidad de boletos disponibles según la zona (A,B).

<img src="https://img.shields.io/badge/POST-blue?style=plastic" alt="POST" height = 20px/> **/general/compra**  Recibe un objeto tipo [![Compra General](https://img.shields.io/badge/Compra_General-f0b729)](https://)donde boletos indica en numero de boletos que compra el cliente. Te devuelve el estado **HTTP 200** si se completó la compra y **HTTP 400** si no hay suficientes boletos.

[![OBJETO - Compra General](https://img.shields.io/badge/OBJETO-Compra_General-f0b729)](https://)

      {
          "zona": [A, B],
          "boletos":[cantidad de boletos a comprar]
      }



<img src="https://img.shields.io/badge/POST-blue?style=plastic" alt="POST" height = 20px/> **/general/compra/abonado**  Recibe un objeto tipo [![Compra General Abonado](https://img.shields.io/badge/Compra_General_Abonado-f02929)](https://) donde boletos indica en numero de boletos que compra el cliente. Te devuelve el estado **HTTP 200** si se completó la compra y **HTTP 400** si no hay suficientes boletos.

[![OBJETO - Compra General Abonado](https://img.shields.io/badge/OBJETO-Compra_General_Abonado-f02929)](https://)

        {
            "zona":[A, B],
            "comprador": {
                "correoComprador": "dacol@gmail.com",
                "nombreComprador": "Daniela Colcha",
                "telefonoComprador": "0979349623",
            },
            "cantidadBoletos": [cantidad de boletos a comprar],
            "sitioVenta": {
                "nombreSitio":[nombre sitio venta]
            },
            "pago":[1,2,3,4],
            "plazo": numero meses solamente si aplica.
        }

## ENDPOINTS para Vendidos

<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/vendidos/total**  Te retorna la cantidad de boletos vendidos totales en todas las localidades.
<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/vendidos/abonados/total**  Cantidad de boletos vendidos para Abonados.

### <<<<<<<<<< LOCALIDADES: TRIBUNA Y CANCHA >>>>>>>>>>>>>>>>>>>
<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/vendidos/*{localidad}/total***  Obtienes la cantidad de boletos vendidos según la localidad **[tribuna, cancha]**.

<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/vendidos/*{localidad}***  Obtienes el array de objetos tipo [![Reporte Compra](https://img.shields.io/badge/Reporte_Compra-2999f0)](https://) en el que consta la información necesaria para mostrar en la tabla de vendidos según la localidad **[tribuna, cancha]**.

<a href="https://"><img src="https://img.shields.io/badge/OBJETO-Reporte_Compra-2999f0" alt="OBJETO - Reporte Compra" height = 25px></a>

    {
        "zona": "A2",
        "tipo": "SILLAS",
        "asientos": [
            4,
            5
        ],
        "comprador": {
            "correoComprador": "dacol@gmail.com",
            "nombreComprador": "Daniela Colcha",
            "telefonoComprador": "0979349623"
        },
        "tipoCompra": "A",
        "sitioVenta": "teodoro-gallegos",
        "pago": {
            "tipoPago": "Contado",
            "metodoPago": "Efectivo"
        },
        "plazo": null
    }


### <<<<<<<<<<<<<<<<<<<<< LOCALIDAD: GENERAL >>>>>>>>>>>>>>>>>>>>>>>>>

<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/vendidos/general**  Obtienes un array de objetos tipo <a href="https://"><img src="https://img.shields.io/badge/Reporte_General_Abonado-29dcf0" alt="Reporte General Abonado"></a> para mostrar en la tabla. 

<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/vendidos/general/*{zona}***  Obtienes un objeto con el valor de boletos disponibles y vendidos.

<a href="https://"><img src="https://img.shields.io/badge/OBJETO-Reporte_General_Abonado-29dcf0" alt="OBJETO - Reporte General Abonado" height = 25px></a>

    {
        "idCompra":1
        "zona": "A",
        "comprador": {
            "correoComprador": "dacol@gmail.com",
            "nombreComprador": "Daniela Colcha",
            "telefonoComprador": "0979349623"
        },
        "cantidadBoletos":2
        "sitioVenta": "teodoro-gallegos",
        "pago": {
            "tipoPago": "Contado",
            "metodoPago": "Efectivo"
        },
        "plazo": null
    }

## ENDPOINTS para eliminar individualmente

<img src="https://img.shields.io/badge/PATCH-9726a6?style=plastic" alt="PATCH" height = 20px/> **/eliminar/general/{idCompra}** Elimina el registro de compra de un abonado en GENERAL según el **idCompra** enviado en el path.

<img src="https://img.shields.io/badge/PATCH-9726a6?style=plastic" alt="PATCH" height = 20px/> **/eliminar/{localidad}** Elimina el registro de compra de los asientos indicados en la localidad establecida, necesita que se envíe un objeto como el de acontinuación. 

<a href="https://"><img src="https://img.shields.io/badge/OBJETO-Eliminar_compra_abonado_tribuna_o_cancha-b729f0" alt="OBJETO - Eliminar compra abonado tribuna o cancha" height = 25px></a>

    {
        "zona": "A1",
        "tipo": "GRADAS",
        "asientosSeleccionados": [1,2]
    }

## ENDPOINTS para reportes
<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/reporte/vendidos**  Obtienes el archivo PDF del reporte de boletos vendidos. 


## ENDPOINTS para sitio venta

<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/sitio-venta**  Obtienes toda la lista de los sitios. 

<img src="https://img.shields.io/badge/POST-blue?style=plastic" alt="POST" height = 20px/> **/sitio-venta/crear**   Crea el sitio de venta.

<img src="https://img.shields.io/badge/POST-blue?style=plastic" alt="POST" height = 20px/>  **/sitio-venta/login**  Te devuelve el estado **HTTP 200** si las credenciales son correctas y **retorna el rol**, por otro lado, **HTTP 400** si son incorrectas y **HTTP 404** si el sitio al que intenta ingresar no existe. 

<a href="https://"><img src="https://img.shields.io/badge/OBJETO-Sitio_Venta-f0be29" alt="OBJETO - Sitio Venta" height = 25px></a>

      {
              "id": num,
              "nombreSitio": [nombreSitio],
              "contraseña": [acceso]
              "rol": [user, admin]
      }

## ENDPOINTS para Asiento 

<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/asiento/{localidad}/{zona}/{tipo}**  Obtienes una lista de asientos según los parámetros solicitados. **Ejemplo:** */asiento/T/A2/SILLAS*
  
<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/asiento/{localidad}/{zona}/{tipo}/disponible**  Obtienes solamente un número que indica la cantidad de asientos disponibles en esa zona.

<img src="https://img.shields.io/badge/PATCH-9726a6?style=plastic" alt="PATCH" height = 20px/> **/asiento/limpiar** Elimina el registro de compra de todos los asientos que **no fueron comprados como abonados**.

<img src="https://img.shields.io/badge/PATCH-9726a6?style=plastic" alt="PATCH" height = 20px/> **/asiento/limpiar-todo**  Elimina el registro de compra de **TODOS** los asientos.


<img src="https://img.shields.io/badge/PATCH-9726a6?style=plastic" alt="PATCH" height = 20px/> **/asiento/limpiar-no-abonado**  Recibe una lista de objetos tipo ASIENTO. Elimina el registro de compra para no abonados.

<a href="https://"><img src="https://img.shields.io/badge/OBJETO-Asiento-f0be29" alt="OBJETO - Asiento" height = 25px></a>

      {
        "localidad": [T, C] ,
        "zona": [A1, A2, B1, B2],
        "tipo": [SLLAS, BUTACAS, GRADAS],
        "estado": [D, N ],
        "comprador": [correoCliente],
        "tipoCompra": [A, N],
        "numAsiento": num,
        "sitioVenta": [nombreSitio],
        "pago": [1,2,3,4],
        "plazo": string,
      }
    
 
## ENDPOINTS para Cliente
<img src="https://img.shields.io/badge/POST-blue?style=plastic" alt="POST" height = 20px/> **/clientes/enviar**   Crea el cliente.
  
<img src="https://img.shields.io/badge/GET-green?style=plastic" alt="GET" height = 20px/>  **/clientes**   Obtiene todos los clientes, solamente si deseas hacer pruebas.

<a href="https://"><img src="https://img.shields.io/badge/OBJETO-Comprador-f0be29" alt="OBJETO - Comprador" height = 25px></a>

    {
          "correoComprador": [correo],
          "nombreComprador": [nombre],
          "telefonoComprador": [número con 10 digitos],
    }


