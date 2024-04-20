package lab1.model.job;

import jakarta.persistence.*;
import lab1.model.common.Status;
import lab1.model.common.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "job_posts")
public class JobPost {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private Integer salary;

    @Column(nullable = false)
    private Integer expectedExperience;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private WorkMode workMode;

    @Column(nullable = false)
    private Long createdBy;

    @Column(nullable = false)
    private Long createdAt;

    @Column(nullable = false)
    private Long updatedAt = createdAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    private Long moderatorId;
}
