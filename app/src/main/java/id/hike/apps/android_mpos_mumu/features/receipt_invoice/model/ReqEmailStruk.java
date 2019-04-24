package id.hike.apps.android_mpos_mumu.features.receipt_invoice.model;

import java.util.List;

public class ReqEmailStruk{
	private String app;
	private String kembalian;
	private String note;
	private String tanggalBeli;
	private String totalItemQty;
	private String namaGerai;
	private String totalItemHarga;
	private String noTransaksi;
	private String alamat;
	private String ppn;
	private String noHpGerai;
	private List<ProdukItem> produk;
	private String payMethod;
	private String diskon;
	private String email;
	private String customer;

	public ReqEmailStruk(String app, String kembalian, String note, String tanggalBeli, String totalItemQty, String namaGerai, String totalItemHarga, String noTransaksi, String alamat, String ppn, String noHpGerai, List<ProdukItem> produk, String payMethod, String diskon, String email, String customer) {
		this.app = app;
		this.kembalian = kembalian;
		this.note = note;
		this.tanggalBeli = tanggalBeli;
		this.totalItemQty = totalItemQty;
		this.namaGerai = namaGerai;
		this.totalItemHarga = totalItemHarga;
		this.noTransaksi = noTransaksi;
		this.alamat = alamat;
		this.ppn = ppn;
		this.noHpGerai = noHpGerai;
		this.produk = produk;
		this.payMethod = payMethod;
		this.diskon = diskon;
		this.email = email;
		this.customer = customer;
	}
}