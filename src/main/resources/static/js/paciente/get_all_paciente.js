window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de peliculas con el método GET
      //nos devolverá un JSON con una colección de peliculas
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de peliculas del JSON
         for(paciente of data){
            //por cada pelicula armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
            var table = document.getElementById("pacienteTable");
            var pacienteRow =table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;

            let deleteLink='<a id=\"a_delete_'+paciente.id+'\"'+
            ' href=\"#\" onclick=\"deleteBy('+paciente.id+')\"'+
            ' class=\"link-danger\">Borrar</a>';

            let updateButton='<button id=\"a_put_'+paciente.id+'\"'+
                                    'type="button" onClick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                    'Actualizar' + '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la pelicula
            //como ultima columna el boton eliminar
            pacienteRow.innerHTML =
                    '<td class=\"td_id\">' + paciente.id + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_dni\">' + paciente.dni + '</td>'+
                    '<td class=\"td_fechaIngreso\">' + paciente.fechaIngreso + '</td>'+
                    '<td class=\"td_id\">' + paciente.domicilio.id + '</td>' +
                    '<td class=\"td_calle\">' + paciente.domicilio.calle + '</td>'+
                    '<td class=\"td_numeroCalle\">' + paciente.domicilio.numero + '</td>'+
                    '<td class=\"td_localidad\">' + paciente.domicilio.localidad + '</td>'+
                    '<td class=\"td_provincia\">' + paciente.domicilio.provincia + '</td>'+
                    '<td>'+deleteLink+'</td>'+
                    '<td>'+updateButton+'</td>';

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