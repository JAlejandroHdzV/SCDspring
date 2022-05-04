package com.cummins.scd.models.entity;



public class UploadFileResponseStr {
	private String idFile;
    private String nombreArchivo;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponseStr(String idFile, String nombreArchivo, String fileDownloadUri, String fileType, long size) {
    	this.idFile = idFile;
    	this.nombreArchivo = nombreArchivo;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    
    
 



	public String getIdFile() {
		return idFile;
	}







	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}







	public String getNombreArchivo() {
		return nombreArchivo;
	}



	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}



	public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
