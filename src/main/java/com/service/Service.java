package com.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.model.EntityModel;
import com.model.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private EntityRepository repository;
    private Gson gson = new Gson();

    public EntityModel create(String body) {
        try {
            Entity entity = gson.fromJson(body, Entity.class);
            Iterable<EntityModel> list = repository.findAll();
            for (EntityModel item :
                    list) {
                if (item.getKey().equals(entity.getKey())) {
                    return null;
                }
            }
            EntityModel item = new EntityModel(entity.getKey(), entity.getValue());
            return repository.save(item);
        } catch (JsonSyntaxException jSE) {
            jSE.printStackTrace();
            return null;
        }
    }

    public EntityModel get(String body) {
        try {
            Id key = gson.fromJson(body, Id.class);
            Iterable<EntityModel> list = repository.findAll();
            for (EntityModel item :
                    list) {
                if (item.getKey().equals(key.getKey())) return item;
            }
            return null;
        } catch (JsonSyntaxException jSE) {
            jSE.printStackTrace();
            return null;
        }
    }

    public EntityModel update(String body) {
        try {
            Iterable<EntityModel> list = repository.findAll();
            Entity entity = gson.fromJson(body, Entity.class);
            for (EntityModel item :
                    list) {
                if (item.getKey().equals(entity.getKey())){
                    item.setValue(entity.getValue());
                    return repository.save(item);
                }
            }
            return null;
        } catch (JsonSyntaxException jSE) {
            jSE.printStackTrace();
            return null;
        }
    }

    private EntityModel update(Integer id, String key, String value) {
        EntityModel entityModel = repository.findById(id).get();
        if (entityModel.getKey().equals(key)) {
            entityModel.setValue(value);
            repository.save(entityModel);
            return entityModel;
        }
        return null;
    }

    public EntityModel delete(String body) {
        try {
            Iterable<EntityModel> list = repository.findAll();
            Entity entity = gson.fromJson(body, Entity.class);
            for (EntityModel item :
                    list) {
                if (item.getKey().equals(entity.getKey())){
                    repository.deleteById(item.getId());
                    if (!repository.existsById(item.getId())) return item;
                    else return null;
                }
            }
            return null;
        } catch (JsonSyntaxException jSE) {
            jSE.printStackTrace();
            return null;
        }
    }

    public Iterable<EntityModel> drop(){
        Iterable<EntityModel> list = repository.findAll();
        repository.deleteAll();
        return list;
    }

    private class Id {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
    private class Entity {
        private String key;
        private String value;

        public Entity(){};

        public String getKey() {
            return key;
        }

        public void setKey(String id) {
            this.key = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
