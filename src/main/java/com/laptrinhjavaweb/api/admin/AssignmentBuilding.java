package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assignmentbuilding")
public class AssignmentBuilding {

    @Autowired
    private IAssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public BuildingDTO createBuilding(@RequestBody AssignmentBuildingCreateDTO req){
        assignmentBuildingService.insert(req);
        return null;
    }
}
