package neu.cs.sacrifice.entity;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import neu.cs.sacrifice.SACRIFICE;
import neu.cs.sacrifice.api.entity.EntityType;

public class EntityBuilder implements EntityFactory {

    @Spawns("player")
    public Entity spawnPlayer(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().friction(0f));

        return FXGL.entityBuilder(data)
                .type(EntityType.PLAYER)
                .bbox(new HitBox(new Point2D(50, 50), BoundingShape.box(100, 250)))
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(physics)
                .with(new PlayerComponent())
                .build();
    }

    @Spawns("background")
    public Entity spawnBackground(SpawnData data){
        Image bgImageName = new Image("/assets/textures/1.png");
        ScrollingBackgroundView bgView = new ScrollingBackgroundView(bgImageName, SACRIFICE.WINDOW_WIDTH, SACRIFICE.WINDOW_HEIGHT);
        return FXGL.entityBuilder(data)
                .view(bgView)
                .zIndex(-1)
                //.with(new IrremovableComponent())
                .build();
    }

}
