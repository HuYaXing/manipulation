// 会员添加
$(".membersAdd").on('click', function () {
    if ($(".membersName").val() == '') {
        alert("请填写会员姓名");
    } else if ($(".pinyinCode").val() == '') {
        alert("请填写会员拼音码");
    } else if ($(".membersPhone").val() == '') {
        alert("请填写会员联系电话");
    } else {
        var membersPhone = $('.membersPhone').val();
        if (/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/g.test(membersPhone)) {
            // 判断手机号是否存在
            $.ajax({
                type: "GET",
                url: "/members/checkPhone",
                data: {
                    membersPhone: $(".membersPhone").val(),
                },
                dataType: "JSON",
                success: function (data) {
                    if (data.code == 0) {
                        $.ajax({
                            type: "PUT",
                            url: "/members",
                            data: {
                                membersName: $(".membersName").val(),
                                pinyinCode: $(".pinyinCode").val(),
                                membersPhone: $(".membersPhone").val()
                            },
                            dataType: "JSON",
                            success: function (data) {
                                if (data.code == 0) {
                                    location.reload(true);
                                } else {

                                }
                            },
                            error: function (msg) {
                                location.reload(true);
                            }
                        })
                    }
                }
            })
        } else {
            alert("手机号格式错误");
        }
    }
})
// 删除会员
$(".deleteMember").on('click', function () {
    var parent = $(this).parent().parent().parent();
    var membersId = parent.children("td.membersId").text();
    var inform = "您确定要删除会员号为 " + membersId + " 的会员信息吗？";
    var r = confirm(inform);
    if (r == true) {
        $.ajax({
            type: "DELETE",
            url: "/members/delete/" + membersId,
            data: {
                membersId:membersId
            },
            dataType: "JSON",
            success: function (data) {
                alert("删除成功");
                location.reload(true);
                },
            error: function (msg) {
                location.reload(true);
            }
        })
    }
    else {

    }
});
// 新增一条治疗记录
$(".zhiLiao").on('click', function () {
    var parent = $(this).parent().parent().parent();
    var membersId = parent.children("td.membersId").text();
    var membersName = parent.children("td.membersName").text();
    // console.log(parent.children("td.selectTd select").val());
    var tuiNaName=$(".selectTd").children("select").val();
    // console.log(tuiNaName);
    var staffName=$(".staffName").children("select").val();
    // 员工Id
    var staffId = $(".staffName").children("select").find("option:selected").attr("data-id");
    console.log(tuiNaName + staffName + staffId);
    var inform = "您确定要添加会员号为 " + membersId + " 的治疗记录吗？";
    var r = confirm(inform);
    if (r == true) {
        $.ajax({
        type: "PUT",
        url: "/record",
        data: {
            membersId:membersId,
            membersName:membersName,
            staffId:staffId,
            staffName:staffName,
            tuinaType:tuiNaName,
        },
        success: function (msg) {
            // alert("添加成功");
            window.location.href = "/record/recordList/1";
            // location.reload(true);
        },
        error: function (msg) {
            alert("失败");
            // location.reload(true);

        }
        })
    }
    else {
        location.reload(true);
    }
});
//数字加减框
$("body").on("click",".num-jian",function (m) {
    var obj = $(this).closest("ul").find(".input-num");
    if (obj.val() <= 0) {
        obj.val(0);
    } else {
        obj.val(parseInt(obj.val()) - 1);
    }
    obj.change();
});
$("body").on("click",".num-jia",function (m) {
    var obj = $(this).closest("ul").find(".input-num");
    obj.val(parseInt(obj.val()) + 1);
    obj.change();
});




