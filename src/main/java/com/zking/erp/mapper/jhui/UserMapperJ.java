package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapperJ {

    User queryByLogin(User user);

}