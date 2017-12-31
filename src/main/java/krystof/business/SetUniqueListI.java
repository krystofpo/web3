package krystof.business;

import org.apache.commons.collections.list.SetUniqueList;

import java.util.*;


public class SetUniqueListI extends SetUniqueList {

    public SetUniqueListI() {

        this(new ArrayList(), new HashSet());
    }
    public SetUniqueListI(List list, Set set) {
        super(list, set);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Label)) return false;

        SetUniqueListI otherList = (SetUniqueListI) o;
        return this.hashCode()==otherList.hashCode();
    }

    @Override
    public int hashCode() {
        ArrayList hashList = new ArrayList(this.getCollection());
        Collections.sort(hashList);
        return hashList.hashCode();

    }
}
