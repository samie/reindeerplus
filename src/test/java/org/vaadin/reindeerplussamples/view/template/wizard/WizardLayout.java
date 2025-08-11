package org.vaadin.reindeerplussamples.view.template.wizard;

import org.vaadin.reindeerplus.component.MaterialSymbol;
import org.vaadin.reindeerplus.component.nav.Step;
import org.vaadin.reindeerplus.component.nav.Stepper;
import org.vaadin.reindeerplussamples.view.HomeView;
import org.vaadin.reindeerplussamples.view.MainLayout;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoUtility.*;


@PageTitle("Wizard")
@ParentLayout(MainLayout.class)
@RoutePrefix(value = "wizard")
public class WizardLayout extends Main implements RouterLayout, AfterNavigationObserver {

    private Stepper stepper;
    private Div content;
    private Div footer;
    private RouterLink previous;
    private RouterLink next;

    public WizardLayout() {
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);
        add(createStepper(), createContent(), createFooter());
    }

    private Stepper createStepper() {
        Step step1 = new Step("Step 1", Step1View.class);
        Step step2 = new Step("Step 2", Step2View.class);
        Step step3 = new Step("Step 3", Step3View.class);
        Step step4 = new Step("Step 4", Step4View.class);

        this.stepper = new Stepper(step1, step2, step3, step4);
        this.stepper.addClassNames(BoxSizing.BORDER, MaxWidth.SCREEN_SMALL, Padding.MEDIUM);
        this.stepper.setOrientation(Stepper.Orientation.HORIZONTAL);
        this.stepper.setSmall(true);
        return this.stepper;
    }

    private Div createContent() {
        this.content = new Div();
        this.content.addClassNames(Flex.ONE, Overflow.AUTO);
        return this.content;
    }

    private Div createFooter() {
        this.previous = new RouterLink("Previous", Step1View.class);
        this.previous.addComponentAsFirst(MaterialSymbol.NAVIGATE_BEFORE.create(IconSize.SMALL));
        this.previous.addClassNames(AlignItems.CENTER, Display.FLEX, Gap.SMALL);

        this.next = new RouterLink("Next", Step2View.class);
        this.next.add(MaterialSymbol.NAVIGATE_NEXT.create(IconSize.SMALL));
        this.next.addClassNames(AlignItems.CENTER, Display.FLEX, Gap.SMALL);

        this.footer = new Div(this.previous, this.next);
        this.footer.addClassNames(Display.FLEX, Gap.XLARGE, Padding.Horizontal.LARGE, Padding.Vertical.MEDIUM);
        return this.footer;
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        if (content != null) {
            this.content.removeAll();
            this.content.getElement().appendChild(content.getElement());
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        if (event.getLocation().getPath().contains("wizard/1")) {
            this.previous.getElement().removeAttribute("href");
            this.next.setRoute(Step2View.class);

        } else if (event.getLocation().getPath().contains("wizard/2")) {
            this.previous.setRoute(Step1View.class);
            this.next.setRoute(Step3View.class);

        } else if (event.getLocation().getPath().contains("wizard/3")) {
            this.previous.setRoute(Step2View.class);
            this.next.setRoute(Step4View.class);

        } else if (event.getLocation().getPath().contains("wizard/4")) {
            this.previous.setRoute(Step3View.class);
            this.next.setRoute(HomeView.class);
        }
    }
}
