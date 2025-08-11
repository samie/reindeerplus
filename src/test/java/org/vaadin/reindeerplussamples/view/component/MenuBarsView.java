package org.vaadin.reindeerplussamples.view.component;

import org.vaadin.reindeerplus.theme.MenuBarTheme;
import org.vaadin.reindeerplussamples.view.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Menu bars")
@Route(value = "menu-bars", layout = MainLayout.class)
public class MenuBarsView extends ComponentView {

    public MenuBarsView() {
        addH2("Theme: rounded", "Not applicable to the Material theme; all menu items are rounded.");
        add(createMenuBar(MenuBarTheme.ROUNDED));

        addH2("Theme: rounded & gap (small)");
        add(createMenuBar(MenuBarTheme.ROUNDED, MenuBarTheme.GAP_SMALL));

        addH2("Theme: rounded & gap (medium)");
        add(createMenuBar(MenuBarTheme.ROUNDED, MenuBarTheme.GAP_MEDIUM));
    }

    private Component createMenuBar(String... themeNames) {
        MenuBar menuBar = new MenuBar();
        menuBar.addThemeNames(themeNames);

        menuBar.addItem("Menu item 1");
        menuBar.addItem("Menu item 2");
        menuBar.addItem("Menu item 3");

        return menuBar;
    }

}
