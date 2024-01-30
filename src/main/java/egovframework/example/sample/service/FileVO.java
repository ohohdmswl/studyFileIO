package egovframework.example.sample.service;

public class FileVO {

	private static final long serialVersionUID = 1L;
	
	private int file_group;
	private int file_no;
	private String original_name;
	private String file_name;
	private String file_extension;
	private String reg_id;
	private String reg_date;
	private String file_path;
	private int board_no;
	public int getFile_group() {
		return file_group;
	}
	public void setFile_group(int file_group) {
		this.file_group = file_group;
	}
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_extension() {
		return file_extension;
	}
	public void setFile_extension(String file_extension) {
		this.file_extension = file_extension;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	@Override
	public String toString() {
		return "FileVO [file_group=" + file_group + ", file_no=" + file_no + ", original_name=" + original_name
				+ ", file_name=" + file_name + ", file_extension=" + file_extension + ", reg_id=" + reg_id
				+ ", reg_date=" + reg_date + ", file_path=" + file_path + ", board_no=" + board_no + "]";
	}
	
	
	

	
	
	
	
}
