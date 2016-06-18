package com.android.locusideas.models.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by anurag on 4/24/16.
 */
public class Designer {

    private String approvalStatus;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private Description description;
    private Links links;
    private OfficeCollection offices;
    private Contact contact;

    public Designer() {
        description = new Description();
        links = new Links();
    }

    void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    String getApprovalStatus() {
        return this.approvalStatus;
    }

    void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    String getCreatedBy() {
        return this.createdBy;
    }

    void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    Date getCreatedAt() {
        return this.createdAt;
    }

    void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    String getUpdatedBy() {
        return this.updatedBy;
    }

    void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    Date getUpdatedAt() {
        return this.updatedAt;
    }

    void setDescription(Description description) {
        this.description = description;
    }

    void setLinks(Links links) {
        this.links = links;
    }

    Description getDescription() {
        return this.description;
    }

    Links getLinks() {
        return this.links;
    }

    public void setOffices(OfficeCollection offices) {
        this.offices = offices;
    }

    public OfficeCollection getOffices() {
        return offices;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    static class Description {

        private String shortDescription;
        private String longDescription;

        public Description() {

        }

        void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        String getShortDescription() {
            return this.shortDescription;
        }

        void setLongDescription(String longDescription) {
            this.longDescription = longDescription;
        }

        String getLongDescription() {
            return this.longDescription;
        }
    }

    static class Links {

        private String primary;
        private List<LinkType> social;
        private List<LinkType> articles;
        private List<LinkType> others;

        public Links() {
            social = new ArrayList<LinkType>();
            articles = new ArrayList<LinkType>();
            others = new ArrayList<LinkType>();
        }

        void setSocialLinks(ArrayList<LinkType> social) {
            this.social = social;
        }

        List<LinkType> getSocialLinks() {
            return this.social;
        }

        void setArticlesLinks(ArrayList<LinkType> articles) {
            this.articles = articles;
        }

        List<LinkType> getArticlesLinks() {
            return this.articles;
        }

        void setOtherLinks(ArrayList<LinkType> others) {
            this.others = others;
        }

        List<LinkType> getOtherLinks() {
            return this.others;
        }

        void setPrimaryLink(String primary) {
            this.primary = primary;
        }

        String getPrimaryLink() {
            return this.primary;
        }

        static class LinkType {
            private String type;
            private String title;
            private String url;

            public LinkType() {

            }

            void setType(String type) {
                this.type = type;
            }

            String getType() {
                return this.type;
            }

            void setTitle(String title) {
                this.title = title;
            }

            String getTitle() {
                return this.title;
            }

            void setUrl(String url) {
                this.url = url;
            }

            String getUrl() {
                return this.url;
            }
        }
    }

    static class OfficeCollection {

        private Office headquarter;
        private List<Office> others;

        public OfficeCollection() {
        }

        public Office getHeadquarter() {
            return headquarter;
        }

        public void setHeadquarter(Office headquarter) {
            this.headquarter = headquarter;
        }

        public List<Office> getOthers() {
            return others;
        }

        public void setOthers(List<Office> others) {
            this.others = others;
        }
    }

    static class Contact {

        private PhoneCollection phone;

        public Contact() {}

        public PhoneCollection getPhone() {
            return phone;
        }

        public void setPhone(PhoneCollection phone) {
            this.phone = phone;
        }

        static class PhoneCollection {

            private Phone primary;
            private List<Phone> others;

            public PhoneCollection() {

            }

            public List<Phone> getOthers() {
                return others;
            }

            public void setOthers(List<Phone> others) {
                this.others = others;
            }

            public Phone getPrimary() {
                return primary;
            }

            public void setPrimary(Phone primary) {
                this.primary = primary;
            }
        }
    }

}
