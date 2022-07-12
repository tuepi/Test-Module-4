function showPage() {
    document.getElementById("container").innerHTML = `
    <button style="margin: auto" onclick="showAddForm()">Thêm Thành Phố</button>
    <h1>Danh Sách Thành Phố</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Thành Phố</th>
            <th scope="col">Quốc Gia</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody id="display">

        </tbody>
    </table>`
    findAll()
}


function findAll() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/cities",
        success: function (data) {
            displayList(data);
        }
    });
}

function displayList(data) {
    let str = ""
    for (let i = 0; i < data.length; i++) {
        str += `<tr>
                <th scope="row">${i + 1}</th>
                <td><button onclick="showDetail(${data[i].id})">${data[i].name}</button></td>
                <td>${data[i].country.name}</td>
                <td><a onclick="showEditForm(${data[i].id})">Chỉnh Sửa</a> | 
                <a  onclick="deleteCity(${data[i].id})">Xóa</a></td>
            </tr>`
    }
    display.innerHTML = str
}

function showDetail(id) {
    let str = ``
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/cities/" + id,
        success: function (data) {
            str += `<button onclick="showPage()">Xem Danh Sách Thành Phố</button>
    <h1>Thành phố ${data.name}</h1>
    <h5>Tên: ${data.name}</h5>
    <h5>Quốc Gia: ${data.country.name}</h5>
    <h5>Diện tích: ${data.area}</h5>
    <h5>Dân số: ${data.population}</h5>
    <h5>GDP: ${data.gdp}</h5>
    <h5>Giới thiệu: ${data.description}</h5>
    <button onclick="edit()">Chỉnh sửa</button>
    <button onclick="deleteCity()">Xóa</button>`
            document.getElementById("container").innerHTML = str
        }
    });
}

function showAddForm() {
    let str = `<h1>Thêm Thành Phố</h1>
    <h5>Tên: <input type="text" id="name"></h5>
    <h5>Quốc Gia: <select name="country" id="country">`
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/countries",
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                str+= `<option id="country" value="${data[i].id}">${data[i].name}aa</option>`
            }
        }
    });
    str += `</select></h5>
    <h5>Diện tích: <input type="text" id="area"></h5>
    <h5>Dân số: <input type="text" id="popu"></h5>
    <h5>GDP: <input type="text" id="gdp"></h5>
    <h5>Giới thiệu: <input type="textarea" id="des"></h5>
    <button onclick="addCity()">Nhập Thành phố</button>
    <button onclick="showPage()">Thoát</button>`

    document.getElementById("container").innerHTML = str

}

function addCity() {
    let name = document.getElementById("name").value;
    let country = document.getElementById("country").value;
    let area = document.getElementById("area").value;
    let population = document.getElementById("popu").value;
    let gdp = document.getElementById("gdp").value;
    let description = document.getElementById("des").value;

    let city = {
        name : name,
        country : {
            id : country
        },
        area : area,
        population : population,
        gdp : gdp,
        description : description
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/cities",
        data: city,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function () {
            alert("Thêm thành công!!!")
            showPage();
        },
        error: function (error) {
            console.log(error)
        },
    })
}
