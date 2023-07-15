package com.example.dashboard.service;

import com.example.dashboard.dao.daoImpl.DashBoardDaoImpl;
import com.example.dashboard.model.DashboardRequest;
import com.example.dashboard.model.DashboardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    DashBoardDaoImpl dashBoardDaoImpl;

    public List<DashboardResponse> findAll(){
        return dashBoardDaoImpl.findAll();
    }
    public DashboardRequest findById(int id){
        return dashBoardDaoImpl.findById(id);
    }
    public DashboardRequest findByOrganization(String org){
        return dashBoardDaoImpl.findByOrganization(org);
    }
    public int addBug(DashboardRequest dashboard){
        return dashBoardDaoImpl.addBug(dashboard);
    }
    public int deleteBug(int id){
        return dashBoardDaoImpl.deleteBug(id);
    }
    public int updateBug(int id, DashboardRequest dashboard){
        return dashBoardDaoImpl.updateBug(id,dashboard);
    }
}
