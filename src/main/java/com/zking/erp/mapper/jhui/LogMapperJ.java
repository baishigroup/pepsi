package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapperJ {

    List<Log> queryLogByLikePager(Log log);

    int insert(Log record);


}