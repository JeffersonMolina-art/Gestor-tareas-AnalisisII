document.addEventListener('DOMContentLoaded', function() {
    const addTasksBtn = document.getElementById('add-tasks');
    const addTaskModal = document.getElementById('add-task');
    const closeAddBtn = document.getElementById('close-add');
    const addBtn = document.getElementById('add');
    const contentTask = document.querySelector('.content-task');

    const updateTaskModal = document.getElementById('update-task');
    const closeUpdateBtn = document.getElementById('close-update');
    const updateBtn = document.getElementById('update');

    const updTitulo = document.getElementById('upd-titulo');
    const updContenido = document.getElementById('upd-contenido');
    const updEstado = document.getElementById('upd-estado');
    const updFecha = document.getElementById('upd-fecha');
    let taskToUpdateId = null;

    let maxTaskId = 0;

    function fetchNotas() {
        fetch("http://localhost:8080/notas/listar")
            .then(response => response.json())
            .then(data => {
                contentTask.innerHTML = '';
                data.forEach(item => {
                    if (item.taskIde > maxTaskId) {
                        maxTaskId = item.taskIde;
                    }

                    const fila = document.createElement('tr');
                    const titulo = document.createElement('td');
                    const contenido = document.createElement('td');
                    const estado = document.createElement('td');
                    const fecha = document.createElement('td');
                    const acciones = document.createElement('td');
                    taskToUpdateId = item.taskIde;
                    titulo.textContent = item.title;
                    contenido.textContent = item.content;
                    estado.textContent = item.estado;
                    fecha.textContent = new Date(item.fecha).toLocaleDateString();

                    const updateBtn = document.createElement('button');
                    updateBtn.textContent = 'Modificar';
                    updateBtn.classList.add('btn', 'btn-primary', 'me-2');
                    updateBtn.onclick = () => {
                        taskToUpdateId = item.taskIde;
                        updTitulo.value = item.title;
                        updContenido.value = item.content;
                        updEstado.value = item.estado;
                        updFecha.value = item.fecha.split('T')[0];
                        updateTaskModal.style.display = 'flex';
                    };

                    const deleteBtn = document.createElement('button');
                    deleteBtn.textContent = 'Eliminar';
                    deleteBtn.classList.add('btn', 'btn-danger');
                    deleteBtn.onclick = () => deleteNota(item.taskIde);

                    acciones.appendChild(updateBtn);
                    acciones.appendChild(deleteBtn);

                    fila.appendChild(titulo);
                    fila.appendChild(contenido);
                    fila.appendChild(estado);
                    fila.appendChild(fecha);
                    fila.appendChild(acciones);

                    contentTask.appendChild(fila);
                });
            })
            .catch(error => console.error('Error:', error));
    }

    addTasksBtn.onclick = function() {
        addTaskModal.style.display = 'flex';
    };

    closeAddBtn.onclick = function() {
        addTaskModal.style.display = 'none';
    };

    closeUpdateBtn.onclick = function() {
        updateTaskModal.style.display = 'none';
    };

    window.onclick = function(event) {
        if (event.target == addTaskModal) {
            addTaskModal.style.display = 'none';
        } else if (event.target == updateTaskModal) {
            updateTaskModal.style.display = 'none';
        }
    };

    addBtn.onclick = async function() {
        try {
            const titulo = document.getElementById('titulo').value;
            const contenido = document.getElementById('contenido').value;
            const estado = document.getElementById('estado').value;
            const fecha = document.getElementById('fecha').value;
    
            maxTaskId += 1;
    
            const response = await fetch('http://localhost:8080/notas/agregar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    taskIde: maxTaskId,
                    title: titulo,
                    content: contenido,
                    fecha: fecha,
                    estado: estado
                })
            });
    
            if (response.ok) {
                addTaskModal.style.display = 'none';
                await fetchNotas(); 
                location.reload();
            } else {
                console.error("Error al guardar tarea");
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };
    
    updateBtn.onclick = function() {
        const titulo = updTitulo.value;
        const contenido = updContenido.value;
        const estado = updEstado.value;
        const fecha = updFecha.value;

        fetch(`http://localhost:8080/notas/actualizar/${taskToUpdateId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                taskIde: taskToUpdateId,
                title: titulo,
                content: contenido,
                fecha: fecha,
                estado: estado
            })
        })
        .then(response => {
            if (response.ok) {
                updateTaskModal.style.display = 'none';
                fetchNotas();
                location.reload();
            } else {
                console.error("Error al actualizar tarea");
            }
            
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    };

    function deleteNota(taskIde) {
        fetch(`http://localhost:8080/notas/eliminar/${taskIde}`, {
            method: 'DELETE'
        })
        .then(response => response.text())
        .then(data => {
            console.log('Success:', data);
            fetchNotas();
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }
    fetchNotas();
});
