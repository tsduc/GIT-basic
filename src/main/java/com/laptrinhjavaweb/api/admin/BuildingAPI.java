package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.ResponseDTO;
import com.laptrinhjavaweb.dto.reponse.StaffResponseDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IUserService userService;

    @PostMapping
    public BuildingDTO createBuilding(@RequestBody BuildingCreateDTO req){
//        buildingService.save(newBuilding);
        buildingService.insert(req);
        return null;
    }

    @PutMapping("/{id}")
    public BuildingDTO updateBuilding(@PathVariable ("id") Long id, @RequestBody BuildingCreateDTO req){
//        buildingService.save(newBuilding);
        buildingService.upadte(req, id);
        return  null;
    }

//    @DeleteMapping("/{id}")
//    public void deleteBuilding(@PathVariable ("id") Long id){
//        buildingService.delete(id);
//    }

    @DeleteMapping
    public void deleteBuilding(@RequestBody BuildingDTO buildingDTO){
        buildingService.delete(buildingDTO.getIds());
    }

    @GetMapping("/{buildingId}/staffs")
    public ResponseDTO loadStaff(@PathVariable ("buildingId") Long id){
        List<StaffResponseDTO> staffs = userService.assignmentStaffs(id);
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(staffs);

        return  result;
    }
}
