package entity;

import java.math.BigInteger;
import java.util.Date;

public class ad_promotion {
	private int id; // ����id
	private BigInteger appId; // ��Դ�ڣ�app_info�������id
	private String adPicPath; // '���ͼƬ�洢·��
	private BigInteger adPV; // �������
	private int carouselPosition; // �ֲ�λ��1-n��
	private Date startTime; // ��Чʱ��
	private Date endTime; // ʧЧʱ��
	private BigInteger createdBy; // �����ߣ���Դ��backend_user�û�����û�id��
	private Date creationDate; // ����ʱ��'
	private BigInteger modifyBy; // �����ߣ���Դ��backend_user�û�����û�id��
	private Date modifyDate; // ���¸���ʱ��

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

	public String getAdPicPath() {
		return adPicPath;
	}

	public void setAdPicPath(String adPicPath) {
		this.adPicPath = adPicPath;
	}

	public BigInteger getAdPV() {
		return adPV;
	}

	public void setAdPV(BigInteger adPV) {
		this.adPV = adPV;
	}

	public int getCarouselPosition() {
		return carouselPosition;
	}

	public void setCarouselPosition(int carouselPosition) {
		this.carouselPosition = carouselPosition;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

}
