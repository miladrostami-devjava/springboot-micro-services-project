package com.microservice.companyms.company.messaging;

import com.microservice.companyms.company.dto.ReviewMessage;
import com.microservice.companyms.company.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class ReviewCompanyConsumer {
  private final CompanyService companyService;
    public ReviewCompanyConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {
        companyService.updateCompanyRating(reviewMessage);

    }



}
