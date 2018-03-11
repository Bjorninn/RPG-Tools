package eu.elieser.exalted.data.dndspell;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bjorn on 15/05/16.
 */
public class Spell
{
    private String name;
    private String description;
    private String higherLevel;
    private Source source;
    private List<String> components;
    private String material;
    private Boolean isRitual;
    private String duration; //TODO change
    private String range; //TODO change
    private Boolean isConcentration;
    private CastingTime castingTime;
    private Integer level;
    private String school;
    @SerializedName("class")

    private List<String> clazz;

    // TODO change
    private List<Archtype> archetypes;
    private List<String> oaths;
    private List<String> circles;
    private List<String> domains;
    private List<String> patrons;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getHigherLevel()
    {
        return higherLevel;
    }

    public void setHigherLevel(String higherLevel)
    {
        this.higherLevel = higherLevel;
    }

    public Source getSource()
    {
        return source;
    }

    public void setSource(Source source)
    {
        this.source = source;
    }

    public List<String> getComponents()
    {
        return components;
    }

    public void setComponents(List<String> components)
    {
        this.components = components;
    }

    public String getMaterial()
    {
        return material;
    }

    public void setMaterial(String material)
    {
        this.material = material;
    }

    public Boolean getRitual()
    {
        return isRitual;
    }

    public void setRitual(Boolean ritual)
    {
        isRitual = ritual;
    }

    public String getDuration()
    {
        return duration;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public Boolean getConcentration()
    {
        return isConcentration;
    }

    public void setConcentration(Boolean concentration)
    {
        isConcentration = concentration;
    }

    public CastingTime getCastingTime()
    {
        return castingTime;
    }

    public void setCastingTime(CastingTime castingTime)
    {
        this.castingTime = castingTime;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public String getSchool()
    {
        return school;
    }

    public void setSchool(String school)
    {
        this.school = school;
    }

    public List<String> getClazz()
    {
        return clazz;
    }

    public void setClazz(List<String> clazz)
    {
        this.clazz = clazz;
    }

    public List<Archtype> getArchetypes()
    {
        return archetypes;
    }

    public void setArchetypes(List<Archtype> archetypes)
    {
        this.archetypes = archetypes;
    }

    public List<String> getOaths()
    {
        return oaths;
    }

    public void setOaths(List<String> oaths)
    {
        this.oaths = oaths;
    }

    public List<String> getCircles()
    {
        return circles;
    }

    public void setCircles(List<String> circles)
    {
        this.circles = circles;
    }

    public List<String> getDomains()
    {
        return domains;
    }

    public void setDomains(List<String> domains)
    {
        this.domains = domains;
    }

    public List<String> getPatrons()
    {
        return patrons;
    }

    public void setPatrons(List<String> patrons)
    {
        this.patrons = patrons;
    }

    public String getRange()
    {
        return range;
    }

    public void setRange(String range)
    {
        this.range = range;
    }

    @Override
    public String toString()
    {
        return "Spell{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", higherLevel='" + higherLevel + '\'' +
                ", source=" + source +
                ", components=" + components +
                ", material='" + material + '\'' +
                ", isRitual=" + isRitual +
                ", duration='" + duration + '\'' +
                ", isConcentration=" + isConcentration +
                ", castingTime='" + castingTime + '\'' +
                ", level='" + level + '\'' +
                ", school='" + school + '\'' +
                ", clazz=" + clazz +
                ", archetypes='" + archetypes + '\'' +
                ", oaths='" + oaths + '\'' +
                ", circles='" + circles + '\'' +
                ", domains='" + domains + '\'' +
                ", patrons='" + patrons + '\'' +
                '}';
    }
}

//{
//        "name":"Abi-Dalzim's Horrid Wilting",
//        "desc":"<p>You draw the moisture from every creature in a 30-foot cube centered on a point you choose within range. Each creature in that area must make a Constitution saving throw. Constructs and undead aren't affected, and plants and water elementals make this saving throw with disadvantage. A creature takes 10d8 necrotic damage on a failed save, or half as much damage on a successful one.You hurl a bubble of acid. Choose one creature within range, or choose two creatures within range that are within 5 feet of each other. A target must succeed on a Dexterity saving throw or take 1d6 acid damage.</p><p>This spells damage increases by 1d6 when you reach 5th Level (2d6), 11th level (3d6) and 17th level (4d6).</p>",
//        "page":"ee pc 15",
//        "range":"150 feet",
//        "components":"V, S, M",
//        "material":"A bit of sponge.",
//        "ritual":"no",
//        "duration":"Instantaneous",
//        "concentration":"no",
//        "casting_time":"1 action",
//        "level":"8th-level",
//        "school":"Necromancy",
//        "class":"Sorcerer, Wizard"
//        },
