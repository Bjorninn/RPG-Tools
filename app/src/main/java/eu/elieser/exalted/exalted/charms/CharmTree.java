package eu.elieser.exalted.exalted.charms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.elieser.exalted.data.Charm;
import eu.elieser.exalted.data.Charms;
import eu.elieser.exalted.data.DataStore;
import eu.elieser.exalted.data.MartialArtsCharm;
import eu.elieser.exalted.data.MartialArtsCharms;

/**
 * Created by bjorn on 23/04/16.
 */
public class CharmTree
{
    private static CharmTree instance;
    private final Map<String, Node> nodes = new HashMap<>();

    public CharmTree()
    {
        instance = this;
    }

    public static CharmTree getCharmTree()
    {
        return instance;
    }

    public void initialize(MartialArtsCharms charms)
    {
        List<MartialArtsCharm> charmList = charms.getCharms();

        for (Charm charm :
                charmList)
        {
            initialize(charm);
        }
    }

    public void initialize(Charms charms)
    {
        List<Charm> charmList = charms.getCharms();

        for (Charm charm :
                charmList)
        {
            initialize(charm);
        }
    }

    private void initialize(Charm charm)
    {
        String charmName = charm.getName();

        Node node;
        if (nodes.containsKey(charmName))
        {
            node = nodes.get(charmName);
        }
        else
        {
            node = new Node(charm.getName());
            nodes.put(charm.getName(), node);
        }

        for (String preCharmName :
                charm.getPrerequisiteCharms())
        {
            Node preNode;
            if (nodes.containsKey(preCharmName))
            {
                preNode = nodes.get(preCharmName);
            }
            else
            {
                preNode = new Node(preCharmName);
                nodes.put(preCharmName, preNode);
            }

            node.prerequisites.add(preNode);
            preNode.unlocks.add(node);
        }
    }

    public List<Charm> getCharmsForPrerequisite(String charmName)
    {
        DataStore dataStore = DataStore.getDataStore();
        Node node = nodes.get(charmName);
        List<Charm> charms = new ArrayList<>(node.prerequisites.size());

        for (Node n :
                node.prerequisites)
        {
            charms.add(dataStore.getCharm(n.name));
        }

        return charms;
    }

    public List<Charm> getCharmsThatUnlock(String charmName)
    {
        DataStore dataStore = DataStore.getDataStore();
        Node node = nodes.get(charmName);
        List<Charm> charms = new ArrayList<>(node.unlocks.size());

        for (Node n :
                node.unlocks)
        {
            charms.add(dataStore.getCharm(n.name));
        }

        return charms;
    }

    public static class Node
    {
        public final String name;
        public final Set<Node> prerequisites = new HashSet<>();
        public final Set<Node> unlocks = new HashSet<>();

        public Node(String name)
        {
            this.name = name;
        }
    }
}
