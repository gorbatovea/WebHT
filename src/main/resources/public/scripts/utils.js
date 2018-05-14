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

function resultFactory(id, value) {
    var div = document.createElement('div');
    div.setAttribute('class', 'result-item');
    if (id !== undefined && value !== undefined){
        var itemId = document.createElement('p');
        var itemValue = document.createElement('p');
        itemId.innerText = 'id=\"' + id + '\"';
        itemValue. innerText = 'value=\"' + value + '\"';
        div.appendChild(itemId);
        div.appendChild(itemValue);
    } else {
        var item = document.createElement('p');
        item.innerText = 'ERROR!';
        div.appendChild(item);
    }
    if (result.children[0] !== undefined) result.children[0].remove();
    result.appendChild(div);
}

function isDigit(value) {
    for(var i = 0; i < value.length; i++) {
        if (isNaN(value[i])) return false;
    }
    return true;
}