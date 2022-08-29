window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de peliculas con el método GET
      //nos devolverá un JSON con una colección de peliculas
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de peliculas del JSON
         for(turno of data){
            //por cada pelicula armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
            var table = document.getElementById("turnoTable");
            var turnoRow =table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

            let deleteLink='<a id=\"a_delete_'+turno.id+'\"'+
            ' href=\"#\" onclick=\"deleteBy('+turno.id+')\"'+
            ' class=\"link-danger\">Borrar</a>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la pelicula
            //como ultima columna el boton eliminar
            turnoRow.innerHTML =
                    '<td class=\"td_id\">' + turno.id + '</td>' +
                    '<td class=\"td_pacienteId\">' + turno.paciente.id + '</td>' +
                    '<td class=\"td_pacienteApellido\">' + turno.paciente.apellido + '</td>' +
                    '<td class=\"td_odontologoId\">' + turno.odontologo.id + '</td>' +
                    '<td class=\"td_odontologoApellido\">' + turno.odontologo.apellido + '</td>' +
                    '<td class=\"td_fecha\">' + turno.fecha + '</td>'+
                    '<td>'+deleteLink+'</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/peliculaList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })