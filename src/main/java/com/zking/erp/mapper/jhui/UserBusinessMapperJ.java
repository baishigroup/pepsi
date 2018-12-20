package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.UserBusiness;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBusinessMapperJ {

    List<UserBusiness>  queryUserBusinessByTypeByKeyId(UserBusiness userBusiness);

}