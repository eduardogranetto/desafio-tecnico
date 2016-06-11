package br.jh.lembre.me.model;

public enum BacklogType {
	SEARCH("info"), WORKSHOP("warning"), WORD("success");
	
	private final String css;
	
	private BacklogType(final String css) {
		this.css = css;
	}
	
	public String getCss() {
		return css;
	}
	
}