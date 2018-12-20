<%@page import="com.zking.erp.util.Tools" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String clientIp = Tools.getLocalIp(request);
%>
<!DOCTYPE html>
<html>
<head>
    <title>单据明细</title>
    <meta charset="utf-8">
    <!-- 指定以IE8的方式来渲染 -->
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/icon.css"/>
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css"/>
    <link type="text/css" rel="stylesheet" href="<%=path %>/css/bill_detail.css"/>
    <script src="<%=path %>/js/jquery-1.8.0.min.js"></script>
    <script src="<%=path %>/js/easyui-1.3.5/jquery.easyui.min.js"></script>
    <script src="<%=path %>/js/easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=path %>/js/common/common.js"></script>
    <script src="<%=path %>/js/pages/materials/bill_detail.js"></script>
    <script>
        var kid = ${sessionScope.user.id};
        var path = "<%=path%>";
        var clientIp = "<%=clientIp%>";
    </script>
</head>
<body>
<div id="bill" class="easyui-panel" style="padding:10px;height:500px;" title="单据明细" iconCls="icon-list"
     collapsible="true" closable="false">
    <%--零售出库--%>
    <div class="retail_out" style="width:1100px;padding:10px 20px;top:50px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;height:50px;">会员卡号：</td>
                <td style="padding:5px;width:200px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:200px;">
                    <span class="OperTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:200px;">
                    <span class="NumberShow"></span>
                </td>
                <td style="width:70px;">付款类型：</td>
                <td style="padding:5px;width:150px;">
                    <span class="payTypeShow"></span>
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <!-- 商品列表table -->
                    <table class="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
                <td colspan="2" valign="top">
                    <table width="100%" class="retail-amount-show">
                        <tr>
                            <td colspan="2">实收金额</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b><span class="change-amount-show"></span></b>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">收款金额</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b><span class="get-amount-show"></span></b>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">找零</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b><span class="back-amount-show"></span></b>
                            </td>
                        </tr>
                        <tr>
                            <td>收款账户：</td>
                            <td align="left" style="width:110px;">
                                <span class="AccountIdShow"></span>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="8" style="width: 1130px; height:35px;">
                    <span class="RemarkShow"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--零售退货--%>
    <div class="retail_back" style="width:1100px;padding:10px 20px;top:50px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;height:50px;">会员卡号：</td>
                <td style="padding:5px;width:200px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:200px;">
                    <span class="OperTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:200px;">
                    <span class="NumberShow"></span>
                </td>
                <td style="width:70px;"></td>
                <td style="padding:5px;width:150px;">
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <!-- 商品列表table -->
                    <table class="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
                <td colspan="2" valign="top">
                    <table width="100%" class="retail-amount-show">
                        <tr>
                            <td colspan="2">实付金额</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b><span class="change-amount-show"></span></b>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">付款金额</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b><span class="get-amount-show"></span></b>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">找零</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b><span class="back-amount-show"></span></b>
                            </td>
                        </tr>
                        <tr>
                            <td>付款账户：</td>
                            <td align="left" style="width:110px;">
                                <span class="AccountIdShow"></span>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="8" style="width: 1130px; height:35px;">
                    <span class="RemarkShow"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--采购入库--%>
    <div class="purchase_in" style="width:1100px;padding:10px 20px;top:20px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">供应商：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OperTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="NumberShow"></span>
                </td>
                <td style="width:70px;"></td>
                <td style="padding:5px;width:140px;"></td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 商品列表table -->
                    <table class="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
            <tr>
                <td>优惠率：</td>
                <td>
                    <span class="DiscountShow" style="width: 110px;"></span>
                    %
                </td>
                <td>付款优惠：</td>
                <td>
                    <span class="DiscountMoneyShow" style="width: 120px;"></span>
                </td>
                <td>优惠后金额：</td>
                <td>
                    <span class="DiscountLastMoneyShow" style="width: 120px;"></span>
                </td>
                <td>本次付款：</td>
                <td style="padding:5px">
                    <span class="ChangeAmountShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td>结算账户：</td>
                <td style="padding:5px">
                    <span class="AccountIdShow"></span>
                </td>
                <td>本次欠款：</td>
                <td style="padding:5px">
                    <span class="DebtShow"></span>
                </td>
                <td>采购费用：</td>
                <td style="padding:5px">
                    <span class="OtherMoneyShow"></span>
                </td>
                <td>结算天数：</td>
                <td style="padding:5px">
                    <span class="AccountDayShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
        </table>
    </div>
    <%--采购退货--%>
    <div class="purchase_back" style="width:1100px;padding:10px 20px;top:20px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">供应商：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OperTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="NumberShow"></span>
                </td>
                <td style="width:70px;"></td>
                <td style="padding:5px;width:140px;"></td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 商品列表table -->
                    <table class="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
            <tr>
                <td>优惠率：</td>
                <td>
                    <span class="DiscountShow" style="width: 110px;"></span>
                    %
                </td>
                <td>付款优惠：</td>
                <td>
                    <span class="DiscountMoneyShow" style="width: 120px;"></span>
                </td>
                <td>优惠后金额：</td>
                <td>
                    <span class="DiscountLastMoneyShow" style="width: 120px;"></span>
                </td>
                <td>本次付款：</td>
                <td style="padding:5px">
                    <span class="ChangeAmountShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td>结算账户：</td>
                <td style="padding:5px">
                    <span class="AccountIdShow"></span>
                </td>
                <td>本次欠款：</td>
                <td style="padding:5px">
                    <span class="DebtShow"></span>
                </td>
                <td>采购费用：</td>
                <td style="padding:5px">
                    <span class="OtherMoneyShow"></span>
                </td>
                <td></td>
                <td style="padding:5px">
                </td>
                <td style="width:100px;"></td>
            </tr>
        </table>
    </div>
    <%--销售出库--%>
    <div class="sale_out" style="width:1100px;padding:10px 20px;top:20px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">客户：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">销售人员：</td>
                <td style="padding:5px;width:130px;">
                    <span class="SalesmanShow"></span>
                </td>
                <td style="width:80px;">单据日期：</td>
                <td style="padding:5px;width:140px;">
                    <span class="OperTimeShow"></span>
                </td>
                <td style="width:70px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="NumberShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 商品列表table -->
                    <table class="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
            <tr>
                <td>优惠率：</td>
                <td>
                    <span class="DiscountShow" style="width: 110px;"></span>
                    %
                </td>
                <td>收款优惠：</td>
                <td>
                    <span class="DiscountMoneyShow" style="width: 120px;"></span>
                </td>
                <td>优惠后金额：</td>
                <td>
                    <span class="DiscountLastMoneyShow" style="width: 120px;"></span>
                </td>
                <td>本次收款：</td>
                <td style="padding:5px">
                    <span class="ChangeAmountShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td>结算账户：</td>
                <td style="padding:5px">
                    <span class="AccountIdShow"></span>
                </td>
                <td>本次欠款：</td>
                <td style="padding:5px">
                    <span class="DebtShow"></span>
                </td>
                <td>销售费用：</td>
                <td style="padding:5px">
                    <span class="OtherMoneyShow"></span>
                </td>
                <td>结算天数：</td>
                <td style="padding:5px">
                    <span class="AccountDayShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
        </table>
    </div>
    <%--销售退货--%>
    <div class="sale_back" style="width:1100px;padding:10px 20px;top:20px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">客户：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">销售人员：</td>
                <td style="padding:5px;width:130px;">
                    <span class="SalesmanShow"></span>
                </td>
                <td style="width:80px;">单据日期：</td>
                <td style="padding:5px;width:140px;">
                    <span class="OperTimeShow"></span>
                </td>
                <td style="width:70px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="NumberShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 商品列表table -->
                    <table class="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
            <tr>
                <td>优惠率：</td>
                <td>
                    <span class="DiscountShow" style="width: 110px;"></span>
                    %
                </td>
                <td>退款优惠：</td>
                <td>
                    <span class="DiscountMoneyShow" style="width: 120px;"></span>
                </td>
                <td>优惠后金额：</td>
                <td>
                    <span class="DiscountLastMoneyShow" style="width: 120px;"></span>
                </td>
                <td>本次退款：</td>
                <td style="padding:5px">
                    <span class="ChangeAmountShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td>结算账户：</td>
                <td style="padding:5px">
                    <span class="AccountIdShow"></span>
                </td>
                <td>本次欠款：</td>
                <td style="padding:5px">
                    <span class="DebtShow"></span>
                </td>
                <td>销售费用：</td>
                <td style="padding:5px">
                    <span class="OtherMoneyShow"></span>
                </td>
                <td></td>
                <td style="padding:5px">
                </td>
                <td style="width:100px;"></td>
            </tr>
        </table>
    </div>
    <%--其它入库--%>
    <div class="other_in" style="width:1100px;padding:10px 20px;top:70px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">供应商：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OperTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="NumberShow"></span>
                </td>
                <td style="width:70px;"></td>
                <td style="padding:5px;width:140px;"></td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 商品列表table -->
                    <table class="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--其它出库--%>
    <div class="other_out" style="width:1100px;padding:10px 20px;top:70px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">客户：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OperTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="NumberShow"></span>
                </td>
                <td style="width:70px;"></td>
                <td style="padding:5px;width:140px;"></td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 商品列表table -->
                    <table class="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--调拨出库--%>
    <div class="allocation_out" style="width:1100px;padding:10px 20px;top:70px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">单据日期：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OperTimeShow"></span>
                </td>
                <td style="width:70px;">单据编号：</td>
                <td style="padding:5px;width:130px;">
                    <span class="NumberShow"></span>
                </td>
                <td style="width:80px;"></td>
                <td style="padding:5px;width:140px;"></td>
                <td style="width:70px;"></td>
                <td style="padding:5px;width:140px;"></td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 商品列表table -->
                    <table class="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--礼品充值--%>
    <div class="gift_recharge" style="width:1100px;padding:10px 20px;top:60px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:140px;">
                    <span class="OperTimeShow"></span>
                </td>
                <td style="width:70px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="NumberShow"></span>
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 商品列表table -->
                    <table class="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--礼品销售--%>
    <div class="gift_out" style="width:1100px;padding:10px 20px;top:60px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:140px;">
                    <span class="OperTimeShow"></span>
                </td>
                <td style="width:70px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="NumberShow"></span>
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 商品列表table -->
                    <table class="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--收入单--%>
    <div class="item_in" style="width:1100px;padding:10px 20px;top:20px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">往来单位：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">经手人：</td>
                <td style="padding:5px;width:130px;">
                    <span class="HandsPersonIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:130px;">
                    <span class="BillTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="BillNoShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 单据列表table -->
                    <table class="accountDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
            <tr>
                <td>收款账户：</td>
                <td style="padding:5px;">
                    <span class="AccountIdShow"></span>
                </td>
                <td>收款金额：</td>
                <td style="padding:5px;">
                    <span class="ChangeAmountShow"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--支出单--%>
    <div class="item_out" style="width:1100px;padding:10px 20px;top:20px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">往来单位：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:130px;">
                    <span class="BillTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="BillNoShow"></span>
                </td>
                <td style="width:70px;"></td>
                <td style="width:140px;"></td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 单据列表table -->
                    <table class="accountDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
            <tr>
                <td>付款账户：</td>
                <td style="padding:5px;">
                    <span class="AccountIdShow"></span>
                </td>
                <td>付款金额：</td>
                <td style="padding:5px;">
                    <span class="ChangeAmountShow"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--收款单--%>
    <div class="money_in" style="width:1100px;padding:10px 20px;top:20px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">付款单位：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">经手人：</td>
                <td style="padding:5px;width:130px;">
                    <span class="HandsPersonIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:130px;">
                    <span class="BillTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="BillNoShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 单据列表table -->
                    <table class="accountDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
            <tr>
                <td>优惠金额：</td>
                <td style="padding:5px;width:120px;">
                    <span class="ChangeAmountShow"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--付款单--%>
    <div class="money_out" style="width:1100px;padding:10px 20px;top:20px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">收款单位：</td>
                <td style="padding:5px;width:130px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">经手人：</td>
                <td style="padding:5px;width:130px;">
                    <span class="HandsPersonIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:130px;">
                    <span class="BillTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:140px;">
                    <span class="BillNoShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 单据列表table -->
                    <table class="accountDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
            <tr>
                <td>优惠金额：</td>
                <td style="padding:5px;width:120px;">
                    <span class="ChangeAmountShow"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--转账单--%>
    <div class="giro" style="width:1100px;padding:10px 20px;top:20px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">经手人：</td>
                <td style="padding:5px;width:130px;">
                    <span class="HandsPersonIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:130px;">
                    <span class="BillTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:130px;">
                    <span class="BillNoShow"></span>
                </td>
                <td style="width:50px;"></td>
                <td style="padding:5px;width:110px;"></td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 单据列表table -->
                    <table class="accountDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
            <tr>
                <td>付款账户：</td>
                <td style="padding:5px">
                    <span class="AccountIdShow"></span>
                </td>
                <td>实付金额：</td>
                <td style="padding:5px">
                    <span class="ChangeAmountShow"></span>
                </td>
            </tr>
        </table>
    </div>
    <%--收预付款--%>
    <div class="advance_in" style="width:1100px;padding:10px 20px;top:20px"
         closed="true" modal="true" cache="false" collapsible="false" closable="true">
        <table>
            <tr>
                <td style="width:60px;">付款会员：</td>
                <td style="padding:5px;width:140px;">
                    <span class="OrganIdShow"></span>
                </td>
                <td style="width:70px;">经手人：</td>
                <td style="padding:5px;width:130px;">
                    <span class="HandsPersonIdShow"></span>
                </td>
                <td style="width:70px;">单据日期：</td>
                <td style="padding:5px;width:130px;">
                    <span class="BillTimeShow"></span>
                </td>
                <td style="width:80px;">单据编号：</td>
                <td style="padding:5px;width:130px;">
                    <span class="BillNoShow"></span>
                </td>
                <td style="width:100px;"></td>
            </tr>
            <tr>
                <td colspan="9" style="width: 1130px;">
                    <!-- 单据列表table -->
                    <table class="accountDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
                </td>
            </tr>
            <tr>
                <td style="width:60px;">单据备注：</td>
                <td colspan="8" style="height:35px;">
                    <span class="RemarkShow" style="width: 1070px; height:35px;"></span>
                </td>
            </tr>
            <tr>
                <td>优惠金额：</td>
                <td style="padding:5px;width:120px;">
                    <span class="ChangeAmountShow"></span>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>