//添加员工
$('.btn btn-warning into').on('click',function () {
    $.ajax({
        type: "put",
        url: "/staff",
        data: {
            'staffName':$('.form-control').val(),
            "staffPhone": $('.form-control').val()
        },
        async: false
    });
});