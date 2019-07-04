package com.anfropippo.test_1_webservice_exam;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class VoteAdapter extends XmlAdapter<VoteImpl, Vote> {

    public VoteImpl marshal(Vote vote) throws Exception{
        
        if ( vote instanceof VoteImpl ) {
            return (VoteImpl) vote;
        }
        
        return new VoteImpl(vote.getMatter(), vote.getVote());
        
    }
    
    public Vote unmarshal(VoteImpl vote) throws Exception {
        
        return (Vote) vote;
        
    }
    
}
