package vn.provl.api.component.entity;

import vn.provl.api.component.IComponent;
import vn.provl.api.component.entity.attribute.Attribute;
import vn.provl.api.component.entity.attribute.AttributeType;
import vn.provl.api.component.entity.behaviour.EntityBehaviour;
import vn.provl.api.component.graphics.Sprite;

public interface Entity extends IComponent {

    void setSprite(Sprite sprite);

    Sprite getSprite();

    <T extends Attribute> T getAttribute(Class<T> attributeType);

    <T extends EntityBehaviour> T getBehaviour(Class<T> behaviourType);
}
