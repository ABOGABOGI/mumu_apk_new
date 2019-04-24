package id.hike.apps.android_mpos_mumu.features.receipt_invoice;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelStruk{

	@SerializedName("summary")
	private List<SummaryItem> summary;

	@SerializedName("produk")
	private List<ProdukItem> produk;

	@SerializedName("info")
	private List<InfoItem> info;

	public void setSummary(List<SummaryItem> summary){
		this.summary = summary;
	}

	public List<SummaryItem> getSummary(){
		return summary;
	}

	public void setProduk(List<ProdukItem> produk){
		this.produk = produk;
	}

	public List<ProdukItem> getProduk(){
		return produk;
	}

	public void setInfo(List<InfoItem> info){
		this.info = info;
	}

	public List<InfoItem> getInfo(){
		return info;
	}
}