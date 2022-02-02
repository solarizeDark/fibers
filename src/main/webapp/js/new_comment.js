/*
    new comment promise edition
 */

let thread_composer =
    (xhr) =>
    {
        let json_response = xhr?.response;
        for (let i in json_response) {
            document.getElementById('fibers').innerHTML +=
                '<div class=\"item\">' + getDate(json_response[i]) + ' #' + json_response[i].id
                + ' comment to: #' + json_response[i].commentTo + '<br>' + json_response[i].section + '</div>'
        }
    };

let get_req =
    () =>
    {
        return new Promise(function (resolve, reject){
            setTimeout(() => {}, 2000);

            let xhr = new XMLHttpRequest();
            xhr.responseType = 'json';
            xhr.open('GET', location.toString());
            xhr.setRequestHeader('type', 'ajax');
            xhr.send();

            xhr.onload = () => resolve(xhr);
            xhr.onerror = () => reject(new Error('error'));
        })
    };

let post_req =
    (comment) =>
    {
        return new Promise(function (resolve, reject) {
            let xhr = new XMLHttpRequest();

            xhr.open('POST', location.toString());
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send(JSON.stringify(comment));

            xhr.onload = () => resolve();
            xhr.onerror = () => reject(new Error('error'));
        })
    };

document.addEventListener("click", (e) => {
    let data;
    if (e.target.className === "modal-create") {

        try {

            data = new FormData(document.getElementById(e.target.dataset.id));

            if (isNaN(parseInt(data.get('comment_to')))) {
                throw new IdError("id value isn't number");
            }

            let comment = {
                'commentTo': data.get('comment_to'),
                'section': data.get('comment')
            }

            post_req(comment)
                .then(get_req)
                .then((error, xhr) => thread_composer(error, xhr));
        } catch (error) {
            if (error instanceof IdError) {
                alert(error.message);
            } else {
                throw error;
            }
        }
    }
});

let getDate =
    json =>
    {
        let getTimePart = val => parseInt(val) < 10 ? '0' + val : val;

        let date = getTimePart(json.creationDate.date.day);
        let month = getTimePart(json.creationDate.date.month);
        let hour = getTimePart(json.creationDate.time.hour);
        let minute = getTimePart(json.creationDate.time.minute);
        let second = getTimePart(json.creationDate.time.second);

        return date + '-' + month + '-' + json.creationDate.date.year + ' '
            + hour + ':' + minute + ':' + second;
    }