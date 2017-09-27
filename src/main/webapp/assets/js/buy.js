$(function () {
    $('.product-card').click(function () {
        $(".product-card").not(this).removeClass('active');
        $(this).addClass('active');
    });
    $('#win-card').click(function () {
        $('#pid-input').val(1);
    });
    $('#mac-card').click(function () {
        $('#pid-input').val(2);
    });
    $('#alipay-card').click(function () {
        $('#payment-type-input').val(1);
        $('#payment-req-form').submit();
    });
    $('#paypal-card').click(function () {
        $('#payment-type-input').val(2);
        $('#payment-req-form').submit();
    });
});