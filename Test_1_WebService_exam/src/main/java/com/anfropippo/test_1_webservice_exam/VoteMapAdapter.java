package com.anfropippo.test_1_webservice_exam;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class VoteMapAdapter extends XmlAdapter<VoteMap, Map<Integer, LinkedList<Vote>>> {

     public VoteMap marshal(Map<Integer, LinkedList<Vote>> boundMap) throws Exception {
        VoteMap valueMap = new VoteMap();
        for (Map.Entry<Integer, LinkedList<Vote>> boundEntry : boundMap.entrySet()) {
            VoteMap.VoteEntry valueEntry  = new VoteMap.VoteEntry();
            valueEntry.setVotes(boundEntry.getValue());
            valueEntry.setId(boundEntry.getKey());
            valueMap.getEntries().add(valueEntry);
        }
        return valueMap;
    }
    
    public Map<Integer, LinkedList<Vote>> unmarshal(VoteMap valueMap) throws Exception {
        Map<Integer, LinkedList<Vote>> boundMap = new LinkedHashMap<Integer, LinkedList<Vote>>();
        for (VoteMap.VoteEntry voteEntry : valueMap.getEntries()) {
            boundMap.put(voteEntry.getId(), voteEntry.getVotes());
        }
        return boundMap;
    }
    
}
