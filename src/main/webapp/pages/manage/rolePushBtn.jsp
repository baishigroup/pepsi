<%@page import="com.zking.erp.util.Tools" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String clientIp = Tools.getLocalIp(request);
%>
<!DOCTYPE html>
<html>
<head>
    <title>角色分配按钮</title>
    <meta charset="utf-8">
    <!-- 指定以IE8的方式来渲染 -->
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon"/>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui-1.3.5/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui-1.3.5/themes/icon.css"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/common.css"/>
    <script type="text/javascript" src="<%=path%>/js/easyui-1.3.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=path%>/js/common/common.js"></script>
</head>
<body>
<!-- 数据显示table -->
<div style="padding-bottom: 10px;">
    <a id="btnOK" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
</div>
<div id="pushList">
    <!-- 数据显示table -->
    <div id="tablePanel" class="easyui-panel" style="padding:1px; top:300px;" title="功能列表-选择按钮权限" iconCls="icon-list"
         closable="false">
        <table id="tableData" style="top:300px;border-bottom-color:#FFFFFF"></table>
    </div>
</div>

<script type="text/javascript">
    var keyId = getUrlParam('id');  //获取传值id（角色id）
    var pushBtnJSON;
    var userBusinessId = 0; //关系id
    var roleBtnStr = ""; //角色按钮权限
    //初始化界面
    $(function () {
        initJSON();
        initTableData();
        showDetails(1, initPageSize);
        bindEvent();
    });

    //初始化JSON
    function initJSON() {
        var url = '<%=path %>/js/pages/manage/pushBtn.json';
        $.ajax({
            url: url,
            type: "get",
            async: false,
            success: function (res) {
                if (res) {
                    pushBtnJSON = res;
                }
            },
            error: function () {

            }
        });
    }

    //初始化表格数据
    function initTableData() {
        $('#tableData').datagrid({
            height: 360,
            rownumbers: true,
            //动画效果
            animate: false,
            //交替出现背景
            striped: true,
            columns: [[
                {field: 'Id', align: "center", hidden: true},
                {title: '名称', field: 'Name', width: 100},
                {
                    title: '按钮列表', field: 'PushBtnList', width: 400, formatter: function (value, rec) {
                        var btnStr = rec.PushBtn;
                        if (btnStr != undefined) {
                            var arr = btnStr.split(",");
                            if (arr.length) {
                                var str = "";
                                for (var i = 0; i < arr.length; i++) {
                                    for (var j = 0; j < pushBtnJSON.length; j++) {
                                        if (pushBtnJSON[j].id === arr[i] - 0) {
                                            str += "<input type='checkbox' value='" + pushBtnJSON[j].id + "' />" + pushBtnJSON[j].text;
                                        }
                                    }
                                }
                                return str;

                            }
                        }
                        else {
                            return "";
                        }
                    }
                }
            ]],
            onLoadError: function () {
                $.messager.alert('页面加载提示', '页面加载异常，请稍后再试！', 'error');
                return;
            }
        });
    }



    function showDetails() {
        $.ajax({
            url: "<%=path%>/userBusiness/getBasicData.do",
            type: "get",
            data: {
                type: 'RoleFunctions',
                keyid: keyId
            },
            success: function (res) {
                if (res  && res.userBusinessList && res.userBusinessList[0]) {
                    userBusinessId = res.userBusinessList[0].id;
                    roleBtnStr = res.userBusinessList[0].btnstr;
                    var getValue = res.userBusinessList[0].value;
                    getValue = getValue.substring(1, getValue.length - 1);
                    if (getValue.indexOf("][")) {
                        var arr = getValue.split("][");
                        arr = arr.toString();
                        $.ajax({
                            url: "<%=path%>/functions/findByIds.do",
                            type: "get",
                            data: {
                                FunctionsIDs: arr
                            },
                            success: function (rec) {
                                $("#tableData").datagrid('loadData', rec);
                            },
                            error: function () {
                                $.messager.alert('提示', '查询数据异常，请稍后再试！', 'error');
                                return;
                            }
                        });
                    }
                }
            },
            error: function () {
                $.messager.alert('提示', '查询数据异常，请稍后再试！', 'error');
                return;
            }
        });
    }

    function bindEvent() {
        setTimeout(function () {
            var bindBody = $("#pushList .datagrid-view2 .datagrid-body");

            //加载按钮信息到缓存中
            if (roleBtnStr) {
                var roleBtnStrArr = JSON.parse(roleBtnStr);
                bindBody.find(".datagrid-row").each(function () {
                    for (var i = 0; i < roleBtnStrArr.length; i++) {
                        if (roleBtnStrArr[i] && roleBtnStrArr[i].funId) {
                            var thisId = $(this).find("[field='Id']").text() - 0;
                            if (roleBtnStrArr[i].funId == thisId) {
                                $(this).find("[field='Id']").attr("data-btn", roleBtnStrArr[i].btnStr);
                                //加载勾选状态
                                $(this).find("[field='PushBtnList']").find("input").each(function () {
                                        var thisValue = $(this).val(); //勾选的值
                                        if (roleBtnStrArr[i].btnStr.indexOf(thisValue) > -1) {
                                            $(this).prop("checked", "checked");
                                        }
                                });
                            }
                        }
                    }
                });
            }

            //按钮勾选事件
            bindBody.find("[field='PushBtnList']").find("input").off("click").on("click", function () {
                var checkStr = "";
                $(this).closest(".datagrid-cell").find("input").each(function () {
                    var thisValue = $(this).val(); //勾选的值
                    var isChecked = $(this).prop("checked");
                    if (isChecked) {
                        checkStr = checkStr + thisValue + ",";
                    }
                });
                if (checkStr) {
                    checkStr = checkStr.substring(0, checkStr.length - 1);
                }
                $(this).closest(".datagrid-row").find("[field='Id']").attr("data-btn", checkStr);
            });
        }, 500);

        $("#btnOK").off("click").on("click", function () {
            if (confirm("您确定要保存吗？")) {
                var bindList = $("#pushList .datagrid-view2 .datagrid-body .datagrid-row");
                var bindArr = [];
                var btnStr = "";
                bindList.each(function () {
                    var funId = $(this).find("[field='Id']").text();
                    var btnStr = $(this).find("[field='Id']").attr("data-btn");
                    if (btnStr !== undefined && btnStr !== "") {
                        var bindJSON = {};
                        bindJSON.funId = funId;
                        bindJSON.btnStr = btnStr;
                        bindArr.push(bindJSON);
                    }
                });
                if (bindArr.length) {
                    btnStr = JSON.stringify(bindArr);
                }
                $.ajax({
                    url: "<%=path%>/userBusiness/updateBtnStr.do",
                    type: "get",
                    data: {
                        UserBusinessID: userBusinessId,
                        Btnstr: btnStr
                    },
                    success: function (res) {
                        var res=res.flag;
                        if (res) {
                            self.parent.$.colorbox.close();
                            alert("操作成功！");
                        }
                        else {
                            $.messager.show({
                                title: '错误提示',
                                msg: '保存按钮权限信息失败，请稍后重试!'
                            });
                        }
                    },
                    error: function () {
                        $.messager.alert('提示', '保存数据异常，请稍后再试！', 'error');
                        return;
                    }
                });
            }
        });
    }
</script>
</body>
</html>