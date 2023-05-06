package com.laptrinhjavaweb.controller.admin;


import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private IUserService UserService;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequestDTO buildingSearchRequest) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch", buildingSearchRequest);
        mav.addObject("buildings", buildingService.findBuilding(buildingSearchRequest));
        mav.addObject("staffmaps", UserService.getStaffMaps());
        mav.addObject("districts", buildingService.getDistrticts());
        mav.addObject("buildingTypes", buildingService.getBuildingTypes());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@ModelAttribute("buildings") BuildingDTO buildingDTO) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("buildingModel", buildingDTO);
        mav.addObject("districts", buildingService.getDistrticts());
        mav.addObject("buildingTypes", buildingService.getBuildingTypes());
        return mav;
    }

    @RequestMapping(value = "/admin/building-update", method = RequestMethod.GET)
    public ModelAndView buildingUpdate(@ModelAttribute("buildings") BuildingDTO buildingDTO) {
        ModelAndView mav = new ModelAndView("admin/building/update");
        mav.addObject("buildingModel", buildingDTO);
        mav.addObject("buildings", buildingService.findById(buildingDTO.getId()));
        mav.addObject("districts", buildingService.getDistrticts());
        mav.addObject("buildingTypes", buildingService.getBuildingTypes());
        return mav;
    }
}
