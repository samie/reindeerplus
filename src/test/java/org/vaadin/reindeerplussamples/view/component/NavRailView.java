package org.vaadin.reindeerplussamples.view.component;

import org.vaadin.reindeerplus.component.MaterialSymbol;
import org.vaadin.reindeerplus.component.nav.NavRail;
import org.vaadin.reindeerplus.component.nav.NavRailItem;
import org.vaadin.reindeerplussamples.view.MainLayout;
import org.vaadin.reindeerplussamples.view.template.ProfileView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Navigation rail")
@Route(value = "nav-rail", layout = MainLayout.class)
public class NavRailView extends ComponentView {

    public NavRailView() {
        addH2("Show sub-items on focus");
        add(createRailNav(true));

        addH2("Don't show sub-items on focus");
        add(createRailNav(false));
    }

    private NavRail createRailNav(boolean showSubItemsOnFocus) {
        NavRail nav = new NavRail();
        nav.setShowSubItemsOnFocus(showSubItemsOnFocus);

        NavRailItem home = new NavRailItem("Home", AppBarsView.class, MaterialSymbol.HOME.create());

        NavRailItem messages = new NavRailItem("Messages", BreadcrumbsView.class, MaterialSymbol.MESSAGE.create());
        messages.addItem(new NavRailItem("Inbox", CheckboxesView.class, MaterialSymbol.INBOX.create()));
        messages.addItem(new NavRailItem("Sent", DialogsView.class, MaterialSymbol.SEND.create()));
        messages.addItem(new NavRailItem("Trash", EmptyStatesView.class, MaterialSymbol.DELETE.create()));

        NavRailItem settings = new NavRailItem("Settings", ProfileView.class, MaterialSymbol.SETTINGS.create());
        settings.addItem(new NavRailItem("Users", GridsView.class, MaterialSymbol.PEOPLE.create()));
        settings.addItem(new NavRailItem("Permissions", HeadersView.class, MaterialSymbol.ADMIN_PANEL_SETTINGS.create()));

        nav.addItem(home, messages, settings);
        return nav;
    }

}
