$(function () {
    $('#pid-input').val(1);
    $('#order-req-form').on('keyup keypress', function (e) {
        var keyCode = e.keyCode || e.which;
        if (keyCode === 13) {
            e.preventDefault();
            return false;
        }
    });
    $('.product-card').click(function () {
        $(".product-card").not(this).removeClass('active');
        $(this).addClass('active');
    });
    $('#basic-card').click(function () {
        $('#pid-input').val(1);
    });
    $('#professional-card').click(function () {
        $('#pid-input').val(2);
    });
    $('#alipay-card').click(function () {
        $('#payment-type-input').val(1);
        $('#order-req-form').submit();
    });
    $('#paypal-card').click(function () {
        $('#payment-type-input').val(2);
        $('#order-req-form').submit();
    });
});