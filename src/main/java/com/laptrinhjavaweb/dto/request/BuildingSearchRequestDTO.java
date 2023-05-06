package com.laptrinhjavaweb.dto.request;

import java.util.List;

public class BuildingSearchRequestDTO {
	private String name;
	private Integer floorArea;
	private String district;
	private String ward;
	private String street;
	private Integer numberOfBasement;
	private String direction;
	private String level;
	private Integer rentAreaFrom;
	private Integer rentAreaTo;
	private Integer rentPirceFrom;
	private Integer rentPirceTo;
	private String managerName;
	private String managerPhone;
	private Long staffId;
	private List<String> buildingTypes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getRentAreaFrom() {
		return rentAreaFrom;
	}

	public void setRentAreaFrom(Integer rentAreaFrom) {
		this.rentAreaFrom = rentAreaFrom;
	}

	public Integer getRentAreaTo() {
		return rentAreaTo;
	}

	public void setRentAreaTo(Integer rentAreaTo) {
		this.rentAreaTo = rentAreaTo;
	}

	public Integer getRentPirceFrom() {
		return rentPirceFrom;
	}

	public void setRentPirceFrom(Integer rentPirceFrom) {
		this.rentPirceFrom = rentPirceFrom;
	}

	public Integer getRentPirceTo() {
		return rentPirceTo;
	}

	public void setRentPirceTo(Integer rentPirceTo) {
		this.rentPirceTo = rentPirceTo;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public List<String> getBuildingTypes() {
		return buildingTypes;
	}

	public void setBuildingTypes(List<String> buildingTypes) {
		this.buildingTypes = buildingTypes;
	}
}
