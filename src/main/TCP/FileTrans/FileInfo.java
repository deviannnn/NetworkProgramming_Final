package main.TCP.FileTrans;

public class FileInfo {

    private String fileOwner;
    private String fileName;
    private byte[] fileData;

    public FileInfo(String fileOwner, String fileName, byte[] fileData) {
        this.fileOwner = fileOwner;
        this.fileName = fileName;
        this.fileData = fileData;
    }

    public String getFileOwner() {
        return fileOwner;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }
}
