package org.vaadin.reindeerplussamples.view.component;

import org.vaadin.reindeerplus.component.nav.Breadcrumb;
import org.vaadin.reindeerplus.component.nav.BreadcrumbItem;
import org.vaadin.reindeerplussamples.view.HomeView;
import org.vaadin.reindeerplussamples.view.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@PageTitle("Breadcrumbs")
@Route(value = "breadcrumbs", layout = MainLayout.class)
public class BreadcrumbsView extends ComponentView {

    public BreadcrumbsView() {
        addClassNames(Padding.Top.LARGE);

        add(new Breadcrumb(
                new BreadcrumbItem("Home", HomeView.class),
                new BreadcrumbItem("Breadcrumbs", BreadcrumbsView.class)
        ));
    }

}
