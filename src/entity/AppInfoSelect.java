package entity;

public class AppInfoSelect {

	private String AppName;
	private String Type;
	private String PcNameID;
	public String getPcNameID() {
		return PcNameID;
	}

	public void setPcNameID(String pcNameID) {
		PcNameID = pcNameID;
	}

	private String classify1;
	private String classify2;

	public String getAppName() {
		return AppName;
	}

	public void setAppName(String appName) {
		AppName = appName;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getClassify1() {
		return classify1;
	}

	public void setClassify1(String classify1) {
		this.classify1 = classify1;
	}

	public String getClassify2() {
		return classify2;
	}

	public void setClassify2(String classify2) {
		this.classify2 = classify2;
	}

	public String getClassify3() {
		return classify3;
	}

	public void setClassify3(String classify3) {
		this.classify3 = classify3;
	}

	private String classify3;

}
