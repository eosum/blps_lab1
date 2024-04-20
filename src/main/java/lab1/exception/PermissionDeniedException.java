package lab1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PermissionDeniedException extends RuntimeException {
    private String message;
}
