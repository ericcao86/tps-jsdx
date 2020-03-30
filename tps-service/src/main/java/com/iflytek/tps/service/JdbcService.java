package com.iflytek.tps.service;

import com.iflytek.tps.service.bean.AtsTableBean;
import com.iflytek.tps.service.bean.IsaTableBean;

import java.util.List;

public interface JdbcService {

    public int insertAts(AtsTableBean ats);

    public int insertIsa(IsaTableBean isa);

    public List<AtsTableBean> findAtsByDate(String Date,Integer pageNo,Integer pageSize);

    public List<IsaTableBean> findIsaByDate(String Date,Integer pageNo,Integer pageSize);
}
