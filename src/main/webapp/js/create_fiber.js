window.addEventListener('load', function (){
    document.getElementById('create-button').addEventListener('click', create_comment);
});

async function create_comment() {
    try {
        let data = new FormData(document.getElementById('new_comment'));

        if (isNaN(parseInt(data.get('comment_to').toString()))) {
            throw new IdError('id value isn\'t number');
        }

        let options = {
            method: 'POST',
            body: data
        }

        await fetch(location.toString(), options);

        let res = await fetch(location.toString(), { method: 'GET' } );
        thread_composer(res);

    } catch (error) {
        if (error instanceof IdError) {
            alert(error.message);
        } else {
            throw error;
        }
    }
}

let thread_composer =
    json =>
    {
        let element = document.getElementById('fibers');
        element.innerHTML = "";
        element.innerHTML
        for (let item of json) {
            element.innerHTML +=
                '<div class=\"item\">' + get_date(item) + ' #' + item.id
                + ' comment to: #' + item.commentTo + '<br>' + item.section + '</div>'
        }
    };

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