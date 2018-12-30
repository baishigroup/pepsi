<%@page import="com.zking.erp.util.Tools" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String clientIp = Tools.getLocalIp(request);
%>
<!DOCTYPE html>
<html>
<head>
    <title>经手人管理</title>
    <meta charset="utf-8">
    <!-- 指定以IE8的方式来渲染 -->
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon"/>
    <script type="text/javascript" src="<%=path %>/js/jquery-1.8.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/icon.css"/>
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css"/>
    <script type="text/javascript" src="<%=path %>/js/easyui-1.3.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
</head>
<body>
<!-- 查询 -->
<div id="searchPanel" class="easyui-panel" style="padding:10px;" title="查询窗口" iconCls="icon-search" collapsible="true"
     closable="false">
    <table id="searchTable">
        <tr>
            <td>姓名：</td>
            <td>
                <input name="searchName" id="searchName" style="width:70px;"/>
            </td>
            <td>类型：</td>
            <td>
                <select name="searchType" id="searchType" style="width:70px;">
                    <option value="">全部</option>
                    <option value="业务员">业务员</option>
                    <option value="仓管员">仓管员</option>
                    <option value="财务员">财务员</option>
                </select>
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="searchBtn">查询</a>&nbsp;&nbsp;
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" id="searchResetBtn">重置</a>
            </td>
        </tr>
    </table>
</div>

<!-- 数据显示table -->
<div id="tablePanel" class="easyui-panel" style="padding:1px;top:300px;" title="经手人列表" iconCls="icon-list"
     collapsible="true" closable="false">
    <table id="tableData" style="top:300px;border-bottom-color:#FFFFFF"></table>
</div>

<div id="personDlg" class="easyui-dialog" style="width:380px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons" modal="true" cache="false" collapsible="false" closable="true">
    <form id="personFM" method="post" novalidate>
        <table>
            <tr>
                <td>类型</td>
                <td style="padding:5px">
                    <select name="Type" id="Type" style="width:230px;">
                        <option value="业务员">业务员</option>
                        <option value="仓管员">仓管员</option>
                        <option value="财务员">财务员</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>姓名</td>
                <td style="padding:5px">
                    <input name="Name" id="Name" class="easyui-validatebox"
                           data-options="required:true,validType:'length[2,30]'" style="width: 230px;height: 20px"/>
                </td>
            </tr>
        </table>
        <input type="hidden" name="clientIp" id="clientIp" value="<%=clientIp %>"/>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" id="savePerson" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" id="cancelPerson" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#personDlg').dialog('close')">取消</a>
</div>

