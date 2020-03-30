package com.iflytek.tps.service.impl;

import com.iflytek.tps.service.JdbcService;
import com.iflytek.tps.service.bean.AtsTableBean;
import com.iflytek.tps.service.bean.IsaTableBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class JdbcServiceImpl implements JdbcService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertAts(AtsTableBean ats) {
        String sql = "insert into ats_info(file_name,file_date,status,ip,file_date_day) values (?,?,?,?,?)";
        return  jdbcTemplate.update(sql,ats.getFileName(),ats.getFileDate(),ats.getStatus(),ats.getIp(),ats.getFileDateDay());
    }

    @Override
    public int insertIsa(IsaTableBean isa) {
        String sql = "insert into isa_info(file_name,file_date,ip,file_date_day) values (?,?,?,?,)";
        return  jdbcTemplate.update(sql,isa.getFileName(),isa.getFileDate(),isa.getIp(),isa.getFileDateDay());
    }

    @Override
    public List<AtsTableBean> findAtsByDate(String Date,Integer pageNo,Integer pageSize) {
        String sql = "select * from ats_info limit(pageNo-1)*pageSize,pageSize";
        return jdbcTemplate.query(sql, new RowMapper<AtsTableBean>() {
            @Override
            public AtsTableBean mapRow(ResultSet resultSet, int i) throws SQLException {
                AtsTableBean bean = new AtsTableBean();
                bean.setId(resultSet.getInt("id"));
                bean.setStatus(resultSet.getString("status"));
                bean.setIp(resultSet.getString("ip"));
                bean.setFileName(resultSet.getString("file_name"));
                bean.setFileDateDay(resultSet.getString("file_date_day"));
                bean.setFileDate(resultSet.getString("file_date"));
                return bean;
            }
        });
    }

    @Override
    public List<IsaTableBean> findIsaByDate(String Date,Integer pageNo,Integer pageSize) {
        return null;
    }
}
