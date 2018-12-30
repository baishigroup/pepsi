package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.SupplierMapperC;
import com.zking.erp.model.cao.Supplier;
import com.zking.erp.service.cao.ISupplierCService;
import com.zking.erp.util.PageBean;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.lang.Boolean;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SupplierCServiceImpl implements ISupplierCService {

    @Autowired
    private SupplierMapperC supplierMapperC;

    @Override
    public int insertSupplier(Supplier supplier) {
        return supplierMapperC.insertSupplier(supplier);
    }

    @Override
    public int updateSupplierById(Supplier supplier) {
        return supplierMapperC.updateSupplierById(supplier);
    }

    @Override
    public int deleteSupplierById(String id) {
        return supplierMapperC.deleteSupplierById(id);
    }

    @Override
    public int deleteSupplierByIds(Supplier supplier) {
        return supplierMapperC.deleteSupplierByIds(supplier);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Supplier> querySupplierPager(Supplier supplier, PageBean pageBean) {
        return supplierMapperC.querySupplierPager(supplier);
    }

    @Override
    public Boolean checkIsNameExist(String filedName, String filedValue, String idFiled, String objectID) {
        List<Supplier> dataList = supplierMapperC.checkIsNameExist(filedName, filedValue, idFiled, objectID);
        if(null != dataList&& dataList.size()>0)
            return true;
        return false;
    }

    @Override
    public int batchSetEnable(Supplier supplier) {
        return supplierMapperC.batchSetEnable(supplier);
    }

    @Override
    public InputStream importExcel(File assetFile) {
        return importExcel(assetFile);
    }

//    @Override
//    public InputStream exmportExcel(String isAllPage, Supplier supplier) {
//        return exmportExcel(isAllPage, supplier);
//    }

    /**
     * 导出Excel表格
     */
    @Override
    public InputStream exmportExcel(String isAllPage, List<Supplier> supplier) {
        try {
            //将OutputStream转化为InputStream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            putDataOnOutputStream(out, supplier);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>导出信息为excel表格异常");
            e.printStackTrace();
        }
        return exmportExcel(isAllPage,supplier);
    }

    /**
     * 生成excel表格
     *
     * @param os
     */
    private void putDataOnOutputStream(OutputStream os, List<Supplier> dataList) {
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(os);
            WritableSheet sheet = workbook.createSheet("信息报表", 0);
            //增加列头
            String[] colunmName = {"名称", "类型", "联系人", "电话", "电子邮箱", "预收款", "期初应收", "期初应付", "备注", "传真", "手机", "地址", "纳税人识别号", "开户行", "账号", "税率", "状态"};
            for (int i = 0; i < colunmName.length; i++) {
                sheet.setColumnView(i, 10);
                sheet.addCell(new Label(i, 0, colunmName[i]));
            }
            if (null != dataList && dataList.size() > 0) {
                int i = 1;
                for (Supplier supplier : dataList) {
                    int j = 0;
                    Map<Integer, String> cellInfo = supplier.getCellInfo();
                    sheet.addCell(new Label(j++, i, supplier.getSupplier()));
                    sheet.addCell(new Label(j++, i, supplier.getType()));
                    sheet.addCell(new Label(j++, i, supplier.getContacts() == null ? "" : supplier.getContacts()));
                    sheet.addCell(new Label(j++, i, supplier.getPhonenum() == null ? "" : supplier.getPhonenum()));
                    sheet.addCell(new Label(j++, i, supplier.getEmail() == null ? "" : supplier.getEmail()));
                    sheet.addCell(getLabelInfo(cellInfo, j++, i, supplier.getAdvancein() == null ? "" : supplier.getAdvancein().toString(), supplier));
                    sheet.addCell(getLabelInfo(cellInfo, j++, i, supplier.getBeginneedget() == null ? "" : supplier.getBeginneedget().toString(), supplier));
                    sheet.addCell(getLabelInfo(cellInfo, j++, i, supplier.getBeginneedpay() == null ? "" : supplier.getBeginneedpay().toString(), supplier));
                    sheet.addCell(new Label(j++, i, supplier.getDescription() == null ? "" : supplier.getDescription()));
                    sheet.addCell(new Label(j++, i, supplier.getFax() == null ? "" : supplier.getFax()));
                    sheet.addCell(new Label(j++, i, supplier.getTelephone() == null ? "" : supplier.getTelephone()));
                    sheet.addCell(new Label(j++, i, supplier.getAddress() == null ? "" : supplier.getAddress()));
                    sheet.addCell(new Label(j++, i, supplier.getTaxnum() == null ? "" : supplier.getTaxnum()));
                    sheet.addCell(new Label(j++, i, supplier.getBankname() == null ? "" : supplier.getBankname()));
                    sheet.addCell(new Label(j++, i, supplier.getAccountnumber() == null ? "" : supplier.getAccountnumber()));
                    sheet.addCell(getLabelInfo(cellInfo, j++, i, supplier.getTaxrate() == null ? "" : supplier.getTaxrate().toString(), supplier));
                    sheet.addCell(new Label(j++, i, supplier.getEnabled()==1 ? "启用" : "禁用"));
                    i++;
                }
            }
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>导出信息为excel表格异常");
            e.printStackTrace();
        }
    }


    /**
     * 根据错误信息进行提示--excel表格背景设置为红色，表示导入信息有误
     *
     * @param cellInfo
     * @param cellNum
     * @param columnNum
     * @param value
     * @return
     */
    private Label getLabelInfo(Map<Integer, String> cellInfo, int cellNum, int columnNum, String value, Supplier supplier) {
        Label label = null;

        //设置背景颜色
        WritableCellFormat cellFormat = new WritableCellFormat();
        try {
            cellFormat.setBackground(Colour.RED);
        } catch (WriteException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>设置单元格背景颜色错误");
            e.printStackTrace();
        }

        if (null == cellInfo || cellInfo.size() == 0) {
            label = new Label(cellNum, columnNum, value);
        } else {
            //表示此单元格有错误
            if (cellInfo.containsKey(cellNum)) {
                if (cellNum == Supplier.EXCEL_ADVANCE_IN) {
                    label = new Label(cellNum, columnNum, supplier.getAdvanceInStr(), cellFormat);
                } else if (cellNum == Supplier.EXCEL_BEGIN_NEED_GET) {
                    label = new Label(cellNum, columnNum, supplier.getBeginNeedGetStr(), cellFormat);
                } else if (cellNum == Supplier.EXCEL_BEGIN_NEED_PAY) {
                    label = new Label(cellNum, columnNum, supplier.getBeginNeedPayStr(), cellFormat);
                } else if (cellNum == Supplier.EXCEL_TAX_RATE) {
                    label = new Label(cellNum, columnNum, supplier.getTaxRateStr(), cellFormat);
                }
            } else {
                label = new Label(cellNum, columnNum, value);
            }
        }
        return label;
    }

}
