async function handle_new_data(url, data, http_method = 'POST') {

    let post_response = await fetch(url, {
        method: http_method,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    });

    if (!post_response.ok) {
       alert('Fail: ' + post_response.status);
    }

    let get_response = await fetch(url, {
        method: 'GET',
        headers: {
            'Type': 'ajax'
        }
    })

    return get_response.json();
}

let get_date =
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
