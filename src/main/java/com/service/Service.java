package com.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.model.EntityModel;
import com.model.EntityRepository;
import com.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private EntityRepository repository;
    private Gson gson = new Gson();

    public EntityModel create(String body) {
        try {
            Entity entity = gson.fromJson(body, Entity.class);
            if (Utils.isNumeric(entity.getId()) && entity.getValue() != null) {
                if (!repository.existsById(Integer.parseInt(entity.getId()))) {
                    EntityModel item = new EntityModel(Integer.parseInt(entity.getId()), entity.getValue());
                    repository.save(item);
                    return item;
                }
            }
        } catch (JsonSyntaxException jSE) {
            jSE.printStackTrace();
        }
        return null;
    }

    public EntityModel get(String body) {
        try {
            Id id = gson.fromJson(body, Id.class);
            if (Utils.isNumeric(id.getId())) {
                Integer identifier = Integer.parseInt(id.getId());
                if (repository.existsById(identifier)) {
                    Optional<EntityModel> optional = repository.findById(identifier);
                    if (optional.isPresent()) return optional.get();
                }
            }
        } catch (JsonSyntaxException jSE) {
            jSE.printStackTrace();
        }
        return null;
    }

    public EntityModel update(String body) {
        try {
            Entity entity = gson.fromJson(body, Entity.class);
            if (Utils.isNumeric(entity.getId()) && entity.getValue() != null) {
                Integer identifier = Integer.parseInt(entity.getId());
                if (repository.existsById(identifier)) {
                    EntityModel item = new EntityModel(identifier, entity.getValue());
                    repository.save(item);
                    return item;
                }
            }
        } catch (JsonSyntaxException jSE) {
            jSE.printStackTrace();
        }
        return null;
    }

    public EntityModel delete(String body) {
        try {
            Id id = gson.fromJson(body, Id.class);
            if (Utils.isNumeric(id.getId())) {
                Integer identifier = Integer.parseInt(id.getId());
                if (repository.existsById(identifier)) {
                    EntityModel item = repository.findById(identifier).get();
                    repository.deleteById(identifier);
                    return item;
                }
            }
        } catch (JsonSyntaxException jSE) {
            jSE.printStackTrace();
        }
        return null;
    }

    public Iterable<EntityModel> drop(){
        Iterable<EntityModel> list = repository.findAll();
        repository.deleteAll();
        return list;
    }

    private class Id {
        private String id;

        public String getId() {
            return id;
        }

        public void setKey(String id) {
            this.id = id;
        }
    }
    private class Entity {
        private String id;
        private String value;

        public Entity(){};

        public String getId() {
            return id;
        }

        public void setKey(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
