package com.wolfhack.service.admin.ui.view;

import com.vaadin.flow.shared.Registration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Component
public class EventBroadcast {

	private final ExecutorService executorService = Executors.newSingleThreadExecutor();

	private final List<Consumer<String>> listeners = new CopyOnWriteArrayList<>();

	public synchronized Registration register(Consumer<String> listener) {
		listeners.add(listener);
		return () -> {
			synchronized(EventBroadcast.class) {
				listeners.remove(listener);
			}
		};
	}

	public void broadcast(String message) {
		listeners.forEach(listener ->
			executorService.execute(() -> listener.accept(message))
		);
	}

}
