$(function () {

    $('#pay-now-button').click(function () {
        $('#payment-req-form').submit();
        $('#info-modal').modal({
            dismissible: false,
            opacity: .5
        });
    });

});
