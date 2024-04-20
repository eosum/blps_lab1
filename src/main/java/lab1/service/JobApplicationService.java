package lab1.service;

import lab1.model.job.JobApplication;
import lab1.model.resume.Resume;
import lab1.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;

    private final ResumeService resumeService;

    public void create(JobApplication jobApplication) {
        jobApplicationRepository.save(jobApplication);
    }

    public Collection<Resume> getApplicants(Long jobPostId) {
        var applicantIds = jobApplicationRepository.findByJobPostId(jobPostId);

        if (applicantIds == null) return Collections.emptyList();

        return resumeService.getAllByUserIds(applicantIds.stream().map(JobApplication::getUserId).toList());
    }
}
