
package com.example.bigstep.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicResponse implements Parcelable
{

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<Music> music = null;
    public final static Creator<MusicResponse> CREATOR = new Creator<MusicResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MusicResponse createFromParcel(Parcel in) {
            return new MusicResponse(in);
        }

        public MusicResponse[] newArray(int size) {
            return (new MusicResponse[size]);
        }

    }
    ;

    protected MusicResponse(Parcel in) {
        this.resultCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.music, (Music.class.getClassLoader()));
    }

    public MusicResponse() {
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Music> getMusic() {
        return music;
    }

    public void setMusic(List<Music> music) {
        this.music = music;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(resultCount);
        dest.writeList(music);
    }

    public int describeContents() {
        return  0;
    }

}
