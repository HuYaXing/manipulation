// 添加医师
function add(){
    var modaladd = document.getElementById('modaladd');
    modaladd.style.visibility = (modaladd.style.visibility == "visible") ? "hidden" : "visible";
}
$(".staffAdd").on('click', function () {
    var staffPhone = $('.staffPhone').val();
    if ($(".staffName").val() == "") {
        alert("请填写医师姓名");
    } else  if ($(".pinyinCode").val() == "") {
        alert("请填写拼音码");
    } else  if ($(".staffPhone").val() == "") {
        alert("请填写手机号");
    }else{
            $.ajax({
                type: "PUT",
                url: "/staff",
                data: {
                    staffName: $(".staffName").val(),
                    staffPhone: $(".staffPhone").val(),
                    pinyinCode: $(".pinyinCode").val()
                },
                dataType: "JSON",
                success: function (data) {
                    if (data.code == 0) {
                        alert("成功");
                        location.reload(true);
                    } else {

                    }
                },
                error: function (msg) {
                    location.reload(true);
                }
            })
    }
 })

// 删除医师
$(".deletestaff").on('click', function () {
    var parent = $(this).parent().parent();
    var staffId = parent.children("td.staffId").text();
    var staffName = parent.children("td.staffName").text();
    var inform = "您确定要删除" + staffName + " 医师的信息吗？";
    var r = confirm(inform);
    if (r == true) {
        $.ajax({
            type: "DELETE",
            url: "/staff/delete/" + staffId,
            data: {
                staffId:staffId
            },
            // dataType: "JSON",
            success: function (data) {
                alert("删除成功");
                location.reload(true);
            },
            error: function (msg) {
                 alert("删除失败");
                 location.reload(true);
            }
        })
    }
    else {

    }
});