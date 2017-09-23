$(function () {
    $('.button-collapse').sideNav();
    $('.collapsible').collapsible();
    $('.dropdown-button').dropdown();
    $('.parallax').parallax();
    $('.product-card').click(function () {
        $(".product-card").not(this).removeClass('active');
        $(this).addClass('active');
    });
});