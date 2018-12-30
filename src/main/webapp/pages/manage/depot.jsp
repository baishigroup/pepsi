<%@page import="com.zking.erp.util.Tools" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String clientIp = Tools.getLocalIp(request);
%>
<!DOCTYPE html>
<html>
<head>
    <title>仓库管理</title>
    <meta charset="utf-8">
    <!-- 指定以IE8的方式来渲染 -->
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/icon.css"/>
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css"/>
    <script type="text/javascript" src="<%=path %>/js/jquery-1.8.0.min.js"></script>
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
            <td>仓库名称：</td>
            <td>
                <input type="text" name="searchName" id="searchName" style="width:100px;"/>
            </td>
            <td>&nbsp;</td>
            <td id="searchRemarkLabel">描述：</td>
            <td>
                <input type="text" name="searchRemark" id="searchRemark" style="width:100px;"/>
            </td>
            <td>&nbsp;</td>
            <td>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="searchBtn">查询</a>&nbsp;&nbsp;
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" id="searchResetBtn">重置</a>
            </td>
        </tr>
    </table>
</div>

<!-- 数据显示table -->
<div id="tablePanel" class="easyui-panel" style="padding:1px;top:300px;" title="仓库列表" iconCls="icon-list"
     collapsible="true" closable="false">
    <table id="tableData" style="top:300px;border-bottom-color:#FFFFFF"></table>
</div>

<div id="depotDlg" class="easyui-dialog" style="width:380px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons" modal="true" cache="false" collapsible="false" closable="true">
    <form id="depotFM" method="post" novalidate>
        <table>
            <tr>
                <td>仓库名称</td>
                <td style="padding:5px">
                    <input name="name" id="name" class="easyui-validatebox"
                           data-options="required:true,validType:'length[2,30]'" style="width: 230px;height: 20px"/>
                </td>
            </tr>
            <tr>
                <td>仓库地址</td>
                <td style="padding:5px">
                    <input name="address" id="address" class="easyui-validatebox"
                           data-options="validType:'length[2,30]'" style="width: 230px;height: 20px"/>
                </td>
            </tr>
            <tr>
                <td>仓储费</td>
                <td style="padding:5px">
                    <input name="warehousing" id="warehousing" class="easyui-numberbox" data-options="min:0,precision:2"
                           style="width: 175px;height: 20px"/>&nbsp;元/天/KG
                </td>
            </tr>
            <tr>
                <td>搬运费</td>
                <td style="padding:5px">
                    <input name="truckage" id="truckage" class="easyui-numberbox" placeholder="如上下搬运20元,则填写10元"
                           data-options="min:0,precision:2" style="width: 215px;height: 20px"/>&nbsp;元
                </td>
            </tr>
            <tr>
                <td>排序</td>
                <td style="padding:5px">
                    <input name="sort" id="sort" class="easyui-textbox" style="width: 230px;height: 20px"/>
                </td>
            </tr>
            <tr>
                <td>描述</td>
                <td style="padding:5px">
                    <textarea name="remark" id="remark" rows="2" cols="2" style="width: 230px;"></textarea>
                </td>
            </tr>
        </table>
        <input type="hidden" name="clientIp" id="clientIp" value="<%=clientIp %>"/>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" id="saveDepot" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" id="cancelDepot" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#depotDlg').dialog('close')">取消</a>
</div>

