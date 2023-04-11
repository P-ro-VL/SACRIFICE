package neu.cs.sacrifice.scene;

import com.almasb.fxgl.dsl.FXGL;
import neu.cs.sacrifice.api.event.EventHandler;
import neu.cs.sacrifice.api.event.EventListener;
import neu.cs.sacrifice.api.event.type.PostGameSceneChangeEvent;

public class TestMoi implements EventListener {

    @EventHandler
    public void onSceneChange(PostGameSceneChangeEvent e){
        FXGL.getGameWorld().spawn("door", 3000, 100);
    }

}
