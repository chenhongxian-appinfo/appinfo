package entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class app_version {
	private int id; // ����id
	private BigInteger appId; // ��Դ�ڣ�app_info�������id
	private String versionNo; // '�汾��',
	private String versionInfo;// �汾����
	private BigInteger publishStatus; // ����״̬����Դ�ڣ�data_dictionary��1 ������ 2 �ѷ��� 3
										// Ԥ������
	private String downloadLink;// ��������
	private BigDecimal versionSize; // '�汾��С����λ��M��
	private BigInteger createdBy; // �����ߣ���Դ��dev_user��������Ϣ����û�id��',
	private Date creationDate; // ����ʱ��
	private BigInteger modifyBy; // �����ߣ���Դ��dev_user��������Ϣ����û�id��
	private Date modifyDate; // ���¸���ʱ��
	private String apkLocPath; // apk�ļ��ķ������洢·��
	private String apkFileName; // �ϴ���apk�ļ�����

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getAppId() {
		return appId;
	}

	public void setAppId(BigInteger appId) {
		this.appId = appId;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getVersionInfo() {
		return versionInfo;
	}

	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}

	public BigInteger getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(BigInteger publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public BigDecimal getVersionSize() {
		return versionSize;
	}

	public void setVersionSize(BigDecimal versionSize) {
		this.versionSize = versionSize;
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

	public String getApkLocPath() {
		return apkLocPath;
	}

	public void setApkLocPath(String apkLocPath) {
		this.apkLocPath = apkLocPath;
	}

	public String getApkFileName() {
		return apkFileName;
	}

	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}

}
