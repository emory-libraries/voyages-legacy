package edu.emory.library.tast.images.site;

public class ImageDetailInfo {
	private String name;
	private String value;
	public ImageDetailInfo(String name, String val) {
		this.name = name;
		this.value = val;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