<script type="text/javascript">
    //初始化界面
    $(function () {
        initTableData();
        initForm();
    });

    //防止表单提交重复
    function initForm() {
        $('#depotFM').form({
            onSubmit: function () {
                return false;
            }
        });
    }

    //初始化表格数据
    function initTableData() {
        $('#tableData').datagrid({
            url: '<%=path %>/cao/depot/findBy.do?type=0',
            //title:'仓库列表',
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
            checkOnSelect: false,
            pagination: true,
            //交替出现背景
            striped: true,
            //loadFilter: pagerFilter,
            pageList:[2,5,10,15],
            pageSize: 10,
            pageNumber: 1,
            columns: [[
                {field: 'id', width: 35, align: "center", checkbox: true},
                {
                    title: '操作', field: 'op', align: "center", width: 60,
                    formatter: function (value, rec) {
                        var str = '';
                        var rowInfo = rec.id + 'AaBb' + rec.name + 'AaBb' + rec.sort + 'AaBb' + rec.remark + 'AaBb'
                            + rec.address + 'AaBb' + rec.warehousing + 'AaBb' + rec.truckage;
                        if (1 == value) {
                            str += '<img title="编辑" src="<%=path%>/js/easyui-1.3.5/themes/icons/pencil.png" style="cursor: pointer;" onclick="editDepot(\'' + rowInfo + '\');"/>&nbsp;&nbsp;&nbsp;';
                            str += '<img title="删除" src="<%=path%>/js/easyui-1.3.5/themes/icons/edit_remove.png" style="cursor: pointer;" onclick="deleteDepot(\'' + rec.id + '\');"/>';
                        }
                        return str;
                    }
                },
                {title: '仓库名称', field: 'name', width: 200},
                {title: '仓库地址', field: 'address', width: 200},
                {title: '仓储费', field: 'warehousing', width: 60},
                {title: '搬运费', field: 'truckage', width: 60},
                {title: '排序', field: 'sort', width: 60},
                {title: '描述', field: 'remark', width: 120}
            ]],
            toolbar: [
                {
                    id: 'addDepot',
                    text: '增加',
                    iconCls: 'icon-add',
                    handler: function () {
                        addDepot();
                    }
                }, '-',
                {
                    id: 'deleteDepot',
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: function () {
                        batDeleteDepot();
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
        if (k == "13" && (obj.id == "name" || obj.id == "sort" || obj.id == "remark")) {
            $("#saveDepot").click();
        }
        //搜索按钮添加快捷键
        if (k == "13" && (obj.id == "searchName" || obj.id == "searchRemark")) {
            $("#searchBtn").click();
        }
    });


    //删除供应商信息
    function deleteDepot(depotID) {
        $.messager.confirm('删除确认', '确定要删除此仓库信息吗？', function (r) {
            if (r) {
                $.ajax({
                    type: "post",
                    url: "<%=path %>/cao/depot/delete.do",
                    dataType: "json",
                    data: ({
                        id: depotID,
                        clientIp: '<%=clientIp %>'
                    }),
                    success: function (tipInfo) {
                        var msg = tipInfo.message;
                        if (msg == '成功') {
                            //加载完以后重新初始化
                            $("#searchBtn").click();
                        }
                        else
                            $.messager.alert('删除提示', '删除仓库信息失败，请稍后再试！', 'error');
                    },
                    //此处添加错误处理
                    error: function () {
                        $.messager.alert('删除提示', '删除仓库信息异常，请稍后再试！', 'error');
                        return;
                    }
                });
            }
        });
    }

    //批量删除供应商
    function batDeleteDepot() {
        var row = $('#tableData').datagrid('getChecked');
        if (row.length == 0) {
            $.messager.alert('删除提示', '没有记录被选中！', 'info');
            return;
        }
        if (row.length == 1) {
            deleteDepot(row[0].id);
            return;
        }
        if (row.length > 1) {
            $.messager.confirm('删除确认', '确定要删除选中的' + row.length + '条仓库信息吗？', function (r) {
                if (r) {
                    var ids = "";
                    for (var i = 0; i < row.length; i++) {
                        if (i == row.length - 1) {
                            ids += row[i].id;
                            break;
                        }
                        //alert(row[i].id);
                        ids += row[i].id + ",";
                    }
                    $.ajax({
                        type: "post",
                        url: "<%=path %>/cao/depot/batchDelete.do",
                        dataType: "json",
                        async: false,
                        data: ({
                            depotIDs: ids,
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
                                $.messager.alert('删除提示', '删除仓库信息失败，请稍后再试！', 'error');
                        },
                        //此处添加错误处理
                        error: function () {
                            $.messager.alert('删除提示', '删除仓库信息异常，请稍后再试！', 'error');
                            return;
                        }
                    });
                }
            });
        }
    }

    //增加
    var url;
    var depotID = 0;
    //保存编辑前的名称
    var orgDepot = "";

    function addDepot() {
        $("#clientIp").val('<%=clientIp %>');
        $('#depotDlg').dialog('open').dialog('setTitle', '<img src="<%=path%>/js/easyui-1.3.5/themes/icons/edit_add.png"/>&nbsp;增加仓库信息');
        $(".window-mask").css({width: webW, height: webH});
        $('#depotFM').form('clear');
        $("#name").focus();

        orgDepot = "";
        depotID = 0;
        url = '<%=path %>/cao/depot/create.do';
    }

    //保存信息
    $("#saveDepot").off("click").on("click", function () {
        if (!$('#depotFM').form('validate'))
            return;
        else if (checkDepotName())
            return;
        else {
            $.ajax({
                type: "post",
                url: url,
                dataType: "json",
                async: false,
                data: ({
                    name: $.trim($("#name").val()),
                    address: $.trim($("#address").val()),
                    warehousing: $.trim($("#warehousing").val()),
                    truckage: $.trim($("#truckage").val()),
                    type: 0,
                    sort: $.trim($("#sort").val()),
                    remark: $.trim($("#remark").val()),
                    clientIp: '<%=clientIp %>'
                }),
                success: function (tipInfo) {
                    if (tipInfo.message) {
                        $('#depotDlg').dialog('close');

                        var opts = $("#tableData").datagrid('options');
                        showDepotDetails();
                    }
                    else {
                        $.messager.show({
                            title: '错误提示',
                            msg: '保存仓库信息失败，请稍后重试!'
                        });
                    }
                },
                //此处添加错误处理
                error: function () {
                    $.messager.alert('提示', '保存仓库信息异常，请稍后再试！', 'error');
                    return;
                }
            });
        }
    });

    //编辑信息
    function editDepot(depotTotalInfo) {
        var depotInfo = depotTotalInfo.split("AaBb");

        $("#clientIp").val('<%=clientIp %>');
        $("#name").focus().val(depotInfo[1]);
        $("#sort").val(depotInfo[2]);
        $("#remark").val(depotInfo[3]);
        $("#address").val(depotInfo[4]);
        $("#warehousing").val(depotInfo[5]);
        $("#truckage").val(depotInfo[6]);

        orgDepot = depotInfo[1];
        $('#depotDlg').dialog('open').dialog('setTitle', '<img src="<%=path%>/js/easyui-1.3.5/themes/icons/pencil.png"/>&nbsp;编辑仓库信息');
        $(".window-mask").css({width: webW, height: webH});
        depotID = depotInfo[0];
        //焦点在名称输入框==定焦在输入文字后面
        $("#name").val("").focus().val(depotInfo[1]);
        url = '<%=path %>/cao/depot/update.do?id=' + depotInfo[0];
    }

    //检查名称是否存在 ++ 重名无法提示问题需要跟进
    function checkDepotName() {
        var name = $.trim($("#name").val());
        //表示是否存在 true == 存在 false = 不存在
        var flag = false;
        //开始ajax名称检验，不能重名
        if (name.length > 0 && (orgDepot.length == 0 || name != orgDepot)) {
            $.ajax({
                type: "post",
                url: "<%=path %>/cao/depot/checkIsNameExist.do",
                dataType: "json",
                async: false,
                data: ({
                    depotID: depotID,
                    name: name
                }),
                success: function (tipInfo) {
                    flag = tipInfo.flag;
                    if (flag) {
                        $.messager.alert('提示', '仓库名称已经存在', 'info');
                        //alert("仓库名称已经存在");
                        //$("#name").val("");
                        return;
                    }
                },
                //此处添加错误处理
                error: function () {
                    $.messager.alert('提示', '检查仓库名称是否存在异常，请稍后再试！', 'error');
                    return;
                }
            });
        }
        return flag;
    }

    //搜索处理
    $("#searchBtn").unbind().bind({
        click: function () {
            showDepotDetails();
        }
    });

    function showDepotDetails() {
        var params={
            name: $.trim($("#searchName").val()),
            type: 0,  //仓库
            remark: $.trim($("#searchRemark").val())
        }
        var options=$("#tableData").datagrid('options');
        options.url="<%=path %>/cao/depot/findBy.do";
        $("#tableData").datagrid('load',params);

    }

    //重置按钮
    $("#searchResetBtn").unbind().bind({
        click: function () {
            $("#searchName").val("");
            $("#searchRemark").val("");

            //加载完以后重新初始化
            $("#searchBtn").click();
        }
    });

    //查看单元（设置）
    $('#setBuilding').click(function () {
        var currentRow = $("#tableData").datagrid("getChecked");
        if (currentRow.length == 0) {
            alert("请选择一条数据再操作！");
            return false;
        }
        parent.addTab(currentRow[0].id + "单元", "<%=path %>/pages/materials/building.jsp?ProjectId=" + currentRow[0].id, "");

    });
</script>
</body>
</html>