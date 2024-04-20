package lab1.utils;

import lab1.model.job.JobPost;
import lab1.model.job.JobPostEntity;
import lab1.model.resume.Resume;
import lab1.model.resume.ResumeEntity;
import org.springframework.data.domain.Page;

import java.util.Collection;

public class TransformationUtils {
    public static ResumeEntity resumeToResponse(Resume resume) {
        return ResumeEntity.builder()
                .resumeId(resume.getId())
                .firstName(resume.getFirstName())
                .lastName(resume.getLastName())
                .email(resume.getEmail())
                .city(resume.getCity())
                .sex(resume.getSex())
                .phoneNumber(resume.getPhoneNumber())
                .position(resume.getPosition())
                .modes(resume.getModes())
                .educations(resume.getEducations())
                .workExperiences(resume.getWorkExperiences())
                .build();
    }

    public static Collection<ResumeEntity> resumeCollectionToResponse(Collection<Resume> resumes) {
        return resumes.stream().map(TransformationUtils::resumeToResponse).toList();
    }

    public static JobPostEntity jobPostToResponse(JobPost jobPost) {
        return JobPostEntity.builder()
                .id(jobPost.getId())
                .city(jobPost.getCity())
                .position(jobPost.getPosition())
                .company(jobPost.getCompany())
                .salary(jobPost.getSalary())
                .expectedExperience(jobPost.getExpectedExperience())
                .description(jobPost.getDescription())
                .workMode(jobPost.getWorkMode())
                .build();
    }
    public static Page<JobPostEntity> jobPostPageToResponse(Page<JobPost> jobPosts) {
        return jobPosts.map(TransformationUtils::jobPostToResponse);
    }
}
