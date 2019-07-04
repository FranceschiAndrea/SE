package com.anfropippo.test_1_webservice_exam;

import javax.xml.bind.annotation.XmlType;
        
@XmlType(name= "Vote")

public class VoteImpl implements Vote{
    
    private String matter;
    private int vote;
    
    public VoteImpl(String matter, int vote) {
        this.matter = matter;
        this.vote = vote;
    }
    
    VoteImpl(){}

    public String getMatter() {
        return matter;
    }

    public int getVote() {
        return vote;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return this.matter + ": " + this.vote;
    }   
    
}
