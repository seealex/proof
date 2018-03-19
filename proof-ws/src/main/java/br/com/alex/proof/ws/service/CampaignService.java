package br.com.alex.proof.ws.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import br.com.alex.proof.ws.repository.CampaignRepository;
import br.com.alex.proof.ws.view.Campaign;

@Service
public class CampaignService implements CommandLineRunner{
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	public Campaign create(Campaign campaign) {
			List<Campaign> campaignList = campaignRepository.findAllBetween(LocalDateTime.now(),  campaign.getValidityDate());
			
			if(campaignList != null && campaignList.size() > 0){
				
				if(campaignList.parallelStream().filter(c -> c.getValidityDate().equals(campaign.getValidityDate())).count() > 0) {
					campaignList.parallelStream().forEach(c -> {
							c.setValidityDate(new Date(c.getValidityDate().getTime() + (1 * 86400000)));
						});
				}
				
				campaignList.add(campaign);
					for(int idx = 0; idx < campaignList.size(); idx++) {
						
						if(idx == campaignList.size() -1)
							break;
						
						if(campaignList.get(idx).getValidityDate().equals(campaignList.get(idx + 1).getValidityDate())) {
							campaignList.get(idx).setValidityDate(new Date(campaignList.get(idx).getValidityDate().getTime() + (1 * 86400000)));
							idx = -1;
						}
					}
				campaignRepository.save(campaignList);
			}else {
				campaignRepository.save(campaign);
			}
			
			
			return campaign;
		}

	public Campaign read(String id){
		Campaign camp = campaignRepository.findByIdAndDate(id, LocalDateTime.now());
		return camp;
	}

	public Campaign update(String id, Campaign campaignView) {
		Campaign campaign = this.read(id);
		campaignView.setId(campaign.getId());
		
		campaignRepository.save(campaignView);
		
		return campaignView;
	}

	public Boolean delete(String id) {
		campaignRepository.delete(id);
		return true;
	}

	public List<Campaign> readAll() {
		return campaignRepository.findAll(LocalDateTime.now());
	}

	public Object updatePartial(Long id, Campaign campaignView) {
		return null;
	}

	@Override
	public void run(String... arg0) throws Exception {
		
	}

}
