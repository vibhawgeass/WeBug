package com.example.dashboard.controller;

import com.example.dashboard.model.DashboardRequest;
import com.example.dashboard.model.DashboardResponse;
import com.example.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/getbugs")
    public List<DashboardResponse> getBugs(){
        return dashboardService.findAll();
    }

    @GetMapping("/getbugbyid/{id}")
    public DashboardRequest getBugById(@PathVariable int id){
        return dashboardService.findById(id);
    }

    @GetMapping("/getbugbyorganization/{org}")
    public DashboardRequest getBugByOrganization(@PathVariable String org){
        return dashboardService.findByOrganization(org);
    }

    @PostMapping("/addbug")
    public int addBug(@RequestBody DashboardRequest dashboard){
        return dashboardService.addBug(dashboard);
    }

    @DeleteMapping("/deletebug/{id}")
    public int deleteBug(@PathVariable int id){
        return dashboardService.deleteBug(id);
    }

    @PutMapping("/updatebug/{id}")
    public int updateBug(@PathVariable int id,@RequestBody DashboardRequest dashboard){
        return dashboardService.updateBug(id,dashboard);
    }
}
