package org.vaadin.reindeerplus.component.nav;

import org.vaadin.reindeerplus.theme.SideNavTheme;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.sidenav.SideNav;

public class TopNav extends SideNav implements HasTheme {

    public TopNav() {
        addThemeName(SideNavTheme.TOP);
    }

    public TopNav(String label) {
        super(label);
        addThemeName(SideNavTheme.TOP);
    }

}
