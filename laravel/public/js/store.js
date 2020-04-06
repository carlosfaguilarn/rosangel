function alertSuccess(message){
    toastr.clear();
    toastr.options = {
        'closeButton':true,
        'progressBar': true,
        'positionClass':'toast-top-right'
    };
    toastr.success(message);
}