package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.Log;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface ILogJService {

    List<Log> queryLogByLikePager(PageBean pageBean,Log log);

    int create(Log record);


}
