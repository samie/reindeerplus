package org.vaadin.reindeerplussamples.view.component;

import org.vaadin.reindeerplus.component.EmptyState;
import org.vaadin.reindeerplus.component.MaterialSymbol;
import org.vaadin.reindeerplussamples.view.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Empty states")
@Route(value = "empty-states", layout = MainLayout.class)
public class EmptyStatesView extends ComponentView {

    public EmptyStatesView() {
        EmptyState emptyState = new EmptyState("Empty in events", "Create an event and it will show up here", "New event");
        emptyState.setIcon(MaterialSymbol.CALENDAR_ADD_ON.create());
        add(emptyState);
    }

}
