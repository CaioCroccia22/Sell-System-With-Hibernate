package br.com.ccroccia.dao.generics;

import java.util.HashMap;
import java.util.Map;


public class SingletonMap {

    private static SingletonMap singletonMap;

    /**
     * Contains all application records.
     * Simulates the database
     */
    protected Map<Class, Map<?, ?>> map;

    private SingletonMap() {
        map = new HashMap<>();
    }

    /**
     * Method that ensures only one instance of this object is returned
     *
     * @return SingletonMap
     */
    public static SingletonMap getInstance() {
        if (singletonMap == null) {
            singletonMap = new SingletonMap();
        }
        return singletonMap;
    }

    public Map<Class, Map<?, ?>> getMap() {
        return this.map;
    }
}
