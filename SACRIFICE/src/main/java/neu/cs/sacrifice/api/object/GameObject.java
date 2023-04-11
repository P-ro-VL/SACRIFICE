package neu.cs.sacrifice.api.object;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;

public class GameObject extends Component implements IGameObject {
    private String ID;
    private boolean isCollidable = true;
    private Texture texture;
    private boolean gravity;

    public GameObject(String ID, String textureName){
        this.ID = ID;
        this.texture = new Texture(FXGL.getAssetLoader().loadImage("plant.png"));

        System.out.println(texture.getImage());
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public boolean isCollidable() {
        return isCollidable;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public double getX() {
        return entity.getX();
    }

    @Override
    public double getY() {
        return entity.getY();
    }

    @Override
    public boolean hasGravity() {
        return gravity;
    }

    @Override
    public void setCollidable(boolean isCollidable) {
        this.isCollidable = isCollidable;
    }

    @Override
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void setLocation(double x, double y) {
        this.entity.setPosition(x,y);
    }

    @Override
    public void setX(double x) {
        this.entity.setX(x);
    }

    @Override
    public void setY(double y) {
        this.entity.setY(y);
    }

    @Override
    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    @Override
    public void onInteract(InteractType interactType) {

    }
}
