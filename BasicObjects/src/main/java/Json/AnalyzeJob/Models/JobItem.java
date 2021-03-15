package Json.AnalyzeJob.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JobItem implements Serializable {

    @SerializedName("payload")
    private String payload;

    private int message_count;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public int getMessage_count() {
        return message_count;
    }

    public void setMessage_count(int message_count) {
        this.message_count = message_count;
    }
}
