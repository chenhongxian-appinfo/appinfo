package entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class backend_user {
	private int id; // 主键id
	private String userCode; // 用户编码
	private String userName; // 用户名称
	private BigInteger userType; // 用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）
	private BigInteger createdBy;// 创建者（来源于backend_user用户表的用户id）
	private Date creationDate; // 创建时间
	private BigInteger modifyBy; // 创建者（来源于dev_user开发者信息表的用户id）',
	private Date modifyDate; // 最新得更新时间
	private String userPassword; // 用户密码

	private String userTypeName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigInteger getUserType() {
		return userType;
	}

	public void setUserType(BigInteger userType) {
		this.userType = userType;
	}

	public BigInteger getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public BigInteger getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(BigInteger modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
