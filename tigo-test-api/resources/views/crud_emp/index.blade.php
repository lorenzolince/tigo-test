<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de empleados</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container" style="margin-top: 2em;">
        <h2>Empleados</h2>

       
         <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th><th>Nombre</th><th>Rol</th><th>Actualizar</th><th>Borrar</th>
                </tr>
            </thead>
            <tbody>
                @foreach($employees as $employee)
                    <tr>
                      <td>{{ $employee->id }}</td>
                      <td>{{ $employee->name }}</td>
                      <td>{{ $employee->role }}</td>
                       <td>
                          <button type="button" onclick="openEditModal({{ $employee->id }}, '{{ $employee->name }}', '{{ $employee->role }}')">
                           Editar
                          </button>
                        </td> 
                        <td>
                            <form method="POST" action="/employees-crud/{{ $employee->id}}">
                                @csrf
                                <button type="submit">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                @endforeach
            </tbody>
        </table>

        {{-- ➕ Formulario para nuevo empleado --}}
        <h3 style="max-width: 400px; margin: auto;">Nuevo empleado</h3>
<form method="POST" action="/employees-crud/create" style="max-width: 400px; margin: auto;">
    @csrf
    <input type="hidden" id="employees_id" name="id" value="0">
    <div style="margin-bottom: 15px;">
        <label for="name" style="display: block; font-weight: bold; margin-bottom: 5px;">Nombre:</label>
        <input type="text" id="name" name="name" required style="width: 100%; padding: 8px;">
    </div>

    <div style="margin-bottom: 15px;">
        <label for="role" style="display: block; font-weight: bold; margin-bottom: 5px;">Rol:</label>
        <input type="text" id="role" name="role" required style="width: 100%; padding: 8px;">
    </div>

    <button type="submit" style="padding: 10px 20px;">Guardar</button>
</form>
    </div>
 <script>
function openEditModal(id, name, role) {
    document.getElementById('employee_id').value = id;
    document.getElementById('edit_name').value = name;
    document.getElementById('edit_role').value = role;
    document.getElementById('editModal').style.display = 'block';
}

function closeEditModal() {
    document.getElementById('editModal').style.display = 'none';
}
</script>
<div id="editModal" style="
    display: none;
    position: fixed;
    top: 10%;
    left: 50%;
    transform: translateX(-50%);
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    border: 1px solid #ccc;
    z-index: 999;
    box-shadow: 0 5px 15px rgba(0,0,0,0.3);
    max-width: 400px;
    width: 100%;
">
    <form method="POST" action="/employees-crud/create" style="display: flex; flex-direction: column; gap: 15px;">
        @csrf
        <input type="hidden" id="employee_id" name="id">

        <div>
            <label for="edit_name" style="display: block; font-weight: bold; margin-bottom: 5px;">Nombre:</label>
            <input type="text" id="edit_name" name="name" required
                style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px;">
        </div>

        <div>
            <label for="edit_role" style="display: block; font-weight: bold; margin-bottom: 5px;">Rol:</label>
            <input type="text" id="edit_role" name="role" required
                style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px;">
        </div>

        <div style="display: flex; justify-content: space-between;">
            <button type="submit" style="padding: 10px 20px;">Guardar</button>
            <button type="button" onclick="closeEditModal()" style="padding: 10px 20px;">Cancelar</button>
        </div>
    </form>
</div>
</body>
</html>