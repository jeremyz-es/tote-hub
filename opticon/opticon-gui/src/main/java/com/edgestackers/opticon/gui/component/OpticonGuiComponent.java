package com.edgestackers.opticon.gui.component;

import com.edgestackers.opticon.gui.view.OpticonGuiController;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        CaesarModule.class,
        MessageModule.class,
        OpticonGuiModule.class,
        OpticonServiceModule.class,
        ViewModule.class,
        ViewModelModule.class,
        ViewControllerModule.class,
})
public interface OpticonGuiComponent {
    OpticonGuiController opticonGuiController();
}
