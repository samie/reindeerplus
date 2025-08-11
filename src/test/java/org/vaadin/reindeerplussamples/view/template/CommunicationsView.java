package org.vaadin.reindeerplussamples.view.template;

import org.vaadin.reindeerplussamples.view.MainLayout;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Communications")
@Route(value = "communications", layout = MainLayout.class)
public class CommunicationsView extends Main {

    public CommunicationsView() {
        // Sidebar
        // - Title
        // - Search
        // - List
        // Content
        // - Header
        // - Different types of messages
    }

}
