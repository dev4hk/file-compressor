public enum FileType {
    ORIGINAL("ORIGINAL FILE"),
    ENCODED("ENCODED FILE"),
    DECODED("DECODED FILE");

    private final String fileType;

    FileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

}
