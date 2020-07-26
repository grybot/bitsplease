package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.models.JobOffer;
import gr.bitsplease.bitsplease.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOfferServiceImpl implements JobOffersService {
   @Autowired
    private JobOfferRepository jobOfferRepository;

    @Override
    public List<JobOffer> getJobOffers() {
        return jobOfferRepository.findAll();
    }
}
