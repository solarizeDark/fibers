async function handle_new_data(url, data) {
    let post_response = await fetch(url, {
       method: 'POST',
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

window.addEventListener('load', function (){
    document.getElementById('create-button').addEventListener('click', create_comment);
});

async function create_comment() {
    try {
        let data = new FormData(document.getElementById('new_comment'));

        if (isNaN(parseInt(data.get('comment_to')))) {
            throw new IdError('id value isn\'t number');
        }

        let comment = {
            'commentTo': data.get('comment_to'),
            'section': data.get('comment')
        }

        let res = await handle_new_data(location.toString(), comment)
        thread_composer(res);

    } catch (error) {
        if (error instanceof IdError) {
            alert(error.message);
        } else {
            throw error;
        }
    }
}
