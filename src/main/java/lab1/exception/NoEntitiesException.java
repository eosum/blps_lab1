package lab1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NoEntitiesException extends RuntimeException {
    private String message;
}
