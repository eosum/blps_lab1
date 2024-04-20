package lab1.repository;

import lab1.model.job.JobPost;
import lab1.model.common.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    Optional<Collection<JobPost>> findAllByCreatedBy(Long createdBy);

    Optional<JobPost> findByModeratorIdAndStatus(Long moderatorId, Status status);

    @Query(value = "select * from job_posts where status = 'WAITING' order by created_at ASC limit 1 for update skip locked", nativeQuery = true)
    Optional<JobPost> findOldestWaiting();

    Optional<Collection<JobPost>> findAllByStatus(Status status);
}
