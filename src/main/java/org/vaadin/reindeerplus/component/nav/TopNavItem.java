package org.vaadin.reindeerplus.component.nav;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.dom.Element;

public class TopNavItem extends SideNavItem {

    public TopNavItem(String label) {
        super(label);
        initMouseEventListeners();
    }

    public TopNavItem(String label, String path) {
        this(label);
        setPath(path);
    }

    public TopNavItem(String label, Class<? extends Component> view) {
        this(label);
        setPath(view);
    }

    public TopNavItem(String label, String path, Component prefixComponent) {
        this(label);
        setPath(path);
        setPrefixComponent(prefixComponent);
    }

    public TopNavItem(String label, Class<? extends Component> view,
                      Component prefixComponent) {
        super(label, view, prefixComponent);
        initMouseEventListeners();
    }

    private void initMouseEventListeners() {
        Element element = getElement();
        element.addEventListener("mouseover", e -> setExpanded(true));
        element.addEventListener("mouseout", e -> setExpanded(false));
    }

}
