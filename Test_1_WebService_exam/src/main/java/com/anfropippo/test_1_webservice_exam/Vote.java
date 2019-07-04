package com.anfropippo.test_1_webservice_exam;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(VoteAdapter.class)

public interface Vote {

    public String getMatter();
    public int getVote();
    
}
