function getEmployeesForDelete() {
    sendRequest("GET", "api/all/employee", null, getEmployeeSuccessHandler, errorHandler);
}

function getDepartmentsForDelete() {
    sendRequest("GET", "api/all/departments", null, getDepartmentsSuccessHandler, errorHandler);
}

function getDepartmentsSuccessHandler(respData) {
    var dropdown = document.getElementById("selectDepartment");
    for (var i = 0; i < respData.length; i++) {
        var option = document.createElement('option');
        option.value = respData[i].id;
        option.innerHTML = respData[i].description;
        dropdown.appendChild(option);
    }
}

function getEmployeeSuccessHandler(respData) {
    var dropdown = document.getElementById("selectEmployee");
    dropdown.innerHTML = ''; // Clear previous options
    for (var j = 0; j < respData.length; j++) {
        var option = document.createElement('option');
        option.value = respData[j].id;
        option.innerHTML = respData[j].name + " " + respData[j].email;
        dropdown.appendChild(option);
    }
}

function getEmployees() {
    sendRequest("GET", "api/all/employee", null, showEmployeesTable, errorHandler);
}

function getDepartments() {
    sendRequest("GET", "api/all/departments", null, showDepartmentsTable, errorHandler);
}

function showEmployeesTable(respData) {
    var table = document.getElementById("employeeTable");
    table.innerHTML = ''; // Clear previous content

    // Adăugați rândul cu numele coloanelor
    var headerRow = table.insertRow(0);
    var idHeader = headerRow.insertCell(0);
    idHeader.innerHTML = "<b>ID</b>";
    var name = headerRow.insertCell(1);
    name.innerHTML = "<b>Name</b>";
    var manager = headerRow.insertCell(2);
    manager.innerHTML = "<b>Manager</b>";
    var department = headerRow.insertCell(3);
    department.innerHTML = "<b>Department</b>";
    var email = headerRow.insertCell(4);
    email.innerHTML = "<b>Email</b>";


    // Adăugați datele
    for (var j = 0; j < respData.length; j++) {
        var row = table.insertRow(-1);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);

        cell1.innerHTML = respData[j].id;
        cell2.innerHTML = respData[j].name;
        cell3.innerHTML = respData[j].managerName;
        cell4.innerHTML = respData[j].departmentDescription;
        cell5.innerHTML = respData[j].email;
    }
}

function showDepartmentsTable(respData) {
    var table = document.getElementById("departmentTable");
    table.innerHTML = ''; // Clear previous content

    // Adăugați rândul cu numele coloanelor
    var headerRow = table.insertRow(0);
    var idHeader = headerRow.insertCell(0);
    idHeader.innerHTML = "<b>ID</b>";
    var descriptionHeader = headerRow.insertCell(1);
    descriptionHeader.innerHTML = "<b>Description</b>";
    var parentDescriptionHeader = headerRow.insertCell(2);
    parentDescriptionHeader.innerHTML = "<b>Parent Description</b>";

    // Adăugați datele
    for (var i = 0; i < respData.length; i++) {
        var row = table.insertRow(-1);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);

        cell1.innerHTML = respData[i].id;
        cell2.innerHTML = respData[i].description;
        cell3.innerHTML = respData[i].parentDescription;
    }
}

// Funcția pentru a comuta între dropdown-uri și tabele
document.addEventListener("DOMContentLoaded", function () {
    var radios = document.getElementsByName("displayOption");

    for (var i = 0; i < radios.length; i++) {
        radios[i].addEventListener("change", function () {
            if (this.value === "dropdown") {
                document.getElementById("dropdowns").style.display = "block";
                document.getElementById("tables").style.display = "none";
            } else if (this.value === "table") {
                document.getElementById("dropdowns").style.display = "none";
                document.getElementById("tables").style.display = "block";
                getEmployeesForDelete(); // Afișați inițial angajații în tabel
            }
        });
    }
});

function errorHandler(status) {
    alert("err response: " + status); // popup on err.
}





function showAddEmployeeForm() {
    document.getElementById("addEmployeeForm").style.display = "block";
    populateDepartmentDropdown(); // Populați dropdown-ul cu departamente
    populateManagerDropdown(); // Populați dropdown-ul cu manageri
}

