window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la pelicula
    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function (event) {
        let pacienteId = document.querySelector('#paciente_idPut').value;
        let domicilioId = document.querySelector('#domicilio_idPut').value;

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una pelicula nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#paciente_idPut').value,
            apellido: document.querySelector('#apellidoPut').value,
            nombre: document.querySelector('#nombrePut').value,
            dni: document.querySelector('#dniPut').value,
            fechaIngreso: document.querySelector('#fechaIngresoPut').value,
            domicilio:{
                id:document.querySelector('#domicilio_idPut').value,
                calle:document.querySelector('#callePut').value,
                numero:document.querySelector('#numeroCallePut').value,
                localidad:document.querySelector('#localidadPut').value,
                provincia:document.querySelector('#provinciaPut').value,
            }

        };

        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/pacientes';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de una pelicula del listado
    //se encarga de llenar el formulario con los datos de la pelicula
    //que se desea modificar
    function findBy(id) {
          const url = '/pacientes'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let paciente = data;
              document.querySelector('#paciente_idPut').value = paciente.id;
              document.querySelector('#apellidoPut').value = paciente.apellido;
              document.querySelector('#nombrePut').value = paciente.nombre;
              document.querySelector('#dniPut').value = paciente.dni;
              document.querySelector('#fechaIngresoPut').value = paciente.fechaIngreso;
              document.querySelector('#domicilio_idPut').value = paciente.domicilio.id;
              document.querySelector('#callePut').value = paciente.domicilio.calle;
              document.querySelector('#numeroCallePut').value = paciente.domicilio.numero;
              document.querySelector('#localidadPut').value = paciente.domicilio.localidad;
              document.querySelector('#provinciaPut').value = paciente.domicilio.provincia;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_paciente_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }