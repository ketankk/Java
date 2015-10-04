package kk.play.entity;

public class Cycles {
	
	private int id;
	private String TYPE;
	private String NAME;
	private String IMAGE;
	private String DESCRIPTION;
	private int QUANTITY;
	private int SIZE;
	private String COLOR;
	public Cycles() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cycles(int id, String tYPE, String nAME, String iMAGE,
			String dESCRIPTION, int qUANTITY, int sIZE, String cOLOR) {
		super();
		this.id = id;
		TYPE = tYPE;
		NAME = nAME;
		IMAGE = iMAGE;
		DESCRIPTION = dESCRIPTION;
		QUANTITY = qUANTITY;
		SIZE = sIZE;
		COLOR = cOLOR;
	}

	public int getSIZE() {
		return SIZE;
	}

	public void setSIZE(int sIZE) {
		SIZE = sIZE;
	}

	public String getCOLOR() {
		return COLOR;
	}

	public void setCOLOR(String cOLOR) {
		COLOR = cOLOR;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getIMAGE() {
		return IMAGE;
	}
	public void setIMAGE(String iMAGE) {
		IMAGE = iMAGE;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public int getQUANTITY() {
		return QUANTITY;
	}
	public void setQUANTITY(int qUANTITY) {
		QUANTITY = qUANTITY;
	}
	
	
	
	

}
