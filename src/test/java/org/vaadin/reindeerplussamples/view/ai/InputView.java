package org.vaadin.reindeerplussamples.view.ai;

import org.vaadin.reindeerplussamples.view.MainLayout;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@PageTitle("AI Chat")
@Route(value = "input", layout = MainLayout.class)
public class InputView extends Main {

    public InputView() {
        addClassNames(Padding.LARGE);
        add(new AIInput());
    }

}
