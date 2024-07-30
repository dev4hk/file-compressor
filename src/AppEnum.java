public enum AppEnum {
    INVALID_NUMBER_OF_ARGUMENTS("Number of arguments must be 3"),
    INVALID_ARGUMENT("Argument must be [source_text_file_path] [compression_option] [destination_text_file_path]"),
    NOT_ABLE_TO_DECODE("Failed to find necessary objects to decode from the file")
    ;

    final String message;

    AppEnum(String message) {
        this.message = message;
    }
}
