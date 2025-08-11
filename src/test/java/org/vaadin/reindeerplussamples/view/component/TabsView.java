package org.vaadin.reindeerplussamples.view.component;

import org.vaadin.reindeerplus.theme.TabTheme;
import org.vaadin.reindeerplussamples.view.MainLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Tabs")
@Route(value = "tabs", layout = MainLayout.class)
public class TabsView extends ComponentView {

    public TabsView() {
        addH2("Theme: segmented");

        Tabs tabs = new Tabs(new Tab("Tab 1"), new Tab("Tab 2"), new Tab("Tab 3"));
        tabs.addThemeName(TabTheme.SEGMENTED);
        add(tabs);
    }

}
