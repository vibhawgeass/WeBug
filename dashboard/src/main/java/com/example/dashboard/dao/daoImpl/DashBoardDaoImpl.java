package com.example.dashboard.dao.daoImpl;

import com.example.dashboard.dao.DashboardDao;
import com.example.dashboard.model.DashboardRequest;
import com.example.dashboard.model.DashboardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DashBoardDaoImpl implements DashboardDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<DashboardResponse> findAll() {
        String sql="select bug_id,message,fromMessage,toMessage,cc,status,organization from bug";
        return jdbcTemplate.query(sql,(rs, rowNum) -> {
            DashboardResponse d=new DashboardResponse();
            d.setBug_id(rs.getInt("bug_id"));
            d.setMessage(rs.getString("message"));
            d.setFromMessage(rs.getString("fromMessage"));
            d.setToMessage(rs.getString("toMessage"));
            String cc=rs.getString("cc");
            String[] out=cc.split(",");
            List<Integer> a = new ArrayList<>();
            for(int i=0;i<out.length;i++){
                a.add(Integer.valueOf(out[i]));
            }
            d.setCc(a);
            d.setStatus(rs.getString("status"));
            d.setOrganization(rs.getString("organization"));
            return d;
        });
    }

    @Override
    public DashboardRequest findById(int id) {
        String sql="select * from bug where bug_id=?";
        return (DashboardRequest) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(DashboardRequest.class));
    }

    @Override
    public DashboardRequest findByOrganization(String org) {
        String sql="select * from bug where organization=?";
        return (DashboardRequest) jdbcTemplate.queryForObject(sql, new Object[]{org}, new BeanPropertyRowMapper(DashboardRequest.class));

    }

    @Override
    public int addBug(DashboardRequest dashboard) {
        String sql="INSERT INTO bug(message,fromMessage,toMessage,cc,status,organization) VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,dashboard.getMessage(),dashboard.getFromMessage(),dashboard.getToMessage(),dashboard.getCc().toString(),dashboard.getStatus(),dashboard.getOrganization());

    }

    @Override
    public int deleteBug(int id) {
        String sql="delete from bug where id=?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateBug(int id, DashboardRequest dashboard) {
        String sql="update user set message=?,fromMessage=?,toMessage=?,cc=?,status=?,organization=? where bug_id=? ";
        return jdbcTemplate.update(sql,dashboard.getMessage(),dashboard.getFromMessage(),dashboard.getToMessage(),dashboard.getCc(),dashboard.getStatus(),dashboard.getOrganization(),dashboard.getBug_id());

    }
}
