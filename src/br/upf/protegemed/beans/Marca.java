package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Marca implements Serializable{

	private static final long serialVersionUID = -8061630155126216171L;
	private Integer codMarca;
	private String desc;
	
	public Marca() {
		super();
	}

	public Marca(Integer codMarca, String desc) {
		super();
		this.codMarca = codMarca;
		this.desc = desc;
	}

	public Integer getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(Integer codMarca) {
		this.codMarca = codMarca;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
