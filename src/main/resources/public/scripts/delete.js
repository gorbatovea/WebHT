var idField;
document.addEventListener('DOMContentLoaded', function () {
    result = document.getElementsByClassName('result')[0];
    idField = document.getElementsByClassName('form-input_id')[0];
    document.getElementsByClassName('form')[0].addEventListener('submit', function (event) {
        event.preventDefault();
        if (isDigit(idField.value)) {
            idField.style.borderColor = 'initial';
        } else {
            idField.style.borderColor = 'red';
            return;
        }
        request('POST', API_DELETE, function (response) {
            if (response !== ""){
                var entity = JSON.parse(response);
                resultFactory(entity.id, entity.value);
            }
            else {
                resultFactory(undefined);
            }
        }, JSON.stringify({
            id: idField.value
        }));
        idField.value = '';
    });
});