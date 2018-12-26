package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.UserBusiness;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IUserBusinessJService {

    List<UserBusiness>  queryUserBusinessByTypeByKeyId(UserBusiness userBusiness);

    /**
     * 检查UserBusiness是否存在，页面唯一性效验使用
     *
     * @param TypeName   类型名称
     * @param TypeVale   类型值
     * @param KeyIdName  关键id
     * @param KeyIdValue 关键值
     * @param UBName     关系名称
     * @param UBValue    关系值
     * @return true==存在 false==不存在
     */
    Boolean checkIsUserBusinessExist(String TypeName, String TypeVale, String KeyIdName, String KeyIdValue, String UBName, String UBValue) ;

    UserBusiness queryUserBusinessById(String id);

    int insert(UserBusiness record);

    int updateByPrimaryKey(UserBusiness record);



}