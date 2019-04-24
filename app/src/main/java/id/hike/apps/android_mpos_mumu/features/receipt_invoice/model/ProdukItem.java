package id.hike.apps.android_mpos_mumu.features.receipt_invoice.model;

public class ProdukItem{
	private String namaProduk;
	private String qty;
	private String hargaProduk;

	public ProdukItem(String namaProduk, String qty, String hargaProduk) {
		this.namaProduk = namaProduk;
		this.qty = qty;
		this.hargaProduk = hargaProduk;
	}
}
