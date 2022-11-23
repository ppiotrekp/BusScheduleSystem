package pl.ppyrczak.busschedulesystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.ppyrczak.busschedulesystem.auth.ApplicationUser;
import pl.ppyrczak.busschedulesystem.auth.UserService;
import pl.ppyrczak.busschedulesystem.controller.util.UserPermission;
import pl.ppyrczak.busschedulesystem.exception.illegalaccess.UserNotAuthorizedException;
import pl.ppyrczak.busschedulesystem.model.Review;
import pl.ppyrczak.busschedulesystem.service.PassengerService;
import pl.ppyrczak.busschedulesystem.service.ReviewService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final PassengerService passengerService;
    private final UserPermission userPermission;

    @GetMapping("/schedules/{id}/reviews")
    public List<Review> getReviewsWithDetailsForSpecificSchedule(@PathVariable Long id,
                                                                 @RequestParam(required = false) int page,
                                                                 Sort.Direction sort) {
        int pageNumber = page >= 0 ? page : 0;
        return reviewService.getReviewsWithDetailsForSpecificSchedule(id, pageNumber, sort);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/review")
    public Review addReview(@Valid @RequestBody Review review,
                            Authentication authentication) throws UserNotAuthorizedException {

        if (!userPermission.hasPermissionToAddReview(review, authentication)) {
            throw new UserNotAuthorizedException();
        }
        return reviewService.addReview(review);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/review/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
