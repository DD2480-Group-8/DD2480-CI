package group8;

/**
 * Enum class to use for reporting the outcome of the build and test.
 * Will be used when determining what status to send to Github.
 */
public enum Status {
    SUCCESS,
    ERROR,
    FAILURE,
    PENDING
}
