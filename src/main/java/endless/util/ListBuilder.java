package endless.util;

import java.util.ArrayList;
import java.util.List;

public class ListBuilder<T> {

    private List<T> list;

    public ListBuilder<T> instance(){
        list = new ArrayList<T>();
        return this;
    }

    public ListBuilder<T> add(T value){
        list.add(value);
        return this;
    }

    public List<T> list(){
        return list;
    }
}
