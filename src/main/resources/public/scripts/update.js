var idField,
    valueField;

document.addEventListener('DOMContentLoaded', function () {
    result = document.getElementsByClassName('result')[0];
    idField = document.getElementsByClassName('form-input_id')[0];
    valueField = document.getElementsByClassName('form-input_value')[0];
    document.getElementsByClassName('form')[0].addEventListener('submit', function (event) {
        event.preventDefault();
        if (isDigit(idField.value)) {
            idField.style.borderColor = 'initial';
        } else {
            idField.style.borderColor = 'red';
            return;
        }
        request('POST', API_UPDATE, function (response) {
            if (response !== ""){
                var entity = JSON.parse(response);
                resultFactory(entity.id, entity.value);
            }
            else {
                resultFactory(undefined);
            }
        }, JSON.stringify({
            id: idField.value,
            value: valueField.value
        }));
        idField.value = '';
        valueField.value = '';
    });
});