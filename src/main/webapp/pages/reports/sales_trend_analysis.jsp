<%@page import="com.zking.erp.util.Tools" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String clientIp = Tools.getLocalIp(request);
%>
<html>
<head>
    <title>销售趋势分析</title>
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

    <script type="text/javascript" src="<%=path %>/js/highcharts/highcharts.js"></script>
    <script type="text/javascript" src="<%=path %>/js/highcharts/oldie.js"></script>
    <script type="text/javascript" src="<%=path %>/js/highcharts/exporting.js"></script>
    <script type="text/javascript" src="<%=path %>/js/highcharts/highcharts-zh_CN.js"></script>
    <script>
        var kid = "${sessionScope.user.id}";
        var path = "<%=path%>";
        var clientIp = "<%=clientIp%>";
        </script>


</head>
<body>

<!-- 查询 -->
<div data-options="region:'north',split:true,border:false"
     style="height: 80px; line-height: 60px; padding-left: 10px;">
        <label>年份：</label><select id="year" class="easyui-combobox" data-options="valueField:'id',textField:'text'"   style="height: 25px;">
            <option value="">---请选择---</option>
        </select>

        <a id="btn_search" href="javascript:void(0)" class="easyui-linkbutton"
           data-options="iconCls:'icon-search'">查询</a>
</div>

<div id="container"></div>

<script type="text/javascript" src="<%=path %>/js/highcharts/aa.js"></script>

</body>
</html>
