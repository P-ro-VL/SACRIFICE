package neu.cs.sacrifice.api.object;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;
import neu.cs.sacrifice.api.utils.TextureLoader;

public class GameObject extends Component implements IGameObject {
    private String ID;
    private boolean isCollidable = true;
    private Texture texture;
    private boolean gravity;

    private Duration livingTime = null;

    public GameObject(String ID, String textureName){
        this.ID = ID;
        this.texture = TextureLoader.loadTexture(textureName);

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
    public Duration getLivingTime() {
        return livingTime;
    }

    @Override
    public double getWidth() {
        return getTexture().getWidth();
    }

    @Override
    public double getHeight() {
        return getTexture().getHeight();
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
    public void setLivingTime(Duration livingTime) {
        this.livingTime = livingTime;
    }

    @Override
    public void onInteract(InteractType interactType) {

    }

}
