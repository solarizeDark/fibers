async function get_data(url, data) {
    return fetch(url,
        {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
    .then(
        () => {
            setTimeout(() => {}, 2000);
            return fetch(url,
                {
                        method: 'GET',
                        headers: {
                            'Type': 'ajax'
                        }
                    }
            )
        })
    .then(response => response.json());
}

document.addEventListener("click", async (e) => {
    if (e.target.className === "modal-create") {

        try {

            let data = new FormData(document.getElementById(e.target.dataset.id));

            if (isNaN(parseInt(data.get('comment_to')))) {
                throw new IdError("id value isn't number");
            }

            let comment = {
                'commentTo': data.get('comment_to'),
                'section': data.get('comment')
            }
            let response = await get_data(location.toString(), comment);
            thread_composer(response);
        } catch (error) {
            if (error instanceof IdError) {
                alert(error.message);
            } else {
                throw error;
            }
        }
    }
});

let thread_composer =
    json =>
        {
            for (let item of json) {
                document.getElementById('fibers').innerHTML +=
                    '<div class=\"item\">' + getDate(item) + ' #' + item.id
                    + ' comment to: #' + item.commentTo + '<br>' + item.section + '</div>'
            }
        };

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