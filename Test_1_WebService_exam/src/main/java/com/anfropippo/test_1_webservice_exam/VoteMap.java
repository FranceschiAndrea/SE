package com.anfropippo.test_1_webservice_exam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "VoteMap")

public class VoteMap {

    private List<VoteEntry> entries = new ArrayList<VoteEntry>();
    
    @XmlElement(nillable = false, name = "entry")
    public List<VoteEntry> getEntries() {
        return entries;
    }
 
    @XmlType(name = "VoteEntry")
    public static class VoteEntry {
        private Integer id;
        private LinkedList<Vote> votes;

        public Integer getId() {
            return id;
        }

        public LinkedList<Vote> getVotes() {
            return votes;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setVotes(LinkedList<Vote> votes) {
            this.votes = votes;
        }
        
    }
    
}
