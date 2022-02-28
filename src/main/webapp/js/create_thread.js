window.addEventListener('load', function (){
    document.getElementById('create-button').addEventListener('click', create_thread);
});

async function create_thread() {

    let formData = new FormData(document.getElementById('new_fiber'));

    let options = {
        method: 'POST',
        body: formData
    }

    await fetch(location.toString(), options);

    rebuild_page();

}

async function rebuild_page() {

    let res = await fetch(location.toString(), { method: 'GET' } );


}

let main_page_composer =
    json =>
    {
        let element = document.getElementById('fibers');
        element.innerHTML = "";

        for (let item of json) {
            let div = document.createElement('div');
            div.className = "item";

            let paragraph = document.createElement('p');
            let date = document.createTextNode(get_date(item));
            paragraph.appendChild(date);

            let id = document.createTextNode(' #' + item.id);
            paragraph.appendChild(id);

            let anchor = document.createElement('a');
            anchor.href = `/fiber?fiber_id=${item.id}`;
            anchor.target = '_self';

            let br = document.createElement('br');
            let section = document.createTextNode(item.section);

            anchor.appendChild(br);
            anchor.appendChild(section);
            paragraph.appendChild(anchor);
            div.appendChild(paragraph);
            element.appendChild(div);
        }

    };