document.addEventListener("click", (e) => {
    let data;
    if (e.target.className === "modal-create") {
        data = new FormData(document.getElementById(e.target.dataset.id));

        if (isNaN(parseInt(data.get('comment_to')))) {
            alert("Incorrect id value");
            return;
        }

        let comment = {
            'commentTo': data.get('comment_to'),
            'section': data.get('comment')
        }
        makePOSTRequest(comment, fiberFlow);
    }
});

function makePOSTRequest(data, callback) {

    let xhr = new XMLHttpRequest();

    xhr.onload = function () {
        if (xhr.status === 500) {
            alert(xhr.statusText);
        }
    }

    xhr.open("POST", location.toString());
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(data));
    xhr.onload = makeGETRequest(callback);
}

async function makeGETRequest(callback) {

    let requestURL = new URL(location.toString());

    await new Promise(r => setTimeout(r, 2000));

    let xhr = new XMLHttpRequest();
    xhr.responseType = 'json';
    xhr.open('GET', requestURL.toString());
    xhr.setRequestHeader('type', 'ajax');
    xhr.send();

    xhr.onload = callback(xhr);
}

function fiberFlow(xhr) {
    let json_response = xhr?.response;
    for (let i in json_response) {
        document.getElementById('fibers').innerHTML +=
            '<div class=\"item\">' + getDate(json_response[i]) + ' #' + json_response[i].id
            + ' comment to: #' + json_response[i].commentTo + '<br>' + json_response[i].section + '</div>'
    }
}

function getDate(json) {
    let date = parseInt(json.creationDate.date.day) < 10 ?
        '0' + json.creationDate.date.day : json.creationDate.date.day;
    let month = parseInt(json.creationDate.date.month) < 10 ?
        '0' + json.creationDate.date.month : json.creationDate.date.month;
    let hour = parseInt(json.creationDate.time.hour) < 10 ?
        '0' + json.creationDate.time.hour : json.creationDate.time.hour;
    let minute = parseInt(json.creationDate.time.minute) < 10 ?
        '0' + json.creationDate.time.minute : json.creationDate.time.minute;
    let second = parseInt(json.creationDate.time.second) < 10 ?
        '0' + json.creationDate.time.second : json.creationDate.time.second;

    return date + '-' + month + '-' + json.creationDate.date.year + ' '
                + hour + ':' + minute + ':' + second;
}



