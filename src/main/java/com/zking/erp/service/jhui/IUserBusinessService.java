package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.UserBusiness;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IUserBusinessService {

    List<UserBusiness>  queryUserBusinessByTypeByKeyId(UserBusiness userBusiness);

}