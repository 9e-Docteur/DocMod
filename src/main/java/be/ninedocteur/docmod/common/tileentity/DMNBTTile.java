package be.ninedocteur.docmod.common.tileentity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import be.ninedocteur.docmod.common.tileentity.system.IDMNBT;
import be.ninedocteur.docmod.common.tileentity.system.NBTEngine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class DMNBTTile extends BlockEntity implements IDMNBT{
	private HashMap<Object, Object> HASHMAP = new HashMap<>();
	private NBTEngine nbtEngine = new NBTEngine(HASHMAP);
	private String name;

	/**
	 * WIP CLASS FOR SAVING IN JSON FILE INSTEAD OF FORGE NBT
	 * @param p_155228_
	 * @param p_155229_
	 * @param p_155230_
	 *
	 */
	//TODO: Json -> block pos for tile; and on place event, add tile name and pos, on remove event, delete it from json
	public DMNBTTile(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
		super(p_155228_, p_155229_, p_155230_);
		// TODO Auto-generated constructor stub
		Path filePath = Paths.get("docmod/dmnbt/dmnbt.json");
	    if (Files.exists(filePath)) {
	        try {
	            String jsonString = Files.readString(filePath);
	            JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
	            if (jsonObject != null) {
	                JsonObject data = jsonObject.getAsJsonObject("dmnbt");
	                if (data != null) {
	                    Set<String> keys = data.keySet();
	                    for (String key : keys) {
	                        JsonArray jsonArray = data.getAsJsonArray(key);
	                        HashMap<String, Object> hashMap = jsonToHashmap(jsonArray);
	                        HASHMAP.put(key, hashMap);
	                    }
	                }
	            }
	        } catch (IOException e) {
	            // Gérer l'exception si le fichier n'a pas pu être lu
	            e.printStackTrace();
	        }
	    }
	}
	
	private HashMap<String, Object> jsonToHashmap(JsonArray jsonArray) {
	    HashMap<String, Object> hashMap = new HashMap<>();

	    for (int i = 0; i < jsonArray.size(); i++) {
	        JsonElement jsonElement = jsonArray.get(i);
	        if (jsonElement.isJsonPrimitive()) {
	            JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
	            if (jsonPrimitive.isBoolean()) {
	                hashMap.put(String.valueOf(i), jsonPrimitive.getAsBoolean());
	            } else if (jsonPrimitive.isNumber()) {
	                hashMap.put(String.valueOf(i), jsonPrimitive.getAsNumber());
	            } else if (jsonPrimitive.isString()) {
	                hashMap.put(String.valueOf(i), jsonPrimitive.getAsString());
	            } else {
	                hashMap.put(String.valueOf(i), jsonPrimitive.getAsCharacter());
	            }
	        } else if (jsonElement.isJsonArray()) {
	            JsonArray nestedArray = jsonElement.getAsJsonArray();
	            hashMap.put(String.valueOf(i), jsonToHashmap(nestedArray));
	        } else if (jsonElement.isJsonObject()) {
	            JsonObject nestedObject = jsonElement.getAsJsonObject();
	            hashMap.put(String.valueOf(i), jsonToHashmap(nestedObject.getAsJsonArray()));
	        }
	    }

	    return hashMap;
	}

	
	public void saveToJSON(String tileName) {
	      JsonObject json = new JsonObject();

	      // Ajouter vos Hashmaps à l'objet JSON
	      json.add("hashmap1", hashMapToJson(tileName));

	      // Écrire le fichier JSON dans le dossier docmod/dmnbt/
	      Path path = Paths.get("docmod/dmnbt/dmnbt.json");
	      try {
	         Files.createDirectories(path.getParent());
	         BufferedWriter writer = Files.newBufferedWriter(path);
	         Gson gson = new GsonBuilder().setPrettyPrinting().create();
	         gson.toJson(json, writer);
	         writer.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	 }
	
	public JsonElement hashMapToJson(String tileName) {
		JsonObject json = new JsonObject();
		   JsonObject data = new JsonObject();
		   JsonArray values = new JsonArray();

		   for (Map.Entry<Object, Object> entry : HASHMAP.entrySet()) {
		      String key = entry.getKey().toString();
		      Object value = entry.getValue();

		      // Ajouter chaque paire clé-valeur de la Hashmap à l'objet JSON
		      if (value instanceof Boolean) {
		         values.add(new JsonPrimitive((Boolean) value));
		      } else if (value instanceof Number) {
		         values.add(new JsonPrimitive((Number) value));
		      } else if (value instanceof String) {
		         values.add(new JsonPrimitive((String) value));
		      } else if (value instanceof Character) {
		         values.add(new JsonPrimitive((Character) value));
		      } else if (value instanceof Map) {
		         values.add(mapToJson((Map<String, Object>) value));
		      } else if (value instanceof Collection) {
		         values.add(collectionToJson((Collection<Object>) value));
		      } else if (value instanceof Object[]) {
		         values.add(arrayToJson((Object[]) value));
		      } else {
		         // Gérer d'autres types d'objets si nécessaire
		      }
		   }

		   data.add(tileName, values);
		   json.add("dmnbt", data);

		   return json;

	}
	
	private JsonElement mapToJson(Map<String, Object> map) {
		   JsonObject json = new JsonObject();

		   for (Map.Entry<String, Object> entry : map.entrySet()) {
		      String key = entry.getKey();
		      Object value = entry.getValue();

		      // Ajouter chaque paire clé-valeur de la Map à l'objet JSON
		      if (value instanceof Boolean) {
		         json.addProperty(key, (Boolean) value);
		      } else if (value instanceof Number) {
		         json.addProperty(key, (Number) value);
		      } else if (value instanceof String) {
		         json.addProperty(key, (String) value);
		      } else if (value instanceof Character) {
		         json.addProperty(key, (Character) value);
		      } else if (value instanceof Map) {
		         json.add(key, mapToJson((Map<String, Object>) value));
		      } else if (value instanceof Collection) {
		         json.add(key, collectionToJson((Collection<Object>) value));
		      } else if (value instanceof Object[]) {
		         json.add(key, arrayToJson((Object[]) value));
		      } else {
		         // Gérer d'autres types d'objets si nécessaire
		      }
		   }

		   return json;
		}

		private JsonElement collectionToJson(Collection<Object> collection) {
		   JsonArray json = new JsonArray();

		   for (Object value : collection) {
		      // Ajouter chaque élément de la Collection à l'objet JSON
		      if (value instanceof Boolean) {
		         json.add(new JsonPrimitive((Boolean) value));
		      } else if (value instanceof Number) {
		         json.add(new JsonPrimitive((Number) value));
		      } else if (value instanceof String) {
		         json.add(new JsonPrimitive((String) value));
		      } else if (value instanceof Character) {
		         json.add(new JsonPrimitive((Character) value));
		      } else if (value instanceof Map) {
		         json.add(mapToJson((Map<String, Object>) value));
		      } else if (value instanceof Collection) {
		         json.add(collectionToJson((Collection<Object>) value));
		      } else if (value instanceof Object[]) {
		         json.add(arrayToJson((Object[]) value));
		      } else {
		         // Gérer d'autres types d'objets si nécessaire
		      }
		   }

		   return json;
		}

		private JsonElement arrayToJson(Object[] array) {
		   JsonArray json = new JsonArray();

		   for (Object value : array) {
		      // Ajouter chaque élément du tableau à l'objet JSON
		      if (value instanceof Boolean) {
		         json.add(new JsonPrimitive((Boolean) value));
		      } else if (value instanceof Number) {
		         json.add(new JsonPrimitive((Number) value));
		      } else if (value instanceof String) {
		         json.add(new JsonPrimitive((String) value));
		      } else if (value instanceof Character) {
		         json.add(new JsonPrimitive((Character) value));
		      } else if (value instanceof Map) {
		         json.add(mapToJson((Map<String, Object>) value));
		      } else if (value instanceof Collection) {
		         json.add(collectionToJson((Collection<Object>) value));
		      } else if (value instanceof Object[]) {
		         json.add(arrayToJson((Object[]) value));
		      } else {
		         // Gérer d'autres types d'objets si nécessaire
		      }
		   }

		   return json;
		}
		
		public NBTEngine getNbtEngine() {
			return nbtEngine;
		}

		@Override
		public void save(NBTEngine nbt) {
			// TODO Auto-generated method stub
			nbt = getNbtEngine();
			saveToJSON(this.getName());
		}

		@Override
		public void load(NBTEngine nbt) {
			// TODO Auto-generated method stub
			nbt = getNbtEngine();
		}
		
		public String getName() {
			if(name.isEmpty()) {
				throw new IllegalStateException("The name of the tile entity is empty. Please in your tile entity, use setName() in your constructor"); 
			} else {
				return this.name;
			}
		}
		
		public void setName(String name) {
			this.name = name;
		}
}