<script type="text/javascript">
    var depotList = null;
    var depotID = null;
    //初始化界面
    $(function () {
        //初始化系统基础信息
        initSystemData();
        initSelectInfo();
        initTableData();
        initForm();
    });

    //初始化系统基础信息
    function initSystemData() {
        $.ajax({
            type: "post",
            url: "<%=path%>/cao/depot/getBasicData.do",
            //设置为同步
            async: false,
            dataType: "json",
            success: function (systemInfo) {
                depotList = systemInfo.depotList;
                var msgTip = systemInfo.message;
                if (msgTip == "exceptoin") {
                    $.messager.alert('提示', '查找系统基础信息异常,请与管理员联系！', 'error');
                    return;
                }
            }
        });
    }

    //初始化页面选项卡
    function initSelectInfo() {
        var options = "";

        if (depotList != null) {
            options = "";
            for (var i = 0; i < depotList.length; i++) {
                var depot = depotList[i];
                if (0 == i) {
                    depotID = depot.id;
                }
                options += '<option value="' + depot.id + '">' + depot.name + '</option>';
            }
            $("#ProjectId").empty().append(options);
            $("#searchProjectId").empty().append('<option value="">全部</option>').append(options);
        }
    }

    //防止表单提交重复
    function initForm() {
        $('#personFM').form({
            onSubmit: function () {
                return false;
            }
        });
    }

    //初始化表格数据
    function initTableData() {
        $('#tableData').datagrid({
            url: '<%=path %>/cao/person/findBy.do',
            //title:'经手人列表',
            //iconCls:'icon-save',
            //width:700,
            height: heightInfo,
            nowrap: false,
            rownumbers: false,
            //动画效果
            animate: false,
            //选中单行
            singleSelect: true,
            collapsible: false,
            selectOnCheck: false,
            //fitColumns:true,
            //单击行是否选中
            //checkOnSelect : false,
            pagination: true,
            //交替出现背景
            striped: true,
            //loadFilter: pagerFilter,
            pageList:[2,5,10,15],
            pageSize: 10,
            pageNumber: 1,
            columns: [[
                {field: 'Id', width: 35, align: "center", checkbox: true},
                {title: '姓名', field: 'Name', width: 180},
                {title: '类型', field: 'Type', width: 180},
                {
                    title: '操作', field: 'op', align: "center", width: 130, formatter: function (value, rec) {
                        var str = '';
                        var rowInfo = rec.Id + 'AaBb' + rec.Type + 'AaBb' + rec.Name;
                        if (1 == value) {
                            str += '<img src="<%=path%>/js/easyui-1.3.5/themes/icons/pencil.png" style="cursor: pointer;" onclick="editPerson(\'' + rowInfo + '\');"/>&nbsp;<a onclick="editPerson(\'' + rowInfo + '\');" style="text-decoration:none;color:black;" href="javascript:void(0)">编辑</a>&nbsp;&nbsp;';
                            str += '<img src="<%=path%>/js/easyui-1.3.5/themes/icons/edit_remove.png" style="cursor: pointer;" onclick="deletePerson(\'' + rec.Id + '\');"/>&nbsp;<a onclick="deletePerson(\'' + rec.Id + '\');" style="text-decoration:none;color:black;" href="javascript:void(0)">删除</a>&nbsp;&nbsp;';
                        }
                        return str;
                    }
                }
            ]],
            toolbar: [
                {
                    id: 'addPerson',
                    text: '增加',
                    iconCls: 'icon-add',
                    handler: function () {
                        addPerson();
                    }
                },
                {
                    id: 'deletePerson',
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: function () {
                        batDeletePerson();
                    }
                }
            ],
            onLoadError: function () {
                $.messager.alert('页面加载提示', '页面加载异常，请稍后再试！', 'error');
                return;
            }
        });
    }

    //初始化键盘enter事件
    $(document).keydown(function (event) {
        //兼容 IE和firefox 事件
        var e = window.event || event;
        var k = e.keyCode || e.which || e.charCode;
        //兼容 IE,firefox 兼容
        var obj = e.srcElement ? e.srcElement : e.target;
        //绑定键盘事件为 id是指定的输入框才可以触发键盘事件 13键盘事件 ---遗留问题 enter键效验 对话框会关闭问题
        if (k == "13" && (obj.id == "Type" || obj.id == "Name")) {
            $("#savePerson").click();
        }
        //搜索按钮添加快捷键
        if (k == "13" && (obj.id == "searchType")) {
            $("#searchBtn").click();
        }
    });


    //删除经手人信息
    function deletePerson(personID) {
        $.messager.confirm('删除确认', '确定要删除此经手人信息吗？', function (r) {
            if (r) {
                $.ajax({
                    type: "post",
                    url: "<%=path %>/cao/person/delete.do",
                    dataType: "json",
                    data: ({
                        id: personID,
                        clientIp: '<%=clientIp %>'
                    }),
                    success: function (tipInfo) {
                        var msg = tipInfo.message;
                        if (msg == '成功') {
                            //加载完以后重新初始化
                            $("#searchBtn").click();
                        }
                        else
                            $.messager.alert('删除提示', '删除经手人信息失败，请稍后再试！', 'error');
                    },
                    //此处添加错误处理
                    error: function () {
                        $.messager.alert('删除提示', '删除经手人信息异常，请稍后再试！', 'error');
                        return;
                    }
                });
            }
        });
    }

    //批量删除经手人
    function batDeletePerson() {
        var row = $('#tableData').datagrid('getChecked');
        if (row.length == 0) {
            $.messager.alert('删除提示', '没有记录被选中！', 'info');
            return;
        }
        if (row.length == 1) {
            deletePerson(row[0].id);
            return;
        }
        if (row.length > 0) {
            $.messager.confirm('删除确认', '确定要删除选中的' + row.length + '条经手人信息吗？', function (r) {
                if (r) {
                    var ids = "";
                    for (var i = 0; i < row.length; i++) {
                        if (i == row.length - 1) {
                            ids += row[i].Id;
                            break;
                        }
                        //alert(row[i].id);
                        ids += row[i].Id + ",";
                    }
                    $.ajax({
                        type: "post",
                        url: "<%=path %>/cao/person/batchDelete.do",
                        dataType: "json",
                        async: false,
                        data: ({
                            personIDs: ids,
                            clientIp: '<%=clientIp %>'
                        }),
                        success: function (tipInfo) {
                            var msg = tipInfo.message;
                            if (msg == '成功') {
                                //加载完以后重新初始化
                                $("#searchBtn").click();
                                $(":checkbox").attr("checked", false);
                            }
                            else
                                $.messager.alert('删除提示', '删除经手人信息失败，请稍后再试！', 'error');
                        },
                        //此处添加错误处理
                        error: function () {
                            $.messager.alert('删除提示', '删除经手人信息异常，请稍后再试！', 'error');
                            return;
                        }
                    });
                }
            });
        }
    }

    //增加
    var url;
    var personID = 0;
    //保存编辑前的名称
    var orgPerson = "";

    function addPerson() {
        $("#clientIp").val('<%=clientIp %>');
        $('#personFM').form('clear');
        $('#personDlg').dialog('open').dialog('setTitle', '<img src="<%=path%>/js/easyui-1.3.5/themes/icons/edit_add.png"/>&nbsp;增加经手人信息');
        $(".window-mask").css({width: webW, height: webH});
        $("#name").val("").focus();

        orgPerson = "";
        personID = 0;
        url = '<%=path %>/cao/person/create.do';
    }

    //保存信息
    $("#savePerson").unbind().bind({
        click: function () {
            if (!$('#personFM').form('validate'))
                return;
            else if (checkDepotName())
                return;
            else {
                if (!$("#Type").val()) {
                    $.messager.alert('提示', '请选择类型！', 'warning');
                    return;
                }
                $.ajax({
                    type: "post",
                    url: url,
                    dataType: "json",
                    async: false,
                    data: ({
                        Type: $.trim($("#Type").val()),
                        Name: $.trim($("#Name").val()),
                        clientIp: '<%=clientIp %>'
                    }),
                    success: function (tipInfo) {
                        if (tipInfo.message) {
                            $('#personDlg').dialog('close');

                            var opts = $("#tableData").datagrid('options');
                            showPersonDetails();
                        }
                        else {
                            $.messager.show({
                                title: '错误提示',
                                msg: '保存经手人信息失败，请稍后重试!'
                            });
                        }
                    },
                    //此处添加错误处理
                    error: function () {
                        $.messager.alert('提示', '保存经手人信息异常，请稍后再试！', 'error');
                        return;
                    }
                });
            }
        }
    });

    //编辑信息
    function editPerson(personTotalInfo) {
        var personInfo = personTotalInfo.split("AaBb");

        $("#clientIp").val('<%=clientIp %>');
        $("#Type").val(personInfo[1]);
        $("#Name").val(personInfo[2]);

        //orgPerson = personInfo[1];
        $('#personDlg').dialog('open').dialog('setTitle', '<img src="<%=path%>/js/easyui-1.3.5/themes/icons/pencil.png"/>&nbsp;编辑经手人信息');
        $(".window-mask").css({width: webW, height: webH});
        personID = personInfo[0];
        //焦点在名称输入框==定焦在输入文字后面
        $("#Name").val("").focus().val(personInfo[2]);
        url = '<%=path %>/cao/person/update.do?id=' + personInfo[0];
    }

    //检查名称是否存在 ++ 重名无法提示问题需要跟进
    function checkDepotName() {
        var name = $.trim($("#Name").val());
        //表示是否存在 true == 存在 false = 不存在
        var flag = false;
        //开始ajax名称检验，不能重名
        if (name.length > 0 && (orgPerson.length == 0 || name != orgPerson)) {
            $.ajax({
                type: "post",
                url: "<%=path %>/cao/person/checkIsNameExist.do",
                dataType: "json",
                async: false,
                data: ({
                    id: depotID,
                    name: name
                }),
                success: function (tipInfo) {
                    flag = tipInfo.flag;
                    if (flag) {
                        $.messager.alert('提示', '经手人名称已经存在', 'info');
                        return;
                    }
                },
                //此处添加错误处理
                error: function () {
                    $.messager.alert('提示', '检查经手人名称是否存在异常，请稍后再试！', 'error');
                    return;
                }
            });
        }
        return flag;
    }

    //搜索处理
    $("#searchBtn").unbind().bind({
        click: function () {
            showPersonDetails();
        }
    });

    function showPersonDetails() {
        var params={
            Name: $.trim($("#searchName").val()),
            Type: $.trim($("#searchType").val())
        }
        var options=$("#tableData").datagrid('options');
        options.url="<%=path %>/cao/person/findBy.do";
        $("#tableData").datagrid('load',params);

    }

    //重置按钮
    $("#searchResetBtn").unbind().bind({
        click: function () {
            $("#searchProjectId").val("");
            $("#searchType").val("");

            //加载完以后重新初始化
            $("#searchBtn").click();
        }
    });

</script>
</body>
</html>