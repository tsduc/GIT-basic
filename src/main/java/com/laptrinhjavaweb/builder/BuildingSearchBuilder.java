package com.laptrinhjavaweb.builder;

import java.util.List;

public class BuildingSearchBuilder {

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
//	private String[] buildingTypes = new String[] {};

	public String getName() {
		return name;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public String getDistrict() {
		return district;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}

	public Integer getRentAreaFrom() {
		return rentAreaFrom;
	}

	public Integer getRentAreaTo() {
		return rentAreaTo;
	}

	public Integer getRentPirceFrom() {
		return rentPirceFrom;
	}

	public Integer getRentPirceTo() {
		return rentPirceTo;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public Long getStaffId() {
		return staffId;
	}

	public List<String> getBuildingTypes() {
		return buildingTypes;
	}

	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.district = builder.district;
		this.ward = builder.ward;
		this.street = builder.street;
		this.numberOfBasement = builder.numberOfBasement;
		this.direction = builder.direction;
		this.level = builder.level;
		this.rentAreaFrom = builder.rentAreaFrom;
		this.rentAreaTo = builder.rentAreaTo;
		this.rentPirceFrom = builder.rentPirceFrom;
		this.rentPirceTo = builder.rentPirceTo;
		this.managerName = builder.managerName;
		this.managerPhone = builder.managerPhone;
		this.staffId = builder.staffId;
		this.buildingTypes = builder.buildingTypes;
	}

	public static class Builder {

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
//		private String[] buildingTypes = new String[] {};
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setFloorArea(Integer floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		
		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}
		
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}

		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}

		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}

		public Builder setRentAreaFrom(Integer rentAreaFrom) {
			this.rentAreaFrom = rentAreaFrom;
			return this;
		}

		public Builder setRentAreaTo(Integer rentAreaTo) {
			this.rentAreaTo = rentAreaTo;
			return this;
		}

		public Builder setRentPirceFrom(Integer rentPirceFrom) {
			this.rentPirceFrom = rentPirceFrom;
			return this;
		}

		public Builder setRentPirceTo(Integer rentPirceTo) {
			this.rentPirceTo = rentPirceTo;
			return this;
		}

		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		public Builder setManagerPhone(String managerPhone) {
			this.managerPhone = managerPhone;
			return this;
		}
		
		public Builder setStaffId(Long staffId) {
			this.staffId = staffId;
			return this;
		}


		public Builder setBuildingTypes(List<String> buildingTypes) {
			this.buildingTypes = buildingTypes;
			return this;
		}

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
	}
}
