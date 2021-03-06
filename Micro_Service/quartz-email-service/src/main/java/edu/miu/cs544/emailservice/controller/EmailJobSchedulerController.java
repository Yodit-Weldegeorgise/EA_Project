package edu.miu.cs544.emailservice.controller;


import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.cs544.emailservice.service.EmailSchedulingService;
import edu.miu.cs544.emailservice.service.request.ScheduleEmailRequest;
import edu.miu.cs544.emailservice.service.response.ScheduleEmailResponse;

import javax.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
public class EmailJobSchedulerController {
    private static final Logger logger = LoggerFactory.getLogger(EmailJobSchedulerController.class);

    @Autowired
    EmailSchedulingService emailSchedulingService;

    @PostMapping("/schedule-email")
    public ResponseEntity<ScheduleEmailResponse> scheduleEmail(@Valid @RequestBody ScheduleEmailRequest emailRequest) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.of(emailRequest.getDepartureDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime().minusHours(24), ZoneId.of("America/Chicago"));
            System.out.println(emailRequest.getDepartureDate());
            System.out.println(dateTime);
            System.out.println(ZonedDateTime.now());
            if(dateTime.isBefore(ZonedDateTime.now())) {
                ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(false,
                        "The provided date and time must be at least 24hrs after the current time");
                return ResponseEntity.badRequest().body(scheduleEmailResponse);
            }

            ScheduleEmailResponse scheduleEmailResponse = emailSchedulingService.schedule(emailRequest, dateTime);
            return ResponseEntity.ok(scheduleEmailResponse);
        } catch (SchedulerException ex) {
            logger.error("Error scheduling email", ex);

            ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(false,
                    "Error scheduling email. Please try later!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scheduleEmailResponse);
        }
    }
}