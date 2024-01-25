package egovframework.example.sample.service;

public class PharmacyCsvVO {

	private static final long serialVersionUID = 1L;
	
	private String csv_no;
	private String csv_nm;
	private int csv_cd;
	private String csv_cdnm;
	private int csv_sidocd;
	private String csv_sidonm;
	private int csv_sigungucd;
	private String csv_sigungunm;
	private String csv_emd;
	private int csv_zip;
	private String csv_addr;
	private String csv_tel;
	private String csv_date;
	private double  csv_x;
	private double  csv_y;
	private String  csv_chk_seoul;
	
	
	public String getCsv_no() {
		return csv_no;
	}
	public void setCsv_no(String csv_no) {
		this.csv_no = csv_no;
	}
	public String getCsv_nm() {
		return csv_nm;
	}
	public void setCsv_nm(String csv_nm) {
		this.csv_nm = csv_nm;
	}
	public int getCsv_cd() {
		return csv_cd;
	}
	public void setCsv_cd(int csv_cd) {
		this.csv_cd = csv_cd;
	}
	public String getCsv_cdnm() {
		return csv_cdnm;
	}
	public void setCsv_cdnm(String csv_cdnm) {
		this.csv_cdnm = csv_cdnm;
	}
	public int getCsv_sidocd() {
		return csv_sidocd;
	}
	public void setCsv_sidocd(int csv_sidocd) {
		this.csv_sidocd = csv_sidocd;
	}
	public String getCsv_sidonm() {
		return csv_sidonm;
	}
	public void setCsv_sidonm(String csv_sidonm) {
		this.csv_sidonm = csv_sidonm;
	}
	public int getCsv_sigungucd() {
		return csv_sigungucd;
	}
	public void setCsv_sigungucd(int csv_sigungucd) {
		this.csv_sigungucd = csv_sigungucd;
	}
	public String getCsv_sigungunm() {
		return csv_sigungunm;
	}
	public void setCsv_sigungunm(String csv_sigungunm) {
		this.csv_sigungunm = csv_sigungunm;
	}
	public String getCsv_emd() {
		return csv_emd;
	}
	public void setCsv_emd(String csv_emd) {
		this.csv_emd = csv_emd;
	}
	public int getCsv_zip() {
		return csv_zip;
	}
	public void setCsv_zip(int csv_zip) {
		this.csv_zip = csv_zip;
	}
	public String getCsv_addr() {
		return csv_addr;
	}
	public void setCsv_addr(String csv_addr) {
		this.csv_addr = csv_addr;
	}
	public String getCsv_tel() {
		return csv_tel;
	}
	public void setCsv_tel(String csv_tel) {
		this.csv_tel = csv_tel;
	}
	public String getCsv_date() {
		return csv_date;
	}
	public void setCsv_date(String csv_date) {
		this.csv_date = csv_date;
	}
	public double getCsv_x() {
		return csv_x;
	}
	public void setCsv_x(double csv_x) {
		this.csv_x = csv_x;
	}
	public double getCsv_y() {
		return csv_y;
	}
	public void setCsv_y(double csv_y) {
		this.csv_y = csv_y;
	}
	public String getCsv_chk_seoul() {
		return csv_chk_seoul;
	}
	public void setCsv_chk_seoul(String csv_chk_seoul) {
		this.csv_chk_seoul = csv_chk_seoul;
	}
	@Override
	public String toString() {
		return "PharmacyCsvVO [csv_no=" + csv_no + ", csv_nm=" + csv_nm + ", csv_cd=" + csv_cd + ", csv_cdnm="
				+ csv_cdnm + ", csv_sidocd=" + csv_sidocd + ", csv_sidonm=" + csv_sidonm + ", csv_sigungucd="
				+ csv_sigungucd + ", csv_sigungunm=" + csv_sigungunm + ", csv_emd=" + csv_emd + ", csv_zip=" + csv_zip
				+ ", csv_addr=" + csv_addr + ", csv_tel=" + csv_tel + ", csv_date=" + csv_date + ", csv_x=" + csv_x
				+ ", csv_y=" + csv_y + ", csv_chk_seoul=" + csv_chk_seoul + "]";
	}
}
