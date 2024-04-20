package lab1.repository;

import lab1.model.job.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    Collection<JobApplication> findByJobPostId(Long jobPostId);
}
