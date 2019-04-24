package id.hike.apps.android_mpos_mumu.global.global_model;

import com.google.gson.annotations.SerializedName;

public class ResCekStokSatuan {

	@SerializedName("productId")
	private Long productId;

	@SerializedName("stok")
	private Long stok;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setProductId(Long productId){
		this.productId = productId;
	}

	public Long getProductId(){
		return productId;
	}

	public void setStok(Long stok){
		this.stok = stok;
	}

	public Long getStok(){
		return stok;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}