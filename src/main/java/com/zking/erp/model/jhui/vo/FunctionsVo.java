package com.zking.erp.model.jhui.vo;

import com.zking.erp.model.all.Functions;

public class FunctionsVo extends Functions {
    /**
     * 拥有的功能列表
     */
    private String hasFunctions = "";

    public String getHasFunctions() {
        return hasFunctions;
    }

    public void setHasFunctions(String hasFunctions) {
        this.hasFunctions = hasFunctions;
    }

    public FunctionsVo(String pnumber) {
        super.setPnumber(pnumber);
    }
}
