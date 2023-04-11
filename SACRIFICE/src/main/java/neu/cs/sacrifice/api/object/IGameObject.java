package neu.cs.sacrifice.api.object;

import com.almasb.fxgl.texture.Texture;

public interface IGameObject {

    public String getID();

    public boolean isCollidable();

    public Texture getTexture();

    public double getX();

    public double getY();

    public boolean hasGravity();

    public void setCollidable(boolean isCollidable);

    public void setTexture(Texture texture);

    public void setLocation(double x, double y);

    public void setX(double x);

    public void setY(double y);

    public void setGravity(boolean gravity);

    public void onInteract(InteractType interactType);
}
