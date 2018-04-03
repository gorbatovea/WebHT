function request(method, url, callback, body) {
    var connection = new XMLHttpRequest();
    connection.open(method, url, false);
    connection.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    connection.send(body);
    if (connection.status === 200) {
        // console.log(url + ' : ' + connection.status);
        if (callback !== null) callback(connection.responseText);
    } else{
        console.error(url + ' : ' + connection.status);
        callback(undefined);
    }
}

function resultFactory(id, key, value) {
    var div = document.createElement('div');
    div.setAttribute('class', 'result-item');
    if (id !== undefined && key !== undefined && value !== undefined){
        var itemId = document.createElement('p');
        var itemKey = document.createElement('p');
        var itemValue = document.createElement('p');
        itemId.innerText = 'id=\"' + id + '\"';
        itemKey.innerText = 'key=\"' + key + '\"';
        itemValue. innerText = 'value=\"' + value + '\"';
        div.appendChild(itemId);
        div.appendChild(itemKey);
        div.appendChild(itemValue);
    } else {
        var item = document.createElement('p');
        item.innerText = 'ERROR!';
        div.appendChild(item);
    }
    if (result.children[0] !== undefined) result.children[0].remove();
    result.appendChild(div);
}