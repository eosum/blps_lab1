package lab1.repository;

import lab1.model.resume.Resume;
import lab1.model.common.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query(value = "select * from resumes where status = 'WAITING' order by created_at ASC limit 1 for update skip locked", nativeQuery = true)
    Optional<Resume> findOldestWaiting();

    Optional<Resume> findByModeratorIdAndStatus(Long moderatorId, Status status);

    Optional<Resume> findByCreatedBy(Long createdBy);
}
