package be.ninedocteur.docmod.common.tileentity.system;

import java.util.HashMap;

public class NBTEngine {
	private HashMap<Object, Object> HASHMAP;
	
	public NBTEngine(HashMap<Object, Object> HASHMAP) {
		this.HASHMAP = HASHMAP;
	}
	
	public void put(Object key, Object value) {
		HASHMAP.put(key, value);
	}
	
	public void get(Object key) {
		HASHMAP.get(key);
	}
	
	public void remove(Object key) {
		HASHMAP.remove(key);
	}
}
