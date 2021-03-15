package Json.AnalyzeJob.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public class ConversionMetainfo implements Serializable {
    private static final long serialVersionUID = -5086055104066130978L;
    @SerializedName("content_type")
    private String contentType;
    @SerializedName("format")
    private String format;
    @SerializedName("spec")
    private Map<String, Integer> spec;
    private String password;
    @SerializedName("owner_password")
    private String ownerPassword;
    @SerializedName("task_id")
    private String taskId;

    public String getContentType() {
        return this.contentType;
    }

    public String getFormat() {
        return this.format;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public int getHeight() {
        return this.spec.get("height");
    }

    public int getWidth() {
        return this.spec.get("width");
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOwnerPassword() {
        return ownerPassword;
    }

    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }
}
