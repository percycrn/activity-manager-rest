package com.usst.ten.demo.entity;

import javax.persistence.Column;
import java.io.Serializable;

public class RecordId implements Serializable {
    @Column(length = 10, nullable = false)
    private Integer uid;
    @Column(length = 10, nullable = false)
    private Integer aid;

    public RecordId() {
    }

    public RecordId(Integer uid, Integer aid) {
        this.uid = uid;
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {

        this.aid = aid;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((uid == null) ? 0 : uid.hashCode());
        result = PRIME * result + ((aid == null) ? 0 : aid.hashCode());
        return result;
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

        final RecordId other = (RecordId) obj;
        if (uid == null) {
            if (other.uid != null) {
                return false;
            }
        } else if (!uid.equals(other.uid)) {
            return false;
        }
        if (aid == null) {
            if (other.aid != null) {
                return false;
            }
        } else if (!aid.equals(other.aid)) {
            return false;
        }
        return true;
    }
}
