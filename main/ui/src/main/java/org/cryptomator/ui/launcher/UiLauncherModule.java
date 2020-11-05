package org.cryptomator.ui.launcher;

import dagger.Module;
import dagger.Provides;
import org.cryptomator.common.JniModule;
import org.cryptomator.integrations.uiappearance.UiAppearanceProvider;
import org.cryptomator.ui.fxapp.FxApplicationComponent;
import org.cryptomator.ui.fxapp.FxApplicationScoped;
import org.cryptomator.ui.traymenu.TrayMenuComponent;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Module(includes = {JniModule.class}, subcomponents = {TrayMenuComponent.class, FxApplicationComponent.class})
public abstract class UiLauncherModule {

	@Provides
	@Singleton
	static Optional<UiAppearanceProvider> provideAppearanceProvider() {
		return ServiceLoader.load(UiAppearanceProvider.class).findFirst();
	}

	@Provides
	@Singleton
	static ResourceBundle provideLocalization() {
		return ResourceBundle.getBundle("i18n.strings");
	}

	@Provides
	@Singleton
	@Named("launchEventQueue")
	static BlockingQueue<AppLaunchEvent> provideFileOpenRequests() {
		return new ArrayBlockingQueue<>(10);
	}

}
