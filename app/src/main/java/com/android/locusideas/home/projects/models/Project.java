package com.android.locusideas.home.projects.models;

import java.util.List;

/**
 * Created on 29/06/16.
 */

public class Project {
    String id;
    String title;
    String createdAt;
    String updatedAt;
    Owner owner;
    Medias medias;

    public class Owner{
        User user;
    }

    public class User{
        String id;
        Name name;
        Picture picture;

        //TODO
        public class Name{
            String  firstName;
            String lastName;

            public String getFirstName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }
        }

        public String getId() {
            return id;
        }

        public Name getName() {
            return name;
        }

        public Picture getPicture() {
            return picture;
        }
    }

    public class Picture{
        String id;
        String type;
        String cloudinaryId;
        String url;
        int width;
        int height;

        public String getType() {
            return type;
        }

        public String getCloudinaryId() {
            return cloudinaryId;
        }

        public String getUrl() {
            return url;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    //TODO remove
    public class Medias{
        int count;
        List<Initial> initial;

        public int getCount() {
            return count;
        }

        public List<Initial> getInitial() {
            return initial;
        }
    }

    public class Initial{
        Media media;
        Integer position;

        public Media getMedia() {
            return media;
        }

        public Integer getPosition() {
            return position;
        }
    }

    public class Media{
        String id;
        String type;
        String title;
        String cloudinaryId;
        String url;
        int width;
        int height;

        public String getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public String getCloudinaryId() {
            return cloudinaryId;
        }

        public String getUrl() {
            return url;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    public User getOwner(){
        return owner.user;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Medias getMedias() {
        return medias;
    }
}


