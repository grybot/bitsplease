package gr.bitsplease.bitsplease.dto;

import java.time.LocalDate;

public interface FinalisedMatches {
    int getApplicantId();

    int getJobOfferId();

    LocalDate getFinalisedDate();
}
