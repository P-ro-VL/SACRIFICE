package neu.cs.sacrifice.api.object;

import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;

public interface IGameObject {

    public String getID();

    public boolean isCollidable();

    public Texture getTexture();

    public double getX();

    public double getY();

    public boolean hasGravity();

    public Duration getLivingTime();

    public double getWidth();

    public double getHeight();

    public void setCollidable(boolean isCollidable);

    public void setTexture(Texture texture);

    public void setLocation(double x, double y);

    public void setX(double x);

    public void setY(double y);

    public void setGravity(boolean gravity);

    public void setLivingTime(Duration livingTime);

    public void onInteract(InteractType interactType);

}
