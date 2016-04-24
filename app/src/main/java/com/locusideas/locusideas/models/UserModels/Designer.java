package com.locusideas.locusideas.models.UserModels;

import java.util.Date;

/**
 * Created by anurag on 4/24/16.
 */
public class Designer {

    private String approvalStatus;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

    void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    String getApprovalStatus() {
        return this.approvalStatus;
    }

    String getCreatedBy() {
        return this.createdBy;
    }

    Date getCreatedAt() {
        return this.createdAt;
    }

    String getUpdatedBy() {
        return  this.updatedBy;
    }

    Date getUpdatedAt() {
        return this.updatedAt;
    }
}
