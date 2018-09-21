$(function () {

    $('li.menu').on('click',function (e) {
        $('div.ul-div').slideToggle(500);
        e.stopPropagation();
    })
    $(document).on('click',function (e) {
        $('div.ul-div').slideUp(500);

    })


})