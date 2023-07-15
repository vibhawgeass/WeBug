package com.example.dashboard.dao;

import com.example.dashboard.model.DashboardRequest;
import com.example.dashboard.model.DashboardResponse;

import java.util.List;

public interface DashboardDao {

    List<DashboardResponse> findAll();
    DashboardRequest findById(int id);
    DashboardRequest findByOrganization(String org);
    int addBug(DashboardRequest dashboard);
    int deleteBug(int id);
    int updateBug(int id, DashboardRequest dashboard);
}
