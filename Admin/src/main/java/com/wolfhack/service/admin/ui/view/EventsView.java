package com.wolfhack.service.admin.ui.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.Arrays;
import java.util.stream.Collectors;

@UIScope
@Route("events")
public class EventsView extends VerticalLayout {

	private final TextArea eventsLog = new TextArea("Events Log");

	private static final int MAX_LINES = 100;

	public EventsView(EventBroadcast eventBroadcast) {
		eventsLog.setWidthFull();
		eventsLog.setHeight("500px");
		eventsLog.setReadOnly(true);
		add(eventsLog);

		eventBroadcast.register(
			event -> getUI()
				.ifPresent(
					ui -> ui.access(() -> {
							updateEventsDisplay(event);
							scrollToBottom();
						}
					)
				)
		);
	}

	private void updateEventsDisplay(String event) {
		String currentValue = eventsLog.getValue();
		String[] lines = currentValue.split("\n");

		if (lines.length >= MAX_LINES) {
			String newValue = Arrays.stream(
					lines,
					lines.length - MAX_LINES + 1,
					lines.length
				)
				.collect(Collectors.joining("\n"));

			eventsLog.setValue(newValue + "\n" + event);
		} else {
			eventsLog.setValue(currentValue + "\n" + event);
		}
	}

	private void scrollToBottom() {
		UI.getCurrent().getPage().executeJs(
			"var textarea = document.querySelector('textarea'); " +
				"textarea.scrollTop = textarea.scrollHeight;"
		);
	}

}
