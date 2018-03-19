
package eu.elieser.exalted.data.genesys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Talent implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tier")
    @Expose
    private Integer tier;
    @SerializedName("activation")
    @Expose
    private String activation;
    @SerializedName("sub_activation")
    @Expose
    private String subActivation;
    @SerializedName("ranked")
    @Expose
    private Boolean ranked;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("source")
    @Expose
    private List<String> source = null;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("keywords")
    @Expose
    private List<String> keywords = null;
    @SerializedName("requirement")
    @Expose
    private Integer requirement = 0;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getTier()
    {
        return tier;
    }

    public void setTier(Integer tier)
    {
        this.tier = tier;
    }

    public String getActivation()
    {
        return activation;
    }

    public void setActivation(String activation)
    {
        this.activation = activation;
    }

    public Boolean getRanked()
    {
        return ranked;
    }

    public void setRanked(Boolean ranked)
    {
        this.ranked = ranked;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<String> getSource()
    {
        return source;
    }

    public void setSource(List<String> source)
    {
        this.source = source;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public List<String> getKeywords()
    {
        return keywords;
    }

    public void setKeywords(List<String> keywords)
    {
        this.keywords = keywords;
    }

    public void addKeyword(String keyword)
    {
        keywords.add(keyword);
    }

    public Integer getRequirement()
    {
        return requirement;
    }

    public void setRequirement(Integer requirement)
    {
        this.requirement = requirement;
    }

    public String getSubActivation()
    {
        return subActivation;
    }

    public void setSubActivation(String subActivation)
    {
        this.subActivation = subActivation;
    }

    public boolean hasKeywords()
    {
        return keywords != null && !keywords.isEmpty();
    }
}
