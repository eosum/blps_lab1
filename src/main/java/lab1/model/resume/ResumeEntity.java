package lab1.model.resume;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lab1.model.common.Status;
import lab1.model.common.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static lab1.utils.ValidationUtils.validatePhoneNumber;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeEntity {
    private Long resumeId;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    private Sex sex;

    @NotNull
    @Email
    @Size(min = 9, max = 120)
    private String email;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @Size(min = 11, max = 12)
    private String phoneNumber;

    @NotNull
    @NotBlank
    private String position;
    private Set<WorkMode> modes;
    private Set<Education> educations;
    private Set<WorkExperience> workExperiences;

    public void validate() {
        validatePhoneNumber(phoneNumber);
    }

    public Resume.ResumeBuilder fromRequest() {
        return Resume.builder()
                .firstName(firstName)
                .lastName(lastName)
                .status(Status.WAITING)
                .email(email)
                .city(city)
                .sex(sex)
                .phoneNumber(phoneNumber)
                .position(position)
                .modes(modes)
                .educations(educations)
                .workExperiences(workExperiences);
    }
}
