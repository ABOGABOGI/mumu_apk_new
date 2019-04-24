package id.hike.apps.android_mpos_mumu.features.profil;

public class ReqUpdateProfilNoFoto {
	private String employeeName;
	private String address;
	private String gender;
	private String pin;
	private String phone;
	private String position;
	private String userId;
	private String email;
	private String username;
	private String urlAvatar;
	private Integer status;
	private Integer storeId;
	private Integer outletId;

	public ReqUpdateProfilNoFoto(String employeeName, String address, String gender, String pin, String phone, String position, String userId, String email, String username, String urlAvatar, Integer status, Integer storeId, Integer outletId) {
		this.employeeName = employeeName;
		this.address = address;
		this.gender = gender;
		this.pin = pin;
		this.phone = phone;
		this.position = position;
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.urlAvatar = urlAvatar;
		this.status = status;
		this.storeId = storeId;
		this.outletId = outletId;
	}
}
