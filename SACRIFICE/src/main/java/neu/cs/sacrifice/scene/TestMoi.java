package neu.cs.sacrifice.scene;

import com.almasb.fxgl.dsl.FXGL;
import neu.cs.sacrifice.SACRIFICE;
import neu.cs.sacrifice.api.event.EventHandler;
import neu.cs.sacrifice.api.event.EventListener;
import neu.cs.sacrifice.api.event.type.PostGameSceneChangeEvent;
import neu.cs.sacrifice.scene.objects.TestObject;

public class TestMoi implements EventListener {

    @EventHandler
    public void onSceneChange(PostGameSceneChangeEvent e){
        SACRIFICE.getInstance().getCurrentGameScene().addObject(new TestObject(), 100, 300);
    }

}
