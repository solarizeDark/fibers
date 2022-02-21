window.addEventListener('load', function (){
    document.getElementById('create-button').addEventListener('click', create_comment);
});

async function create_comment() {
    try {
        let data = new FormData(document.getElementById('new_comment'));

        if (isNaN(parseInt(data.get('comment_to').toString()))) {
            throw new IdError('id value isn\'t number');
        }

        let comment = {
            'commentTo': data.get('comment_to'),
            'section': data.get('comment')
        }

        let res = await handle_new_data(location.toString(), comment);
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