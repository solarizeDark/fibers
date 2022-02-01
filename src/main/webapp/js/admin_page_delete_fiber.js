document.addEventListener("click", (e) => {
    let data;
    if (e.target.className === "modal-create") {
        data = new FormData(document.getElementById(e.target.dataset.id));

        let id = {
            'id': data.get('id'),
        }
        makePOSTRequest(id, fibersAll);
    }
});

function fibersAll(xhr) {
    let json_response = xhr?.response;
    for (let i in json_response) {
        document.getElementById('fibers_list').innerHTML +=
            '<tr>' +
            '<td> ${json_response[i].id} </td>' +
            '<td> ${getDate(json_response[i])} </td>' +
            '<td> ${json_response[i].section} </td>' +
            '<td> ${json_response[i].commentTo} </td>' +
            '</tr>';
    }
}