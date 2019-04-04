// 新增一条治疗记录
$(".massage").on('click', function () {
    var parent = $(this).parent().parent();
    var membersId = parent.children("td.membersId").text();
    var membersName = parent.children("td.membersName").text();
    var staffId = parent.children("td.staffName").children("#staffNames").find("option:selected").val();
    var staffName = parent.children("td.staffName").children("#staffNames").find("option:selected").text();
    var tuinaName = parent.children("td.tuinaName").children("div.tuinaNames").children("button.tuinaName").text();
    var inform = "您确定要添加"+ staffName +"医师为"+ membersName +"会员进行"+ tuinaName +"推拿记录吗？";
    var r = confirm(inform);
    if (r == true) {
        $.ajax({
            type: "PUT",
            url: "/record",
            data: {
                membersId: membersId,
                membersName: membersName,
                staffName: staffName,
                tuinaType: tuinaName,
                staffId: staffId,
            },
            success: function (msg) {
                alert("添加成功");
                window.location.href = "/record/recordList/1";
            },
            error: function (msg) {
                alert("失败");
            }
        })
    }
    else {
        location.reload(true);
    }
});
// 删除治疗记录
$(".deletejilu").on('click', function () {
    var parent = $(this).parent().parent();
    var recordId = parent.children("td.record").text();
    var inform = "您确定要删除" + recordId + " 的信息吗？";
    var r = confirm(inform);
    if (r == true) {
        $.ajax({
            type: "DELETE",
            url: "/record/delete/" + recordId,
            data: {
                record:recordId
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