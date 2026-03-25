package br.com.ccroccia.dao.generics;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import annotation.KeyType;
import br.com.ccroccia.dao.Persistent;
import br.com.ccroccia.exceptions.KeyTypeNotFoundException;

/**
 * @author rodrigo.pires
 *
 * Generic class that implements the generic interface with CRUD methods
 */
public abstract class GenericDAO<T extends Persistent, E extends Serializable> implements IGenericDAO<T,E> {

    /**
     * Singleton is required to have only one MAP in the system
     */
    private SingletonMap singletonMap;

    public abstract Class<T> getEntityClass();

    public abstract void updateData(T entity, T registeredEntity);

    public GenericDAO() {
        this.singletonMap = SingletonMap.getInstance();
    }

    public E getKey(T entity) throws KeyTypeNotFoundException {
        Field[] fields = entity.getClass().getDeclaredFields();
        E returnValue = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(KeyType.class)) {
                KeyType keyType = field.getAnnotation(KeyType.class);
                String methodName = keyType.value();
                try {
                    Method method = entity.getClass().getMethod(methodName);
                    returnValue = (E) method.invoke(entity);
                    return returnValue;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new KeyTypeNotFoundException("Primary key of object " + entity.getClass() + " not found", e);
                }
            }
        }
        if (returnValue == null) {
            String msg = "Primary key of object " + entity.getClass() + " not found";
            System.out.println("**** ERROR ****" + msg);
            throw new KeyTypeNotFoundException(msg);
        }
        return null;
    }

    @Override
    public Boolean register(T entity) throws KeyTypeNotFoundException {
        Map<E, T> innerMap = getInnerMap();
        E key = getKey(entity);
        if (innerMap.containsKey(key)) {
            return false;
        }

        innerMap.put(key, entity);
        return true;
    }

	private Map<E, T> getInnerMap() {
		Map<E, T> innerMap = (Map<E, T>) this.singletonMap.getMap().get(getEntityClass());
		if (innerMap == null) {
			innerMap = new HashMap<>();
			this.singletonMap.getMap().put(getEntityClass(), innerMap);
		}
		return innerMap;
	}

    @Override
    public void delete(E value) {
        Map<E, T> innerMap = getInnerMap();
        T registeredEntity = innerMap.get(value);
        if (registeredEntity != null) {
            innerMap.remove(value, registeredEntity);
        }
    }

    @Override
    public void update(T entity) throws KeyTypeNotFoundException {
        Map<E, T> innerMap = getInnerMap();
        E key = getKey(entity);
        T registeredEntity = innerMap.get(key);
        if (registeredEntity != null) {
            updateData(entity, registeredEntity);
        }
    }

    @Override
    public T find(E value) {
        Map<E, T> innerMap = getInnerMap();
        return innerMap.get(value);
    }

    @Override
    public Collection<T> findAll() {
        Map<E, T> innerMap = getInnerMap();
        return innerMap.values();
    }
}
