package entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class backend_user {
	private int id; // ����id
	private String userCode; // �û�����
	private String userName; // �û�����
	private BigInteger userType; // �û���ɫ���ͣ���Դ�������ֵ����Ϊ�����ܡ������г�����Ӫ�����ۣ�
	private BigInteger createdBy;// �����ߣ���Դ��backend_user�û�����û�id��
	private Date creationDate; // ����ʱ��
	private BigInteger modifyBy; // �����ߣ���Դ��dev_user��������Ϣ����û�id��',
	private Date modifyDate; // ���µø���ʱ��
	private String userPassword; // �û�����

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
