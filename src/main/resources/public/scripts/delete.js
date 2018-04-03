var keyField;
document.addEventListener('DOMContentLoaded', function () {
    result = document.getElementsByClassName('result')[0];
    keyField = document.getElementsByClassName('form-input_key')[0];
    document.getElementsByClassName('form')[0].addEventListener('submit', function (event) {
        event.preventDefault();
        request('POST', API_DELETE, function (response) {
            if (response !== ""){
                var entity = JSON.parse(response);
                resultFactory(entity.id, entity.key, entity.value);
            }
            else {
                resultFactory(undefined);
            }
        }, JSON.stringify({
            key: keyField.value
        }));
        keyField.value = '';
    });
});