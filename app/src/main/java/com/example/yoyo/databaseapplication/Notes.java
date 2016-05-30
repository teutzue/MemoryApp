package com.example.yoyo.databaseapplication;

/**
 * Created by yoyo on 4/20/2016.
 */
public class Notes
{
   private int _id;
   private String topic;
   private String description;
    //private byte[] image;
    private String picturepath;

    public Notes()
    {

    }

    public Notes(int _id, String topic, String description)
    {
        this._id = _id;
        this.topic = topic;
        this.description = description;
    }

    public Notes(int _id, String topic, String description, String image) {
        this._id = _id;
        this.topic = topic;
        this.description = description;
        picturepath = image;
    }

    public String getImage() {
        return picturepath;
    }

    public void setImage(String path) {
        picturepath = path;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "_id=" + _id +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", picturepath='" + picturepath + '\'' +
                '}';
    }

    public Notes(String topic, String description)
    {
        this.topic = topic;
        this.description = description;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int get_id() {
        return _id;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }
}
