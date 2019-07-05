package com.anfropippo.test_2_api_exam;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Vote")
public class Vote {

    private int votation;
    private String matter;

    public int getVotation() {
        return votation;
    }

    public String getMatter() {
        return matter;
    }

    public void setVotation(int votation) {
        this.votation = votation;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.votation;
        hash = 97 * hash + Objects.hashCode(this.matter);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vote other = (Vote) obj;
        if (this.votation != other.votation) {
            return false;
        }
        if (!Objects.equals(this.matter, other.matter)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vote{" + "votation=" + votation + ", matter=" + matter + '}';
    }
    
    
    
}
