package Json.AnalyzeJob.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TransformerFailJobItem implements Serializable {
    private static final long serialVersionUID = -1615651547395497056L;
    private String extension;
    @SerializedName("convert_kinds")
    private Map<String, ConversionMetainfo> convertMetas;
    @SerializedName("global_meta_info")
    private ConversionMetainfo globalMeta;
    @SerializedName("converted_kinds")
    private Set<String> convertedKinds = new HashSet<String>();
    @SerializedName("force_convert")
    private boolean forceConvert;
    @SerializedName("need_encrypt")
    private boolean needEncrypt;
    private boolean tryAnotherWhenFail = true;
    private int version;
    @SerializedName("additional_infos")
    private Map<String, Object> additionalInfos;
    private transient String jobMd5;

    public boolean isForceConvert() {
        return forceConvert;
    }

    public void setForceConvert(boolean forceConvert) {
        this.forceConvert = forceConvert;
    }

    public Map<String, ConversionMetainfo> getConvertMetas() {
        return this.convertMetas;
    }

    public void rmConvertMeta(String convertKind) {
        if (convertMetas != null && convertMetas.containsKey(convertKind)) {
            convertMetas.remove(convertKind);
        }
    }

    public void setExtension(String ext) {
        this.extension = ext;
    }

    public String getExtension() {
        if (extension == null) {
            return null;
        }
        return this.extension.trim().toLowerCase();
    }

    public void setConvertMetas(Map<String, ConversionMetainfo> convertKinds) {
        this.convertMetas = convertKinds;
    }

    public Set<String> getConvertedKinds() {
        return convertedKinds;
    }

    public void setConvertedKinds(Set<String> convertedKinds) {
        this.convertedKinds = convertedKinds;
    }

    public boolean isTryAnotherWhenFail() {
        return tryAnotherWhenFail;
    }

    public void setTryAnotherWhenFail(boolean tryAnotherWhenFail) {
        this.tryAnotherWhenFail = tryAnotherWhenFail;
    }

    public boolean isNeedEncrypt() {
        return needEncrypt;
    }

    public void setNeedEncrypt(boolean needEncrypt) {
        this.needEncrypt = needEncrypt;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Map<String, Object> getAdditionalInfos() {
        return additionalInfos;
    }

    public void setAdditionalInfos(Map<String, Object> additionalInfos) {
        this.additionalInfos = additionalInfos;
    }


    public String getJobMd5() {
        return jobMd5;
    }

    public void setJobMd5(String jobMd5) {
        this.jobMd5 = jobMd5;
    }

    public ConversionMetainfo getGlobalMeta() {
        return globalMeta;
    }

    public void setGlobalMeta(ConversionMetainfo globalMeta) {
        this.globalMeta = globalMeta;
    }
}
