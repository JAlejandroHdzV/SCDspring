package com.cummins.scd.global;


public class FileStorageException extends Exception {

    /**
	 * Excepciones
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Type {
        IO_EXCEPTION("I/O error", 500),
        FILE_ALREADY_EXISTS("File already exists", 500),
        FILE_NOT_FOUND("File not found", 404),
        MORE_THAN_ONE_FILE("More than one file with this name exists", 500),
        STORAGE_INACCESSIBLE("Can not get access to the storage", 503);

        private String message;

        private int httpStatus;

        Type(String message, int httpStatus) {
            this.message = message;
            this.httpStatus = httpStatus;
        }

        public String getMessage() {
            return message;
        }

        public int getHttpStatus() {
            return httpStatus;
        }

        public static Type fromHttpStatus(int status) {
            for (Type type : values()) {
                if (type.getHttpStatus() == status)
                    return type;
            }
            return IO_EXCEPTION;
        }
    }

    private Type type;
    private String fileName;

    public FileStorageException(Type type, String fileName) {
        this(type, fileName, null);
    }

    public FileStorageException(Type type, String fileName, Throwable cause) {
        super(type.getMessage() + ": " + fileName, cause);
        this.type = type;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public Type getType() {
        return type;
    }
}
