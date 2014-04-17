package com.itucity.dsmp.tripwiki.util;

public class ReturnPackaging {
	
	private String connection_error;
	private String upload_error;
	private String download_error;
	private String unknown_error;
	private String import_error;
	private String export_error;
	
	public String getConnection_error() {
		return connection_error;
	}
	public void setConnection_error(String connection_error) {
		this.connection_error = connection_error;
	}
	public String getUpload_error() {
		return upload_error;
	}
	public void setUpload_error(String upload_error) {
		this.upload_error = upload_error;
	}
	public String getDownload_error() {
		return download_error;
	}
	public void setDownload_error(String download_error) {
		this.download_error = download_error;
	}
	public String getUnknown_error() {
		return unknown_error;
	}
	public void setUnknown_error(String unknown_error) {
		this.unknown_error = unknown_error;
	}
	public String getImport_error() {
		return import_error;
	}
	public void setImport_error(String import_error) {
		this.import_error = import_error;
	}
	public String getExport_error() {
		return export_error;
	}
	public void setExport_error(String export_error) {
		this.export_error = export_error;
	}
	
}
