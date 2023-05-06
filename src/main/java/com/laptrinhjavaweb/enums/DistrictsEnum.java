package com.laptrinhjavaweb.enums;

public enum DistrictsEnum {
	
	Q1("Quận 1"),
    Q2("Quận 2"),
    Q3("Quận 3"),
    Q6("Quận 6");
    private final String districtValue;

    DistrictsEnum(String districtValue) {
        this.districtValue = districtValue;
    }

	public String getDistrictValue() {
		return districtValue;
	}
}
