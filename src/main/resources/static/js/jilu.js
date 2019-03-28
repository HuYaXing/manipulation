// 删除治疗记录
$(".deletejilu").on('click', function () {
    var parent = $(this).parent().parent();
    var recordId = parent.children("td.recordId").text();
    var inform = "您确定要删除" + recordId + " 的信息吗？";
    var r = confirm(inform);
    if (r == true) {
        $.ajax({
            type: "DELETE",
            url: "/record/delete/" + recordId,
            data: {
                recordId:recordId
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