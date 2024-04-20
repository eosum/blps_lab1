package lab1.model.resume;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperience {
    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String duties;
   // private LocalDate since;
   // private LocalDate by;
}
