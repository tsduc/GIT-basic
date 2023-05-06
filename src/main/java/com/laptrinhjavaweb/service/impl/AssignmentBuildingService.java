package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.request.AssignmentBuildingCreateDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void insert(AssignmentBuildingCreateDTO req) {
        for (Long staffId: req.getStaffs()){
            AssignmentBuildingEntity assignmentBuildingEntity = assignmentBuildingRepository.findByBuilding_IdAndUser_Id(req.getBuildingId(), staffId);
            if(assignmentBuildingEntity == null){
                assignmentBuildingEntity = new AssignmentBuildingEntity();


                BuildingEntity buildingEntity = buildingRepository.findById(req.getBuildingId()).orElse(null);
                assignmentBuildingEntity.setBuilding(buildingEntity);
                UserEntity userEntity = userRepository.findById(staffId).orElse(null);
                assignmentBuildingEntity.setUser(userEntity);

                assignmentBuildingRepository.save(assignmentBuildingEntity);
//                assignmentBuildingRepository.insert(assignmentBuildingEntity);
            }
        }
    }
}
