package br.upf.protegemed.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Responsavel implements Serializable{

	private static final long serialVersionUID = -9134702167170815266L;
	private Integer codResp;
	private String desc;
	
	public Responsavel() {
		super();
	}

	public Responsavel(Integer codResp, String desc) {
		super();
		this.codResp = codResp;
		this.desc = desc;
	}

	public Integer getCodResp() {
		return codResp;
	}

	public void setCodResp(Integer codResp) {
		this.codResp = codResp;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
