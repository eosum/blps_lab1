package lab1.service;

import jakarta.transaction.Transactional;
import lab1.exception.NoEntitiesException;
import lab1.model.emailmessage.EmailMessage;
import lab1.model.job.JobPost;
import lab1.model.common.Status;
import lab1.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;
    private final UserService userService;
    private final EmailService emailService;
    public JobPost create(JobPost.JobPostBuilder jobPost, Long userId) {
        jobPost.createdBy(userId);
        var time = System.currentTimeMillis();
        jobPost.createdAt(time);
        jobPost.updatedAt(time);
        jobPost.status(Status.WAITING);

        var buildedJobPost = jobPost.build();
        jobPostRepository.save(buildedJobPost);
        return buildedJobPost;
    }

    public Collection<JobPost> getAllJobPosts() {
        return jobPostRepository.findAllByStatus(Status.APPROVED).orElseThrow(() -> new NoEntitiesException("Вакансий для просмотра нет"));
    }

    public JobPost getJobPostById(Long jobPostId) {
        return jobPostRepository.findById(jobPostId).orElseThrow(() -> new NoEntitiesException("Ууупс, вакансия куда - то исчезла"));
    }

    public Collection<JobPost> getByUserId(Long userId) {
        return jobPostRepository.findAllByCreatedBy(userId).orElseThrow(() -> new NoEntitiesException("Вы не разместили ни одной вакансии"));
    }

    @Transactional
    public JobPost getJobPostForReview(Long moderatorId) {
        var assignedJobPost = jobPostRepository.findByModeratorIdAndStatus(moderatorId, Status.ASSIGNED);
        if (assignedJobPost.isPresent()) return assignedJobPost.get();

        var jobPost = jobPostRepository.findOldestWaiting().orElseThrow(() -> new NoEntitiesException("Новых вакансий для проверки пока нет"));
        jobPost.setStatus(Status.ASSIGNED);
        jobPost.setModeratorId(moderatorId);
        jobPost.setUpdatedAt(System.currentTimeMillis());
        jobPostRepository.save(jobPost);
        return jobPost;
    }

    @Transactional
    public void validate(Long jobPostId, Boolean isValid) {
        var jobPost = jobPostRepository.findById(jobPostId).orElseThrow();

        Status resultStatus = isValid ? Status.APPROVED : Status.ASSIGNED;

        jobPost.setStatus(resultStatus);
        jobPost.setUpdatedAt(System.currentTimeMillis());
        jobPostRepository.save(jobPost);

        var userEmail = userService.findById(jobPost.getCreatedBy()).getEmail();
        emailService.sendEmail(
                new EmailMessage(
                        userEmail,
                        "Корректность заполнения вакансии",
                        isValid ? "Ваша вакансия заполнено корректно. Объявление будет размещено в ближайшее время" : "В вашей вакансии есть некорректно заполненные данные. " +
                                "Пожалуйста, проверьте введенные данные. В случае возникновении вопросов пишите нам на email, с которого пришло это сообщение."
                )
        );
    }
}