var departmentData = {}
var managerData ={}
function populateDepartmentDropdown() {
    var dropdown = document.getElementById("employeeDepartment");
    dropdown.innerHTML = '';

    // Apelează funcția pentru a obține departamentele și populate dropdown-ul
    sendRequest("GET", "api/all/departments", null, function(respData) {
        for (var i = 0; i < respData.length; i++) {
            var option = document.createElement('option');
            option.value = respData[i].id;
            option.innerHTML = respData[i].description;
            dropdown.appendChild(option);

            // Stochează datele pentru a le accesa ulterior
            departmentData[respData[i].id] = {
                id: respData[i].id,
                description: respData[i].description
            };
        }
    }, errorHandler);
}

function populateManagerDropdown() {
    var dropdown = document.getElementById("employeeManager");
    dropdown.innerHTML = '';

    // Apelează funcția pentru a obține managerii și populate dropdown-ul
    sendRequest("GET", "api/all/employee", null, function(respData) {
        for (var i = 0; i < respData.length; i++) {
            var option = document.createElement('option');
            option.value = respData[i].id;
            option.innerHTML = respData[i].name;
            dropdown.appendChild(option);

            // Stochează datele pentru a le accesa ulterior
            managerData[respData[i].id] = {
                id: respData[i].id,
                name: respData[i].name
            };
        }
    }, errorHandler);
}

function addEmployee() {
    var name = document.getElementById("employeeName").value;
    var email = document.getElementById("employeeEmail").value;
    var departmentId = document.getElementById("employeeDepartment").value;
    var managerId = document.getElementById("employeeManager").value;

    var data = {
        "email": email,
        "name": name,
        "departmentDescription": departmentData[departmentId].description,
        "managerName": managerData[managerId].name
    };

    console.log(data.name);
    console.log(data.email);
    console.log(data.departmentDescription);
    console.log(data.managerName);

    // Apelează funcția pentru a adăuga angajatul și reîmprospătați tabela
    sendRequest("POST", "api/create/employee", JSON.stringify(data), function() {
        getEmployees(); // Reîmprospătați tabela cu angajații după adăugarea unui nou angajat
        document.getElementById("addEmployeeForm").style.display = "none"; // Ascundeți formularul după adăugare
    }, errorHandler);
}

function showAddDepartmentForm() {
    document.getElementById("addDepartmentForm").style.display = "block";
    // Alte acțiuni pe care le considerați necesare pentru afișarea formularului de adăugare a departamentului
}

function addDepartment() {
    var description = document.getElementById("departmentDescription").value;
    var parentDepartment = document.getElementById("parentDepartment").value;

    var data = {
        "description": description,
        "parentDepartment": parentDepartment
    };

    // Apelează funcția pentru a adăuga departamentul și reîmprospătați tabela
    sendRequest("POST", "api/create/department", JSON.stringify(data), function() {
        getDepartments(); // Reîmprospătați tabela cu departamentele după adăugarea unui nou departament
        document.getElementById("addDepartmentForm").style.display = "none"; // Ascundeți formularul după adăugare
    }, errorHandler);
}

document.addEventListener("DOMContentLoaded", function () {
    // Ascultați schimbările în dropdown-ul pentru angajați
    document.getElementById("selectEmployee").addEventListener("change", function () {
        var selectedEmployeeId = this.value;
        if (selectedEmployeeId) {
            // Apelați funcția pentru ștergerea angajatului
            deleteEmployee(selectedEmployeeId);
        }
    });
    document.getElementById("selectDepartment").addEventListener("change", function () {
        var selectedDepartmentId = this.value;
        if (selectedDepartmentId) {
            // Apelați funcția pentru ștergerea angajatului
            deleteDepartment(selectedDepartmentId);
        }
    });
});

function deleteEmployee(employeeId) {
    // Obțineți numele și email-ul angajatului din dropdown
    var selectedEmployeeData = employeeId.split(" "); // Presupunând că ID-ul angajatului este format din "ID Nume"
    var employeeName = selectedEmployeeData[1];
    var employeeEmail = selectedEmployeeData[2];

    // Apelați funcția pentru ștergerea angajatului
    sendRequest("DELETE", "api/employee/by/" + employeeName + "/" + employeeEmail, null, function () {
        // Reîncărcați dropdown-ul cu angajați după ștergere
        getEmployeesForDelete();
    }, errorHandler);
}

function deleteDepartment(departmentId) {
    // Obțineți numele și email-ul angajatului din dropdown
    var selectedDepartmentData = departmentId.split(" "); // Presupunând că ID-ul angajatului este format din "ID Nume"
    var departmentDescription = selectedDepartmentData[1];

    // Apelați funcția pentru ștergerea angajatului
    sendRequest("DELETE", "api/department/by/" + departmentDescription, null, function () {
        // Reîncărcați dropdown-ul cu angajați după ștergere
        getDepartmentsForDelete();
    }, errorHandler);
}