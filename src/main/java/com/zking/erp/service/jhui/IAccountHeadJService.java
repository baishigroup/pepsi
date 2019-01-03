package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.AccountHead;
import com.zking.erp.util.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IAccountHeadJService {

    /**
     * 查看账户主表的数据（分页，模糊）
     * @param accountHead
     * @param pageBean
     * @return
     */
    List<AccountHead> queryAccoutByLikePager(AccountHead accountHead, PageBean pageBean);

    AccountHead queryAccountHeadById(String id);

    int updateById(AccountHead record);

    int insert(AccountHead record);

    int delete(AccountHead accountHead);

    int deleteById(String id);

    List<AccountHead>  queryDetailByNumber(AccountHead accountHead);




}