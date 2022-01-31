package com.example.unique_tech.converter;

import java.util.function.Function;

public class BaseConvertor<Model, Entity> {
    private final Function<Model, Entity> fromModel;
    private final Function<Entity, Model> fromEntity;

    public BaseConvertor(Function<Model, Entity> fromModel, Function<Entity, Model> fromEntity) {
        this.fromModel = fromModel;
        this.fromEntity = fromEntity;
    }

    public final Entity convertFromModel(Model model) {
        return fromModel.apply(model);
    }

    public final Model convertFromEntity(Entity entity) {
        return fromEntity.apply(entity);
    }
}